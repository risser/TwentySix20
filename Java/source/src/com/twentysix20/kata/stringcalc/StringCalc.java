package com.twentysix20.kata.stringcalc;

public class StringCalc {

	public static Object add(String paramStr) {
		if (paramStr.startsWith("//")) {
			char c = paramStr.charAt(2);
			paramStr = paramStr.substring(4).replace(c,',');
		}
		paramStr = paramStr.trim();
		if (paramStr.length() == 0) return 0;

		String[] numbers = paramStr.split("\\s*[\\,\\n]\\s*");
		StringBuffer negativeExceptionBuffer = new StringBuffer();
		int total = 0;
		for (String numberStr : numbers) {
			int number = Integer.parseInt(numberStr);
			if (number < 0) {
				if (negativeExceptionBuffer.length() > 0)
					negativeExceptionBuffer.append(",");
				negativeExceptionBuffer.append(numberStr);
			}
			total += number;
		}
		if (negativeExceptionBuffer.length() > 0)
			throw new RuntimeException("Negatives not allowed: "+negativeExceptionBuffer);
		return total;
	}

}
