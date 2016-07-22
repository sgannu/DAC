package com.gannu.rr;

import java.util.ArrayList;
import java.util.List;

import com.gannu.rr.graph.INode;

public class TownNode implements INode {
	List<INode> successors;
	String name;
	int weight = 0;
	int visited = 0;

	public TownNode(String name) {
		this.name = name;
		successors = new ArrayList<INode>();
	}

	public List<INode> getSuccessors() {
		return successors;
	}

	@Override
	public void addSuccessor(INode node) {
		successors.add((TownNode) node);
	}

	@Override
	public void setWeight(int value) {
		weight = value;
	}

	@Override
	public int getWeight() {
		return weight;
	}

	public String toString() {
		return name;
	}

	@Override
	public boolean isSameAs(INode node) {
		return name.equals(((TownNode) node).getName());
	}

	@Override
	public boolean equals(Object node) {
		return isSameAs((INode) node);
	}

	public String getName() {
		return name;
	}
}
