/*
 * Created on October 21, 2004, 6:47 PM
 */

package com.twentysix20.mtg.data.mana;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.Bag;
import org.apache.commons.collections.HashBag;

import com.twentysix20.mtg.MtgConstants;

/**
 *
 * @author  tpnr007
 */
public class ManaCost {
    
    Bag bag = new HashBag();
    private int generic = 0;
    private String stringRepresentation = null;

    static List<ManaSymbol> xSymbols = Arrays.asList(new ManaSymbol[] {
            ManaCostFactory.getSymbol(MtgConstants.SYMBOL_X),
            ManaCostFactory.getSymbol(MtgConstants.SYMBOL_Y), 
            ManaCostFactory.getSymbol(MtgConstants.SYMBOL_Z)});
    static List<ManaSymbol> hybridSymbols = Arrays.asList(new ManaSymbol[] {
            ManaCostFactory.getSymbol(MtgConstants.SYMBOL_HYBRID_WU),
            ManaCostFactory.getSymbol(MtgConstants.SYMBOL_HYBRID_WB), 
            ManaCostFactory.getSymbol(MtgConstants.SYMBOL_HYBRID_2W), 
            ManaCostFactory.getSymbol(MtgConstants.SYMBOL_HYBRID_2U), 
            ManaCostFactory.getSymbol(MtgConstants.SYMBOL_HYBRID_2B), 
            ManaCostFactory.getSymbol(MtgConstants.SYMBOL_HYBRID_2R), 
            ManaCostFactory.getSymbol(MtgConstants.SYMBOL_HYBRID_2G), 
            ManaCostFactory.getSymbol(MtgConstants.SYMBOL_HYBRID_UB),
            ManaCostFactory.getSymbol(MtgConstants.SYMBOL_HYBRID_UR), 
            ManaCostFactory.getSymbol(MtgConstants.SYMBOL_HYBRID_BR),
            ManaCostFactory.getSymbol(MtgConstants.SYMBOL_HYBRID_BG), 
            ManaCostFactory.getSymbol(MtgConstants.SYMBOL_HYBRID_RG),
            ManaCostFactory.getSymbol(MtgConstants.SYMBOL_HYBRID_RW), 
            ManaCostFactory.getSymbol(MtgConstants.SYMBOL_HYBRID_GW),
            ManaCostFactory.getSymbol(MtgConstants.SYMBOL_HYBRID_GU)});
    static List<ManaSymbol> phyrexianSymbols = Arrays.asList(new ManaSymbol[] {
            ManaCostFactory.getSymbol(MtgConstants.SYMBOL_PHYREXIAN_WHITE),
            ManaCostFactory.getSymbol(MtgConstants.SYMBOL_PHYREXIAN_BLUE), 
            ManaCostFactory.getSymbol(MtgConstants.SYMBOL_PHYREXIAN_BLACK),
            ManaCostFactory.getSymbol(MtgConstants.SYMBOL_PHYREXIAN_RED), 
            ManaCostFactory.getSymbol(MtgConstants.SYMBOL_PHYREXIAN_GREEN)});
    static List<ManaSymbol> manaSymbols = Arrays.asList(new ManaSymbol[] {
            ManaCostFactory.getSymbol(MtgConstants.SYMBOL_WHITE),
            ManaCostFactory.getSymbol(MtgConstants.SYMBOL_BLUE), 
            ManaCostFactory.getSymbol(MtgConstants.SYMBOL_BLACK),
            ManaCostFactory.getSymbol(MtgConstants.SYMBOL_RED), 
            ManaCostFactory.getSymbol(MtgConstants.SYMBOL_GREEN),
            ManaCostFactory.getSymbol(MtgConstants.SYMBOL_WHITE), 
            ManaCostFactory.getSymbol(MtgConstants.SYMBOL_BLUE),
            ManaCostFactory.getSymbol(MtgConstants.SYMBOL_BLACK), 
            ManaCostFactory.getSymbol(MtgConstants.SYMBOL_RED),
            ManaCostFactory.getSymbol(MtgConstants.SYMBOL_GREEN)});

    //    WUBRGWUBRG
    
    ManaCost() {} // only the factory can create one.

    public String toString() {
        if (stringRepresentation == null) {
            StringBuffer buf = new StringBuffer("");
            listToBuffer(buf, xSymbols);
            if ((generic > 0) || (bag.size() == 0)) 
                buf.append(generic);
            List<ManaSymbol> shortList = findShortestList();
            listToBuffer(buf, shortList);
            listToBuffer(buf, hybridSymbols);
            listToBuffer(buf, phyrexianSymbols);
            stringRepresentation = buf.toString();
        }
        return stringRepresentation;
    }

    private void listToBuffer(StringBuffer buf, List<ManaSymbol> list) {
        Iterator<ManaSymbol> itrList = list.iterator();
        while (itrList.hasNext()) {
            ManaSymbol symbol = (ManaSymbol)itrList.next();
            int count = bag.getCount(symbol);
            for (int i = 0; i < count; i++)
                buf.append(symbol.toString());
        }
    }

    private List<ManaSymbol> findShortestList() {
        List<ManaSymbol> list = manaSymbols.subList(0, 5);
        Set<ManaSymbol> unique = new HashSet<ManaSymbol>(bag.uniqueSet());
        unique.removeAll(hybridSymbols);
        unique.removeAll(phyrexianSymbols);
        unique.removeAll(xSymbols);
        if (unique.size() == 0) return list;

        Iterator<ManaSymbol> itrUnique = unique.iterator();
        while (itrUnique.hasNext()) {
            ManaSymbol baseSymbol = (ManaSymbol)itrUnique.next();
            int basePos = manaSymbols.indexOf(baseSymbol);
            int lastPos = 0;
            Iterator<ManaSymbol> itrU2 = unique.iterator();
            while (itrU2.hasNext()) {
                ManaSymbol secondSymbol = (ManaSymbol)itrU2.next();
                if (secondSymbol.equals(baseSymbol)) continue;
                int pos2 = manaSymbols.subList(basePos, manaSymbols.size()).indexOf(secondSymbol);
                if (pos2 > lastPos)
                    lastPos = pos2;
            }
            List<ManaSymbol> sub = manaSymbols.subList(basePos, basePos + lastPos + 1);
            if (sub.size() < list.size())
                list = sub;
        }
        return list;
    }

    public boolean equals(Object other) {
        if (other == null) return false;
        if (!(other instanceof ManaCost)) throw new IllegalArgumentException("Can't check equals against an object that isn't a ManaCost.");
        String me = this.toString();
        String you = other.toString();
        return me.equals(you);
    }

    public boolean isCostless() {
        return (this instanceof Costless);
    }

    public static Costless costless() {
        return Costless.getInstance();
    }

    void addCost(ManaSymbol cost) {
        if (cost == null)
            throw new IllegalArgumentException("Can't add null cost.");
        bag.add(cost);
        stringRepresentation = null;
    }

    public void setGeneric(String numStr) {
        setGeneric(Integer.parseInt(numStr));
    }

    public void setGeneric(int gen) {
        generic = gen;
        stringRepresentation = null;
    }

    public int convertedCost() {
        List<ManaSymbol> list = new ArrayList<ManaSymbol>(bag);
        list.removeAll(xSymbols);
        int size = list.size();
        return generic + size;
    }
}



