package com.wf.jdf.range;

public class Range {
	private int min;
	private int max;

	public Range(int min, int max) {
		this.min = min;
		this.max = max;
	}

	public boolean contains(int i) {
		return (i >= min) && (i < max);
	}

	public Range intersection(Range other) {
		int newMin = min;
		int newMax = max;
		for (; newMin < max && !other.contains(newMin); newMin++); 

		if (newMin == max)
			return null;
	
		for (; !other.contains(newMax-1); newMax--);

		return new Range(newMin, newMax);
	}

	public boolean equals(Object other) {
		if (!(other instanceof Range)) return false;

		Range otherRange = (Range)other;
		return (otherRange.min == min) && (otherRange.max == max);
	}

	public String toString() {
		return "["+min+","+max+")";
	}
}
