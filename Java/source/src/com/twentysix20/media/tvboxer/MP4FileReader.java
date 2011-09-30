package com.twentysix20.media.tvboxer;

import java.io.File;
import java.io.IOException;

import com.coremedia.iso.IsoBufferWrapper;
import com.coremedia.iso.IsoBufferWrapperImpl;
import com.coremedia.iso.IsoFile;

public class MP4FileReader {
	private File file;
	
	public MP4FileReader(File file) {
		this.file = file;
	}

	public IsoFile readAndParse() throws IOException {
        IsoBufferWrapper isoBufferWrapper = new IsoBufferWrapperImpl(file);
        IsoFile isoFile = new IsoFile(isoBufferWrapper);
        isoFile.parse();
        return isoFile;
	}
}
