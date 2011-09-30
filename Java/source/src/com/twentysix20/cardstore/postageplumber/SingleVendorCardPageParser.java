package com.twentysix20.cardstore.postageplumber;

import com.twentysix20.util.StringUtil;
import com.twentysix20.util.html.HtmlLoader;

public class SingleVendorCardPageParser {
	private static final int COLUMN_QUANTITY = 5;
	private static final int COLUMN_STORE_NAME = 2;
	private HtmlLoader loader;

	public SingleVendorCardPageParser(HtmlLoader loader) {
		this.loader  = loader;
	}

	public String getCartIDandQuantity(String vendorName, int id) throws Exception {

		String page = loader.getHtmlPage("http://store.tcgplayer.com/product.aspx?id="+id);
		if (page.indexOf("Currently out of stock") > -1) return null;
		
		String cardName = StringUtil.grab(page, "<span id=\"ctl00_cphMain_lblName\">","</span>");
		String cardSet = StringUtil.grab(page, "<a id=\"ctl00_cphMain_hylSetName\"","</a>");
		cardSet = cardSet.substring(cardSet.indexOf('>') + 1);

		String bigRow = StringUtil.grab(page, "PriceListHeader","</table>");
		if (bigRow == null) 
			throw new NullPointerException("Whoops! Couldn't find the Price List for '"+cardName+" - "+cardSet+"'!");

		bigRow = bigRow.substring(bigRow.indexOf("</tr>"));

		int largestQuantity = 0;
		String largestQuantityID = null;
		String[] rows = bigRow.split("(</?tr>)+");
		for (String originalRow : rows) {
			if (StringUtil.isEmpty(originalRow)) continue;

			String row = originalRow.replaceAll("<[^t][^d][^>]*>", "");
			String[] values = row.split("<td[^>]*>");
			if (vendorName.equals(values[COLUMN_STORE_NAME])) {
				String cartID = StringUtil.grab(originalRow,"javascript:AddCart(",",");
				int quantity = Integer.parseInt(values[COLUMN_QUANTITY]);
				if (quantity > largestQuantity) {
					largestQuantityID = cartID;
					largestQuantity = quantity;
				}
			}
		}

		String result = largestQuantityID == null ? null : largestQuantityID + "|" + largestQuantity;
		return result;
	}
}
