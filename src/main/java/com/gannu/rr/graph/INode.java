package com.gannu.rr.graph;

import java.util.List;

public interface INode {
	public void addSuccessor(INode node);

	public List<INode> getSuccessors();

	public void setWeight(int parseInt);

	public int getWeight();

	public boolean isSameAs(INode node);

}
