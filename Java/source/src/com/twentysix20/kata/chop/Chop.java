package com.twentysix20.kata.chop;

public class Chop {

	public static int linearChop(int search, int... array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == search) return i; 
		}
		return -1;
	}

	public static int recursiveChop(int search, int[] array) {
		return recursiveChop(search, 0, array.length, array);
	}

	private static int recursiveChop(int search, int first, int last, int[] array) {
System.out.println(search+" : " + first + " - " + last + " : " + array);
		if (last <= first) return -1;
		if (array[first] == search) return first;
		if (last == first + 1) return -1;
		int mid = first + (last - first) / 2;
		int midval = array[mid];
		return (search < midval ? recursiveChop(search, first, mid, array) : recursiveChop(search, mid, last, array));
	}
}
