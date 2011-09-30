/*
 * TabbedTextOutput.java
 *
 * Created on September 4, 2004, 8:07 AM
 */

package com.twentysix20.mtg.old.spoiler.output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

import com.twentysix20.mtg.old.spoiler.cardinfo.CardInfo;

/**
 *
 * @author  tpnr007
 */
public class TabbedTextOutput extends AbstractOutput {
    
    public TabbedTextOutput(String name, List list) {
        super(name,list);
    }
    public void generateFile() throws java.io.IOException {
		BufferedWriter outFile = new BufferedWriter(new FileWriter(getFileName()));
		Iterator cards = getCardList().iterator();

        outFile.write("Name\tFace\tFacePos\tVersion\tCost\tSupertype\tType\tSubtypes\tEnchantees\t"+
                      "Power\tTough\tText\tFlavor\tSet\tRarity\tArtist\tNumber\n");
        while (cards.hasNext()) {
            CardInfo card = (CardInfo)cards.next();
System.out.println(card.getName());
            outFile.write(card.getName()+"\t");
            outFile.write(card.getFace()+"\t");
            outFile.write(card.getFacePosition()+"\t");
            outFile.write(card.getVersion()+"\t");
            outFile.write(card.getCost()+"\t");
            outFile.write(card.getSupertype()+"\t");
            outFile.write(card.getTypes()+"\t");
            outFile.write(card.getSubtypes()+"\t");
            outFile.write(card.getEnchantees()+"\t");
            outFile.write(card.getPower()+"\t");
            outFile.write(card.getTough()+"\t");
            outFile.write(card.getRules().toString("; ")+"\t");
            outFile.write(card.getFlavor()+"\t");
            outFile.write(card.getSetName()+"\t");
            outFile.write(card.getRarityLetter()+"\t");
            outFile.write(card.getArtist()+"\t");
            outFile.write(card.getNumber()+"\t");
            outFile.write(card.getCostSpecific('X')+"\t");
            outFile.write(card.getCostGeneric()+"\t");
            outFile.write(card.getCostSpecific('W')+"\t");
            outFile.write(card.getCostSpecific('U')+"\t");
            outFile.write(card.getCostSpecific('B')+"\t");
            outFile.write(card.getCostSpecific('R')+"\t");
            outFile.write(card.getCostSpecific('G')+"\t");
            outFile.write("\n");
        }
		outFile.close();
    }
}
