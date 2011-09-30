package com.twentysix20.media.tvboxer;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import com.coremedia.iso.IsoFile;
import com.coremedia.iso.IsoOutputStream;
import com.twentysix20.util.FileUtil;

public class MP4FileWriter {
	private IsoFile isoFile;
	
	public MP4FileWriter(IsoFile isoFile) {
		this.isoFile = isoFile;
	}

	public void writeToFile(String name) throws IOException {
		System.out.print("Writing "+name+"...");
		createNewDirectoryIfNecessary(name);

		long start = new Date().getTime();
	    FileOutputStream fos = null;
	    BufferedOutputStream bos = null;
	    IsoOutputStream ios = null;
		try {
			fos = new FileOutputStream(new File(name));
		    bos = new BufferedOutputStream(fos, 1024 * 1024 * 12);
		    ios = new IsoOutputStream(bos);
		    isoFile.getBox(ios);
		    long end = new Date().getTime();
		    System.out.println(" Complete: "+((end-start)/1000)+" sec.");
		} finally {
			if (ios != null) {
				ios.flush();
				ios.close();
			}

			if (bos != null)
				bos.close();
			if (fos != null)
				fos.close();
		}
	}

	private void createNewDirectoryIfNecessary(String filename) {
		String directoryName = filename.substring(0,filename.lastIndexOf('\\'));
		File directory = new File(directoryName);
		if (!directory.exists()) {
			directory.mkdirs();
			System.out.println("\n . . Created directory: "+directoryName);
		}
	}

	public void close() {
		isoFile = null;
	}
}
