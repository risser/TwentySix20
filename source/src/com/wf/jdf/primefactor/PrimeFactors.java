package com.wf.jdf.primefactor;

import java.util.ArrayList;
import java.util.List;

public class PrimeFactors {

	public static Object generate(int i) {
		List list = new ArrayList();

		int factor = 1;
		while (++factor <= java.lang.Math.sqrt(i)) {
			while (i % factor == 0) {
				list.add(factor);
				i /= factor;
			}
		}
		if (i > 1)
			list.add(i);
		return list;
	}

}
