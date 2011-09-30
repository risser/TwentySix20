/*
 * OracleParser.java
 *
 * Created on October 6, 2004, 11:22 PM
 */

package com.twentysix20.mtg.old.spoiler.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.twentysix20.mtg.MtgConstants;
import com.twentysix20.mtg.old.spoiler.cardinfo.CardInfo;
import com.twentysix20.mtg.old.spoiler.cardinfo.OracleCardInfo;
import com.twentysix20.util.StringUtil;

/**
 * @author  Peter Risser
 */
public class OracleTextParser extends AbstractParser {
    public final static int NAME = 0;
    public final static int COST = 1;
    public final static int TYPE = 2;
    public final static int PT = 3;
    public final static int RULES = 4;
    
    CardInfo currentCard;
    List splitLines;
    List flipLines;
    int state = NAME;

    public OracleTextParser(String name) throws IOException {
        super(name);
    }
    
    protected BufferedReader getReader(String name) throws IOException {
        return new BufferedReader(new FileReader(name));
    }

    protected void initialize() {
    }

    protected void readInitializationInformation() throws IOException {
    }

    protected String cleanUp(String line) {
        line = line.trim().replace('”','"').replace('“','"').replace('’','\'').replace('—','-').replaceAll("Æ","Ae");
        line = line.replaceAll("\\{1/2\\}","½");
        line = line.replaceAll("\\(w/b\\)","[WB]");
        line = line.replaceAll("\\(u/r\\)","[UR]");
        line = line.replaceAll("\\(r/w\\)","[RW]");
        line = line.replaceAll("\\(u/b\\)","[UB]");
        line = line.replaceAll("\\(b/g\\)","[BG]");
        line = line.replaceAll("\\(r/g\\)","[RG]");
        line = line.replaceAll("\\(g/w\\)","[GW]");
        return line;
    }
    
    protected void parseLine(String line) {
        line = cleanUp(line);
        if ("".equals(line))
            finishWithCard();
        else {
            handleAttributeLine(line);
        }
    }
    
    private void finishWithCard() {
        if ((state != RULES) && (currentCard != null) && (currentCard.getTypeLine() != null) && (currentCard.getTypeLine().indexOf("Land") < 0))
            throw new IllegalStateException("Can't finish with card ("+currentCard.getName()+") if there's no rule text or P/T. (state="+state+")");
        if (flipLines != null) {
            String name = currentCard.getName();

            List tempHoldFlipLines = flipLines;
            flipLines = null;
            splitLines = null;

            Iterator itrLines = tempHoldFlipLines.iterator();
            String otherName = null;
            if (name.equals("Curse of the Fire Penguin"))
                otherName = "Fire Penguin";
            else
                otherName = (String)itrLines.next();

            String cost = currentCard.getCost();
            currentCard.clearName();
            currentCard.setName(name);
//            currentCard.setName(name + " ["+name+"]");
            currentCard.setRules("[Flips to: "+otherName+"]");
            storeCurrentCard();
            currentCard = null;

            handleAttributeLine(otherName);
//            handleAttributeLine(name + " ["+otherName+"]");
            currentCard.setCost(cost);
            while (itrLines.hasNext())
                handleAttributeLine((String)itrLines.next());
            currentCard.setRules("[Flips from: "+name+"]");
        } else if (splitLines != null) {
            String bigName = currentCard.getName();
            String[] littleNames = bigName.split("\\s*/+\\s*");
            String bigCost = currentCard.getCost();
            String bigType = currentCard.getTypeLine();
            String bigRules = currentCard.getRules().toString("; ");
            
            // this is totally stupid.  Oh well.
            currentCard = new OracleCardInfo();
            currentCard.setName(littleNames[0]);
            currentCard.setCost(bigCost);
            currentCard.setType(bigType);
            currentCard.setRules(bigRules);
            storeCurrentCard();

            for (int i = 1; i < littleNames.length; i++) {
                List tempList = splitLines;
                currentCard = null;
                splitLines = null;
                flipLines = null;
                handleAttributeLine(littleNames[i]);
//                handleAttributeLine(bigName + " ("+littleNames[i]+")");
                Iterator itrLines = tempList.iterator();
                while (itrLines.hasNext()) {
                    String line = (String)itrLines.next();
                    handleAttributeLine(line);
                }
                bigCost = bigCost + " // " + currentCard.getCost();
                bigRules = bigRules + " // " + currentCard.getRules().toString("; ");
                storeCurrentCard();
            }
/*            
            for (int i = 1; i < littleNames.length; i++) {
                storeCurrentCard();
                currentCard = null;
                List tempList = splitLines;
                splitLines = null;
                flipLines = null;
                handleAttributeLine(bigName + " ("+littleNames[i]+")");
                Iterator itrLines = tempList.iterator();
                while (itrLines.hasNext()) {
                    String line = (String)itrLines.next();
                    handleAttributeLine(line);
                }
            }
            */
            currentCard = null;
            splitLines = null;
            flipLines = null;
            
            currentCard = new OracleCardInfo();
            currentCard.setName(bigName);
            currentCard.setCost(bigCost);
            currentCard.setType(bigType);
            currentCard.setRules(bigRules);
        }

        storeCurrentCard();
        currentCard = null;
        splitLines = null;
        flipLines = null;
    }

