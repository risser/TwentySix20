package com.twentysix20.mtg.magiccardsinfo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.twentysix20.mtg.data.card.Card;
import com.twentysix20.mtg.data.card.CardFactory;
import com.twentysix20.mtg.data.card.CardTemplate;
import com.twentysix20.mtg.data.pt.PowerToughness;
import com.twentysix20.util.StringUtil;
import com.twentysix20.util.html.HtmlLoader;

public class MagicCardsInfoParser {
    static private Pattern cardHtmlParser = Pattern.compile(".*?<a href.*?/en/((?:.*?)).html\">((?:.*?))</a>.*?<img[^>]*>((?:.*?)), <i>((?:.*?))</i>"+
    		".*?<p>((?:.*?))</p>.*?<b>((?:.*?))</b>.*?<i>((?:.*?))</i>.*?Illus. ((?:.*?))</p>"
    		,Pattern.CASE_INSENSITIVE);
	private HtmlLoader loader;

	public MagicCardsInfoParser(HtmlLoader loader) {
		this.loader = loader;
	}

	public List<Card> parse(String setAbbreviation) throws Exception {
		List<Card> cardList = new ArrayList<Card>();
		String url = "http://magiccards.info/query?q=%2B%2Be%3A"+setAbbreviation+"%2Fen&v=spoiler&s=issue";
		String page = loader.getHtmlPage(url);
		page = StringUtil.grab(page, "<table cellpadding=\"3\"", "</table>");
		page = page.substring(page.indexOf("<td"));
		String[] rows = page.split("<tr>");
		for (String row : rows) {
			cardList.addAll(processRow(row));
		}
		return cardList;
	}

	private List<Card> processRow(String row) {
		List<Card> cardList = new ArrayList<Card>();
		if (!row.contains("colspan=\"4\"")) {
			String[] cardHtmls = row.split("<td");
			for (String cardHtml : cardHtmls) {
				if (cardHtml.trim().isEmpty()) continue;
				cardList.add(processCardHtml(cardHtml));
			}
		}
		return cardList;
	}

	private Card processCardHtml(String cardHtml) {
		CardTemplate template = new CardTemplate();
	    Matcher m = cardHtmlParser.matcher(cardHtml);
	    if (m.find())
	    {
	        String number = m.group(1);
	        String name = m.group(2);
//	        String set = m.group(3).trim();
	        String rarity = m.group(4);
	        String typeLineEtc = m.group(5);
	        String rules = m.group(6).replaceAll("<br><br>", "\n");
	        String flavor = m.group(7);
	        String artist = m.group(8);
	        template.setArtist(artist);
	        template.setFlavor(flavor);
	        template.setName(name);
	        template.setNumber(number);
	        template.setRarity(rarity.substring(0,1));
	        template.setRules(rules);
	        String[] splitTypeLine = typeLineEtc.split(",");
	        String costs = splitTypeLine[1].trim();
	        if (!costs.isEmpty()) {
		        int lastChar = costs.indexOf(' ');
		        if (lastChar == -1)
		        	lastChar = costs.length();
				String cost = costs.substring(0,lastChar).trim();
				cost = cost.replace('{', '(').replace('}',')');
				template.setCost(cost);
	        }
	        String typeAndPT = splitTypeLine[0].trim();
	        if (typeAndPT.contains(" ")) {
	        	int lastSpace = typeAndPT.lastIndexOf(' ');
		        String lastWord = typeAndPT.substring(lastSpace+1);
		        if (lastWord.matches(PowerToughness.VALID_PT_LINE)) {
		        	template.setType(typeAndPT.substring(0,lastSpace));
		        	template.setPT(lastWord);
		        } else {
		        	template.setType(typeAndPT);
		        }
	        } else {
		        template.setType(typeAndPT);
	        }	        	
//	        System.out.println("***\n"+template);
	    } else {
	    	System.out.println("NO MATCH: "+cardHtml);
	    }
System.out.println(template.getName());
        return CardFactory.create(template);
	}
}
