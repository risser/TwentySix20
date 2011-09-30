/*
 * Set.java
 *
 * Created on October 21, 2004, 6:44 PM
 */

package com.twentysix20.mtg.data.set;

import java.util.Calendar;

import com.twentysix20.mtg.data.enums.Border;

/**
 *
 * @author  tpnr007
 */
public class Set {
    private String code = null;
    private String name = null;
    private Calendar releaseDate = null;
    private Border defaultBorder = null;
    
    /** Creates a new instance of Set */
    public Set() {
    }
    
    /**
     * Getter for property code.
     * @return Value of property code.
     */
    public java.lang.String getCode() {
        return code;
    }
    
    /**
     * Setter for property code.
     * @param code New value of property code.
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }
    
    /**
     * Getter for property name.
     * @return Value of property name.
     */
    public java.lang.String getName() {
        return name;
    }
    
    /**
     * Setter for property name.
     * @param name New value of property name.
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }
    
    /**
     * Getter for property releaseDate.
     * @return Value of property releaseDate.
     */
    public java.util.Calendar getReleaseDate() {
        return releaseDate;
    }
    
    /**
     * Setter for property releaseDate.
     * @param releaseDate New value of property releaseDate.
     */
    public void setReleaseDate(java.util.Calendar releaseDate) {
        this.releaseDate = releaseDate;
    }
    
    /**
     * Getter for property defaultBorder.
     * @return Value of property defaultBorder.
     */
    public com.twentysix20.mtg.data.enums.Border getDefaultBorder() {
        return defaultBorder;
    }
    
    /**
     * Setter for property defaultBorder.
     * @param defaultBorder New value of property defaultBorder.
     */
    public void setDefaultBorder(com.twentysix20.mtg.data.enums.Border defaultBorder) {
        this.defaultBorder = defaultBorder;
    }
    
}
