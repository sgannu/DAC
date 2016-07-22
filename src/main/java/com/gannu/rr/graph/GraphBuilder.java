package com.gannu.rr.graph;

import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * @author gannu. Builds the DGraph and provides iterator for routes, specific
 *         route
 */
public class GraphBuilder {
	IAbstractNodeFactory nodeBuilder;
	DGraph graph;

	public GraphBuilder(DGraph graph, IAbstractNodeFactory nodeBuilder) {
		this.graph = graph;
		this.nodeBuilder = nodeBuilder;
	}

	public void build(String input) {
		StringTokenizer links = new StringTokenizer(input, ", ");

		while (links.hasMoreTokens()) {
			String link = links.nextToken().trim();
			String[] names = link.split("");

			INode start = nodeBuilder.getInstance(names[0]);
			INode end = nodeBuilder.getInstance(names[1]);
			end.setWeight(Integer.parseInt(names[2]));

			graph.add(start, end);
		}
	}

	public Iterator getPathIterator(String start, String end) {
		return new GraphPathIterator(graph, nodeBuilder.getInstance(start), nodeBuilder.getInstance(end));
	}

	public Route getRoute(String[] routeString) {
		Route route = new Route();
		boolean found = false;

		for (int i = 0; i < routeString.length - 1; ++i) {
			INode startNode = nodeBuilder.getInstance(routeString[i]);
			List<INode> successors = graph.getNode(startNode).getSuccessors();
			INode nextNode = nodeBuilder.getInstance(routeString[i + 1]);

			if (i == 0)
				route.add(startNode);
			found = false;

			for (INode node : successors) {
				if (node.isSameAs(nextNode)) {
					route.add(node);
					found = true;
					break;
				}
			}
			if (!found) {
				route = new Route();
				break;
			}
		}
		return route;
	}
}
