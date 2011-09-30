package com.twentysix20.mtg.articleparser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Entry {
    private boolean matchFlag = false;
    private String actualString = null;
    private String sortString = null;
    private HashMap bigStorage = new HashMap();
    private boolean root = false;


    public Entry() {
        root = true;
    }
    public Entry(String s) {
        if (s == null) throw new IllegalArgumentException("Cannot create an Entry with a null string.");
        if ("".equals(s.trim())) throw new IllegalArgumentException("Cannot create an Entry with an empty or all-whitespace string.");
        setActualString(s);
        setSortString(Entry.convertToSortString(s));
    }

    public void add(List strs) {
        if (strs == null) throw new IllegalArgumentException("Cannot add a null List.");
        if (strs.size() == 0)
            setMatchFlag();
        else {
            if (!(strs.get(0) instanceof String)) throw new IllegalArgumentException("List ("+strs+") must contain only Strings.");
            String theString = (String)strs.get(0);
            String sortString = Entry.convertToSortString(theString);
            Entry e;
            if (bigStorage.containsKey(sortString)) {
                e = (Entry)bigStorage.get(sortString);
            } else {
                e = new Entry(theString);
                bigStorage.put(e.getSortString(), e);
            }
            e.add(strs.subList(1, strs.size()));
        }
    }
    public void add(String[] strs) {
        if (strs == null) throw new IllegalArgumentException("Cannot add a null Array.");
        ArrayList list = new ArrayList();
        for (int i = 0; i < strs.length; i++)
            list.add(strs[i]);
        add(list);
    }

    public String find(List strs) {
        if (strs == null) throw new IllegalArgumentException("Cannot find a null List.");
        if (strs.size() == 0)
            return isMatch() ? getActualString() : null;
        else {
            if (!(strs.get(0) instanceof String)) throw new IllegalArgumentException("List ("+strs+") must contain only Strings.");
            String searchStr = Entry.convertToSortString((String)strs.get(0));
            if (!bigStorage.containsKey(searchStr))
                return null;
            else {
                Entry e = (Entry)bigStorage.get(searchStr);
                String foundStr = e.find(strs.subList(1, strs.size()));
                boolean isHyphenated = (foundStr == null ? false : (foundStr.charAt(0) == '-'));
                return (foundStr == null ? null : (isRoot() ? "" : getActualString() + (isHyphenated ? "" : " ")) + foundStr);
            }
        }
    }
    public String find(String[] strs) {
        if (strs == null) throw new IllegalArgumentException("Cannot add a null Array.");
        ArrayList list = new ArrayList();
        for (int i = 0; i < strs.length; i++)
            list.add(strs[i]);
        return find(list);
    }
    
    public boolean isMatch() {
        return matchFlag;
    }
    public void setMatchFlag() {
        matchFlag = true;
    }
    public void clearMatchFlag() {
        matchFlag = false;
    }

    public String getActualString() {
        return actualString;
    }
    private void setActualString(String s) {
        actualString = s;
    }

    public String getSortString() {
        return sortString;
    }
    private void setSortString(String s) {
        sortString = s;
    }
    
    public boolean isRoot() {
        return root;
    }

    public static String convertToSortString(String s) {
        if (s == null) throw new IllegalArgumentException("Cannot convert null string. ("+s+")");
        if ("".equals(s.trim())) throw new IllegalArgumentException("Cannot convert empty string. ("+s+")");
        StringBuffer buf = new StringBuffer(s.toUpperCase());
        int i = 0;
        while (i < buf.length()) {
            char c = buf.charAt(i);
            // delete anything that's not a letter
            if (!Character.isLetter(c) && c != '\'')
                buf.deleteCharAt(i);
            else {
                // if it's A..Z, leave it alone; 
                if ((c < 'A') || (c > 'Z')) {
                    switch (c) {
                        case '\'' : break; // ignore apostrophes
                        case 'À' : 
                        case 'Á' : 
                        case 'Â' : 
                        case 'Ã' : 
                        case 'Ä' : 
                        case 'Å' : 
                            buf.setCharAt(i, 'A'); break;
                        case 'Ç' : 
                            buf.setCharAt(i, 'C'); break;
                        case 'È' : 
                        case 'É' : 
                        case 'Ê' : 
                        case 'Ë' : 
                            buf.setCharAt(i, 'E'); break;
                        case 'Ì' : 
                        case 'Í' : 
                        case 'Î' : 
                        case 'Ï' : 
                            buf.setCharAt(i, 'I'); break;
                        case 'Ñ' : 
                            buf.setCharAt(i, 'N'); break;
                        case 'Ò' : 
                        case 'Ó' : 
                        case 'Ô' : 
                        case 'Õ' : 
                        case 'Ö' : 
                        case 'Ø' : 
                            buf.setCharAt(i, 'O'); break;
                        case 'Ù' : 
                        case 'Ú' : 
                        case 'Û' : 
                        case 'Ü' : 
                            buf.setCharAt(i, 'U'); break;
                        case 'Ý' : 
                            buf.setCharAt(i, 'Y'); break;
                        case 'ß' : 
                            buf = buf.deleteCharAt(i).insert(i,"SS"); break;
                        case 'Æ' : 
                            buf = buf.deleteCharAt(i).insert(i,"AE"); break;
                        default :
                            throw new IllegalArgumentException("Unrecognized character: '"+c+"'.");
                    }
                }
                i++;
            }
        }
        return buf.toString();
    }
}