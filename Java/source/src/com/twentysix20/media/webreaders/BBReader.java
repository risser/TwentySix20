package com.twentysix20.media.webreaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.twentysix20.util.StringUtil;

public class BBReader {
    public static final String THE_URL = "";
    public static Map<String,String[]> conversionMap = new HashMap<String,String[]>();
    public static Random rand = new Random();

    public static void setupConversions () {
        conversionMap.put("OTHER",new String[]{"Canadian Singles Chart",
                                               "European Hot 100 Singles",
                                               "Hot Dance Music/Club Play",
                                               "Hot Dance Music/Maxi-Singles Sales",
                                               "Hot Digital Songs",
                                               "Adult Contemporary",
                                               "Club Play Singles",
                                               "Hot Dance Singles Sales",
                                               "Hot Latin Tracks",
                                               "Latin Tropical/Salsa Airplay",
                                               "Latin Tropical Airplay",
                                               "Latin Pop Airplay",
                                               "Adult Top 40",
                                               "Hot Ringtones",
                                               "Hot Canadian Digital Singles",
                                               "Top 40 Adult Recurrents",
                                               "Hot Country Singles & Tracks",
                                               "Hot Adult Top 40 Recurrents",
                                               "Country Singles",
                                               "Hot Adult Contemporary Tracks",
                                               "Hot Adult Top 40 Tracks",
                                               "Hot RingMasters",
                                               "iLike Libraries",
                                               "iLike Libraries: Most Added",
                                               "Yahoo Audio",
                                               "AOL Radio",
                                               "Hot Dance Club Play",
                                               "Germany Songs",
                                               "United Kingdom Songs",
                                               "Canadian Hot 100",
                                               "Hot Latin Songs",
                                               "Tropical/Salsa",
                                               "Japan Hot 100 Singles",
                                               "France",
                                               "Dance Music/Club Play Singles"
                });
        conversionMap.put("BBRB",new String[]{
        		"Hot R&B/Hip-Hop Singles & Tracks",
                "R&B Singles",
                "Rhythmic Top 40",
                "Black Singles",
                "Hot R&B/Hip-Hop Songs"
                });
        conversionMap.put("BBROCK",new String[]{"Mainstream Rock",
                "Mainstream Rock Tracks",
                "Rock Songs",
                                                "Hot Mainstream Rock Tracks"
                });
        conversionMap.put("BBHARD",new String[]{"Modern Rock",
                                                "Modern Rock Tracks",
                                                "Hot Modern Rock Tracks"
                });
        conversionMap.put("BBRAP",new String[]{"Hot Rap Singles",
                                               "Hot Rap Tracks"
                });
        conversionMap.put("BBPOP",new String[]{"Pop 100",
        									   "Hot 100 Airplay",
                                               "The Billboard Hot 100",
                                               "Pop Singles",
                                               "Pop 100 Airplay"
                });
        conversionMap.put("BBT40",new String[]{"Top 40 Mainstream",
                                               "Top 40 Tracks",
                                               "Mainstream Top 40"
                });
        conversionMap.put("BBDISCO",new String[]{"Disco Singles"});
    }

    public static void main(String[] args) throws Exception {
        setupConversions();
        InputStreamReader isr = new InputStreamReader( System.in );
        BufferedReader stdin = new BufferedReader( isr );
        System.out.println("Enter:");
        String input = stdin.readLine();
        if (!input.startsWith("http")) {
            String name = input;
            String url = getURLforArtist(name);
            input = url + "/charts-awards/billboard-singles";
        }

        List<List<String>> newList = getDataFromChart(input);
        Map<String,Map<String,Integer>> newMap = processList(newList);
        outputCharts(newMap);
    }

    public static String getURLforArtist(String name) throws MalformedURLException, UnsupportedEncodingException, IOException, ParseException {
        String urlString = "http://www.allmusic.com/search/artist/"+name;
        while (urlString.indexOf(' ') > -1)
            urlString = urlString.replace(' ', '+');
        URL source = new URL(urlString);

        BufferedReader in = new BufferedReader(new InputStreamReader(source.openStream(),"UTF-8"));

        String inputLine;
        String foundName;
        String genre;
        String url;

        StringBuffer buff = new StringBuffer();
        while ((inputLine = in.readLine()) != null)
            buff.append(inputLine);
        in.close();
        String s = buff.toString();
        if (s.indexOf("AMG Artist ID") > -1) {
            int bpos = s.indexOf("class=\"amgid\"");
            if (bpos == -1) throw new RuntimeException("CRAP!005");
            String code = StringUtil.grab(s,">","<",bpos).trim().replaceAll("\\s", "");
            url = "http://www.allmusic.com/artist/"+code;
            foundName = StringUtil.grab(s,"<title>","|").trim();
            if (foundName == null) throw new RuntimeException("CRAP!008");
            bpos = s.indexOf("<h3>Genres</h3>");
            if (bpos == -1) throw new RuntimeException("CRAP!006");
            genre = StringUtil.grab(s, "<strong>","</strong>",bpos);
            if (genre == null) throw new RuntimeException("CRAP!007");
        } else {
            int bpos = s.indexOf("Years Active");
            if (bpos == -1) throw new RuntimeException("CRAP!001");
            bpos = s.indexOf("<td><a href=",bpos);
            if (bpos == -1) throw new RuntimeException("CRAP!002");
            url = StringUtil.grab(s, "\"", "\"",bpos);
            if (url == null) throw new RuntimeException("CRAP!003");
            bpos += 10;
            foundName = StringUtil.grab(s,">","<",bpos);
            if (foundName == null) throw new RuntimeException("CRAP!004");
            genre = StringUtil.grab(s, "<td>","</td>",bpos);
            if (genre == null) throw new RuntimeException("CRAP!005");
        }

        System.out.println("SEARCHING FOR: "+name);
        System.out.println("FOUND: "+foundName);
        System.out.println("GENRE: "+genre);
        return url;
    }

