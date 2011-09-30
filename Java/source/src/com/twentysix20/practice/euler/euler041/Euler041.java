package com.twentysix20.practice.euler.euler041;

public class Euler041 {

	public static void main(String[] args) {
		for (int num = 531032769; num > 0; num--) {
			if (num % 2 == 0) continue;
			if (!PanDigit.isPanDigit(num)) continue;
System.out.println(num);			
			if (Prime.isPrime(num)) {
				System.out.println("IS PRIME!");
				break;
			}
		}
	}
}
