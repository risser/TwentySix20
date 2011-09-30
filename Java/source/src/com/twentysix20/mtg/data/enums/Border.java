/******************************************************************************
* Created on Mar 23, 2006
*/
package com.twentysix20.mtg.data.enums;

public class Border {

    // Borders
    static final public Border BORDER_BLACK = new Border("Black");
    static final public Border BORDER_WHITE = new Border("White");
    static final public Border BORDER_SILVER = new Border("Silver");

    private String color;

    private Border(String color) {
        this.color = color;
    }
    
    public String getColor() {
        return color;
    }
}
