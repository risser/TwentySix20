/*
 * TestCardInfo.java
 *
 * Created on September 15, 2004, 10:57 PM
 */

package com.twentysix20.mtg.old.spoiler;

import junit.framework.TestCase;

import com.twentysix20.mtg.old.spoiler.cardinfo.CardInfo;
import com.twentysix20.mtg.old.spoiler.cardinfo.SpoilerCardInfo;
import com.twentysix20.util.StringList;

/**
 *
 * @author  tpnr007
 */
public class TestCardInfo extends TestCase {
    
	public static void main(String args[]) {
		junit.textui.TestRunner.run(TestCardInfo.class);
	}

    public void testRules_AncientSpring() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("Ancient Spring comes into play tapped. Tap: Add U to your mana");
		card.setRules("pool. Tap, Sacrifice Ancient Spring: Add WB to your mana pool.");
        
        StringList result = card.getRules();
        assertEquals(3,result.size());
        assertEquals("Ancient Spring comes into play tapped.",result.get(0));
        assertEquals("Tap: Add U to your mana pool.",result.get(1));
        assertEquals("Tap, Sacrifice Ancient Spring: Add WB to your mana pool.",result.get(2));
    }
    
    public void testRules_Absorb() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("Counter target spell. You gain 3 life.");
        
        StringList result = card.getRules();
        assertEquals(1,result.size());
        assertEquals("Counter target spell. You gain 3 life.",result.get(0));
    }
    
    public void testRules_AncientKavu() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("2: Ancient Kavu becomes colorless until end of turn.");
        
        StringList result = card.getRules();
        assertEquals(1,result.size());
        assertEquals("2: Ancient Kavu becomes colorless until end of turn.",result.get(0));
    }
    
    public void testRules_AetherRift() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("At the beginning of your upkeep, discard a card at random from");
		card.setRules("your hand. If you discard a creature card this way, put that card");
		card.setRules("into play unless any player pays 5 life.");
        
        StringList result = card.getRules();
        assertEquals(1,result.size());
        assertEquals("At the beginning of your upkeep, discard a card at random from your hand. If you discard a creature card this way, put that card into play unless any player pays 5 life.",result.get(0));
    }
    
    public void testRules_AlloyGolem() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("As Alloy Golem comes into play, choose a color. Alloy Golem is");
        card.setRules("the chosen color. (It's still an artifact.)");
        
        StringList result = card.getRules();
        assertEquals(2,result.size());
        assertEquals("As Alloy Golem comes into play, choose a color.",result.get(0));
        assertEquals("Alloy Golem is the chosen color. (It's still an artifact.)",result.get(1));
    }

    public void testRules_AggressiveUrge() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("Target creature gets +1/+1 until end of turn. Draw a card.");
        
        StringList result = card.getRules();
        assertEquals(2,result.size());
        assertEquals("Target creature gets +1/+1 until end of turn.",result.get(0));
        assertEquals("Draw a card.",result.get(1));
    }

    public void testRules_ArmoredGuardian() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("1WW: Target creature you control gains protection from the color");
        card.setRules("of your choice until end of turn. 1UU: Armored Guardian can't be");
		card.setRules("the target of spells or abilities this turn.");
        
        StringList result = card.getRules();
        assertEquals(2,result.size());
        assertEquals("1WW: Target creature you control gains protection from the color of your choice until end of turn.",result.get(0));
        assertEquals("1UU: Armored Guardian can't be the target of spells or abilities this turn.",result.get(1));
    }
    
    public void testRules_BlazingSpecter() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("Flying; haste (This creature may attack and Tap the turn it comes");
        card.setRules("under your control.) Whenever Blazing Specter deals combat damage");
		card.setRules("to a player, that player discards a card from his or her hand.");
        
        StringList result = card.getRules();
        assertEquals(2,result.size());
        assertEquals("Flying; haste (This creature may attack and Tap the turn it comes under your control.)",result.get(0));
        assertEquals("Whenever Blazing Specter deals combat damage to a player, that player discards a card from his or her hand.",result.get(1));
    }
    
    public void testRules_CauldronDance() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("Play Cauldron Dance only during combat. Return target creature");
        card.setRules("card from your graveyard to play. That creature gains");
		card.setRules("haste. Return it to your hand at end of turn. Put a creature card");
		card.setRules("from your hand into play. That creature gains haste. Put it into");
		card.setRules("your graveyard at end of turn.");
        
        StringList result = card.getRules();
