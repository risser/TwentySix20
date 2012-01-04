package com.twentysix20.dlc.mapping;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.twentysix20.util.FileUtil;
import com.twentysix20.util.StringUtil;

public class IDMappingReader {
	private String filename;

	public IDMappingReader(String filename) {
		this.filename = filename;
	}

	public IDMapping createMapping() throws FileNotFoundException, IOException {
		IDMapping mapping = new IDMapping();
		String[] mappingStrings = FileUtil.scanFile(filename).split("\n");
		for (String mappingString : mappingStrings) {
			if (StringUtil.isEmpty(mappingString)) continue;
			String[] values = mappingString.trim().split("\\s*->\\s*");
			int id = Integer.parseInt(values[0]);
			mapping.addMapping(id,values[1]);
		}

		return mapping;
	}
}