package com.twentysix20.media.tvboxer.boxes;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.twentysix20.media.tvboxer.boxes.AppleVideoInfoBox;


public class TestAppleVideoInfoBox {

	@Test public void createCast() {
		AppleVideoInfoBox box = new AppleVideoInfoBox();
		box.setCast(Arrays.asList("Johnny Boy","Gillian Girl","Frank the Dog"));
		String xml = AppleVideoInfoBox.VIDEO_INFO_BASE_XML_PREFIX + 
			"<key>cast</key><array>" + 
			"<dict><key>name</key><string>Johnny Boy</string></dict>" + 
			"<dict><key>name</key><string>Gillian Girl</string></dict>" + 
			"<dict><key>name</key><string>Frank the Dog</string></dict>" + 
			"</array>" +
			AppleVideoInfoBox.VIDEO_INFO_BASE_XML_SUFFIX;
		assertEquals(xml, box.getData());
	}

	@Test public void createSingleCast() {
		AppleVideoInfoBox box = new AppleVideoInfoBox();
		box.setCast(Arrays.asList("Frank the Dog"));
		String xml = AppleVideoInfoBox.VIDEO_INFO_BASE_XML_PREFIX + 
			"<key>cast</key><array><dict>" + 
			"<key>name</key><string>Frank the Dog</string>" + 
			"</dict></array>" +
			AppleVideoInfoBox.VIDEO_INFO_BASE_XML_SUFFIX;
		assertEquals(xml, box.getData());
	}

	@Test public void createEmptyCast() {
		AppleVideoInfoBox box = new AppleVideoInfoBox();
		box.setCast(new ArrayList<String>());
		String xml = AppleVideoInfoBox.VIDEO_INFO_BASE_XML_PREFIX + 
			AppleVideoInfoBox.VIDEO_INFO_BASE_XML_SUFFIX;
		assertEquals(xml, box.getData());
	}

	@Test public void createDirectors() {
		AppleVideoInfoBox box = new AppleVideoInfoBox();
		box.setDirectors(Arrays.asList("Johnny Boy","Gillian Girl","Frank the Dog"));
		String xml = AppleVideoInfoBox.VIDEO_INFO_BASE_XML_PREFIX + 
			"<key>directors</key><array>" + 
			"<dict><key>name</key><string>Johnny Boy</string></dict>" + 
			"<dict><key>name</key><string>Gillian Girl</string></dict>" + 
			"<dict><key>name</key><string>Frank the Dog</string></dict>" + 
			"</array>" +
			AppleVideoInfoBox.VIDEO_INFO_BASE_XML_SUFFIX;
		assertEquals(xml, box.getData());
	}

	@Test public void createStudio() {
		AppleVideoInfoBox box = new AppleVideoInfoBox();
		box.setStudio("The Big Studio");
		String xml = AppleVideoInfoBox.VIDEO_INFO_BASE_XML_PREFIX + 
			"<key>studio</key><string>The Big Studio</string>" +
			AppleVideoInfoBox.VIDEO_INFO_BASE_XML_SUFFIX;
		assertEquals(xml, box.getData());
	}

	@Test public void createEverything() {
		AppleVideoInfoBox box = new AppleVideoInfoBox();
		box.setCast(Arrays.asList("JJ Actor","KK Actor"));
		box.setDirectors(Arrays.asList("JJ Director"));
		box.setCodirectors(Arrays.asList("JJ Codirector"));
		box.setProducers(Arrays.asList("JJ Producer"));
		box.setScreenwriters(Arrays.asList("JJ Screenwriter"));
		String xml = AppleVideoInfoBox.VIDEO_INFO_BASE_XML_PREFIX + 
			"<key>cast</key><array>" + 
			"<dict><key>name</key><string>JJ Actor</string></dict>" + 
			"<dict><key>name</key><string>KK Actor</string></dict>" + 
			"</array>" +
			"<key>directors</key><array><dict>" + 
			"<key>name</key><string>JJ Director</string>" + 
			"</dict></array>" +
			"<key>codirectors</key><array><dict>" + 
			"<key>name</key><string>JJ Codirector</string>" + 
			"</dict></array>" +
			"<key>producers</key><array><dict>" + 
			"<key>name</key><string>JJ Producer</string>" + 
			"</dict></array>" +
			"<key>screenwriters</key><array><dict>" + 
			"<key>name</key><string>JJ Screenwriter</string>" + 
			"</dict></array>" +
			AppleVideoInfoBox.VIDEO_INFO_BASE_XML_SUFFIX;
		assertEquals(xml, box.getData());
	}

/////////////////////////

	private AppleVideoInfoBox createXMLBox(String key, List<String> listOfNames) {
		String xml = "";
		for (String name : listOfNames) {
			xml += "<key>name</key><string>" + name + "</string>";
		}
		xml = AppleVideoInfoBox.VIDEO_INFO_BASE_XML_PREFIX +
			"<key>" + key + "</key><array><dict>" + 
			xml + 
			"</dict></array>" + 
			AppleVideoInfoBox.VIDEO_INFO_BASE_XML_SUFFIX;
		return new AppleVideoInfoBox(xml);
	}
}