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
import com.twentysix20.util.StringUtil;


/**
 *
 * @author  Peter Risser
 */

public class XmlPrintedOutput extends AbstractOutput {

    public XmlPrintedOutput(String name, List list) {
        super(name,list);
    }
    
    public void generateFile() throws IOException {
		BufferedWriter outFile = new BufferedWriter(new FileWriter(getFileName()));
        outFile.write("<cards type=\"Magic: The Gathering\">\n");
		Iterator cards = getCardList().iterator();
        while (cards.hasNext()) {
            CardInfo card = (CardInfo)cards.next();
System.out.println(":"+card.getName());
            outFile.write("\t<card name=\""+card.getName()+"\"");
            outFile.write(printAttr("face",card.getFace()));
            outFile.write(printAttr("facepos",card.getFacePosition()));
            outFile.write("/>\n");
            outFile.write("\t\t<print");
            outFile.write(printAttr("set", card.getSetName()));
            outFile.write(printAttr("version", card.getVersion()));
            outFile.write(printAttr("number", card.getNumber()));
            outFile.write(printAttr("rarity", card.getRarity()));
            outFile.write(printAttr("cost", card.getCost()));
            outFile.write(">\n");
            outFile.write(printElem("typeline", card.getTypeLine()));
            if (!StringUtil.isEmpty(card.getPower()))
                outFile.write("\t\t\t<pt power=\""+card.getPower()+"\" toughness=\""+card.getTough()+"\"/>\n");
            outFile.write(card.getRules().toString("\t\t\t<text>","</text>\n"));
            outFile.write(printElem("flavor", card.getFlavor()));
            outFile.write(printElem("artist", card.getArtist()));
            outFile.write("\t\t</print>\n");
            outFile.write("\t</card>\n");
        }
        
        outFile.write("</cards>\n");
		outFile.close();
    }
    
    private String printAttr(String attr, String value) {
        if (StringUtil.isEmpty(value))
            return "";
        else
            return (" "+attr+"=\""+value+"\"");
    }
    private String printElem(String elem, String value) {
        if (StringUtil.isEmpty(value))
            return "";
        else
            return ("\t\t\t<"+elem+">"+value+"</"+elem+">\n");
    }
    
}