package com.twentysix20.practice.euler.euler001;

public class ThreesFives {

	public static void main(String[] args) {
		int total = 0;
		for (int i = 1; i < 1000; i++)
			if (i % 3 == 0 || i % 5 == 0) {
				System.out.println(i);
				total += i;
			}
		System.out.println("===\n"+total);
	}
}
