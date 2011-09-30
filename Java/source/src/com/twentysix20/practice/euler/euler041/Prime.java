package com.twentysix20.practice.euler.euler041;

public class Prime {

	public static boolean isPrime(int num) {
		if (num == 2) return true;
		if (num % 2 == 0) return false;
		double root = Math.sqrt(num)+1;
		for (int i = 3; i < root; i+=2)
			if (num % i == 0) return false;
		return true;
	}

}
