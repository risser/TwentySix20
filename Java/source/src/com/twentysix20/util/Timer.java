package com.twentysix20.util;

public class Timer {
	long startMillis = 0;
	long stopMillis = 0;
	boolean running = false;
	boolean neverStarted = true;
	String name;

	public Timer(String name) {
		this.name = name;
	}

	public Timer() {
		this("Timer");
	}

	public Timer start() {
		running = true;
		if (neverStarted)
			startMillis = System.currentTimeMillis();
		neverStarted = false;
		return this;
	}

	public Timer stop() {
		stopMillis = System.currentTimeMillis();
		running = false;
		return this;
	}

	public long calcTime() {
		if (running)
			return System.currentTimeMillis() - startMillis;
		else
			return stopMillis - startMillis;
	}

	public String toString() {
		return outputTime();
	}
	
	public String outputTime() {
		long originalTime = calcTime();
		long time = originalTime;
		StringBuffer output = new StringBuffer();
		output.append(name).append(": ");
		if (running)
			output.append("RUNNING: ");

		int ms = (int)(time % 1000);
		time /= 1000;
		if (time > 0) {
			int sec = (int)(time % 60);
			time /= 60;
			
			if (time > 0) {
				int min = (int)(time % 60);
				time /= 60;
				
				if (time > 0) {
					int hr = (int)(time % 24);
					time /= 24;
					
					if (time > 0) {
						int days = (int)(time % 365);
						time /= 365;
						
						if (time > 0)
							output.append(time).append("y, ");
						output.append(days).append("d, ");
					}
					output.append(hr).append("h, ");
				}
				output.append(min).append("m, ");
			}
			output.append(sec).append("s, ");
		}
		output.append(ms).append("ms; (").append(originalTime).append("ms)");
		return output.toString();
	}
}
