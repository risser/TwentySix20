package com.twentysix20.lyrics;

import com.twentysix20.util.html.HtmlUtil;

public class LyricCleanup {
    static public String clean(String lyrix, String artist) {
        lyrix = HtmlUtil.cleanHtml(lyrix);
        lyrix = lyrix.replaceAll("\\s*<[Bb][Rr] ?[/\\\\]?>\\s*", "\n");
        lyrix = lyrix.replaceAll("&#10;", "");
        lyrix = lyrix.replaceAll("</?p>", "\n\n");
        lyrix = lyrix.replaceAll("</?[BbUuIi]>", "");
        lyrix = lyrix.replaceAll("&nbsp;", " ");
        lyrix = lyrix.replaceAll("<span.*?>","");
        lyrix = lyrix.replaceAll("\\[.?(Lyrics )?[Ff]rom:.*?\\]","");
        lyrix = lyrix.replaceAll("<img.*?>","");
        lyrix = lyrix.replaceAll("</span>","\n");
        lyrix = lyrix.replaceAll("<div.*?</div>","");
        lyrix = lyrix.replaceAll("(?i)(\n|^)"+artist+".*?(\n\n|$)", "");
        lyrix = lyrix.replaceAll("\n\n\n","\n\n");
        lyrix = lyrix.replaceAll("\\.\\.\\.\\s*$","");
        return lyrix.trim();
    }

}
