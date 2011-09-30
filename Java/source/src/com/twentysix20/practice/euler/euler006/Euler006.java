package com.twentysix20.practice.euler.euler006;

public class Euler006 {

	public static long calculate(int number) {
		long sumSquares = 0L;
		long squareSums = 0L;

		for (int i = 1; i <= number; i++) {
			sumSquares += (i*i);
			squareSums += i;
		}
		squareSums *= squareSums;
		return squareSums - sumSquares;
	}
	
}
