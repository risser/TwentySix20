/*
 * XmlOracleOutput.java
 *
 * Created on September 4, 2004, 8:07 AM
 */

package com.twentysix20.mtg.old.spoiler.output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

import com.twentysix20.mtg.old.spoiler.cardinfo.CardInfo;
import com.twentysix20.util.StringUtil;

/**
 *
 * @author  tpnr007
 */
public class XmlOracleOutput extends AbstractOutput {
    
    public XmlOracleOutput(String name, List list) {
        super(name,list);
    }
    public void generateFile() throws java.io.IOException {
		BufferedWriter outFile = new BufferedWriter(new FileWriter(getFileName()));
        outFile.write("<cards type=\"Magic: The Gathering\">\n");
		Iterator cards = getCardList().iterator();
        while (cards.hasNext()) {
            CardInfo card = (CardInfo)cards.next();
System.out.println(":"+card.getName());
            outFile.write("\t<card name=\""+card.getName()+"\"");
            outFile.write(printAttr("face",card.getFace()));
            outFile.write(printAttr("facepos",card.getFacePosition()));
            outFile.write(">\n");
            outFile.write("\t\t<oracle>\n");
            if (!StringUtil.isEmpty(card.getCost())) {
                outFile.write("\t\t\t<cost");
                outFile.write(printAttr("generic", card.getCostGeneric()));
                outFile.write(printCost("white", card.getCostSpecific('W')));
                outFile.write(printCost("blue", card.getCostSpecific('U')));
                outFile.write(printCost("black", card.getCostSpecific('B')));
                outFile.write(printCost("red", card.getCostSpecific('R')));
                outFile.write(printCost("green", card.getCostSpecific('G')));
                outFile.write(printCost("X", card.getCostSpecific('X')));
                outFile.write(printCost("Y", card.getCostSpecific('Y')));
                outFile.write(printCost("Z", card.getCostSpecific('Z')));
                outFile.write("/>\n");
            }
            outFile.write(printElem("supertype",card.getSupertype()));
            outFile.write(card.getTypes().toString("\t\t\t<type>","</type>\n"));
            outFile.write(card.getEnchantees().toString("\t\t\t<enchantee>","</enchantee>\n"));
            outFile.write(card.getSubtypes().toString("\t\t\t<subtype>","</subtype>\n"));
            if (!StringUtil.isEmpty(card.getPower()))
                outFile.write("\t\t\t<pt power=\""+card.getPower()+"\" toughness=\""+card.getTough()+"\"/>\n");
            outFile.write(card.getRules().toString("\t\t\t<ability>","</ability>\n"));
            outFile.write("\t\t</oracle>\n");
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
    private String printCost(String attr, String value) {
        if (StringUtil.isEmpty(value) || "0".equals(value))
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
