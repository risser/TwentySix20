/******************************************************************************
*
* Proprietary and Confidential
* Ohio Farmers Insurance Company
*
* This document contains material which is the proprietary and confidential
* property of Ohio Farmers Insurance Company.
*
* The right to view, reproduce, modify, distribute, or in any way display
* this work is prohibited without the express written consent of the Ohio
* Farmers Insurance Company.
*
* Copyright (c) 2006 Ohio Farmers Insurance Company
* All rights reserved.
*
* Created on Apr 22, 2006
*/
package com.twentysix20.mtg.data.mana;

import java.util.*;

import com.twentysix20.mtg.MtgConstants;
import com.twentysix20.util.StringUtil;

public class ManaCostFactory {
    private static final String HALF_WHITE = "W\\s?\\(HALF\\)";
    private static final String EMPTY_COST = "\\{\\s+\\}";
    private static final String HYBRID = "\\([WUBRG2]\\/[WUBRG]\\)";
    private static final String PHYREXIAN = "\\([WUBRG]P\\)";
    private static final String STANDARD_COST = "((\\{?[[01234567890,]+|WUBRGXYZ]\\}?)|("+HYBRID+")|("+PHYREXIAN+"))+";
    
    public static final String VALID_COST_LINE = STANDARD_COST+"|"+STANDARD_COST+"\\s?//\\s?"+STANDARD_COST+"|"+EMPTY_COST+"|"+HALF_WHITE;
    public static final String VALID_COST = "X*Y*Z*[0123456789,]*([WUBRG]+|("+HYBRID+")|("+PHYREXIAN+"))*|½W"; // ½ /\\[\\]
    private static Map symbolCache = new HashMap();

    private ManaCostFactory() {}

