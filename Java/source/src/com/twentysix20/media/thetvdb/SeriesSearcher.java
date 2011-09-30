package com.twentysix20.media.thetvdb;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.twentysix20.media.thetvdb.model.Series;
import com.twentysix20.media.thetvdb.model.SeriesSearchResults;
import com.twentysix20.util.StringUtil;
import com.twentysix20.util.html.FancyInternetHtmlLoader;
import com.twentysix20.util.html.HtmlLoader;

public class SeriesSearcher {

	private SeriesSearchResults results;

	public SeriesSearcher(String showName, HtmlLoader htmlLoader) throws Exception {
		loadXML(showName, htmlLoader);
	}

	public List<String> getSeriesIDs() {
		List<String> ids = new ArrayList<String>();
		for (Series series : results.series)
			ids.add(series.getSeriesID());
		return ids;
	}

	private void loadXML(String showName, HtmlLoader htmlLoader) throws Exception {
		String xml = htmlLoader.getHtmlPage(StringUtil.replaceIndexes(APIConstants.URL_SERIES_SEARCH, showName));
		results = (SeriesSearchResults)xstream().fromXML(xml);
		if (results.series == null) {
			results.series = new ArrayList<Series>();
		}
	}

	private XStream xstream() {
		XStream xstream = new XStream();
		xstream.addImplicitCollection(SeriesSearchResults.class, "series");
		xstream.alias("Data", SeriesSearchResults.class);
		xstream.alias("Series", Series.class);
		return xstream;
	}

    @Override
	public String toString() {
		return String.format("SeriesSearcher [%s]", results);
	}

	public static void main(String[] args) throws Exception {
    	HtmlLoader loader = new FancyInternetHtmlLoader();
    	System.out.println(new SeriesSearcher("Scooby-Doo",loader));
    }
}
