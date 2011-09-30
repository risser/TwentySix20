package com.twentysix20.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class LogUtil {
	static public String stackTrace(Throwable t) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		t.printStackTrace(printWriter);
		return stringWriter.toString();
	}

	static public String printException(Throwable t) {
		return stackTrace(t);
//		return t + "; " + stackTrace(t);
	}

	static public LogTimer startTimer() {
		return new LogUtil().new LogTimer(System.currentTimeMillis());
	}

	static public String logTimerDuration(LogTimer timer) {
		return logTimerDuration(timer, false);
	}

	static public String logTimerDuration(LogTimer timer, boolean inSeconds) {
		long time = System.currentTimeMillis() - timer.getStartTime();
		if (inSeconds)
			return Double.toString(time / 1000.0)+"s"; // 4.059s; 0.059s
		else
			return Long.toString(time)+"ms"; // 4059ms; 59ms
	}

	public class LogTimer {
		private long startTime;

		public LogTimer(long startTime) {
			this.startTime = startTime;
		}

		public long getStartTime() {
			return startTime;
		}
	}
}
