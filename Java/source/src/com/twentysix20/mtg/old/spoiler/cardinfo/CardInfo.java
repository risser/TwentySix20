/*
 * CardInfo.java
 *
 * Created on September 4, 2004, 8:19 AM
 */

package com.twentysix20.mtg.old.spoiler.cardinfo;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.regex.Pattern;

import com.twentysix20.mtg.MtgConstants;
import com.twentysix20.util.StringList;
import com.twentysix20.util.StringUtil;

/**
 *
 * @author  tpnr007
 */
abstract public class CardInfo implements Cloneable {

    public static String[] attributeList = {"name","color","cost","type","pt","rules","flavor","artist","rarity","number","setName"};

    public static String ABILITYWORDS = "[Ff]lying|([Ff]irst|[Dd]ouble) [Ss]trike|[Tt]rample|[Pp]rotection [Ff]rom [\\w]+|[Vv]igilance|[Dd]efender|[Hh]orsemanship|[Hh]aste|[Ff]ear|[Pp]hasing";
    public static String KEYWORDS = "Affinity for|Splice|Soulshift|Cycling|Bushido|Morph|Threshold|Flashback|Rampage|Flanking|Cumulative [Uu]pkeep";
    public static String POWERWORDS = "As|Play";
    public static String IGNORE_COST = "((^|%)[^:]*)";
    public static Pattern ABILITYWORD_PATTERN = Pattern.compile("((^|\\.|%)[\\s]*)((("+ABILITYWORDS+")[,;\\s]*)+\\.?(@\\d@%)?)");
    public static Pattern KEYWORD_PATTERN = Pattern.compile(KEYWORDS);
    public static Pattern COST_PATTERN = Pattern.compile("[, '\\w\\d\\{\\}\\-]+:");
    public static Pattern PERCENT_PATTERN = Pattern.compile("\\s*%[\\s%]*");
    public static Pattern POWERWORD_PATTERN = Pattern.compile(IGNORE_COST+"(("+POWERWORDS+") [^\\.]*\\.(\\s)*(If [^\\.]*\\.)*)");
    public static Pattern DRAWCARD_PATTERN = Pattern.compile(IGNORE_COST+"(Draw a card\\.)");
        /*
        String COST_MATCH_STRING = ;
            } else if (workingLine.matches(ABILITYWORD_MATCH_STRING+"[\\s\\.].*")) {
            } else if (workingLine.matches("[^\"]*"+COST_MATCH_STRING+".*")) {
         */

//    public static StringList attributes = new StringList(attributeList);
    public static HashMap attributeSetter = new HashMap();
    static {
        Method[] methods = CardInfo.class.getMethods();
        for (int i = 0; i < attributeList.length; i++) {
            String setter = "set" + attributeList[i].substring(0,1).toUpperCase() 
                                  + attributeList[i].substring(1);
            boolean found = false;
            for (int j = 0; j < methods.length && !found; j++)
                if (methods[j].getName().equals(setter))
                {
                    attributeSetter.put(attributeList[i], methods[j]);
                    found = true;
                }
            if (!found) throw new IllegalStateException("No setter found for '"+attributeList[i]+"'.");
        }
    }
    
    private String cardName = "";
    private String nameOfSet = "";
    protected String cost = "";
    private String typeLine = "";
    private String supertype = "";
  	private StringList types = new StringList();
 	private StringList subtypes = new StringList();
 	private StringList enchantees = new StringList();
	private String rarity = "";
	private String power = "";
	private String tough = "";
    private StringBuffer flavor = new StringBuffer();
	private String artist = "";
	private String number = "";
    private String face = "";
    private String facePosition = "";
    private String version = "";
    
    private boolean append = false;
    private boolean inaQuote = false;
    private boolean findAPeriod = false;

    public CardInfo() {}
    public CardInfo(String name) {
        cardName = name;
    }
    
	public void setName (String value) {
        if (!StringUtil.isEmpty(cardName))
            throw new IllegalStateException("Can't assign name when name ("+cardName+") is already set.");
        if (value.matches(".* [ABCD]$"))
            value = value.substring(0,value.length()-2);

        // switch out ^ characters?  And AE?
        int p = value.indexOf("//");
        if (p > -1) {
            String[] ss = value.split("\\s*[\\(\\)]\\s*");
            if (ss.length > 1) {
                cardName = ss[0];
                face = ss[1];
                facePosition = ((value.indexOf(face) < p) ? "left" : "right");
                return;
            } 
        }
        String[] ss2 = value.split("\\s*[\\[\\]]\\s*");
        if (ss2.length > 1) {
            cardName = ss2[0];
            face = ss2[1];
            facePosition = (ss2[1].equals(ss2[0]) ? "top" : "bottom");
            return;
        } 
        
        cardName = value;
	}
    public String getName() {
        return cardName;
    }
    public void clearName() {
        cardName = null;
    }
    public String getFace() {
        return face;
    }
    public String getFacePosition() {
        return facePosition;
    }
    
