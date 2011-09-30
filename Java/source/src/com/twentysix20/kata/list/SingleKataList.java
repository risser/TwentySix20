package com.twentysix20.kata.list;

public class SingleKataList implements KataList {
	private SingleKataNode root = new SingleKataNode(null);
	private int nodeCount = 0;
	private SingleKataNode last = root;

	@Override 
	public void add(KataNode newNode) {
		SingleKataNode castNode = (SingleKataNode)newNode;
		castNode.setNext(null);
		last.setNext(castNode);
		last = castNode;
		nodeCount++;
	}

	@Override
	public KataNode find(String value) {
		if (value == null) return null;
		SingleKataNode node = root;
		while (node.hasNext()) {
			node = node.next();
			if (node.value() == value)
				return node;
		}
		return null;
	}

	@Override
	public void delete(KataNode node) {
		if (node == null) return;

		SingleKataNode aNode = root;
		while (aNode.hasNext()) {
			if (node == aNode.next()) {
				aNode.setNext(aNode.next().next());
				if (node == last)
					last = aNode;
				nodeCount--;
				return;
			}
			aNode = aNode.next();
		}
	}

	@Override
	public String[] values() {		
		String[] array = new String[nodeCount];
		SingleKataNode node = root;
		int count = 0;
		while (node.hasNext()) {
			node = node.next();
			array[count] = node.value();
			count++;
		}
		
		return array;
	}
}