    private void storeCurrentCard() {
        if ((currentCard != null) && !StringUtil.isEmpty(currentCard.getName()))
            this.storeCard(currentCard);
    }
    
    protected void lastCard() {
        storeCurrentCard();
    }
    
    private void handleAttributeLine(String line) {
        if (currentCard == null) {
            currentCard = new OracleCardInfo();
//            currentCard.setSetName(getSet());
            state = NAME;
        }
        switch (state) {
            case NAME : 
                currentCard.setName(line);
                state++;
                break;
            case COST : 
                state++;
                line = handleSplit(line);
//                if (StringUtil.contains(line, "{")) {
                if ("Little Girl".equals(currentCard.getName())) {
                    currentCard.setCost("½W");
                    break;
                } else if (line.toUpperCase().matches("[{}XYZWUBRG0123456789/, ]+") || line.toUpperCase().matches("[0123456789X]*(\\[[WUBRG]{2}\\])+")) {
                    currentCard.setCost(line);
                    break;
                } // note that if there's no cost (land), it falls through to TYPE, which is what we want.
            case TYPE : 
                state++;
                line = handleSplit(line);
                if (line.startsWith("Summon "))
                    line = "Creature - " + line.substring(7);
                if (line.equals("Interrupt"))
                    line = "Instant";
                boolean good = false;
                for (int i = 0; i < MtgConstants.TYPES.length; i++)
                    if (StringUtil.contains(line,MtgConstants.TYPES[i]))
                        good = true;
                good = good || StringUtil.contains(line, "Eaturecray");
                good = good || StringUtil.contains(line, "Scariest");
                good = good || StringUtil.contains(line, "Enchant");
                if (!good) throw new IllegalStateException("Card ("+currentCard.getName()+") can't have a type of '"+line+"'.");
                
                currentCard.setType(line);
                break;
            case PT   : 
                state++;
                String[] ss = line.split("/");
                if (ss.length == 2) {
                    if ((ss[0].length() <= 3) && (ss[1].length() <= 3)) {
                        currentCard.setPt(line);
                        break;
                    }
                }
            case RULES : 
                line = handleSplit(line);
                if (flipLines != null)
                    flipLines.add(line); 
                else if ("-----".equals(line)) {
                    flipLines = new ArrayList();
                } else
                    currentCard.setRules(line);
        }
    }
    
    private String handleSplit(String line) {
        int p = line.indexOf("//");
        if (p == -1) return line;

        String theRest = line.substring(p+2).trim();
        if (splitLines == null)
            splitLines = new ArrayList();
        splitLines.add(theRest);
        
        line = line.substring(0,p-1).trim();
        return line;
    }

    public static void main(String args[]) throws Exception {
		OracleTextParser t = new OracleTextParser("/Risser/MTG/spoilers/oracle-promo.txt"); 
        System.out.println("Done.");
	}
}
