/*
 * XmlPrintedOutput.java
 *
 * Created on September 4, 2004, 8:08 AM
 */

package com.twentysix20.mtg.old.spoiler.output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.twentysix20.mtg.old.spoiler.cardinfo.CardInfo;
import com.twentysix20.mtg.old.spoiler.parser.HtmlParser;
import com.twentysix20.util.StringUtil;


/**
 *
 * @author  Peter Risser
 */

public class PicturePagesOutput extends AbstractOutput {
    
    String picPage = "";

    public PicturePagesOutput(String name, List list) {
        super(name,list);
    }
    public PicturePagesOutput(String name, List list, String pic) {
        this(name,list);
        picPage = pic;
    }
    
    public void generateFile() throws IOException {
		BufferedWriter outFile = new BufferedWriter(new FileWriter(getFileName()));
		Iterator cards = getCardList().iterator();

        outFile.write("<html><head><title>Picture Pages");
        String setName = "";
        if (cards.hasNext()) 
            setName = ((CardInfo)getCardList().get(0)).getSetName();
        if (!StringUtil.isEmpty(setName))
            outFile.write(" for "+setName);
        outFile.write("</title></head><body>\n");
        while (cards.hasNext()) {
            CardInfo card = (CardInfo)cards.next();
            String name;
            name = card.getName();
            if (StringUtil.contains(name,"Big Furry"))
                name = "BFM";
            if ((!StringUtil.isEmpty(card.getVersion()) && !card.getVersion().equals("1")) ||
                card.getSetName().equals("INV") 
                || card.getSetName().equals("UGL") 
                || card.getSetName().equals("7ED")) // forest 1 needs version in Invasion
                name += card.getVersion();
            if (card.getFacePosition().equals("bottom"))
                name += " Flipped";
            if (card.getSetName().equals("7ED"))
                name = name.replaceAll("Circle of Protection","CoP");
            outFile.write("<img alt=\""+name+"\" src=\"");
            outFile.write("http://www.wizards.com/global/images/magic/");
            if (StringUtil.isEmpty(picPage))
                outFile.write(card.getSetName());
            else
                outFile.write(picPage);
            outFile.write("/");
            if (!card.getSetName().equals("UGL"))
                name = name.replaceAll("!","");
            outFile.write(cleanup(name));
            outFile.write(".jpg\">\n");
            System.out.println(card.getName());
        }
        outFile.write("</body></html>");
		outFile.close();
    }
    
    private String cleanup(String s) {
        return StringUtil.simplify(s.replaceAll("[\\s\\-/]+","_").replaceAll("[\\u00AE:',\\.\"]",""));
    }

    public static void main(String args[]) throws Exception {
		HtmlParser t = new HtmlParser("/Risser/MTG/spoilers/CHKamigawa.html"); //test.txt
//<img alt="&AElig;ther Spellbomb" src="http://www.wizards.com/global/images/magic/Mirrodin/AEther_Spellbomb.jpg">
        AbstractOutput x = new PicturePagesOutput("/Risser/MTG/spoilers/XML/"+t.getSet()+".html", 
                t.getList(), t.getPicPage());
        x.generateFile();
        System.out.println("* Done.");
	}
}