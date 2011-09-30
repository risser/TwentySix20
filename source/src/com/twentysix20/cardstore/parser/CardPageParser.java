package com.twentysix20.cardstore.parser;

import com.twentysix20.cardstore.supplyrecord.StandardSupplyRecord;
import com.twentysix20.cardstore.supplyrecord.SupplyRecord;
import com.twentysix20.cardstore.supplyrecord.SupplyRecords;
import com.twentysix20.cardstore.vendor.VendorFactory;
import com.twentysix20.util.StringUtil;
import com.twentysix20.util.html.HtmlLoader;

public class CardPageParser {
	private static final int COLUMN_COST = 7;
	private static final int COLUMN_QUANTITY = 6;
	private static final int COLUMN_CONDITION = 5;
	private static final int COLUMN_STORE_NAME = 3;
	private HtmlLoader loader;

	public CardPageParser(HtmlLoader loader) {
		this.loader  = loader;
	}

	public SupplyRecords getSupplyRecords(String url) throws Exception {
		SupplyRecords records = new SupplyRecords();
		
		String page = loader.getHtmlPage("http://store.tcgplayer.com/"+url);
		if (page.indexOf("Currently out of stock") > -1) return records;
		
		String cardName = StringUtil.grab(page, "<span id=\"ctl00_cphMain_lblName\">","</span>");
		String cardSet = StringUtil.grab(page, "<a id=\"ctl00_cphMain_hylSetName\"","</a>");
		cardSet = cardSet.substring(cardSet.indexOf('>') + 1);

		String bigRow = StringUtil.grab(page, "PriceListHeader","</table>");
		if (bigRow == null) 
			throw new NullPointerException("Whoops! Couldn't find the Price List for '"+cardName+" - "+cardSet+"'!");

		bigRow = bigRow.substring(bigRow.indexOf("</tr>"));

		String[] rows = bigRow.split("(</?tr>)+");
		for (String row : rows) {
			if (StringUtil.isEmpty(row)) continue;

			row = row.replaceAll("<[^t][^d][^>]*>", "");
			String[] values = row.split("<td[^>]*>");
			SupplyRecord record = new StandardSupplyRecord(cardName, 
					VendorFactory.find(values[COLUMN_STORE_NAME]), 
					cardSet, values[COLUMN_CONDITION], Integer.parseInt(values[COLUMN_QUANTITY]), values[COLUMN_COST].substring(1));

			records.add(record);
		}
		
		return records;
	}
}