    static public ManaCost create(String s) {
        s = s.toUpperCase().replaceAll("[ \\{\\}]","");
        if (StringUtil.isEmpty(s)) // if it's { } (evermind), leave it costless
            return Costless.getInstance();
        if (HalfWhiteCost.HALF_WHITE_ORACLE.equals(s) || MtgConstants.SYMBOL_HALF_WHITE.equals(s))
            return HalfWhiteCost.getInstance();
//        if ("N/A".equals(cost.trim().toUpperCase()) || "LAND".equals(cost.trim().toUpperCase()))
//            cost = "";
//        if (StringUtil.isEmpty(cost)) throw new IllegalArgumentException("Can't create cost with null or blank string: ("+cost+").");
        if (!s.matches(VALID_COST))
            throw new IllegalArgumentException("Invalid characters in cost ("+s+").");
        s = s.replaceAll(",","");
        String numStr = "";
        ManaCost cost = new ManaCost();
        while (s.indexOf('/') > 0) {
            int p = s.indexOf('/');
            String hybrid = s.substring(p-2, p+3);
            cost.addCost(getSymbol(hybrid));
            s = s.substring(0, p-2) + s.substring(p+3);
        }
        while (s.indexOf('P') > 0) {
            int p = s.indexOf('P');
            String phyrexian = s.substring(p-2, p+2);
            cost.addCost(getSymbol(phyrexian));
            s = s.substring(0, p-2) + s.substring(p+2);
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c))
                numStr += c;
            else
                cost.addCost(getSymbol(s.substring(i, i+1)));
        }
        if (!StringUtil.isEmpty(numStr))
            cost.setGeneric(numStr);
        
        return cost;
    }

    static ManaSymbol getSymbol(String s) {
        if ("(G/B)".equals(s) ||
            "(R/B)".equals(s) ||
            "(U/G)".equals(s) ||
            "(W/G)".equals(s) ||
            "(G/R)".equals(s) ||
            "(W/R)".equals(s) ||
            "(B/U)".equals(s) ||
            "(R/U)".equals(s) ||
            "(U/W)".equals(s) ||
            "(B/W)".equals(s))
            s = "("+s.charAt(3)+"/"+s.charAt(1)+")";
        ManaSymbol symbol = (ManaSymbol)symbolCache.get(s);
        if (symbol == null) {
            if (MtgConstants.SYMBOL_WHITE.equals(s))
                symbol = new ManaSymbolWhite();
            else if (MtgConstants.SYMBOL_BLUE.equals(s))
                symbol = new ManaSymbolBlue();
            else if (MtgConstants.SYMBOL_BLACK.equals(s))
                symbol = new ManaSymbolBlack();
            else if (MtgConstants.SYMBOL_RED.equals(s))
                symbol = new ManaSymbolRed();
            else if (MtgConstants.SYMBOL_GREEN.equals(s))
                symbol = new ManaSymbolGreen();
            else if (MtgConstants.SYMBOL_HYBRID_BG.equals(s))
                symbol = new ManaSymbolHybridBG();
            else if (MtgConstants.SYMBOL_HYBRID_BR.equals(s))
                symbol = new ManaSymbolHybridBR();
            else if (MtgConstants.SYMBOL_HYBRID_GU.equals(s))
                symbol = new ManaSymbolHybridGU();
            else if (MtgConstants.SYMBOL_HYBRID_GW.equals(s))
                symbol = new ManaSymbolHybridGW();
            else if (MtgConstants.SYMBOL_HYBRID_RG.equals(s))
                symbol = new ManaSymbolHybridRG();
            else if (MtgConstants.SYMBOL_HYBRID_RW.equals(s))
                symbol = new ManaSymbolHybridRW();
            else if (MtgConstants.SYMBOL_HYBRID_UB.equals(s))
                symbol = new ManaSymbolHybridUB();
            else if (MtgConstants.SYMBOL_HYBRID_UR.equals(s))
                symbol = new ManaSymbolHybridUR();
            else if (MtgConstants.SYMBOL_HYBRID_WU.equals(s))
                symbol = new ManaSymbolHybridWU();
            else if (MtgConstants.SYMBOL_HYBRID_WB.equals(s))
                symbol = new ManaSymbolHybridWB();
            else if (MtgConstants.SYMBOL_HYBRID_2W.equals(s))
                symbol = new ManaSymbolHybrid2W();
            else if (MtgConstants.SYMBOL_HYBRID_2U.equals(s))
                symbol = new ManaSymbolHybrid2U();
            else if (MtgConstants.SYMBOL_HYBRID_2B.equals(s))
                symbol = new ManaSymbolHybrid2B();
            else if (MtgConstants.SYMBOL_HYBRID_2R.equals(s))
                symbol = new ManaSymbolHybrid2R();
            else if (MtgConstants.SYMBOL_HYBRID_2G.equals(s))
                symbol = new ManaSymbolHybrid2G();
            else if (MtgConstants.SYMBOL_PHYREXIAN_WHITE.equals(s))
                symbol = new ManaSymbolPhyrexianWhite();
            else if (MtgConstants.SYMBOL_PHYREXIAN_BLUE.equals(s))
                symbol = new ManaSymbolPhyrexianBlue();
            else if (MtgConstants.SYMBOL_PHYREXIAN_BLACK.equals(s))
                symbol = new ManaSymbolPhyrexianBlack();
            else if (MtgConstants.SYMBOL_PHYREXIAN_RED.equals(s))
                symbol = new ManaSymbolPhyrexianRed();
            else if (MtgConstants.SYMBOL_PHYREXIAN_GREEN.equals(s))
                symbol = new ManaSymbolPhyrexianGreen();
            else if (MtgConstants.SYMBOL_X.equals(s))
                symbol = new ManaSymbolX();
            else if (MtgConstants.SYMBOL_Y.equals(s))
                symbol = new ManaSymbolY();
            else if (MtgConstants.SYMBOL_Z.equals(s))
                symbol = new ManaSymbolZ();
            else
                throw new IllegalArgumentException("Can't make heads or tails of '"+s+"'.  Can't turn it into a mana cost.");
            symbolCache.put(s, symbol);
        }
        return symbol;
    }
}
