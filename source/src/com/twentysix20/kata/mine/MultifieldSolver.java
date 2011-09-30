package com.twentysix20.kata.mine;

import java.util.ArrayList;

public class MultifieldSolver {
	private FieldSolver solverStrategy;
	public MultifieldSolver(FieldSolver solver) {
		solverStrategy = solver;
	}
	public String[] solve(String... input) {
		int index = 0;
		ArrayList<String[]> list = new ArrayList<String[]>();

		while (true) {
			int rows = getRows(input[index++]);
			if (rows == 0) break;
			String[] field = new String[rows];
			for (int j = 0; j < rows; j++)
				field[j] = input[index++];
			String[] result = solverStrategy.solve(field);
			list.add(result);
		}
		return output(list);
	}

	private String[] output(ArrayList<String[]> list) {
		ArrayList<String> strList = new ArrayList<String>();
		int count = 0;
		for (String[] result : list) {
			count++;
			if (count > 1)
				strList.add("");
			strList.add("Field #"+count+":");
			for (String line : result)
				strList.add(line);
		}
		return strList.toArray(new String[]{});
	}

	private int getRows(String line) {
		String[] nums = line.split(" ");
		return Integer.parseInt(nums[0]);
	}
}
