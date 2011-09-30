/******************************************************************************
* Created on Mar 23, 2006
*/
package com.twentysix20.mtg.data.enums;


public class Colorless extends Color {
    private static Colorless self = new Colorless();
    public static Colorless getInstance() {
        return self;
    }
    
    private Colorless() {}
    
    public boolean equals(Object other) {
        return (other instanceof Colorless);
    }
    
    public String toString() {
        return "";
    }
    
    public String getName() {
        throw new UnsupportedOperationException("Colorless has no name.");
    }
    
    public String getCode() {
        throw new UnsupportedOperationException("Colorless has no code.");
    }
    
}
