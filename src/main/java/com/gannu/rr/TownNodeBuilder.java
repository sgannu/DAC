package com.gannu.rr;

import com.gannu.rr.graph.IAbstractNodeFactory;
import com.gannu.rr.graph.INode;

public class TownNodeBuilder implements IAbstractNodeFactory {

	@Override
	public INode getInstance(String name) {
		return new TownNode(name);
	}

}
