package com.twentysix20.media.tvboxer;

import java.io.File;
import java.io.FileFilter;

public class TVFileFilter implements FileFilter {

	@Override
	public boolean accept(File file) {
		String name = file.getName();
		return (name.matches(".+[0-9][0-9]\\.[0-9][0-9].+\\.mp4"));
	}
}