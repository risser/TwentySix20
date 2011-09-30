package com.twentysix20.media.webreaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.twentysix20.util.StringUtil;

public class CompReader {
    public static final String THE_URL = "";
    public static Random rand = new Random();
	static private Pattern suffixPattern = Pattern.compile("[jJsS]r\\.?", Pattern.DOTALL);

    public static String root = "http://www.allmusic.com";
    public static String urlRoot = "<td class=\"cell\"><a href=\"";


    public static void main(String[] args) throws Exception {
        InputStreamReader isr = new InputStreamReader( System.in );
        BufferedReader stdin = new BufferedReader( isr );
        System.out.println("Enter URL:");
        String url = stdin.readLine();

        System.out.println();
        List<Song> songList = getSongsFromAlbumPage(url);
        for (Song song : songList)
        	getComposersForSong(song);

        System.out.println();
        for (Song song : songList)
        	System.out.println(song+"\n");
    }

    public static List<Song> getSongsFromAlbumPage(String input) throws MalformedURLException, UnsupportedEncodingException, IOException, ParseException {
        URL source = new URL(input);
        BufferedReader in = new BufferedReader(new InputStreamReader(source.openStream(),"UTF-8"));

        String inputLine;
        List<Song> songList = new ArrayList<Song>();

        while ((inputLine = in.readLine()) != null) {
        	if (inputLine.indexOf("AMG Review") > -1) continue;

        	if (inputLine.indexOf(urlRoot) == -1) continue;
        	inputLine = StringUtil.grab(inputLine,"<a","</a>");
            Song song = new Song();
            song.url = StringUtil.grab(inputLine,"\"","\"");
            song.name = StringUtil.grab(inputLine,">");
System.out.println("Found song: "+song.name);            
            songList.add(song);
        }
        in.close();
System.out.println();
        return songList;
    }

    private static void getComposersForSong(Song song) throws UnsupportedEncodingException, IOException {
    	System.out.println("Searching composers for song: "+song.name);
		URL source = new URL((song.url));
        BufferedReader in = new BufferedReader(new InputStreamReader(source.openStream(),"UTF-8"));

        String inputLine;

        while ((inputLine = in.readLine()) != null) {
        	if (inputLine.indexOf("No Results Found") > -1) {
        		song.notFound = true;
        		break;
        	}
        	int pos = inputLine.indexOf("<td class=\"content\">");
        	if (pos == -1) continue;
        	inputLine = inputLine.replaceAll("</?a.*?>", "");

        	String composers = StringUtil.grab(inputLine, ">", "<");
    		String[] ss = composers.split("/");
    		for (String name : ss) {
    			name = name.trim();
    			if (StringUtil.isEmpty(name)) continue;
System.out.println("::"+name+"::");    			
    			String[] names = name.split(" *, *");
    			if (names.length != 1) {
    				Matcher m = suffixPattern.matcher(names[1]);
    				if (m.find())
    					name = names[0]+" "+names[1];
    				else
    					name = names[1]+" "+names[0];
    			}
    			song.composers.add(name.replaceAll("  "," "));
        	}
        }
        if (song.notFound || song.composers.size() == 0)
        	song.composers.add("No Composers Found");
        in.close();
	}

	private static String buildServerDllCall() {
//        return "http://wc"+serverNumber()+".allmusic.com/cg/amg.dll?";
        return "http://www.allmusic.com/cg/amg.dll?";
    }


    private static String serverNumber() {
        int i = rand.nextInt(10);
        if (i == 0)
            return "10";
        else
            return "0"+i;
    }
}