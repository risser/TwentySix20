package com.twentysix20.dlc.mapping;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.twentysix20.util.FileUtil;
import com.twentysix20.util.StringUtil;

public class StringMappingReader {
	private String filename;

	public StringMappingReader(String filename) {
		this.filename = filename;
	}

	public StringMapping createMapping() throws FileNotFoundException, IOException {
		StringMapping mapping = new StringMapping();
		String[] mappingStrings = FileUtil.scanFile(filename).split("\n");
		for (String mappingString : mappingStrings) {
			if (StringUtil.isEmpty(mappingString)) continue;
			String[] names = mappingString.trim().split("\\s*->\\s*");
			mapping.addMapping(names[0],names[1]);
		}

		return mapping;
	}
}