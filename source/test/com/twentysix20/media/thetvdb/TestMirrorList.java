package com.twentysix20.media.thetvdb;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.twentysix20.util.FileUtil;
import com.twentysix20.util.html.HtmlLoader;


public class TestMirrorList {
	@Test public void retrieveListOfMirrors() throws Exception {
		StringBuffer buff = FileUtil.readFile(this, "xmls/mirrors.xml");
		HtmlLoader mockLoader = mock(HtmlLoader.class);
		when(mockLoader.getHtmlPage(anyString())).thenReturn(buff.toString());

		MirrorList mirrors = new MirrorList(mockLoader);
		assertEquals("http://thetvdb.com", mirrors.getXmlUrl());
	}
}