//        assertEquals("Play Cauldron Dance only during combat.",result.get(0));
//        assertEquals("Return target creature card from your graveyard to play. That creature gains haste. Return it to your hand at end of turn. Put a creature card from your hand into play. That creature gains haste. Put it into your graveyard at end of turn.",result.get(1));
//        assertEquals(2,result.size());
    }

    public void testRules_CinderShade() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("B: Cinder Shade gets +1/+1 until end of turn. R, Sacrifice Cinder");
        card.setRules("Shade: Cinder Shade deals damage equal to its power to target");
		card.setRules("creature.");
        
        StringList result = card.getRules();
        assertEquals(2,result.size());
        assertEquals("B: Cinder Shade gets +1/+1 until end of turn.",result.get(0));
        assertEquals("R, Sacrifice Cinder Shade: Cinder Shade deals damage equal to its power to target creature.",result.get(1));
    }

    public void testRules_CoastalTower() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("Coastal Tower comes into play tapped.");
        card.setRules("Tap: Add W or U to your mana pool.");
        
        StringList result = card.getRules();
        assertEquals(2,result.size());
        assertEquals("Coastal Tower comes into play tapped.",result.get(0));
        assertEquals("Tap: Add W or U to your mana pool.",result.get(1));
    }

    public void testRules_CrimsonAlcolyte() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("Protection from red. W: Target creature gains protection from red");
        card.setRules("until end of turn.");
        
        StringList result = card.getRules();
        assertEquals(2,result.size());
        assertEquals("Protection from red.",result.get(0));
        assertEquals("W: Target creature gains protection from red until end of turn.",result.get(1));
    }

    public void testRules_CrosissAttendant() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("1, Sacrifice Crosis's Attendant: Add UBR to your mana pool.");
        
        StringList result = card.getRules();
        assertEquals(1,result.size());
        assertEquals("1, Sacrifice Crosis's Attendant: Add UBR to your mana pool.",result.get(0));
    }

    public void testRules_CrosisThePurger() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("Flying. Whenever Crosis, the Purger deals combat damage to a");
        card.setRules("player, you may pay 2B. If you do, choose a color. That player");
        card.setRules("reveals his or her hand and discards all cards of that color from");
        card.setRules("it.");

        StringList result = card.getRules();
        assertEquals(2,result.size());
        assertEquals("Flying.",result.get(0));
        assertEquals("Whenever Crosis, the Purger deals combat damage to a player, you may pay 2B. If you do, choose a color. That player reveals his or her hand and discards all cards of that color from it.",result.get(1));
    }

    public void testRules_CryptAngel() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("Flying, protection from white. When Crypt Angel comes into play,");
        card.setRules("return target blue or red creature card from your graveyard to");
        card.setRules("your hand.");

    	StringList result = card.getRules();
        assertEquals(2,result.size());
        assertEquals("Flying, protection from white.",result.get(0));
        assertEquals("When Crypt Angel comes into play, return target blue or red creature card from your graveyard to your hand.",result.get(1));
    }

    public void testRules_DefilingTears() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("Until end of turn, target creature becomes black, gets +1/-1, and");
        card.setRules("gains \"B: Regenerate this creature.\"");

        StringList result = card.getRules();
        assertEquals(1,result.size());
        assertEquals("Until end of turn, target creature becomes black, gets +1/-1, and gains \"B: Regenerate this creature.\"",result.get(0));
    }

    public void testRules_DefilingTears2() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("Until end of turn, target creature becomes black, gets +1/-1, and gains \"B: Regenerate");
        card.setRules("this creature.\"");

        StringList result = card.getRules();
        assertEquals(1,result.size());
        assertEquals("Until end of turn, target creature becomes black, gets +1/-1, and gains \"B: Regenerate this creature.\"",result.get(0));
    }

    public void testRules_DefilingTears3() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("Until end of turn, target creature becomes black, gets +1/-1, and gains \"B:");
        card.setRules("Regenerate this creature.\"");

        StringList result = card.getRules();
        assertEquals(1,result.size());
        assertEquals("Until end of turn, target creature becomes black, gets +1/-1, and gains \"B: Regenerate this creature.\"",result.get(0));
    }

    public void testRules_DefilingTears4() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("Until end of turn, target creature becomes black, gets +1/-1, and gains");
        card.setRules("\"B: Regenerate this creature.\"");

        StringList result = card.getRules();
        assertEquals(1,result.size());
        assertEquals("Until end of turn, target creature becomes black, gets +1/-1, and gains \"B: Regenerate this creature.\"",result.get(0));
    }

    public void testRules_DevouringStrossus() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("Flying, trample. At the beginning of your upkeep, sacrifice a");
        card.setRules("creature. Sacrifice a creature: Regenerate Devouring Strossus.");

    	StringList result = card.getRules();
        assertEquals(3,result.size());
        assertEquals("Flying, trample.",result.get(0));
        assertEquals("At the beginning of your upkeep, sacrifice a creature.",result.get(1));
        assertEquals("Sacrifice a creature: Regenerate Devouring Strossus.",result.get(2));
    }

    public void testRules_Duskwalker() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("Kicker 3B (You may pay an additional 3B as you play this spell.)");
        card.setRules("If you paid the kicker cost, Duskwalker comes into play with two");
        card.setRules("+1/+1 counters on it and has \"Duskwalker can't be blocked except");
        card.setRules("by artifact creatures and/or black creatures.\"");

    	StringList result = card.getRules();
        assertEquals(2,result.size());
        assertEquals("Kicker 3B (You may pay an additional 3B as you play this spell.)",result.get(0));
        assertEquals("If you paid the kicker cost, Duskwalker comes into play with two +1/+1 counters on it and has \"Duskwalker can't be blocked except by artifact creatures and/or black creatures.\"",result.get(1));
    }

    public void testRules_FiresofYavimaya() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("Creatures you control have haste. (They may attack and Tap the");
        card.setRules("turn they come under your control.) Sacrifice Fires of Yavimaya:");
        card.setRules("Target creature gets +2/+2 until end of turn.");

    	StringList result = card.getRules();
        assertEquals(2,result.size());
        assertEquals("Creatures you control have haste. (They may attack and Tap the turn they come under your control.)",result.get(0));
        assertEquals("Sacrifice Fires of Yavimaya: Target creature gets +2/+2 until end of turn.",result.get(1));
    }

    public void testRules_GalinasKnight() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("Protection from red");

    	StringList result = card.getRules();
        assertEquals(1,result.size());
        assertEquals("Protection from red",result.get(0));
    }

    public void testRules_GeothermalCrevice() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("Geothermal Crevice comes into play tapped.");
        card.setRules("Tap: Add R to your mana pool. Tap, Sacrifice Geothermal Crevice:");
        card.setRules("Add BG to your mana pool.");

    	StringList result = card.getRules();
        assertEquals(3,result.size());
        assertEquals("Geothermal Crevice comes into play tapped.",result.get(0));
        assertEquals("Tap: Add R to your mana pool.",result.get(1));
        assertEquals("Tap, Sacrifice Geothermal Crevice: Add BG to your mana pool.",result.get(2));
    }

    public void testRules_GlimmeringAngel() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("Flying. U: Glimmering Angel can't be the target of spells or");
        card.setRules("abilities this turn.");

    	StringList result = card.getRules();
        assertEquals(2,result.size());
        assertEquals("Flying.",result.get(0));
        assertEquals("U: Glimmering Angel can't be the target of spells or abilities this turn.",result.get(1));
    }

    public void testRules_Harrow() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("As an additional cost to play Harrow, sacrifice a land.");
        card.setRules("Search your library for up to two basic land cards and put them");
        card.setRules("into play. Then shuffle your library.");

    	StringList result = card.getRules();
        assertEquals(2,result.size());
        assertEquals("As an additional cost to play Harrow, sacrifice a land.",result.get(0));
        assertEquals("Search your library for up to two basic land cards and put them into play. Then shuffle your library.",result.get(1));
    }

    public void testRules_KangeeAerieKeeper() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("Kicker 2X (You may pay an additional 2X as you play this spell.)");
        card.setRules("Flying. When Kangee, Aerie Keeper comes into play, if you paid");
        card.setRules("the kicker cost, put X feather counters on it. All Birds get");
        card.setRules("+1/+1 for each feather counter on Kangee, Aerie Keeper.");

    	StringList result = card.getRules();
        assertEquals(3,result.size());
        assertEquals("Kicker 2X (You may pay an additional 2X as you play this spell.)",result.get(0));
        assertEquals("Flying.",result.get(1));
        assertEquals("When Kangee, Aerie Keeper comes into play, if you paid the kicker cost, put X feather counters on it. All Birds get +1/+1 for each feather counter on Kangee, Aerie Keeper.",result.get(2));
    }

    public void testRules_KeldonNecropolis() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("Tap: Add one colorless mana to your mana pool.");
        card.setRules("4R, Tap, Sacrifice a creature: Keldon Necropolis deals 2 damage");
        card.setRules("to target creature or player.");

    	StringList result = card.getRules();
        assertEquals(2,result.size());
        assertEquals("Tap: Add one colorless mana to your mana pool.",result.get(0));
        assertEquals("4R, Tap, Sacrifice a creature: Keldon Necropolis deals 2 damage to target creature or player.",result.get(1));
    }

    public void testRules_LlanowarElite() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("Kicker 8 (You may pay an additional 8 as you play this spell.)");
        card.setRules("Trample. If you paid the kicker cost, Llanowar Elite comes into");
        card.setRules("play with five +1/+1 counters on it.");

    	StringList result = card.getRules();
        assertEquals(3,result.size());
        assertEquals("Kicker 8 (You may pay an additional 8 as you play this spell.)",result.get(0));
        assertEquals("Trample.",result.get(1));
        assertEquals("If you paid the kicker cost, Llanowar Elite comes into play with five +1/+1 counters on it.",result.get(2));
    }

    public void testRules_MaraudingKnight() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("Protection from white Marauding Knight gets +1/+1 for each plains");
        card.setRules("your opponents control.");

    	StringList result = card.getRules();
        assertEquals(2,result.size());
        assertEquals("Protection from white",result.get(0));
        assertEquals("Marauding Knight gets +1/+1 for each plains your opponents control.",result.get(1));
    }

    public void testRules_MetathranTransport() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("Flying. Metathran Transport can't be blocked by blue creatures.");
        card.setRules("U: Target creature becomes blue until end of turn.");

    	StringList result = card.getRules();
        assertEquals(3,result.size());
        assertEquals("Flying.",result.get(0));
        assertEquals("Metathran Transport can't be blocked by blue creatures.",result.get(1));
        assertEquals("U: Target creature becomes blue until end of turn.",result.get(2));
    }

    public void testRules_MeteorStorm() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("2RG, Discard two cards at random from your hand: Meteor Storm");
        card.setRules("deals 4 damage to target creature or player.");

    	StringList result = card.getRules();
        assertEquals(1,result.size());
        assertEquals("2RG, Discard two cards at random from your hand: Meteor Storm deals 4 damage to target creature or player.",result.get(0));
    }

    public void testRules_PhyrexianBattleflies() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("Flying. B: Phyrexian Battleflies gets +1/+0 until end of turn.");
        card.setRules("This ability may be played no more than twice each turn.");

    	StringList result = card.getRules();
        assertEquals(2,result.size());
        assertEquals("Flying.",result.get(0));
        assertEquals("B: Phyrexian Battleflies gets +1/+0 until end of turn. This ability may be played no more than twice each turn.",result.get(1));
    }

    public void testRules_CommandoRaid() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("Until end of turn, target creature you control");
        card.setRules("gains \"When this creature deals combat damage to");
        card.setRules("a player, you may have it deal damage equal to its");
        card.setRules("power to target creature that player controls.\"");

    	StringList result = card.getRules();
        assertEquals(1,result.size());
        assertEquals("Until end of turn, target creature you control gains \"When this creature deals combat damage to a player, you may have it deal damage equal to its power to target creature that player controls.\"",result.get(0));
    }

    public void testRules_LavamancersSkill() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("Enchanted creature has \"Tap: This creature deals");
        card.setRules("1 damage to target creature.\" If enchanted");
        card.setRules("creature is a Wizard, it has \"Tap: This creature");
        card.setRules("deals 2 damage to target creature.\"");

    	StringList result = card.getRules();
        assertEquals(1,result.size());
        assertEquals("Enchanted creature has \"Tap: This creature deals 1 damage to target creature.\" If enchanted creature is a Wizard, it has \"Tap: This creature deals 2 damage to target creature.\"",result.get(0));
    }

    public void testRules_ShadowbloodEgg() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("{2}, {T}, Sacrifice Shadowblood Egg: Add {B}{R} to your mana pool. Draw a card.");

    	StringList result = card.getRules();
        assertEquals(1,result.size());
        assertEquals("{2}, {T}, Sacrifice Shadowblood Egg: Add {B}{R} to your mana pool. Draw a card.",result.get(0));
    }

    public void testRules_Annihilate() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("Destroy target nonblack creature. It can't be regenerated. Draw a");
        card.setRules("card.");

    	StringList result = card.getRules();
        assertEquals(2,result.size());
        assertEquals("Destroy target nonblack creature. It can't be regenerated.",result.get(0));
        assertEquals("Draw a card.",result.get(1));
    }

    public void testRules_PearlspearCourier() {
//        CardInfo card = new SpoilerCardInfo("Pearlspear Courier");
        CardInfo card = new SpoilerCardInfo();
        card.setRules("You may choose not to untap Pearlspear Courier during your");
        card.setRules("untap step. 2W, Tap: As long as Pearlspear Courier remains");
        card.setRules("tapped, target Soldier gets +2/+2 and has \"Attacking doesn't");
        card.setRules("cause this creature to tap.\"");

    	StringList result = card.getRules();
        assertEquals(2,result.size());
        assertEquals("You may choose not to untap Pearlspear Courier during your untap step.",result.get(0));
        assertEquals("2W, Tap: As long as Pearlspear Courier remains tapped, target Soldier gets +2/+2 and has \"Attacking doesn't cause this creature to tap.\"",result.get(1));
    }

    public void testRules_AkromasBlessing() {
        CardInfo card = new SpoilerCardInfo();
//        CardInfo card = new SpoilerCardInfo("Akroma's Blessing");
        card.setRules("Creatures you control gain protection from the color of your");
        card.setRules("choice until end of turn. Cycling W (W, Discard this card from");
        card.setRules("your hand: Draw a card.)");
        
        StringList result = card.getRules();
        assertEquals(2,result.size());
        assertEquals("Creatures you control gain protection from the color of your choice until end of turn.",result.get(0)); 
        assertEquals("Cycling W (W, Discard this card from your hand: Draw a card.)",result.get(1));
    }
        
    public void testRules_AkromasVengeance() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("Destroy all artifacts, creatures, and enchantments. Cycling 3");
        card.setRules("(3, Discard this card from your hand: Draw a card.)");
        
        StringList result = card.getRules();
        assertEquals(2,result.size());
        assertEquals("Destroy all artifacts, creatures, and enchantments.",result.get(0)); 
        assertEquals("Cycling 3 (3, Discard this card from your hand: Draw a card.)",result.get(1));
    }        

    public void testRules_Aurification() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("Whenever a creature deals damage to you, put a gold counter on");
        card.setRules("it. Each creature with a gold counter on it is a Wall in addition");
        card.setRules("to its other creature types. (Walls can't attack.) When");
        card.setRules("Aurification leaves play, remove all gold counters from all");
        card.setRules("creatures.");
        
