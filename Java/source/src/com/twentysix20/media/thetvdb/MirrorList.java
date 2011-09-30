package com.twentysix20.media.thetvdb;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.thoughtworks.xstream.XStream;
import com.twentysix20.media.thetvdb.model.Mirror;
import com.twentysix20.media.thetvdb.model.Mirrors;
import com.twentysix20.util.html.FancyInternetHtmlLoader;
import com.twentysix20.util.html.HtmlLoader;

public class MirrorList {
	private List<String> xmls = new ArrayList<String>();
	private List<String> banners = new ArrayList<String>(); 
	private List<String> zips = new ArrayList<String>();

	private Random random = new Random();

	public MirrorList(HtmlLoader htmlLoader) throws Exception {
		loadXML(htmlLoader);
	}

	public String getXmlUrl() {
		return xmls.get(random.nextInt(xmls.size()));
	}

	private void loadXML(HtmlLoader htmlLoader) throws Exception {
		String xml = htmlLoader.getHtmlPage(APIConstants.URL_MIRRORS);
		Mirrors mirrors = (Mirrors)xstream().fromXML(xml);
		for (Mirror mirror : mirrors.mirrors) {
			if (mirror.isXMLMirror())
				xmls.add(mirror.getMirrorpath());
			if (mirror.isZipMirror())
				zips.add(mirror.getMirrorpath());
			if (mirror.isBannerMirror())
				banners.add(mirror.getMirrorpath());
		}
	}

	private XStream xstream() {
		XStream xstream = new XStream();
		xstream.addImplicitCollection(Mirrors.class, "mirrors");
		xstream.alias("Mirrors", Mirrors.class);
		xstream.alias("Mirror", Mirror.class);
		return xstream;
	}

    public static void main(String[] args) throws Exception {
    	HtmlLoader loader = new FancyInternetHtmlLoader();
    	new MirrorList(loader);
    }
}