    public static List<List<String>> getDataFromChart(String input) throws MalformedURLException, UnsupportedEncodingException, IOException, ParseException {
    	URL source = new URL(input);
        BufferedReader in = new BufferedReader(new InputStreamReader(source.openStream(),"UTF-8"));

        List<List<String>> list = new ArrayList<List<String>>();

        String inputLine;
        StringBuffer buff = new StringBuffer();
        while ((inputLine = in.readLine()) != null)
            buff.append(inputLine);
        in.close();
        String s = buff.toString();

        int bpos = 0;
        
        while ((bpos = s.indexOf("id=\"trlink\"", bpos)) > -1) {
        	String sub = StringUtil.grab(s, ">", "</tr>", bpos);
			List<String> bits = breakItDown(sub);
			list.add(bits);
			bpos +=1;
        }
        in.close();

        return list;
    }

    private static String cleanSong(String s) {
if (s.indexOf("(") > -1) System.out.println(">>>>>> PArens>>>"+s);        
        s = s.toUpperCase();
        s = s.replaceAll("['\\.]","");
        s = s.replaceAll("\\(.*\\)", "");
        s = s.replaceAll(" M ","M ");
        s = s.replaceAll(" S ","S ");
        s = s.replaceAll(" T ","T ");
        s = s.replaceAll("[^ 0-9A-Z]+"," ");
        s = s.replaceAll("  "," ");
        return s.trim();
    }

    public static List<String> breakItDown(String s) {
        String[] ss = s.split("<td");
        List<String> l = new ArrayList<String>();
        String song = ss[3];
        song = StringUtil.grab(song, ">","<",song.indexOf("href"));

        String chart = ss[5];
        chart = StringUtil.grab(chart, ">","<");
        String pos = ss[6];
        pos = StringUtil.grab(pos, ">","<");

        l.add(cleanSong(song));
        l.add(chart.trim());
        if (pos.equals("&nbsp;"))
            pos = "999";
        l.add(pos.trim());
//System.out.println(l.get(0)+": "+l.get(1)+" ("+l.get(2)+")");        
        return l;
    }

    public static Map<String,Map<String,Integer>> processList(List<List<String>> list) {
        Map<String,Map<String,Integer>> map = new HashMap<String,Map<String,Integer>>();
        Iterator<List<String>> itr = list.iterator();
        while (itr.hasNext()) {
        	List<String> bits = itr.next();
            String song = bits.get(0).toString();
            String chart = bits.get(1).toString();
            String newChart = convertChart(chart);
            Integer place = new Integer(bits.get(2).toString());

            if (map.containsKey(song)) {
                Map<String,Integer> chartMap = map.get(song);
                if (chartMap.containsKey(newChart)) {
                    Integer oldPlace = chartMap.get(newChart);
                    if (place.compareTo(oldPlace) < 0)
                        chartMap.put(newChart, place);
                } else {
                    chartMap.put(newChart, place);
                }
            } else {
            	Map<String,Integer> chartMap = new HashMap<String,Integer>();
                chartMap.put(newChart, place);
                map.put(song, chartMap);
            }
        }
        return map;
    }

    public static String convertChart(String chart) {
        Iterator<String> itr = conversionMap.keySet().iterator();
        while (itr.hasNext()) {
            String key = itr.next().toString();
            String[] vals = (String[])conversionMap.get(key);
            for (int i = 0; i < vals.length; i++)
                if (vals[i].equals(chart))
                    return key;
        }
        return "***"+chart;
    }

    public static void outputCharts(Map<String,Map<String,Integer>> map) {
        Set<String> songSet = map.keySet();
        List<String> songList = new ArrayList<String>(songSet);
        Collections.sort(songList);
        Iterator<String> itrSongs = songList.iterator();
        while (itrSongs.hasNext()) {
            String song = itrSongs.next().toString();
            Map<String,Integer> chartMap = map.get(song);
            System.out.println("\n"+song);
            Iterator<String> itrCharts = chartMap.keySet().iterator();
            while (itrCharts.hasNext()) {
                String chart = itrCharts.next();
                if (!chart.equals("OTHER")) {
                    Integer pos = (Integer)chartMap.get(chart);
                    System.out.println(chart+":"+(pos != null ? (pos.intValue() < 10 ? "0" : ""):"null") + pos);
                }
            }
        }
    }
}