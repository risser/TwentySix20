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
* Created on Mar 27, 2006
*/
package com.twentysix20.mtg.data;

import com.twentysix20.mtg.data.type.Type;
import com.twentysix20.testutil.TestCase2620;

public class TestType extends TestCase2620 {

    public void testSuperTypes() {
        assertTrue("Basic".matches(Type.VALID_SUPERTYPE));
        assertTrue("Snow".matches(Type.VALID_SUPERTYPE));
        assertTrue("Legendary".matches(Type.VALID_SUPERTYPE));
        assertTrue("World".matches(Type.VALID_SUPERTYPE));
        assertTrue("Basic Snow".matches(Type.VALID_SUPERTYPE));
    }

    public void testGoodTypes() {
        Type t;
        t = new Type("Instant");
        t = new Type("Instant - Arcane");
        t = new Type("Instant -- Arcane");

        t = new Type("Sorcery");
        t = new Type("Sorcery - Arcane");

        t = new Type("Land");
        t = new Type("Basic Land - Island");
        t = new Type("Basic Snow Land - Island");
        t = new Type("Land - Island Mountain");
        t = new Type("Land - Lair");
//        t = new Type("Snow-Covered Land");
        t = new Type("Legendary Land");

        t = new Type("Artifact");
        t = new Type("Artifact - Equipment");
        t = new Type("Legendary Artifact");

        t = new Type("Enchantment");
        t = new Type("Enchantment - Aura");
        t = new Type("Legendary Enchantment");

        t = new Type("Creature");
        t = new Type("Legendary Creature");
        t = new Type("Creature - Zombie");
        t = new Type("Snow Creature - Angel");
        t = new Type("Creature - Zombie Cleric");
        t = new Type("Creature - Human Soldier Archer");
        t = new Type("Creature - Gaea's-Avenger");
        t = new Type("Creature - Spider Mutant");

        t = new Type("Artifact Creature");
        t = new Type("Legendary Artifact Creature");
        t = new Type("Artifact Creature - Zombie");

        t = new Type("Artifact Land");
    }

    public void testValid() {
        assertTrue("Instant".matches(Type.VALID_TYPE_LINE));
        assertTrue("Instant - Arcane".matches(Type.VALID_TYPE_LINE));
        assertTrue("Instant -- Arcane".matches(Type.VALID_TYPE_LINE));

        assertTrue("Sorcery".matches(Type.VALID_TYPE_LINE));
        assertTrue("Sorcery - Arcane".matches(Type.VALID_TYPE_LINE));

        assertTrue("Land".matches(Type.VALID_TYPE_LINE));
        assertTrue("Basic Land - Island".matches(Type.VALID_TYPE_LINE));
        assertTrue("Snow Basic Land - Island".matches(Type.VALID_TYPE_LINE));
        assertTrue("Land - Island Mountain".matches(Type.VALID_TYPE_LINE));
        assertTrue("Land - Lair".matches(Type.VALID_TYPE_LINE));
        assertTrue("Legendary Land".matches(Type.VALID_TYPE_LINE));
//        assertTrue("Snow-Covered Land".matches(Type.VALID_TYPE_LINE));

        assertTrue("Artifact".matches(Type.VALID_TYPE_LINE));
        assertTrue("Artifact - Equipment".matches(Type.VALID_TYPE_LINE));
        assertTrue("Legendary Artifact".matches(Type.VALID_TYPE_LINE));

        assertTrue("Enchantment".matches(Type.VALID_TYPE_LINE));
        assertTrue("Enchantment - Aura".matches(Type.VALID_TYPE_LINE));
        assertTrue("Enchantment - Shrine".matches(Type.VALID_TYPE_LINE));
        assertTrue("Legendary Enchantment".matches(Type.VALID_TYPE_LINE));
        assertTrue("World Enchantment".matches(Type.VALID_TYPE_LINE));

        assertTrue("Creature".matches(Type.VALID_TYPE_LINE));
        assertTrue("Legendary Creature".matches(Type.VALID_TYPE_LINE));
        assertTrue("Creature - Zombie".matches(Type.VALID_TYPE_LINE));
        assertTrue("Snow Creature - Angel".matches(Type.VALID_TYPE_LINE));
        assertTrue("Creature - Zombie Cleric".matches(Type.VALID_TYPE_LINE));
        assertTrue("Creature - Human Soldier Archer".matches(Type.VALID_TYPE_LINE));
        assertTrue("Creature - Gaea's-Avenger".matches(Type.VALID_TYPE_LINE));
        assertTrue("Creature - Spider Mutant".matches(Type.VALID_TYPE_LINE));

        assertTrue("Artifact Creature".matches(Type.VALID_TYPE_LINE));
        assertTrue("Legendary Artifact Creature".matches(Type.VALID_TYPE_LINE));
        assertTrue("Artifact Creature - Zombie".matches(Type.VALID_TYPE_LINE));

        assertTrue("Artifact Land".matches(Type.VALID_TYPE_LINE));
    }

