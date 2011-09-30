/*
 * AbstractOutput.java
 *
 * Created on September 28, 2004, 12:35 PM
 */

package com.twentysix20.mtg.old.spoiler.output;

import java.io.IOException;
import java.util.List;

import com.twentysix20.util.StringUtil;

/**
 *
 * @author  tpnr007
 */
abstract public class AbstractOutput {
    
    private String fileName;
    private List cardList;

    public AbstractOutput(String name, List list) {
        if (StringUtil.isEmpty(name)) throw new IllegalArgumentException("Can't pass an empty name.");
        if (list == null) throw new IllegalArgumentException("Can't pass an empty card list.");
        fileName = name;
        cardList = list;
    }
    
    public String getFileName() {
        return fileName;
    }
    
    public List getCardList() {
        return cardList;
    }
    
    abstract public void generateFile() throws IOException;
    
}
