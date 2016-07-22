package com.gannu.rr;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.gannu.rr.graph.DGraph;
import com.gannu.rr.graph.GraphBuilder;
import com.gannu.rr.graph.INode;
import com.gannu.rr.graph.IPathExpression;
import com.gannu.rr.graph.Route;

/**
 * 
 * @author gannu: Can be enhanced to include caching mechanism to cache routes
 *         that are already evaluated.
 */
public class RailRoadSystem {
	private GraphBuilder rrGraph;
	private Logger log = Logger.getLogger(this.getClass());

	public void build(String fileName) throws IOException {

		String paths = readFile(fileName);
		rrGraph = new GraphBuilder(new DGraph<TownNode>(), new TownNodeBuilder());
		rrGraph.build(paths);
	}

	public GraphBuilder getRrGraph() {
		return rrGraph;
	}

	/*
	 * Get all paths between two stations without loops
	 */
	public List<Route> getAllPaths(String start, String end) {
		Iterator<INode[]> itr = rrGraph.getPathIterator(start, end);
		List<Route> res = new ArrayList<Route>();

		if (itr.hasNext()) {

			do {
				Route route = new Route();
				INode[] path = itr.next();
				for (INode n : path) {
					route.add(n);
				}
				res.add(route);
			} while (itr.hasNext());
		} else {
			log.error(start + "->" + end + ":" + Route.NO_SUCH_ROUTE);
		}

		return res;
	}

	/*
	 * Evaluate all paths by revisiting multiple loops
	 */
	public List<Route> getAllPaths(String start, String end, IPathExpression ex) {
		List<Route> directPaths = getAllPaths(start, end);
		List<Route> loops = getAllPaths(end, end);
		List<Route> res = new ArrayList<Route>();

		directPaths.forEach(p -> {
			// log.info("getAllPaths iteration:" + p);
			if (ex.test(p)) {
				res.add(p);
			}
		});

		if (directPaths.size() > 0) {
			res.addAll(addPaths(directPaths, loops, ex));
		} else {
			log.error(start + "->" + end + ":" + Route.NO_SUCH_ROUTE);
		}

		return res;
	}

	/*
	 * find shortest path between two stations
	 */
	public Route shortestPath(String start, String end) {
		Iterator<INode[]> itr = rrGraph.getPathIterator(start, end);
		Route res = new Route();
		INode[] shortPath = null;
		int shortW = 0;

		if (itr.hasNext()) {
			do {
				INode[] path = itr.next();
				int w = 0;

				for (INode n : path) {
					w += n.getWeight();
				}

				if (shortW == 0 || shortW > w) {
					shortW = w;
					shortPath = path;
				}
			} while (itr.hasNext());

			for (INode n : shortPath) {
				res.add(n);
			}
		}

		log.info("Shortest between " + start + " & " + end + ": " + res);
		return res;
	}

	/*
	 * get route based on specific path: eg: A-B-C
	 */
	public Route getRoute(String routeString) {
		Route route = rrGraph.getRoute(routeString.split("-|,"));

		log.info("Distance of " + route);
		return route;
	}

	// Add loops
	private List<Route> addPaths(List<Route> orig, List<Route> loops, IPathExpression ex) {
		List<Route> res = new ArrayList<Route>();

		orig.forEach(p -> {
			loops.forEach(a -> {
				Route n = p.clone();
				n.add(a);
				// log.info("addPaths iteration:" + n);
				if (ex.test(n)) {
					res.add(n);
				}
			});
		});
		if (res.size() > 0)
			res.addAll(addPaths(res, loops, ex));
		return res;
	}

	private String readFile(String path) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded);
	}

}