    public void testBadTypes() {
        tryType("Cheese");
        tryType("Cheese Land");
        tryType("Creature Land");
        tryType("Instant-Arcane");
        tryType("Instant-Arcane");
        tryType("Instant -Arcane");
        tryType("Instant- Arcane");
        tryType("Creature - Zombie.Soldier");
        tryType("");
        tryType(null);
    }
    
    public void testGet_SingleType() {
        Type t = new Type("Instant");
        assertFalse(t.hasSupertypes());
        assertSize(0, t.getSupertypes());
        assertSize(1, t.getTypes());
        assertEquals("Instant", t.getType(0));
        assertFalse(t.hasSubtypes());
        assertSize(0, t.getSubtypes());
    }
    
    public void testGet_TypeSubtypeDualHyphen() {
        Type t = new Type("Instant -- Arcane");
        assertFalse(t.hasSupertypes());
        assertSize(0, t.getSupertypes());
        assertSize(1, t.getTypes());
        assertEquals("Instant", t.getType(0));
        assertTrue(t.hasSubtypes());
        assertSize(1, t.getSubtypes());
        assertEquals("Arcane", t.getSubtype(0));
    }
    
    public void testGet_Exceptions() {
        Type t = new Type("Instant");
        try {
            assertEquals("Instant", t.getType(1));
            fail("Should throw IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // good
        }
        try {
            assertEquals("Instant", t.getSupertype(0));
            fail("Should throw IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // good
        }
        try {
            assertEquals("Instant", t.getSubtype(0));
            fail("Should throw IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // good
        }
    }
    
    public void testEquals() {
        assertTrue(new Type("Instant").equals(new Type("Instant")));
        assertFalse(new Type("Sorcery").equals(new Type("Instant")));
        assertTrue(new Type("Land - Island Plains").equals(new Type("Land - Plains Island")));
        assertTrue(new Type("Land - Island Plains").equals(new Type(null,"Land","Plains Island")));
        assertFalse(new Type("Creature - Cat Soldier Archer").equals(new Type("Creature - Cat Soldier")));
        assertTrue(new Type("Creature - Cat Soldier Archer").equals(new Type("","Creature","Soldier Archer Cat")));
        assertTrue(new Type("World Enchantment").equals(new Type("World","Enchantment",null)));
        assertTrue(new Type("Snow Basic Land - Island").equals(new Type("Basic Snow","Land","Island")));
        assertTrue(new Type("Artifact Land").equals(new Type("Artifact Land")));
        assertFalse(new Type("Basic Land").equals(Type.typeless()));
        assertFalse(Type.typeless().equals(new Type("Instant")));
        assertTrue(Type.typeless().equals(Type.typeless()));
    }
    
    public void testToString() {
        assertEquals("Instant",new Type("Instant").toString());
        assertEquals("Land - Plains Island",new Type(null,"Land","Plains Island").toString());
        assertEquals("Legendary Land - Island Plains",new Type("Legendary","Land","Island Plains").toString());
        assertEquals("Creature - Cat Soldier Archer", new Type("Creature - Cat Soldier Archer").toString());
        assertEquals("Creature - Cat Soldier Archer", new Type(null,"Creature","Cat Soldier Archer").toString());
//        assertEquals("Snow-Covered Land",new Type("Snow-Covered","Land",null).toString());
        assertEquals("World Enchantment",new Type("World","Enchantment",null).toString());
    }

    private void tryType(String type) {
        try {
            Type t = new Type(type);
            fail("Should have thrown IllegalArgumentException."); 
        } catch (IllegalArgumentException e) {
            // good 
        }
    }
}
