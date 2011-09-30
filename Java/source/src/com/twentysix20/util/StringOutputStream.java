package com.twentysix20.util;

import java.io.OutputStream;

public class StringOutputStream extends OutputStream{

	protected StringBuffer buf = new StringBuffer();

	public StringOutputStream() {}

	public void close() {}

	public void flush() {
		buf.delete(0, buf.length());
	}

	public void write(byte[] b) {
		String str = new String(b);
		this.buf.append(str);
	}
	
	public void write(byte[] b, int off, int len) {
		String str = new String(b, off, len);
		this.buf.append(str);
	}

	public void write(int b) {
		String str = Integer.toString(b);
		this.buf.append(str);
	}

	public String toString() {
		return buf.toString();
	}

	public String flushToString() {
		String s = buf.toString();
		flush();
		return s;
	}
}