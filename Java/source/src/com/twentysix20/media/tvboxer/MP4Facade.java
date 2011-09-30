package com.twentysix20.media.tvboxer;

import java.io.File;
import java.io.IOException;

import com.coremedia.iso.IsoFile;
import com.coremedia.iso.IsoFileConvenienceHelper;
import com.coremedia.iso.boxes.apple.AppleItemListBox;

public class MP4Facade {
	private File file;
	private IsoFile isoFile;
	private MP4FileWriter writer;

	public MP4Facade(File file) throws IOException {
		this.file = file;
		this.isoFile = new MP4FileReader(file).readAndParse();
		writer = new MP4FileWriter(isoFile);
	}

	public AppleItemListBox getAppleItemListBox() {
		checkClosed();
		AppleItemListBox appleItemListBox = (AppleItemListBox) IsoFileConvenienceHelper.get(isoFile, "moov/udta/meta/ilst");
		if (appleItemListBox == null) throw new IllegalStateException("This mp4 doesn't have an Apple Item List Box.  Please set one up before using.");
		return appleItemListBox;
	}

	public void writeUpdates() throws IOException {
		checkClosed();
		String fileName = file.getCanonicalPath();
		int lastDot = fileName.lastIndexOf('\\');
		String newName = fileName.substring(0,lastDot) + "-new" + fileName.substring(lastDot);
		writer.writeToFile(newName);
	}

	public boolean isIpod() {
		return (file.getName().toUpperCase().contains("(IPOD)"));
	}

	public void close() {
		isoFile = null;
		writer.close();
		writer = null;
	}

	private void checkClosed() {
		if (isoFile == null)
			throw new IllegalStateException("MP4 '"+file.getName()+"' has been closed.  No further work can be done on it.");
	}
}