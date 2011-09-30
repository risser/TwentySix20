package com.twentysix20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tools.ant.filters.StringInputStream;

import com.twentysix20.cardstore.money.Money;
import com.twentysix20.util.StringUtil;
import com.twentysix20.util.html.HtmlUtil;
import com.twentysix20.util.html.InternetHtmlLoader;

public class BuyList {

	private static final String END_OF_SET_LINE = "<END>";
	private static final String IGNORE = "<IGNORE>";
	private static final String STAR_CITY = "StarCity";
	private static final String BLACK_BORDER = "BlackBorder";
	private static final String STRIKE_ZONE = "StrikeZone";

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Map<String, Map<String, Money>> editionCardToPricesMap = new HashMap<String, Map<String, Money>>();
		processStarCity(editionCardToPricesMap);
		processBlackBorder(editionCardToPricesMap);
		processStrikeZone(editionCardToPricesMap);

        List<String> sortedCardEditionList = new ArrayList<String>(editionCardToPricesMap.keySet());
        Collections.sort(sortedCardEditionList);
        for (String editionCardName : sortedCardEditionList) {
        	String[] ss = editionCardName.split(":",2);
        	String set = ss[0].replaceAll("- "," ");
        	if (IGNORE.equals(set)) continue;
        	String name = ss[1];
            String starCity = "";
            String blackBorder = "";
            String strikeZone = "";
            Map<String, Money> storeToPriceMap = editionCardToPricesMap.get(editionCardName);            
            for (String storeName : storeToPriceMap.keySet()) {
            	String moneyString = storeToPriceMap.get(storeName).toString();
				if (STRIKE_ZONE.equals(storeName))
            		strikeZone = moneyString;
            	if (BLACK_BORDER.equals(storeName))
            		blackBorder = moneyString;
            	if (STAR_CITY.equals(storeName))
            		starCity = moneyString;
			}
			System.out.println(set + "\t" + name + "\t" + blackBorder + "\t" + strikeZone + "\t" + starCity);
        }
	}

	private static void processStarCity(Map<String, Map<String, Money>> editionCardToPricesMap) throws Exception, UnsupportedEncodingException, IOException {
		String page = new InternetHtmlLoader().getHtmlPage("http://sales.starcitygames.com/buylist/");
		page = StringUtil.grab(page, "<table>", "bottomaddress");
		page = page.replaceAll("<td class=\"middleheaderbuy\" colspan=\"2\" width=\"33%\">", "<td>");
		page = page.replaceAll("<td class=\"deckdbbody2? scr\" align=\"left\">", "<td>");
		page = page.replaceAll("<td class=\"deckdbbody2? scr\">", "<td>");
		page = page.replaceAll("<td class=\"deckdbbody2? scr\" align=\"left\" colspan=\"2\">", "<td></td><td>");
		page = page.replaceAll("<strong>REMINDER: Chronicles U1 Cards Do Not Count As Rares!</strong>", "");
		page = page.replaceAll("<td class=\"deckdbheader scr\"><b><span class=\"articletext\">NM Price</span></b></td>", "");
		page = page.replaceAll("<td class=\"deckdbheader scr\"><b><span class=\"articletext\">Card</span></b></td>", "");
		page = page.replaceAll("<td width=\"15\">&nbsp</td>", "");
		page = page.replaceAll("<td colspan=\"2\">&nbsp;</td>", "<td></td><td></td>");
		page = page.replaceAll("\\s+", " ");
		page = page.replaceAll("<tr>", "");
		page = page.replaceAll("<table>", "");
		page = page.replaceAll("</?strong>", "");
		page = page.replaceAll("</tr>", "\n");
		page = page.replaceAll("</table>", "\n\n");
		page = page.replaceAll("</td> ?<td>", "|");
		page = page.replaceAll("</?td>", "");
		page = page.replaceAll("\n\n\n ", "\n<END>\n");
		page = page.replaceAll("\n  \n", "\n");
		page = StringUtil.grab(page, " ", "<td colspan=\"9\"").trim();
        BufferedReader in = new BufferedReader(new InputStreamReader(new StringInputStream(page),"UTF-8"));
        String inputLine;

        String firstSetName = null;
        String secondSetName = null;
        String thirdSetName = null;
        boolean titleMode = true;
        while ((inputLine = in.readLine()) != null) {
    		String[] ss = inputLine.trim().split("\\|");
			if (titleMode) {
	        	firstSetName = cleanUpSetName(ss[0]);
	        	secondSetName = cleanUpSetName(ss[1]);
        		if (ss.length > 2)
    	        	thirdSetName = cleanUpSetName(ss[2]);
        		else
        			thirdSetName = IGNORE;
        		titleMode = false;
System.out.println(firstSetName+", "+secondSetName+", "+thirdSetName);        		
        		continue;
        	}
        	if (END_OF_SET_LINE.equals(inputLine)) {
        		titleMode = true;
        		continue;
        	}
        	if (!StringUtil.isEmpty(ss[0]))
        		addNewPrice(editionCardToPricesMap, STAR_CITY, firstSetName, ss[0], ss[1]);
        	if (ss.length > 2 && !StringUtil.isEmpty(ss[2]))
        		addNewPrice(editionCardToPricesMap, STAR_CITY, secondSetName, ss[2], ss[3]);
        	if (ss.length > 4 && !StringUtil.isEmpty(ss[4]))
        		addNewPrice(editionCardToPricesMap, STAR_CITY, thirdSetName, ss[4], ss[5]);
        }
        in.close();
	}

	private static void processBlackBorder(Map<String, Map<String, Money>> editionCardToPricesMap) throws Exception, UnsupportedEncodingException, IOException {
		String urlPage = new InternetHtmlLoader().getHtmlPage("http://www.blackborder.com/cgi-bin/shopping/buylist.cgi");
		urlPage = StringUtil.grab(urlPage,"buylist:","<td colspan=5>");
		urlPage = urlPage.replaceAll("<tr>","");
		urlPage = urlPage.replaceAll("<td>","");
		String[] urlRows = urlPage.split("</tr>");
		for (String urlRow : urlRows) {
			String[] urls = urlRow.split("</td>");
			for (int i = 0; i < urls.length; i++) {
				if (i > 2) continue;
				String url = urls[i];
				if (!url.startsWith("<a href")) continue;
				url = StringUtil.grab(url, "\"","\"");
				processBlackBorderPage(editionCardToPricesMap, url);
			}
		}
	}

	private static void processStrikeZone(Map<String, Map<String, Money>> editionCardToPricesMap) throws Exception, UnsupportedEncodingException, IOException {
		String urlPage = new InternetHtmlLoader().getHtmlPage("http://shop.strikezoneonline.com/BuyList/Magic_the_Gathering_Singles.html");
		urlPage = StringUtil.grab(urlPage,"<TR class='LeftItemRow'>","<TR><TD class='LeftTitle'>");
		String[] urlRows = urlPage.split("<TR class='LeftItemRow'>");
		for (String urlRow : urlRows) {
			String url = StringUtil.grab(urlRow, "\"", "\"");
			url = "http://shop.strikezoneonline.com/BuyList"+url.substring(url.lastIndexOf('/'));
			System.out.println(url);
			processStrikeZonePage(editionCardToPricesMap, url);
		}
	}

	private static void processStrikeZonePage(Map<String, Map<String, Money>> editionCardToPricesMap, String url) throws Exception {
		String page = new InternetHtmlLoader().getHtmlPage(url);
		String set = StringUtil.grab(page,"<h1>Singles","Buy Lists</h1>").trim().replaceAll("&nbsp;"," ");
		set = cleanUpSetName(set);
		System.out.println(set);		
		page = StringUtil.grab(page, "<TD align=center>$","</TABLE>");
		if (page == null) return;

		page = page.replaceAll("<!--.*?-->","");
		page = page.replaceAll("</?a.*?>","");
		page = page.replaceAll("<TD></TR>","");
		page = page.replaceAll("<TD .*?>","<td>");
		page = page.replaceAll("<TR.*?>","");
		String[] items = page.split("</[Tt][Rr]>");
		for (String item : items) {
			String[] fields = item.split("<[Tt][Dd]>");
			String name = fields[1];
			boolean foil = (fields[4].indexOf("Foil") > -1);
			if (foil)
				name += " (Foil)";
			String price = fields[6];
    		addNewPrice(editionCardToPricesMap, STRIKE_ZONE, set, name, price);
		}
	}

	private static void processBlackBorderPage(Map<String, Map<String, Money>> editionCardToPricesMap, String url) throws Exception {
System.out.println(url);		
		String page = new InternetHtmlLoader().getHtmlPage("http://www.blackborder.com/cgi-bin/shopping/" + url);
		page = StringUtil.grab(page, "</th></tr>","</table>");
		page = page.replaceAll("</?form.*?>","");
		page = page.replaceAll("<input.*?>","");
		page = page.replaceAll("<td.*?>","");
		page = page.replaceAll("</?a.*?> *","");
//		page = page.replaceAll("<span.*?br>","");
//		page = page.replaceAll("</span>","");
		page = page.replaceAll("<img src=\"http://www.blackborder.com/bbcart/images/cent.gif\">","¢");
		page = page.replaceAll("<tr>","");
		String[] rows = page.split("\\s*</tr>\\s*");
		for (String row : rows) {
			String[] fields = row.split("</td>");
			String cardName = fields[0].trim();
			String set = fields[1].replaceAll("</span>", "");
			set = StringUtil.grab(set, "<br>").trim();
			set = cleanUpSetName(set);
			String price = fields[4].trim();
//			System.out.println(set+":"+cardName+" : "+price);
    		addNewPrice(editionCardToPricesMap, BLACK_BORDER, set, cardName, price);
		}
	}

	private static void addNewPrice(Map<String, Map<String, Money>> cardEditionMap, String storeName, String setName, String cardName, String price) {
		cardName = cardName.replaceAll("\\(FOIL\\)","").trim();
		if (setName.endsWith("Foil") || setName.endsWith("(Foil)")) {
			setName = setName.replaceAll("\\(?[Ff]oil\\)?","").trim();
			cardName += " (Foil)";
		}
		cardName = cleanUpName(cardName);
		Map<String, Money> storeToPriceMap = cardEditionMap.get(cardEditionMapKey(setName, cardName));
		if (storeToPriceMap == null) {
			storeToPriceMap = new HashMap<String, Money>();
			cardEditionMap.put(cardEditionMapKey(setName, cardName), storeToPriceMap);
		}
		storeToPriceMap.put(storeName, monify(price));
	}

	private static String cleanUpName(String cardName) {
		cardName = cardName.replaceAll(" Of "," of ").replaceAll(" The "," the ").replaceAll(" To "," to ")
			.replaceAll("-t","-T").replaceAll("-d","-D").replaceAll("-h","-H")
			.replaceAll("-c","-C").replaceAll("-g","-G").replaceAll(" At "," at ").replaceAll("Lim-[Dd]ul", "Lim-Dûl")
			.replaceAll("A[Ee][Tt]her","Æther").replaceAll(" / "," // ").replaceAll("Ifh-Biff","Ifh-Bíff")
			.replaceAll("Junun","Junún").replaceAll("World Bottling","World-Bottling")
			.replaceAll("Will-[Oo]'-[Tt]he-[Ww]isp","Will-o'-the-Wisp");
		if ("Sheep".equals(cardName) || "Squirrel".equals(cardName))
			cardName += " Token";
		return cardName;
	}

	private static String cardEditionMapKey(String setName, String cardName) {
		return setName+":"+cardName;
	}

	private static String cleanUpSetName(String setName) {
    	if (setName.startsWith("World of Warcraft")) return IGNORE;
    	if (setName.startsWith("Foreign Magic")) return IGNORE;
    	if (setName.startsWith("From the Vault")) return IGNORE;
    	if (setName.startsWith("Vanguard")) return IGNORE;
    	if (setName.startsWith("Collector's Edition")) return IGNORE;
		String name = setName.replaceAll(" Singles", "");
		name = name.replaceAll(": City of Guilds","").replaceAll("Promotional Cards","Promotional");
		name = name.replace(':', '-');
		name = name.replaceAll(" Time Shifted","");
		if ("Coldsnap Theme Deck Reprints".equals(name))
			name = "Coldsnap Theme Deck";
		if ("Portal II".equals(name))
			name = "Portal Second Age";
		if ("Futuresight".equals(name))
			name = "Future Sight";
		if ("Portal I".equals(name))
			name = "Portal";
		if (name.indexOf("Core Set") > -1)
			name = "M" + name.replaceAll("(Magic|Core Set)", "").replaceAll("M1","201").trim();
		if (name.endsWith("Revised"))
			name = "Revised Edition";
		if ("Unlimited".equals(name) || "Alpha".equals(name) || "Beta".equals(name))
			name = "Alpha/Beta/Unlimited Edition ("+name+")";

		return name.trim();
	}

	private static Money monify(String moneyStr) {
		if (moneyStr.startsWith("$"))
			moneyStr = moneyStr.substring(1);
		if (moneyStr.endsWith("¢")) {
			moneyStr = "0."+StringUtil.padLeft(moneyStr.substring(0,moneyStr.length()-1),2,'0');
		}
		return new Money(moneyStr);
	}
}
