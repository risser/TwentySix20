package com.twentysix20.practice.euler.euler097;

public class Euler097 {

	public static void main(String[] args) {
		long big = 1L;
		for (int i = 0; i < 7830457; i++) {
			big *= 2;
			big = big % 10000000000L; 
		}
		big = big * 28433 + 1;
		big = big % 10000000000L; 
		System.out.println(big);
	}
}
