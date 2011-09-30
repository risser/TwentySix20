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

import com.twentysix20.mtg.MtgConstants;
import com.twentysix20.util.StringList;
import com.twentysix20.util.StringUtil;

public class Type {
    public static final String VALID_SUPERTYPE = "(("+MtgConstants.SUPERTYPE_BASIC+"|"+MtgConstants.SUPERTYPE_LEGENDARY+"|"+MtgConstants.SUPERTYPE_SNOW+"|"+MtgConstants.SUPERTYPE_TRIBAL+"|"+MtgConstants.SUPERTYPE_WORLD+")\\s?)+";
    public static final String VALID_TYPE = 
            MtgConstants.TYPE_INSTANT + "|"
            + MtgConstants.TYPE_SORCERY + "|"
            + MtgConstants.TYPE_ARTIFACT + "|"
            + MtgConstants.TYPE_PLANESWALKER + "|"
            + MtgConstants.TYPE_ENCHANTMENT + "|"
            + "(" + MtgConstants.TYPE_ARTIFACT + "\\s+)?" + MtgConstants.TYPE_LAND + "|"
            + "((" + MtgConstants.TYPE_ARTIFACT + "|" + MtgConstants.TYPE_ENCHANTMENT + "|" + MtgConstants.TYPE_LAND + "|)\\s+)?" + MtgConstants.TYPE_CREATURE;
    public static final String VALID_SUBTYPE = "[A-Za-z',\\- ]+";
    public static final String VALID_COMPLETE_TYPE = "(("+VALID_SUPERTYPE+")\\s)?("+VALID_TYPE+")(\\s+\\-+(\\s+"+VALID_SUBTYPE+")+)?";

    public static final String VALID_OLD_STYLE_ENCHANT = MtgConstants.ENCHANT+"\\s+("+MtgConstants.ENCHANT_ARTIFACT+"|"+MtgConstants.ENCHANT_CREATURE+")";
    // this is NOT currently a complete list of possible enchants.

    public static final String VALID_TYPE_LINE = VALID_COMPLETE_TYPE+"|"+VALID_COMPLETE_TYPE+"\\s?//\\s?"+VALID_COMPLETE_TYPE+"|"+VALID_OLD_STYLE_ENCHANT;

    private StringList types;
    private StringList supertypes;
    private StringList subtypes;

    protected Type () {}

    public Type(String typeline) {
        if (StringUtil.isEmpty(typeline)) throw new IllegalArgumentException("Can't create Type with null or blank typeline.");
        if (!typeline.matches(Type.VALID_TYPE_LINE)) throw new IllegalArgumentException("Typeline '"+typeline+"' is not a valid typeline.");
        setTypes(typeline);
    }
    public Type(String _supertype, String _type, String _subtype) {
//        if (StringUtil.isEmpty(_supertype)) throw new IllegalArgumentException("Can't create Type with null or blank supertype.");
        if (StringUtil.isEmpty(_type)) throw new IllegalArgumentException("Can't create Type with null or blank type.");
//        if (StringUtil.isEmpty(_subtype)) throw new IllegalArgumentException("Can't create Type with null or blank subtype.");
        setTypes(_supertype, _type, _subtype);
    }

    protected void setTypes(String typestr) {
        if (typestr.matches(VALID_OLD_STYLE_ENCHANT)) {
            setTypes("", MtgConstants.TYPE_ENCHANTMENT, MtgConstants.ENCHANTMENT_AURA);
        } else {
            String[] subsplit = typestr.split("\\s+-+|-+\\s+");
            String[] supersplit = subsplit[0].split("\\s+");
            String _supertype = "";
            String _type = "";
            String _subtype = (subsplit.length == 1 ? null : subsplit[1]);
            for (int i = 0; i < supersplit.length; i++)
                if (MtgConstants.SUPERTYPES_LIST.contains(supersplit[i]))
                    _supertype = _supertype + " " + supersplit[i];
                else
                    _type = _type + " " + supersplit[i];
            _supertype = StringUtil.isEmpty(_supertype) ? null : _supertype.trim();
            _type = _type.trim();
            _subtype = StringUtil.isEmpty(_subtype) ? null : _subtype.trim();
            setTypes(_supertype, _type, _subtype);
        }
    }

    protected void setTypes(String supertype, String type, String subtype) {
//        if (supertype != null && !supertype.matches(VALID_SUPERTYPE)) throw new IllegalArgumentException("'"+supertype+"' is not a valid supertype.");
        if (!type.matches(VALID_TYPE)) throw new IllegalArgumentException("'"+type+"' is not a valid type.");
//        if (subtype != null && !subtype.matches(VALID_SUBTYPE)) throw new IllegalArgumentException("'"+subtype+"' is not a valid subtype.");
        if (StringUtil.isEmpty(supertype))
            supertypes = new StringList();
        else
            supertypes = new StringList(supertype.split(" "));
        types = new StringList(type.split(" "));
        if (StringUtil.isEmpty(subtype))
            subtypes = new StringList();
        else
            subtypes = new StringList(subtype.split(" "));
    }
    
    public String getTypeLine() {
        String sup = supertypes.toString(" ");
        String typ = types.toString(" ");
        String sub = subtypes.toString(" ");
        return ((sup.length() > 0) ? sup + " " : "") + typ + ((sub.length() > 0) ? " - " + sub : ""); 
    }
    
    protected String getSortedTypeLine() {
        String sup = supertypes.sortedCopy().toString(" ");
        String typ = types.sortedCopy().toString(" ");
        String sub = subtypes.sortedCopy().toString(" ");
        return ((sup.length() > 0) ? sup + " " : "") + typ + ((sub.length() > 0) ? " - " + sub : ""); 
    }
    
    public StringList getTypes() {
        return types;
    }
    
    public String getType(int i) {
        if (i >= getTypes().size()) throw new IllegalArgumentException("Size of type list is "+getTypes().size()+". "+i+" is too big.");
        return getTypes().get(i);
    }
    
    public StringList getSupertypes() {
        return supertypes;
    }
    
    public String getSupertype(int i) {
        if (i >= getSupertypes().size()) throw new IllegalArgumentException("Size of supertype list is "+getSupertypes().size()+". "+i+" is too big.");
        return getSupertypes().get(i);
    }
    
    public boolean hasSupertypes() {
        return (getSupertypes().size() > 0);
    }
    
    public StringList getSubtypes() {
        return subtypes;
    }
    
    public String getSubtype(int i) {
        if (i >= getSubtypes().size()) throw new IllegalArgumentException("Size of subtype list is "+getSubtypes().size()+". "+i+" is too big.");
        return getSubtypes().get(i);
    }
    
    public boolean hasSubtypes() {
        return (getSubtypes().size() > 0);
    }
    
    public String toString() {
        return getTypeLine();
    }

    public boolean equals(Object other) {
        if (other == null) return false;
        if (!(other instanceof Type)) throw new IllegalArgumentException("Can't check equals against an object that isn't a Type.");
        
        return this.getSortedTypeLine().equals(((Type)other).getSortedTypeLine());
    }
    
    public boolean isTypeless() {
        return (this instanceof Typeless);
    }
    
    public static Typeless typeless() {
        return Typeless.getInstance();
    }

    public boolean hasType(String key) {
        return types.contains(key);
    }

    public boolean hasSuperType(String key) {
        return supertypes.contains(key);
    }

    public boolean hasSubType(String key) {
        return subtypes.contains(key);
    }
}
