package com.twentysix20.media.tvboxer.boxes;

import java.util.ArrayList;
import java.util.List;

import com.coremedia.iso.IsoFileConvenienceHelper;
import com.coremedia.iso.boxes.apple.AppleGenericBox;
import com.coremedia.iso.boxes.apple.AppleNameBox;
import com.twentysix20.util.StringUtil;

public class AppleVideoInfoBox extends MP4GenericBox {
	static public final String VIDEO_INFO_MEANING = "com.apple.iTunes";
	static public final String VIDEO_INFO_BOX_NAME = "iTunMOVI";

	static public final String VIDEO_INFO_KEY_CAST = "cast";
	static public final String VIDEO_INFO_KEY_CODIRECTORS = "codirectors";
	static public final String VIDEO_INFO_KEY_DIRECTORS = "directors";
	static public final String VIDEO_INFO_KEY_PRODUCERS = "producers";
	static public final String VIDEO_INFO_KEY_SCREENWRITERS = "screenwriters";
	static public final String VIDEO_INFO_KEY_COPY_WARNING = "copy-warning";
	static public final String VIDEO_INFO_KEY_ASSET_INFO = "asset-info";
	static public final String VIDEO_INFO_KEY_STUDIO = "studio";

	static public final String VIDEO_INFO_BASE_XML_PREFIX = 
			"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
			"<!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" " +
			"\"http://www.apple.com/DTDs/PropertyList-1.0.dtd\">" +
			"<plist version=\"1.0\"><dict>";
	static public final String VIDEO_INFO_BASE_XML_SUFFIX = "</dict></plist>";

	private List<String> directors = new ArrayList<String>();
	private List<String> codirectors = new ArrayList<String>();
	private List<String> cast = new ArrayList<String>();
	private List<String> producers = new ArrayList<String>();
	private List<String> screenwriters = new ArrayList<String>();
	private String studio;
	private String copyWarning;
//	private String assetInfo;

	public AppleVideoInfoBox(AppleGenericBox x) {
		super(x);
	}

	public AppleVideoInfoBox() {
		super(VIDEO_INFO_BOX_NAME, VIDEO_INFO_BASE_XML_PREFIX + VIDEO_INFO_BASE_XML_SUFFIX, 1);
	}

	public AppleVideoInfoBox(String xml) {
		super(VIDEO_INFO_BOX_NAME, xml, 1);
	}

	public void setCast(List<String> cast) {
		this.cast = cast;
		updateData();
	}

	public void setDirectors(List<String> directors) {
		this.directors = directors;
		updateData();
	}

	public void setCodirectors(List<String> codirectors) {
		this.codirectors = codirectors;
		updateData();
	}

	public void setProducers(List<String> producers) {
		this.producers = producers;
		updateData();
	}

	public void setScreenwriters(List<String> screenwriters) {
		this.screenwriters = screenwriters;
		updateData();
	}

	public void setStudio(String studio) {
		this.studio = studio;
		updateData();
	}

	public void setCopyWarning(String studio) {
		this.studio = studio;
		updateData();
	}

	public void setXML(String xml) {
		setData(xml, 1);
	}

	private void updateData() {
		String xml = 
			generateXML(VIDEO_INFO_KEY_COPY_WARNING, copyWarning) +
			generateXML(VIDEO_INFO_KEY_STUDIO, studio) +
			generateXML(VIDEO_INFO_KEY_CAST, cast) +
			generateXML(VIDEO_INFO_KEY_DIRECTORS, directors) + 
			generateXML(VIDEO_INFO_KEY_CODIRECTORS, codirectors) +
			generateXML(VIDEO_INFO_KEY_PRODUCERS, producers) +
			generateXML(VIDEO_INFO_KEY_SCREENWRITERS, screenwriters);
		xml = VIDEO_INFO_BASE_XML_PREFIX + xml + VIDEO_INFO_BASE_XML_SUFFIX;
		setXML(xml);
	}

	private String generateXML(String key, List<String> listOfNames) {
		if (listOfNames == null || listOfNames.isEmpty()) return "";

		String xml = "<key>"+key+"</key><array>";
		for (String name : listOfNames)
			xml += "<dict><key>name</key><string>"+name+"</string></dict>";
		xml += "</array>";
		return xml;
	}

	private String generateXML(String key, String value) {
		if (StringUtil.isEmpty(value)) return "";

		String xml = "<key>"+key+"</key><string>"+value+"</string>";
		return xml;
	}

	public static boolean isTheTypeOf(AppleGenericBox x) {
		AppleNameBox nameBox = (AppleNameBox)IsoFileConvenienceHelper.get(x, "name");
		return VIDEO_INFO_BOX_NAME.equals(nameBox.getName());
	}
}