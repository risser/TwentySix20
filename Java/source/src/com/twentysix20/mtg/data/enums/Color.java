/******************************************************************************
* Created on Mar 23, 2006
*/
package com.twentysix20.mtg.data.enums;

import com.twentysix20.util.StringUtil;

public class Color {

    static final public Color WHITE = new Color("White", "W");
    static final public Color BLUE = new Color("Blue", "U");
    static final public Color BLACK = new Color("Black", "B");
    static final public Color RED = new Color("Red", "R");
    static final public Color GREEN = new Color("Green", "G");
    static final public Color COLORLESS = Colorless.getInstance();
    
    private String name;
    private String codeLetter;

    protected Color() {
        name = "";
        codeLetter = " ";
    }
    
    private Color(String name, String code) {
        if (StringUtil.isEmpty(name)) throw new IllegalArgumentException("Name can't be blank or null.");
        if (StringUtil.isEmpty(code)) throw new IllegalArgumentException("Code can't be blank or null.");
        if (code.length() != 1) throw new IllegalArgumentException("Code must be exactly one letter.");
        this.name = name;
        this.codeLetter = code;
    }

    public String getName() {
        return name;
    }
    
    public String getCode() {
        return codeLetter;
    }

    static Color getColor(String code) {
        if (StringUtil.isEmpty(code)) throw new IllegalArgumentException("Code can't be blank or null.");
        if (code.length() != 1) throw new IllegalArgumentException("Code must be exactly one letter.");
        switch (code.charAt(0)) {
            case 'B' : return BLACK;
            case 'U' : return BLUE;
            case 'R' : return RED;
            case 'G' : return GREEN;
            case 'W' : return WHITE;
            default : throw new IllegalArgumentException("Code ("+code+") not recognized.");
        }
    }
}
