package com.twentysix20.kata.supermarket;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ItemCollection implements Iterable<Item> {
	private Set<Item> items = new HashSet<Item>();

	public void addItem(Item item) {
		items.add(item);
	}

	public void addItem(String name) {
		items.add(new Item(name));
	}

	public void addItems(int count, String name) {
		for (int i = 0; i < count; i++)
			addItem(new Item(name));
	}

	@Override
	public Iterator<Item> iterator() {
		return items.iterator();
	}
}