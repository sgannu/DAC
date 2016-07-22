package com.gannu.rr.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Stack;

/**
 * 
 * @author gannu. An Iterator for the available paths between START and END
 *         nodes
 *
 * @param <T>
 */
public class GraphPathIterator<T extends INode> implements Iterator<INode[]> {

	private Stack<INode> stack;
	private List<INode[]> paths;
	private Set<INode> onPath = new HashSet<INode>(); // the set of vertices on
														// the path

	int cursor; // index of next path to return
	int lastRet = -1; // index of last path returned; -1 if no such

	public GraphPathIterator(DGraph<T> graph, INode start, INode end) {

		paths = new ArrayList<INode[]>();
		stack = new Stack<INode>();
		enumerate(graph, start, end, true);
	}

	// use DFS
	private void enumerate(DGraph<T> graph, INode start, INode end, boolean first) {

		stack.push(start);
		onPath.add(start);

		if (start.isSameAs(end) && !first) {
			INode[] l = new INode[stack.size()];
			for (int i = l.length - 1; i >= 0; i--) {
				l[i] = stack.elementAt(i);
			}
			paths.add(l);
		}

		// All neighbors that would continue path with repeating a node
		else {
			List<INode> successors = graph.getNode(start).getSuccessors();
			for (INode n : successors) {
				if (!onPath.contains(n))
					enumerate(graph, n, end, false);
			}
		}

		// done with node start
		stack.pop();
		onPath.remove(start);
	}

	@Override
	public boolean hasNext() {
		return cursor != paths.size();
	}

	@SuppressWarnings("unchecked")
	public INode[] next() {
		int i = cursor;
		if (i >= paths.size())
			throw new NoSuchElementException();
		cursor = i + 1;
		return paths.get(lastRet = i);
	}
}
