package com.twentysix20.cardstore.parser;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static junit.framework.Assert.assertEquals;

import com.twentysix20.testutil.TestSingleHtmlLoader;
import com.twentysix20.util.html.HtmlLoader;


public class TestSearchParser {
	@Test public void testSingleResult() throws Exception {
        HtmlLoader loader = new TestSingleHtmlLoader(this,"search_SingleFind_SingleMatch.txt");
        SearchParser parser = new SearchParser(loader);
        List<String> actual = parser.getUrls("It That Betrays");

        List<String> expected = new ArrayList<String>();
        expected.add("product.aspx?id=34701");
        assertEquals(expected, actual);
	}

	@Test public void testMultipleMatchesOneVersion() throws Exception {
        HtmlLoader loader = new TestSingleHtmlLoader(this,"search_MultiFind_SingleMatch.txt");
        SearchParser parser = new SearchParser(loader);
        List<String> actual = parser.getUrls("Delay");

        List<String> expected = new ArrayList<String>();
        expected.add("product.aspx?id=14876");
        assertEquals(expected, actual);
	}

	@Test public void testMultipleMatchesOneVersion_NotFirst() throws Exception {
        HtmlLoader loader = new TestSingleHtmlLoader(this,"search_MultiFind_SingleMatch_NotFirst.txt");
        SearchParser parser = new SearchParser(loader);
        List<String> actual = parser.getUrls("Melee");

        List<String> expected = new ArrayList<String>();
        expected.add("product.aspx?id=4792");
        assertEquals(expected, actual);
	}

	@Test public void testMultipleMatchMultipleVersion() throws Exception {
        HtmlLoader loader = new TestSingleHtmlLoader(this,"search_MultiFind_MultiVersion.txt");
        SearchParser parser = new SearchParser(loader);
        List<String> actual = parser.getUrls("Desolation Giant");

        List<String> expected = new ArrayList<String>();
        expected.add("product.aspx?id=7938");
        expected.add("product.aspx?id=14579");
        assertEquals(expected, actual);
	}

	@Test public void testMultipleMatchMultipleVersionNotFirst() throws Exception {
        HtmlLoader loader = new TestSingleHtmlLoader(this,"search_MultiFind_MultiVersion_NotFirst.txt");
        SearchParser parser = new SearchParser(loader);
        List<String> actual = parser.getUrls("Lich");

        List<String> expected = new ArrayList<String>();
        expected.add("product.aspx?id=9117");
        expected.add("product.aspx?id=1170");
        expected.add("product.aspx?id=8815");
        assertEquals(expected, actual);
	}

	@Test public void testNoFinds() throws Exception {
        HtmlLoader loader = new TestSingleHtmlLoader(this,"search_NoFinds.txt");
        SearchParser parser = new SearchParser(loader);
        List<String> actual = parser.getUrls("Fooberry");

        List<String> expected = new ArrayList<String>();
        assertEquals(expected, actual);
	}

	@Test public void testFindsButNoMatches() throws Exception {
        HtmlLoader loader = new TestSingleHtmlLoader(this,"search_FindButNoMatches.txt");
        SearchParser parser = new SearchParser(loader);
        List<String> actual = parser.getUrls("Martyrs");

        List<String> expected = new ArrayList<String>();
        assertEquals(expected, actual);
	}

	@Test public void testOnlyNonMagicFinds() throws Exception {
        HtmlLoader loader = new TestSingleHtmlLoader(this,"search_OnlyNonMagicFinds.txt");
        SearchParser parser = new SearchParser(loader);
        List<String> actual = parser.getUrls("Robotic Homing Chicken");

        List<String> expected = new ArrayList<String>();
        assertEquals(expected, actual);
	}

	@Test public void testIgnoringCase() throws Exception {
        HtmlLoader loader = new TestSingleHtmlLoader(this,"search_MultiFind_MultiVersion_NotFirst.txt");
        SearchParser parser = new SearchParser(loader);
        List<String> actual = parser.getUrls("liCh");

        List<String> expected = new ArrayList<String>();
        expected.add("product.aspx?id=9117");
        expected.add("product.aspx?id=1170");
        expected.add("product.aspx?id=8815");
        assertEquals(expected, actual);
	}
}
