/*
 * MtgConstants.java
 *
 * Created on September 12, 2004, 6:23 PM
 *
 * http://sales.starcitygames.com/cardscans/MAGMIR/MAGMIR353.jpg
 * http://sales.starcitygames.com/cardscans/MAG7TH/MAG7TH353.jpg
 * http://www.wizards.com/global/images/magic/general/Forest.jpg
 * http://www.wizards.com/global/images/magic/odyssey/Forest2.jpg
 * http://www.wizards.com/global/images/magic/alliances/carrier_pigeons.jpg
 * http://www.wizards.com/global/images/magic/alliances/carrier_pigeons2.jpg
 * http://www.wizards.com/global/images/magic/apocalypse/fire_ice.jpg
 * http://www.wizards.com/global/images/magic/unglued/b.f.m._big_furry_monster.jpg
 * http://www.wizards.com/global/images/magic/general/bfm.jpg
 * http://www.wizards.com/global/images/magic/unglued/bfm1.jpg
 * http://www.wizards.com/global/images/magic/unglued/bfm2.jpg
 * uz = urza's saga; ul = urza's legacy
 * dark = the dark
 * p3k
 * mirrodin
 *onslaught
 *an=arabiannights
 *8e
 *fd=fifthdawn
 *darksteel
 *chk
 */

package com.twentysix20.mtg;

import com.twentysix20.util.StringList;

/**
 *
 * @author  Peter Risser
 */
public class MtgConstants {

    // Types
    static final public String TYPE_ENCHANTMENT = "Enchantment";
    static final public String TYPE_ARTIFACT = "Artifact";
    static final public String TYPE_CREATURE = "Creature";
    static final public String TYPE_LAND = "Land";
    static final public String TYPE_INSTANT = "Instant";
    static final public String TYPE_SORCERY = "Sorcery";
//    static final public String TYPE_TRIBAL = "Tribal";
    static final public String TYPE_PLANESWALKER = "Planeswalker";
    static final public String[] TYPES = {TYPE_ENCHANTMENT, TYPE_ARTIFACT, TYPE_CREATURE, TYPE_LAND, TYPE_INSTANT, TYPE_SORCERY};

    static final public String[] PERMANENT_TYPES = {TYPE_ENCHANTMENT, TYPE_ARTIFACT, TYPE_CREATURE, TYPE_LAND, TYPE_PLANESWALKER};
    static final public StringList PERMANENT_TYPES_LIST = new StringList(PERMANENT_TYPES);
    static final public String[] NON_PERMANENT_TYPES = {TYPE_SORCERY, TYPE_INSTANT};
    static final public StringList NON_PERMANENT_TYPES_LIST = new StringList(NON_PERMANENT_TYPES);

    // Supertypes
    static final public String SUPERTYPE_BASIC = "Basic";
    static final public String SUPERTYPE_LEGENDARY = "Legendary";
    static final public String SUPERTYPE_SNOW = "Snow";
    static final public String SUPERTYPE_TRIBAL = "Tribal";
    static final public String SUPERTYPE_WORLD = "World";
//    static final public String SUPERTYPE_SNOW_COVERED = "Snow-Covered";
    static final public String[] SUPERTYPES = {SUPERTYPE_BASIC, SUPERTYPE_LEGENDARY, SUPERTYPE_SNOW, SUPERTYPE_WORLD, SUPERTYPE_TRIBAL};
    static final public StringList SUPERTYPES_LIST = new StringList(SUPERTYPES);
    
    // Functional Subtypes
    static final public String SPELL_ARCANE = "Arcane";

    static final public String ARTIFACT_EQUIPMENT = "Equipment";

    static final public String ENCHANT = "Enchant";
    static final public String ENCHANTMENT_SHRINE = "Shrine";
    static final public String ENCHANTMENT_AURA = "Aura";

    // Types of enchantees
    static final public String ENCHANT_PLAYER = "Player";
    static final public String ENCHANT_WORLD = "World";
    static final public String ENCHANT_EQUIPMENT = ARTIFACT_EQUIPMENT;
    static final public String ENCHANT_CREATURE = TYPE_CREATURE;
    static final public String ENCHANT_ARTIFACT = TYPE_ARTIFACT;
    static final public String ENCHANT_ARTIFACT_CREATURE = TYPE_ARTIFACT + " " + TYPE_CREATURE;
    static final public String ENCHANT_LAND = TYPE_LAND;
    static final public String ENCHANT_ENCHANTMENT = TYPE_ENCHANTMENT;
    static final public String ENCHANT_PERMANENT = "Permanent";
    static final public String[] ENCHANT_TYPES = {
        ENCHANT_PLAYER,
        ENCHANT_WORLD,
        ENCHANT_EQUIPMENT,
        ENCHANT_CREATURE,
        ENCHANT_ARTIFACT,
        ENCHANT_LAND,
        ENCHANT_ENCHANTMENT,
        ENCHANT_ARTIFACT_CREATURE,
        ENCHANT_PERMANENT
    };
    static final public StringList ENCHANT_TYPE_LIST = new StringList(ENCHANT_TYPES);
    
    static final public String SYMBOL_X = "X";
    static final public String SYMBOL_Y = "Y";
    static final public String SYMBOL_Z = "Z";
    static final public String SYMBOL_WHITE = "W";
    static final public String SYMBOL_BLUE = "U";
    static final public String SYMBOL_BLACK = "B";
    static final public String SYMBOL_RED = "R";
    static final public String SYMBOL_GREEN = "G";
    static final public String SYMBOL_PHYREXIAN_WHITE = "(WP)";
    static final public String SYMBOL_PHYREXIAN_BLUE = "(UP)";
    static final public String SYMBOL_PHYREXIAN_BLACK = "(BP)";
    static final public String SYMBOL_PHYREXIAN_RED = "(RP)";
    static final public String SYMBOL_PHYREXIAN_GREEN = "(GP)";
    static final public String SYMBOL_HYBRID_WU = "(W/U)";
    static final public String SYMBOL_HYBRID_WB = "(W/B)";
    static final public String SYMBOL_HYBRID_2W = "(2/W)";
    static final public String SYMBOL_HYBRID_2B = "(2/B)";
    static final public String SYMBOL_HYBRID_UB = "(U/B)";
    static final public String SYMBOL_HYBRID_UR = "(U/R)";
    static final public String SYMBOL_HYBRID_BR = "(B/R)";
    static final public String SYMBOL_HYBRID_BG = "(B/G)";
    static final public String SYMBOL_HYBRID_2R = "(2/R)";
    static final public String SYMBOL_HYBRID_2G = "(2/G)";
    static final public String SYMBOL_HYBRID_RG = "(R/G)";
    static final public String SYMBOL_HYBRID_RW = "(R/W)";
    static final public String SYMBOL_HYBRID_GW = "(G/W)";
    static final public String SYMBOL_HYBRID_GU = "(G/U)";
    static final public String SYMBOL_HYBRID_2U = "(2/U)";
    static final public String SYMBOL_HALF_WHITE = "½W";

    static final public String RARITY_TIMESHIFTED = "Timeshifted";
    static final public String RARITY_MYTHIC = "Mythic";
    static final public String RARITY_RARE = "Rare";
    static final public String RARITY_UNCOMMON = "Uncommon";
    static final public String RARITY_COMMON = "Common";
    static final public String RARITY_LAND = "Land";

    static final public String BORDER_BLACK = "Black";
    static final public String BORDER_WHITE = "White";
    static final public String BORDER_SILVER = "Silver";

    /** Can't create one of these. */
    private MtgConstants() {}
    
}