    public String getVersion() {
        return version;
    }
    public void setVersion(String v) {
        version = v;
    }
    
	public void setCost (String value) {
        if ("N/A".equals(value.trim().toUpperCase()) || "LAND".equals(value.trim().toUpperCase()))
            value = "";
        if (!StringUtil.isEmpty(cost))
            throw new IllegalStateException("Can't assign cost ("+value+") for '"+cardName+"' when cost is already set.");
        if (value.length() > 15)
            throw new IllegalArgumentException("Cost value ("+value+") for '"+cardName+"' can't be longer than 15.");
        value = value.toUpperCase().replaceAll("[ \\{\\}]","");
        if (!value.matches("[WUBRGXYZ0123456789½/\\[\\]]*"))
            throw new IllegalArgumentException("Invalid characters in cost ("+value+") for '"+cardName+"'.");
		cost = value;
	}
    public String getCost() {
        return cost;
    }
    
    public String getCostGeneric() {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < cost.length(); i++)
            if (Character.isDigit(cost.charAt(i)))
                buf.append(cost.charAt(i));
        return buf.toString();
    }
    
    public String getCostSpecific(char c) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < cost.length(); i++)
            if (cost.charAt(i) == c)
                buf.append(cost.charAt(i));
        return ""+buf.length();
    }

    public void setType (String value) {
        if (!StringUtil.isEmpty(typeLine))
            throw new IllegalStateException("Can't assign types for '"+cardName+"' when typeline is already set.");
        typeLine = value;
        value = value.replaceAll(" \\-+ "," - ");
        boolean foundSupertype = false;
        do {
            foundSupertype = false;
            for (int i = 0; i < MtgConstants.SUPERTYPES.length && !foundSupertype; i++) {
                if (value.startsWith(MtgConstants.SUPERTYPES[i])) {
                    if (StringUtil.isEmpty(supertype))
                        supertype = MtgConstants.SUPERTYPES[i];
                    else
                        supertype += " " + MtgConstants.SUPERTYPES[i];
                    value = value.substring(MtgConstants.SUPERTYPES[i].length() + 1);
                    foundSupertype = true;
                }
            }
        } while (foundSupertype);
		if (value.startsWith(MtgConstants.ENCHANT + " ")) {
            // pull off the enchantees, replace with vanilla 'ENCHANTMENT'
            String[] e = value.split("[\\s]+");
            for (int i = 1; i < e.length; i++)
                enchantees.add(e[i].trim());
            int pos = value.indexOf(" - ");
            value = MtgConstants.TYPE_ENCHANTMENT + (pos < 0 ? "" : value.substring(pos));
		} 
//            String[] bigsplit = value.split("[^\\w\\s]+");
        String[] bigsplit = value.split(" - ");
        types.addAll(bigsplit[0].split(" "));
        if (bigsplit.length > 1)
            subtypes.addAll(bigsplit[1].split(" "));
    }
    public String getTypeLine() {
        // break into types/subtypes/supertypes on get
        return typeLine;
    }
