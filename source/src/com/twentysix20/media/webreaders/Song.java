package com.twentysix20.media.webreaders;

import java.util.ArrayList;
import java.util.List;

public class Song {
	String name;
	String url;
	boolean notFound = false;
	List<String> composers = new ArrayList<String>();

	public String toString() {
		StringBuffer buff = new StringBuffer();
		for (String name : composers)
			buff.append(name).append("/");
		buff.deleteCharAt(buff.length()-1);
		return "> " + name + "\n\t" + buff;
	}
}
