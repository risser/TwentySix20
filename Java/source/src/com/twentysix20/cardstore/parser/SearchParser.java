package com.twentysix20.cardstore.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.twentysix20.util.StringUtil;
import com.twentysix20.util.html.HtmlLoader;

public class SearchParser {
    private HtmlLoader loader;

    public SearchParser(HtmlLoader loader) throws IOException {
        super();
        this.loader = loader;
        //ctl00_cphMain_pnlItems
    }

	public List<String> getUrls(String cardName) throws Exception {
		ArrayList<String> urls = new ArrayList<String>();
		String page = loader.getHtmlPage("http://store.tcgplayer.com/Products.aspx?name="+cardName.replace(' ', '+'));
		String searchString = ">"+cardName+"<";
		int actualCardNamePos = page.toUpperCase().indexOf(searchString.toUpperCase());
		if (actualCardNamePos < 0)
			return urls;

		searchString = page.substring(actualCardNamePos,actualCardNamePos+searchString.length());
		
		int p;
		while ((p = page.indexOf(searchString)) > -1) {
			int urlPos = page.lastIndexOf("<a href",p);
			if (urlPos < 0) throw new RuntimeException("Couldn't find '<a href' in search page.");
			String url = StringUtil.grab(page, "\"", "\"",urlPos);

			page = page.substring(p + cardName.length());
			String toEndOfLine = page.substring(0,page.indexOf("</table>"));
			if (toEndOfLine.indexOf("Magic: the Gathering") > -1)
				urls.add(url);
		}

		return urls;
	}
}