/*    
    public void setTypes (StringList value)
	{
        types = value;
	}
    public void addType (String value) {
        types.add(value);
    }
    public void clearTypes() {
        types.clear();
    }
 */
    public StringList getTypes() {
        return types;
    }
   
    /*
	public void setSupertype (String value)
	{
		supertype = value;
	}
     */
    public String getSupertype() {
        return supertype;
    }
    
    /*
	public void setSubtypes (StringList value)
	{
		subtypes = value;
	}
    public void addSubtype (String value) {
        subtypes.add(value);
    }
    public void clearSubtypes() {
        subtypes.clear();
    }
     */
    public StringList getSubtypes() {
        return subtypes;
    }

    public StringList getEnchantees() {
        return enchantees;
    }

    
    public void setPt(String value) {
        if (value.length() > 7)
            throw new IllegalArgumentException("PT value ("+value+") for '"+cardName+"' can't be longer than 7.");
        if (StringUtil.isEmpty(value) || "N/A".equals(value.toUpperCase())) return;
        if (value.trim().equals("/")) return;
        String[] pt = value.split("/");        
        if (pt.length != 2) 
            throw new IllegalArgumentException("Incorrect format for PT value ("+value+") for '"+cardName+"'.");
        setPower(pt[0].trim());
        setTough(pt[1].trim());
    }
    
	public void setPower (String value) {
        if (value.length() > 3)
            throw new IllegalArgumentException("Power value ("+value+") for '"+cardName+"' can't be longer than 3.");
        if (!StringUtil.isEmpty(power))
            throw new IllegalStateException("Can't assign power for '"+cardName+"' when power is already set.");
		power = value;
	}
    public String getPower () {
        return power;
    }
    
	public void setTough (String value) {
        if (value.length() > 5)
            throw new IllegalArgumentException("Toughness value ("+value+") for '"+cardName+"' can't be longer than 5.");
        if (!StringUtil.isEmpty(tough))
            throw new IllegalStateException("Can't assign toughness for '"+cardName+"' when toughness is already set.");
		tough = value;
	}
    public String getTough () {
        return tough;
    }
    
	public void setRarity (String value) {
        if (StringUtil.isEmpty(getName())) return; 
        // if it's an empty name, chances are that it's header crap

        rarity = value;
	}
    public String getRarity () {
        String value = rarity.toUpperCase().trim();
        if (StringUtil.isEmpty(value) || value.indexOf("DIRT") > -1 || value.indexOf("LAND") > -1 || value.charAt(0) == 'L')
            return "land";
        if (value.charAt(0) == 'S')
            return "starter";
        if (value.indexOf("UNCOMMON") > -1 || value.charAt(0) == 'U')
            return "uncommon";
        if (value.indexOf("COMMON") > -1 || value.charAt(0) == 'C')
            return "common";
        if (value.indexOf("RARE") > -1 || value.charAt(0) == 'R')
            return "rare";
        throw new IllegalArgumentException("Rarity value ("+rarity+") for '"+cardName+"' is an unknown format.");
    }
    public String getRarityLetter() {
        String rare = getRarity();
        return rare.substring(0,1).toUpperCase();
    }
    
	public void setNumber(String value) {
        if (StringUtil.isEmpty(getName())) return; 
        // if it's an empty name, chances are that it's header crap

        if (!StringUtil.isEmpty(number))
            throw new IllegalStateException("Can't assign number for '"+cardName+"' when number is already set.");
        String[] s = value.split("/");
        value = s[0];
        if (value.length() > 3)
            throw new IllegalArgumentException("Final number value ("+value+") for '"+cardName+"' can't be longer than 3.");
		number = value;
	}
    public String getNumber() {
        return number;
    }

	public void setArtist (String value) {
        if (StringUtil.isEmpty(artist))
            artist = value;
        else
            artist += " " + value;
	}
    public String getArtist() {
        return artist;
    }

	public void setFlavor(String value) {
        if (flavor.length() > 0) flavor.append(" ");
		flavor.append(value.trim());
	}
    public String getFlavor() {
        return flavor.toString();
    }
    
    public void setColor(String value) {
        // do nothing
    }
    
    public void setSetName(String value) {
        nameOfSet = value;
    }
    public String getSetName() {
        return nameOfSet;
    }

	abstract public void setRules(String value);
    abstract public StringList getRules();

    public void setAttribute(String attr, String value) {
        if (!attributeSetter.containsKey(attr)) 
            System.out.println("*** FAULTY ATTRIBUTE ("+attr+"): no match found for card '"+this.getName()+"'.");
        value = value.replaceAll("[\\x96\\x97\\u2014]", "-");
        value = value.replaceAll("[\\x9c\\x9d\\u201c\\u201d]", "\"");
        value = value.replaceAll("[\\x98\\x99\\u2018\\u2019]", "'");
        value = value.replaceAll("\\xa0", " ");
        Method m = (Method)attributeSetter.get(attr);
        try { 
            m.invoke(this, new String[]{value}); 
        } catch (Exception iae) { throw new RuntimeException(iae); }
    }

    public Object clone() {
        CardInfo card;
        try {
            card = (CardInfo)super.clone();
        } catch (CloneNotSupportedException cnse) {
            return null;
        }
        card.flavor = new StringBuffer(this.flavor.toString());
        return card;
    }
}
