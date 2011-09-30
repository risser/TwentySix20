package com.twentysix20.practice.euler.euler002;

public class Euler002 {
// 0,1,1,2,3,5,8,13,21,34,55,89,144
	public static long calculate(int limit) {
		if (limit < 2)
			return 0L;

		int previousPrevious = 0;
		int previous = 1;
		int current = previous + previousPrevious;

		long evenSum = 0L;
		
		while (current <= limit) {
			if (current % 2 == 0)
				evenSum += current;
			previousPrevious = previous;
			previous = current;
			current = previous + previousPrevious;
		}
		
		return evenSum;
	}
	
}