//        StringList result = card.getRules();
//        assertEquals("Whenever a creature deals damage to you, put a gold counter on it.",result.get(0));
//        assertEquals("Each creature with a gold counter on it is a Wall in addition to its other creature types. (Walls can't attack.)",result.get(1));
//        assertEquals("When Aurification leaves play, remove all gold counters from all creatures.",result.get(2));
//        assertEquals(3,result.size());
    }        

    public void testRules_CrudeRampart() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("(Walls can't attack.) Morph 4W (You may play this face down as");
        card.setRules("a 2/2 creature for 3. Turn it face up any time for its morph");
        card.setRules("cost.)");
        
        StringList result = card.getRules();
        assertEquals(2,result.size());
        assertEquals("(Walls can't attack.)",result.get(0));
        assertEquals("Morph 4W (You may play this face down as a 2/2 creature for 3. Turn it face up any time for its morph cost.)",result.get(1));

    }

    public void testRules_DaruHealer() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("Tap: Prevent the next 1 damage that would be dealt to target");
        card.setRules("creature or player this turn. Morph W (You may play this face");
        card.setRules("down as a 2/2 creature for 3. Turn it face up any time for its");
        card.setRules("morph cost.)");
        
        StringList result = card.getRules();
        assertEquals(2,result.size());
        assertEquals("Tap: Prevent the next 1 damage that would be dealt to target creature or player this turn.",result.get(0));
        assertEquals("Morph W (You may play this face down as a 2/2 creature for 3. Turn it face up any time for its morph cost.)",result.get(1));
    }        

    public void testRules_DaruLancer() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("First strike. Morph 2WW (You may play this face down as a");
        card.setRules("2/2 creature for 3. Turn it face up any time for its morph cost.)");
        
        StringList result = card.getRules();
        assertEquals(2,result.size());
        assertEquals("First strike.",result.get(0));
        assertEquals("Morph 2WW (You may play this face down as a 2/2 creature for 3. Turn it face up any time for its morph cost.)",result.get(1));
    }        

    public void testRules_ExaltedAngel() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("Flying. Whenever Exalted Angel deals damage, you gain that");
        card.setRules("much life. Morph 2WW (You may play this face down as a 2/2");
        card.setRules("creature for 3. Turn it face up any time for its morph cost.)");
        
        StringList result = card.getRules();
        assertEquals(3,result.size());
        assertEquals("Flying.",result.get(0));
        assertEquals("Whenever Exalted Angel deals damage, you gain that much life.",result.get(1));
        assertEquals("Morph 2WW (You may play this face down as a 2/2 creature for 3. Turn it face up any time for its morph cost.)",result.get(2));
    }        

    public void testRules_IronfistCrusher() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("Ironfist Crusher may block any number of creatures. Morph 3W");
        card.setRules("(You may play this face down as a 2/2 creature for 3. Turn it");
        card.setRules("face up any time for its morph cost.)");
        
        StringList result = card.getRules();
        assertEquals(2,result.size());
        assertEquals("Ironfist Crusher may block any number of creatures.",result.get(0));
        assertEquals("Morph 3W (You may play this face down as a 2/2 creature for 3. Turn it face up any time for its morph cost.)",result.get(1));
    }        
 
    public void testRules_Backslide() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("Turn target creature with morph face down. Cycling U (U,");
        card.setRules("Discard this card from your hand: Draw a card.)");
        
        StringList result = card.getRules();
        assertEquals(2,result.size());
        assertEquals("Turn target creature with morph face down.",result.get(0));
        assertEquals("Cycling U (U, Discard this card from your hand: Draw a card.)",result.get(1));
    }        

    public void testRules_SpinedBasher() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("Morph 2B (You may play this face down as a 2/2");
        card.setRules("creature for 3. Turn it face up any time for its");
        card.setRules("morph cost.)");
        
        StringList result = card.getRules();
        assertEquals(1,result.size());
        assertEquals("Morph 2B (You may play this face down as a 2/2 creature for 3. Turn it face up any time for its morph cost.)",result.get(0));
    }

    public void testRules_Clone() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("As Clone comes into play, you may choose a creature in play.");
        card.setRules("If you do, Clone comes into play as a copy of that creature.");
        
        StringList result = card.getRules();
        assertEquals(1,result.size());
        assertEquals("As Clone comes into play, you may choose a creature in play. If you do, Clone comes into play as a copy of that creature.",result.get(0));
    }    

    public void testRules_UndeadGladiator() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("1B, Discard a card from your hand: Return Undead");
        card.setRules("Gladiator from your graveyard to your hand. Play");
        card.setRules("this ability only during your upkeep. Cycling 1B (1B,");
        card.setRules("Discard this card from your hand: Draw a card.)");
        
        StringList result = card.getRules();
        assertEquals(2,result.size());
        assertEquals("1B, Discard a card from your hand: Return Undead Gladiator from your graveyard to your hand. Play this ability only during your upkeep.",result.get(0));
        assertEquals("Cycling 1B (1B, Discard this card from your hand: Draw a card.)",result.get(1));
    }    

    public void testRules_Draco() {
        CardInfo card = new SpoilerCardInfo();
        card.setRules("Draco costs 2 less to play for each basic land type among");
        card.setRules("lands you control. Flying. At the beginning of your upkeep,");
        card.setRules("sacrifice Draco unless you pay 10. This cost is reduced by 2");
        card.setRules("for each basic land type among lands you control.");
        
        StringList result = card.getRules();
        assertEquals(3,result.size());
        assertEquals("Draco costs 2 less to play for each basic land type among lands you control.",result.get(0));
        assertEquals("Flying.",result.get(1));
        assertEquals("At the beginning of your upkeep, sacrifice Draco unless you pay 10. This cost is reduced by 2 for each basic land type among lands you control.",result.get(2));
    }    
}
