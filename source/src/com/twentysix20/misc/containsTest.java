package com.twentysix20.misc;

import java.util.ArrayList;

public class containsTest {
	private String myString = null;
	public containsTest (String s)
	{
		myString = s;
	}

	public boolean equals(Object o)
	{
System.out.println("  In Equals: " + myString + " = " + o);
		if (o instanceof containsTest)
		{
			containsTest ct = (containsTest)o;
			return myString.equals(ct.getString());
		}
		if (o instanceof String)
			return myString.equals(o);
		return (this == o);
	}

	public String getString()
	{
		return myString;
	}

	public String toString()
	{
		return myString;
	}

	public void setString(String s)
	{
		myString = s;
	}


	static public void main (String[] args) throws Exception {
		containsTest a = new containsTest("Test1");
		containsTest b = new containsTest("Test2");
		int i = 0;
		containsTest c = new containsTest("Test" + ++i);

		System.out.println("a.b: " + a.equals(b));
		System.out.println("a.c: " + a.equals(c));
		System.out.println("a==b: " + (a == b));
		System.out.println("a==c: " + (a == c));

		ArrayList list = new ArrayList();
		list.add(a);
		list.add(b);
		System.out.println("\n");
		System.out.println("A in List: " + list.contains(a));
		System.out.println("B in List: " + list.contains(b));
		System.out.println("C in List: " + list.contains(c));
		System.out.println("indexOf A: " + list.indexOf(a));
		System.out.println("indexOf B: " + list.indexOf(b));
		System.out.println("indexOf C: " + list.indexOf(c));
		b.setString("Test1");
		System.out.println("\n");
		System.out.println("B+ in List: " + list.contains(b));
		System.out.println("indexOf B+: " + list.indexOf(b));
	}
}