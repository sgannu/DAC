package com.gannu.rr.graph;

import java.util.ArrayList;

/**
 *
 * @author gannu Graph representation of railroad connections
 */
public class DGraph<T extends INode> implements IGraph {
	ArrayList<T> nodes;

	public DGraph() {
		nodes = new ArrayList<T>();
	}

	@Override
	public void add(INode start, INode end) {
		if (nodes.contains(start)) {
			for (T node : nodes) {
				if (node.isSameAs(start)) {
					node.addSuccessor(end);
					break;
				}
			}
		} else {
			start.addSuccessor(end);
			nodes.add((T) start);
		}
	}

	public INode getNode(INode node) {
		INode found = null;
		for (T n : nodes) {
			if (n.isSameAs(node)) {
				found = n;
				break;
			}
		}
		assert found != null;
		return found;
	}

}
