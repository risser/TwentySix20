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
* Created on Mar 24, 2006
*/
package com.twentysix20.mtg.data.type;

import com.twentysix20.util.StringList;


public class Typeless extends Type {

    private static Typeless self = new Typeless();
    public static Typeless getInstance() {
        return self;
    }
    
    public String toString() {
        return "";
    }
    
    public boolean equals(Object other) {
        return (other instanceof Typeless);
    }
    
    public String getTypeLine() {
        throw new UnsupportedOperationException("Can't check supertypes for a Typeless item.");
    }
    
    protected String getSortedTypeLine() {
        return ""; // not technically correct, but since we only use this for testing .equals, we'll do it for now.
    }
    
    public StringList getTypes() {
        throw new UnsupportedOperationException("Can't check types for a Typeless item.");
    }
    
    public String getType(int i) {
        throw new UnsupportedOperationException("Can't check types for a Typeless item.");
    }
    
    public StringList getSupertypes() {
        throw new UnsupportedOperationException("Can't check supertypes for a Typeless item.");
    }
    
    public String getSupertype(int i) {
        throw new UnsupportedOperationException("Can't check supertypes for a Typeless item.");
    }
    
    public boolean hasSupertypes() {
        throw new UnsupportedOperationException("Can't check supertypes for a Typeless item.");
    }
    
    public StringList getSubtypes() {
        throw new UnsupportedOperationException("Can't check subtypes for a Typeless item.");
    }
    
    public String getSubtype(int i) {
        throw new UnsupportedOperationException("Can't check subtypes for a Typeless item.");
    }
    
    public boolean hasSubtypes() {
        throw new UnsupportedOperationException("Can't check subtypes for a Typeless item.");
    }
}
