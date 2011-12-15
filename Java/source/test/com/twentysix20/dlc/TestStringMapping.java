package com.twentysix20.dlc;

import static org.junit.Assert.*;

import org.junit.Test;

import com.twentysix20.dlc.mapping.StringMapping;


public class TestStringMapping {

	@Test public void basicStringMapping() {
		StringMapping mapping = new StringMapping("Old", "New");
		assertEquals("New", mapping.map("Old"));
		assertEquals("New", mapping.map("New"));
		assertEquals("Olde", mapping.map("Olde"));
		assertEquals("OlD", mapping.map("OlD"));
		assertEquals("Newe", mapping.map("Newe"));
	}

	@Test public void multipleStringMapping() {
		StringMapping mapping = new StringMapping("Old", "New");
		mapping.addMapping("Another", "New");
		mapping.addMapping("Third", "A Third");
		assertEquals("New", mapping.map("Old"));
		assertEquals("Olde", mapping.map("Olde"));
		assertEquals("New", mapping.map("Another"));
		assertEquals("A Third", mapping.map("Third"));
	}
}
