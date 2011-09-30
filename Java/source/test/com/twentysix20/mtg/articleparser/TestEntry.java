package com.twentysix20.mtg.articleparser;

import junit.framework.TestCase;

public class TestEntry extends TestCase {
    private String[] _TwoHeadedGiant = {"Two","-Headed","Giant","of","Foriys"};
    private String[] _TwoHeadedDragon = {"Two","-Headed","Dragon"};
    private String[] _Tundra = {"Tundra"};
    private String[] _TundraWolf = {"Tundra","Wolf"};
    private String[] _ChoMannoRevolutionary = {"Cho","-Manno,","Revolutionary"};
    private String[] _ChoMannosBlessing = {"Cho","-Manno's","Blessing"};
    private String[] _BeaconOfCreation = {"Beacon","of","Creation"};
    private String[] _BeaconOfDestiny = {"Beacon","of","Destiny"};
    private String[] _BeaconOfDestruction = {"Beacon","of","Destruction"};
    private String[] _ErtaiTheCorrupted = {"Ertai,","the","Corrupted"};
    private String[] _ErtaiWizardAdept = {"Ertai,","Wizard","Adept"};
    private String[] _ErtaisFamiliar = {"Ertai's","Familiar"};
    private String[] _ErtaisMeddling = {"Ertai's","Meddling"};
    private String[] _MartyrsCry = {"Martyr's","Cry"};
    private String[] _MartyrsOfKorlis = {"Martyrs","of","Korlis"};
    private String[] _MartyrsTomb = {"Martyrs'","Tomb"};
    private String[] _Dandan = {"Dandân"};
    private String[] _AetherFlash = {"Æther","Flash"};
    private String[] _LegionsOfLimDul = {"Legions","of","Lim","-Dûl"};
    
	public static void main(String args[]) {
		junit.textui.TestRunner.run(TestEntry.class);
	}

	public void testRoot() {
        Entry root = new Entry();
        
        assertTrue(root.isRoot());
        assertFalse(root.isMatch());
	}

	public void testOneWord() {        
        Entry root = new Entry();
        root.add(_Tundra);
        
        assertNull(root.find(_TundraWolf));
        assertNull(root.find(_TwoHeadedGiant));
        assertNull(root.find(_Dandan));
        assertEquals("Tundra",root.find(_Tundra));
	}
    
	public void testOneAndTwoWord() {        
        Entry root = new Entry();
        root.add(_Tundra);
        root.add(_TundraWolf);
        
        assertNull(root.find(_TwoHeadedGiant));
        assertNull(root.find(_Dandan));
        assertEquals("Tundra",root.find(_Tundra));
        assertEquals("Tundra Wolf",root.find(_TundraWolf));
	}
    
    public void testMultipleMatch() {
        Entry root = new Entry();
        root.add(_BeaconOfCreation);
        root.add(_BeaconOfDestiny);
        
        assertNull(root.find(_TwoHeadedGiant));
        assertNull(root.find(_BeaconOfDestruction));
        assertNull(root.find(_Dandan));
        assertEquals("Beacon of Destiny",root.find(_BeaconOfDestiny));
        assertEquals("Beacon of Creation",root.find(_BeaconOfCreation));
    }
    
    public void testHyphenated() {
        Entry root = new Entry();
        root.add(_TwoHeadedDragon);
        root.add(_ChoMannosBlessing);
        
        assertNull(root.find(_TundraWolf));
        assertNull(root.find(_TwoHeadedGiant));
        assertNull(root.find(_Dandan));
        assertNull(root.find(_ChoMannoRevolutionary)); 
        assertEquals("Two-Headed Dragon", root.find(_TwoHeadedDragon));
        assertEquals("Cho-Manno's Blessing", root.find(_ChoMannosBlessing));
    }
    
    public void testManyApostrophes() {
        Entry root = new Entry();
        root.add(_MartyrsCry);
        root.add(_MartyrsOfKorlis);
        root.add(_MartyrsTomb);

        assertNull(root.find(_TundraWolf));
        assertNull(root.find(_TwoHeadedGiant));
        assertNull(root.find(_Dandan));
        assertNull(root.find(_ChoMannoRevolutionary)); 
        assertEquals("Martyr's Cry", root.find(_MartyrsCry));
        assertEquals("Martyrs of Korlis", root.find(_MartyrsOfKorlis));
        assertEquals("Martyrs' Tomb", root.find(_MartyrsTomb));
    }
    
    public void testConvert() {
        assertEquals("AETHER",Entry.convertToSortString(_AetherFlash[0]));
        assertEquals("DANDAN",Entry.convertToSortString(_Dandan[0]));
        assertEquals("DUL",Entry.convertToSortString(_LegionsOfLimDul[3]));
        assertEquals("MANNO'S",Entry.convertToSortString(_ChoMannosBlessing[1]));
        
    }
}