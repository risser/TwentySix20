package com.twentysix20.practice.euler.euler041;

public class PanDigit {

	public static boolean isPanDigit(int num) {
		int places = (int)Math.round(Math.floor(Math.log10(num)+0.00000001))+1;

		boolean[] exists = new boolean[places+1];
		exists[0] = true;
		while (num > 0) {
			int digit = num % 10;
			if (digit > places) return false;
			if (exists[digit]) return false;
			exists[digit] = true;
			num /= 10;
		}
		return true;
	}

}
