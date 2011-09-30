package com.twentysix20.media.tvboxer.boxes;

import java.io.UnsupportedEncodingException;

import com.coremedia.iso.IsoFileConvenienceHelper;
import com.coremedia.iso.boxes.apple.AppleDataBox;
import com.coremedia.iso.boxes.apple.AppleGenericBox;
import com.coremedia.iso.boxes.apple.AppleMeanBox;
import com.coremedia.iso.boxes.apple.AppleNameBox;

public class MP4GenericBox {

	private AppleGenericBox box;

	public MP4GenericBox(AppleGenericBox x) {
		this.box = x;
	}

	public MP4GenericBox(String name, String data, int flags) {
		this.box = new AppleGenericBox();
		setMeaning("com.apple.iTunes");
		setName(name);
		setData(data, flags);
	}

	public String getMeaning() {
		AppleMeanBox meanBox = (AppleMeanBox)IsoFileConvenienceHelper.get(box, "mean");
		return meanBox.getMeaning();
	}
	public void setMeaning(String meaning) {
		AppleMeanBox subBox = (AppleMeanBox)IsoFileConvenienceHelper.get(box, "mean");
	    if (subBox == null) {
	    	subBox = new AppleMeanBox();
		    box.addBox(subBox);
	    }
	    subBox.setMeaning(meaning);
	}

	public String getData() {
		AppleDataBox dataBox = (AppleDataBox)IsoFileConvenienceHelper.get(box, "data");
		return new String(dataBox.getContent());
	}
	public void setData(String data, int flags) {
		AppleDataBox subBox = (AppleDataBox)IsoFileConvenienceHelper.get(box, "data");
	    if (subBox == null) {
	    	subBox = new AppleDataBox();
		    box.addBox(subBox);
	    }
		try {
			subBox.setContent(data.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		subBox.setFlags(flags);
	}

	public String getName() {
		AppleNameBox nameBox = (AppleNameBox)IsoFileConvenienceHelper.get(box, "name");
		return nameBox.getName();
	}
	public void setName(String name) {
		AppleNameBox subBox = (AppleNameBox)IsoFileConvenienceHelper.get(box, "name");
	    if (subBox == null) {
	    	subBox = new AppleNameBox();
		    box.addBox(subBox);
	    }
	    subBox.setName(name);
	}

	public AppleGenericBox getUnderlyingBox() {
		return this.box;
	}
}
