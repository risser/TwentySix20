package com.twentysix20.media.thetvdb;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import com.twentysix20.util.FileUtil;
import com.twentysix20.util.html.HtmlLoader;


public class TestSeriesSearcher {
	@Test public void retrieveValidSearch() throws Exception {
		StringBuffer buff = FileUtil.readFile(this, "xmls/search.xml");
		HtmlLoader mockLoader = mock(HtmlLoader.class);
		when(mockLoader.getHtmlPage(anyString())).thenReturn(buff.toString());

		SeriesSearcher search = new SeriesSearcher("Scooby-Doo", mockLoader);
		assertEquals(Arrays.asList("73817"), search.getSeriesIDs());
	}

	@Test public void retrieveEmptySearch() throws Exception {
		StringBuffer buff = FileUtil.readFile(this, "xmls/emptysearch.xml");
		HtmlLoader mockLoader = mock(HtmlLoader.class);
		when(mockLoader.getHtmlPage(anyString())).thenReturn(buff.toString());

		SeriesSearcher search = new SeriesSearcher("Scooby-Doo", mockLoader);
		assertEquals(new ArrayList<String>(), search.getSeriesIDs());
	}

	@Test public void retrieveMultipleSearch() throws Exception {
		StringBuffer buff = FileUtil.readFile(this, "xmls/multiplesearch.xml");
		HtmlLoader mockLoader = mock(HtmlLoader.class);
		when(mockLoader.getHtmlPage(anyString())).thenReturn(buff.toString());

		SeriesSearcher search = new SeriesSearcher("Scooby-Doo", mockLoader);
		assertEquals(Arrays.asList("73543", "78260", "174681"), search.getSeriesIDs());
	}
}
		
