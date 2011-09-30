package com.twentysix20.kata.list;

public class SingleKataNode implements KataNode {
	private String value;
	private SingleKataNode next;

	public SingleKataNode(String value) {
		this.value = value;
	}

	@Override public String value() {
		return value;
	}

	public void setNext(SingleKataNode node) {
		next = node;
	}

	public SingleKataNode next() {
		return next;
	}

	public boolean hasNext() {
		return next != null;
	}

}
