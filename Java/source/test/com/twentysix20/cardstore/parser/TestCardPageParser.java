package com.twentysix20.cardstore.parser;

import static junit.framework.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.twentysix20.cardstore.supplyrecord.StandardSupplyRecord;
import com.twentysix20.cardstore.supplyrecord.SupplyRecord;
import com.twentysix20.cardstore.vendor.VendorFactory;
import com.twentysix20.testutil.TestSingleHtmlLoader;
import com.twentysix20.util.html.HtmlLoader;


public class TestCardPageParser {
	@Test public void testNormalPage() throws Exception {
        HtmlLoader loader = new TestSingleHtmlLoader(this,"cardpage_normal.txt");
        CardPageParser parser = new CardPageParser(loader);
        Set<SupplyRecord> actual = parser.getSupplyRecords(null);
        assertEquals(5,actual.size());

        Set<SupplyRecord> expected = new HashSet<SupplyRecord>();
        expected.add(new StandardSupplyRecord("Chicken Egg", VendorFactory.find("ABUGames"), "Unglued", "Unplayed", 4, "0.09"));
        expected.add(new StandardSupplyRecord("Chicken Egg", VendorFactory.find("adventuresON"), "Unglued", "Unplayed", 36, "0.10"));
        expected.add(new StandardSupplyRecord("Chicken Egg", VendorFactory.find("TrollandToad"), "Unglued", "Unplayed", 77, "0.27"));
        expected.add(new StandardSupplyRecord("Chicken Egg", VendorFactory.find("TrollandToad"), "Unglued", "Played", 31, "0.20"));
        expected.add(new StandardSupplyRecord("Chicken Egg", VendorFactory.find("HotsauceGames"), "Unglued", "Unplayed", 9, "0.25"));
        assertEquals(expected, actual);
	}

	@Test public void testSinglePrice() throws Exception {
        HtmlLoader loader = new TestSingleHtmlLoader(this,"cardpage_onePrice.txt");
        CardPageParser parser = new CardPageParser(loader);
        Set<SupplyRecord> actual = parser.getSupplyRecords(null);

        Set<SupplyRecord> expected = new HashSet<SupplyRecord>();
        expected.add(new StandardSupplyRecord("Chicken Egg", VendorFactory.find("ABUGames"), "Unglued", "Unplayed", 4, "0.09"));
        assertEquals(expected, actual);
	}

	@Test public void testOutOfStock() throws Exception {
        HtmlLoader loader = new TestSingleHtmlLoader(this,"cardpage_outOfStock.txt");
        CardPageParser parser = new CardPageParser(loader);
        Set<SupplyRecord> actual = parser.getSupplyRecords(null);

        Set<SupplyRecord> expected = new HashSet<SupplyRecord>();
        assertEquals(expected, actual);
	}
}
