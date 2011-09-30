package com.twentysix20.media.webreaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.twentysix20.util.StringUtil;

public class WikiCompReader {
    public static final String THE_URL = "";
    public static Random rand = new Random();
	static private Pattern suffixPattern = Pattern.compile("[jJsS]r\\.?", Pattern.DOTALL);

    public static String root = "http://en.wikipedia.org/wiki/";
    public static String urlRoot = "<td class=\"cell\"><a href=\"";


    public static void main(String[] args) throws Exception {
        InputStreamReader isr = new InputStreamReader( System.in );
        BufferedReader stdin = new BufferedReader( isr );
        System.out.println("Enter URL or Article Name:");
        String url = stdin.readLine();

        System.out.println();
        List<Song> songList = getComposers(url);

        System.out.println();
        for (Song song : songList)
        	System.out.println(song+"\n");
    }

    public static List<Song> getComposers(String input) throws MalformedURLException, UnsupportedEncodingException, IOException, ParseException {
    	if (!StringUtil.contains(input, "/"))
    		input = root + input;
        URL source = new URL(input);
        BufferedReader in = new BufferedReader(new InputStreamReader(source.openStream(),"UTF-8"));

        String inputLine;
        List<Song> songList = new ArrayList<Song>();

        boolean inTrackSection = false;
        StringBuilder sb = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
        	if (!inTrackSection) {
        		if (!StringUtil.contains(inputLine, "<table class=\"tracklist\"")) 
        			continue;
        		inTrackSection = true;
        	}
        	if (StringUtil.contains(inputLine,"colspan"))
        		inTrackSection = false;
        	else
        		sb = sb.append(inputLine);
        }
        in.close();
        int pos = 0;
        Map<String,String> personMap = new HashMap<String,String>();
        while (sb.indexOf("<td",pos) > 0) {
        	pos = sb.indexOf("<td",pos)+1;
        	pos = sb.indexOf("<td",pos)+1;
        	String title = StringUtil.grab(sb.toString(), ">", "</td>", pos);
        	title = cleanName(title);
System.out.println(title);
			Song song = new Song();
			song.name = title;
        	pos = sb.indexOf("<td",pos)+1;
        	String composerString = StringUtil.grab(sb.toString(), ">", "</td>", pos);
        	composerString = cleanName(composerString);
			Matcher m = suffixPattern.matcher(composerString);
			if (m.find()) {
	        	composerString = composerString.replaceAll(", ?[jJ]r.?", " Jr.");
	        	composerString = composerString.replaceAll(", ?[sS]r.?", " Sr.");
			}
System.out.println("   "+composerString);
			String[] composers = composerString.split(" *, *");
			for (String composer : composers) {
				String[] names = composer.split(" ");
				String lastName = names[names.length-1];
				Matcher m2 = suffixPattern.matcher(lastName);
				if (m2.find() || "II".equals(lastName))
					lastName = names[names.length-2];
				if (lastName.equals(composer)) {
					if (personMap.get(lastName) != null)
						composer = personMap.get(lastName);
					else
						personMap.put(lastName, composer);
				}
				else
					personMap.put(lastName, composer);
				song.composers.add(composer);
			}
			songList.add(song);
			pos = sb.indexOf("</tr>",pos);
        }
System.out.println();
        return songList;
    }

	private static String cleanName(String comps) {
		comps = comps.replaceAll("\\<.*?\\>", "").replaceAll("&#160;", " ");
		comps = StringUtil.unescapeXml(comps);
		return comps;
	}
}