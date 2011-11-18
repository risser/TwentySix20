package com.twentysix20.dlc.rawxml;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.twentysix20.dlc.DLCCollection;
import com.twentysix20.dlc.model.Artist;
import com.twentysix20.util.FileUtil;

public class DlcXmlReader {
	private String filename;
	private XStream xstream;

	public DlcXmlReader(String filename) {
		super();
		this.filename = filename;
        createXStream();
	}

	private void createXStream() {
		xstream = new XStream();
        xstream.alias("song", RawSong.class);
        xstream.alias("xml", RawSongContainer.class);
        xstream.addImplicitCollection(RawSongContainer.class, "songs");
	}

	public RawSongContainer readRawSongContainer() throws FileNotFoundException, IOException {
		String xml = FileUtil.scanFile(filename);
		return (RawSongContainer)xstream.fromXML(xml);
	}
}
