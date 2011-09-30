package com.twentysix20.kata.list;

import static org.junit.Assert.*;

import org.junit.Test;


public class KataListTest {

	@Test public void EmptyListNotFound() {
		KataList list = newList();
		assertNull(list.find("Fred"));
	}

	@Test public void oneNodeFind() {
		KataList list = newList();
		list.add(newNode("Fred"));

		KataNode fred = list.find("Fred");
		assertNotNull(fred);
		assertEquals("Fred", fred.value());
	}

	@Test public void oneNodeNotFound() {
		KataList list = newList();
		list.add(newNode("Fred"));

		assertNull(list.find("Wilma"));
	}

	@Test public void twoNodeFind() {
		KataList list = newList();
		list.add(newNode("Fred"));
		list.add(newNode("Wilma"));

		KataNode wilma = list.find("Wilma");
		assertNotNull(wilma);
		assertEquals("Wilma", wilma.value());

		KataNode fred = list.find("Fred");
		assertNotNull(fred);
		assertEquals("Fred", fred.value());
	}

	@Test public void twoNodeNotFound() {
		KataList list = newList();
		list.add(newNode("Fred"));
		list.add(newNode("Wilma"));

		assertNull(list.find("Barney"));
	}

	@Test public void twoNodeArray() {
		KataList list = newList();
		list.add(newNode("Fred"));
		list.add(newNode("Wilma"));

		String[] array = list.values();
		assertEquals(2, array.length);
		assertEquals("Fred",array[0]);
		assertEquals("Wilma",array[1]);
	}

	@Test public void OneNodeArray() {
		KataList list = newList();
		list.add(newNode("Fred"));

		String[] array = list.values();
		assertEquals(1, array.length);
		assertEquals("Fred",array[0]);
	}

	@Test public void ZeroNodeArray() {
		KataList list = newList();

		String[] array = list.values();
		assertEquals(0, array.length);
	}

	@Test public void deleteMid() {
		KataList list = newList();
		list.add(newNode("Fred"));
		list.add(newNode("Wilma"));
		KataNode barney = newNode("Barney");
		list.add(barney);
		list.add(newNode("Betty"));
		assertEquals(4, list.values().length);

		list.delete(barney);

		String[] array = list.values();
		assertEquals(3, array.length);
		assertEquals("Fred",array[0]);
		assertEquals("Wilma",array[1]);
		assertEquals("Betty",array[2]);
	}

	@Test public void deleteFail() {
		KataList list = newList();
		list.add(newNode("Fred"));
		list.add(newNode("Wilma"));
		list.add(newNode("Barney"));
		list.add(newNode("Betty"));
		assertEquals(4, list.values().length);

		list.delete(newNode("Dino"));

		String[] array = list.values();
		assertEquals(4, array.length);
	}

	@Test public void deleteFirst() {
		KataList list = newList();
		KataNode barney = newNode("Barney");
		list.add(barney);
		list.add(newNode("Fred"));
		list.add(newNode("Wilma"));
		list.add(newNode("Betty"));
		assertEquals(4, list.values().length);

		list.delete(barney);

		String[] array = list.values();
		assertEquals(3, array.length);
		assertEquals("Fred",array[0]);
		assertEquals("Wilma",array[1]);
		assertEquals("Betty",array[2]);
	}

	@Test public void deleteLast() {
		KataList list = newList();
		list.add(newNode("Fred"));
		list.add(newNode("Wilma"));
		list.add(newNode("Betty"));
		KataNode barney = newNode("Barney");
		list.add(barney);
		assertEquals(4, list.values().length);

		list.delete(barney);

		String[] array = list.values();
		assertEquals(3, array.length);
		assertEquals("Fred",array[0]);
		assertEquals("Wilma",array[1]);
		assertEquals("Betty",array[2]);
	}

	@Test public void deleteSingle() {
		KataList list = newList();
		KataNode barney = newNode("Barney");
		list.add(barney);
		assertEquals(1, list.values().length);

		list.delete(barney);

		String[] array = list.values();
		assertEquals(0, array.length);
	}

	@Test public void deleteWithEmpty() {
		KataList list = newList();

		list.delete(newNode("Barney"));

		String[] array = list.values();
		assertEquals(0, array.length);
	}

	@Test public void deleteThenAdd() {
		KataList list = newList();
		list.add(newNode("Fred"));
		list.add(newNode("Wilma"));
		KataNode barney = newNode("Barney");
		list.add(barney);
		list.add(newNode("Betty"));
		assertEquals(4, list.values().length);

		list.delete(barney);
		list.add(newNode("Dino"));

		String[] array = list.values();
		assertEquals(4, array.length);
		assertEquals("Fred",array[0]);
		assertEquals("Wilma",array[1]);
		assertEquals("Betty",array[2]);
		assertEquals("Dino",array[3]);
	}

	@Test public void deleteThenAddLast() {
		KataList list = newList();
		list.add(newNode("Fred"));
		list.add(newNode("Wilma"));
		list.add(newNode("Betty"));
		KataNode barney = newNode("Barney");
		list.add(barney);
		assertEquals(4, list.values().length);

		list.delete(barney);
		list.add(newNode("Dino"));

		String[] array = list.values();
		assertEquals(4, array.length);
		assertEquals("Fred",array[0]);
		assertEquals("Wilma",array[1]);
		assertEquals("Betty",array[2]);
		assertEquals("Dino",array[3]);
	}

	

	public KataNode newNode(String value) {
		return new SingleKataNode(value);
	}
	public KataList newList() {
		return new SingleKataList();
	}
}
