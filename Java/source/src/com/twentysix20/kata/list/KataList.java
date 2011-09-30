package com.twentysix20.kata.list;

public interface KataList {
	public void add(KataNode newNode);
	public KataNode find(String value);
	public void delete(KataNode node);
	public String[] values();
}
