package com.gannu.rr.graph;

import java.util.ArrayList;
import java.util.List;

public class Route implements Cloneable {
	private List<INode> nodes = new ArrayList<INode>();
	private int distance = 0;
	public static final String NO_SUCH_ROUTE = "NO SUCH ROUTE";

	public int getDistance() {
		return distance;
	}

	public List<INode> getNodes() {
		return nodes;
	}

	public void add(INode n) {
		nodes.add(n);
		distance += n.getWeight();
	}

	public void add(Route route) {
		List<INode> n = route.getNodes();
		for (int i = 1; i < n.size(); i++)
			nodes.add(n.get(i));

		distance += route.getDistance();
	}

	public String toString() {
		if (distance == 0)
			return NO_SUCH_ROUTE;

		StringBuilder str = new StringBuilder();
		nodes.forEach(n -> {
			str.append(n).append("->");
		});
		str.append(distance);
		return str.toString();
	}

	public Route clone() {
		Route r = new Route();
		r.distance = this.distance;
		r.nodes = new ArrayList<INode>();
		r.nodes.addAll(nodes);
		return r;
	}
}
