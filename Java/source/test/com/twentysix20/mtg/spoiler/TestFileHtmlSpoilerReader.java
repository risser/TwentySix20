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
* Created on Jun 21, 2006
*/
package com.twentysix20.mtg.spoiler;

import java.util.List;

import com.twentysix20.mtg.data.card.Card;
import com.twentysix20.mtg.data.card.FlipCard;
import com.twentysix20.mtg.data.card.SplitCard;
import com.twentysix20.mtg.data.card.StandardCard;
import com.twentysix20.mtg.data.enums.Rarity;
import com.twentysix20.mtg.data.mana.ManaCost;
import com.twentysix20.mtg.data.mana.ManaCostFactory;
import com.twentysix20.mtg.data.pt.PowerToughness;
import com.twentysix20.mtg.data.type.Type;
import com.twentysix20.mtg.spoiler.reader.FileHtmlSpoilerReader;
import com.twentysix20.testutil.TestCase2620;
import com.twentysix20.util.FileUtil;

public class TestFileHtmlSpoilerReader extends TestCase2620 {

    public void testColdSnap() throws Exception {
        FileHtmlSpoilerReader sRead = new FileHtmlSpoilerReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/spoiler-coldsnap.html"));
        List cards = sRead.getEntryList();
        assertSize(1, cards);
        validateAdarkarValkyrie((StandardCard)cards.get(0), 1);
    }

    public void testOneCreature_NoHeaderFooter() throws Exception {
        FileHtmlSpoilerReader sRead = new FileHtmlSpoilerReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/spoiler-creature-nohead.html"));
        List cards = sRead.getEntryList();
        assertSize(1, cards);
        validateAquastrandSpider((StandardCard)cards.get(0), 1);
    }

    public void testMultilineRulesText() throws Exception {
        FileHtmlSpoilerReader sRead = new FileHtmlSpoilerReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/spoiler-multiline-rules.html"));
        List cards = sRead.getEntryList();
        assertSize(1, cards);
        validateAquastrandSpider((StandardCard)cards.get(0), 1);
    }

    public void testMultilineFlavorText() throws Exception {
        FileHtmlSpoilerReader sRead = new FileHtmlSpoilerReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/spoiler-multiline-flavor.html"));
        List cards = sRead.getEntryList();
        assertSize(1, cards);
        validateBraceForImpact((StandardCard)cards.get(0), 1);
    }

    public void testCreature_ExtraSpaces() throws Exception {
        FileHtmlSpoilerReader sRead = new FileHtmlSpoilerReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/spoiler-creature-extraspaces.html"));
        List cards = sRead.getEntryList();
        assertSize(1, cards);
        validateAquastrandSpider((StandardCard)cards.get(0), 1);
    }

    public void testTwoCreatures_NoHeaderFooter() throws Exception {
        FileHtmlSpoilerReader sRead = new FileHtmlSpoilerReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/spoiler-2creatures-nohead.html"));
        List cards = sRead.getEntryList();
        assertSize(2, cards);
        validateAquastrandSpider((StandardCard)cards.get(0),1);
        validateBronzeBombshell((StandardCard)cards.get(1),2);
    }

    public void testThreeCreatures_NoHeaderFooter_AltBack() throws Exception {
        FileHtmlSpoilerReader sRead = new FileHtmlSpoilerReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/spoiler-3creatures-altback.html"));
        List cards = sRead.getEntryList();
        assertSize(3, cards);
        validateAquastrandSpider((StandardCard)cards.get(0),1);
        validateBeaconHawk((StandardCard)cards.get(1),2);
        validateBronzeBombshell((StandardCard)cards.get(2),3);
    }

    public void testTwoCreatures_DoubleTR() throws Exception {
        try {
            FileHtmlSpoilerReader sRead = new FileHtmlSpoilerReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/spoiler-2creatures-doubletr.html"));
            List cards = sRead.getEntryList();
            fail("Should have thrown IllegalStateException.");
        } catch (IllegalStateException e) {
            //good
        }
    }

    public void testVanillaCreatureWithFlavor() throws Exception {
        FileHtmlSpoilerReader sRead = new FileHtmlSpoilerReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/spoiler-creature-vanillaflavor.html"));
        List cards = sRead.getEntryList();
        assertSize(1, cards);
        validateEagerCadet((StandardCard)cards.get(0),true);
    }

    public void testFlavorlessVanillaCreature() throws Exception {
        FileHtmlSpoilerReader sRead = new FileHtmlSpoilerReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/spoiler-creature-vanillaflavorless.html"));
        List cards = sRead.getEntryList();
        assertSize(1, cards);
        validateEagerCadet((StandardCard)cards.get(0),false);
    }

    public void testInstantAndSorcery() throws Exception {
        FileHtmlSpoilerReader sRead = new FileHtmlSpoilerReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/spoiler-instant-sorcery.html"));
        List cards = sRead.getEntryList();
        assertSize(2, cards);
        validateBondOfAgony((StandardCard)cards.get(0),1);
        validateBraceForImpact((StandardCard)cards.get(1),2);
    }

    public void testLand() throws Exception {
        FileHtmlSpoilerReader sRead = new FileHtmlSpoilerReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/spoiler-land.html"));
        List cards = sRead.getEntryList();
        assertSize(1, cards);
        validateAzoriusChancery((StandardCard)cards.get(0),1);
    }

    public void testSplit() throws Exception {
        FileHtmlSpoilerReader sRead = new FileHtmlSpoilerReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/spoiler-split.html"));
        List cards = sRead.getEntryList();
        assertSize(1, cards);
        validateBoundDetermined((SplitCard)cards.get(0),1);
    }

    public void testLargeSizeHtml() throws Exception {
        FileHtmlSpoilerReader sRead = new FileHtmlSpoilerReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/spoiler-fullHtml.html"));
        List cards = sRead.getEntryList();
        assertSize(3, cards);
        validateAquastrandSpider((StandardCard)cards.get(0), 1);
        validateBondOfAgony((StandardCard)cards.get(1),2);
        validateBoundDetermined((SplitCard)cards.get(2),3);
    }

    public void testFlip() throws Exception {
// NOT CURRENTLY IMPLEMENTED FOR HTML SPOILERS (will need to add checks to prevent flipped rules text attributes from overriding regular attributes, like in Oracle reader.)        
        
//        CardReader oRead = new FileOracleReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/spoiler-flips.txt"));
//        List cards = oRead.getEntryList();
//        assertSize(3, cards);
//        validateAkkiLavarunner((FlipCard)cards.get(0));
//        validateErayo((FlipCard)cards.get(1));
//        validateCurseOfTheFirePenguin((FlipCard)cards.get(2));
    }

    private void validateAkkiLavarunner(FlipCard entry) {
        assertEquals("Akki Lavarunner", entry.getName(FlipCard.NORMAL));
        assertEquals("Tok-Tok, Volcano Born", entry.getName(FlipCard.FLIPPED));
        assertEquals(ManaCostFactory.create("3R"), entry.getCost());
        assertEquals(new Type(null,"Creature","Goblin Warrior"), entry.getType(FlipCard.NORMAL));
        assertEquals(new Type("Legendary","Creature","Goblin Shaman"), entry.getType(FlipCard.FLIPPED));
        assertEquals(new PowerToughness("1/1"), entry.getPT(FlipCard.NORMAL));
        assertEquals(new PowerToughness("2/2"), entry.getPT(FlipCard.FLIPPED));
        assertSize(2, entry.getRuleText(FlipCard.NORMAL));
        assertEquals("Haste", entry.getRuleText(FlipCard.NORMAL, 0));
        assertEquals("Whenever Akki Lavarunner deals damage to an opponent, flip it.", entry.getRuleText(FlipCard.NORMAL, 1));
        assertSize(2, entry.getRuleText(FlipCard.FLIPPED));
        assertEquals("Protection from red", entry.getRuleText(FlipCard.FLIPPED, 0));
        assertEquals("If a red source would deal damage to a player, it deals that much damage plus 1 to that player instead.", entry.getRuleText(FlipCard.FLIPPED, 1));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateErayo(FlipCard entry) {
        assertEquals("Erayo, Soratami Ascendant", entry.getName(FlipCard.NORMAL));
        assertEquals("Erayo's Essence", entry.getName(FlipCard.FLIPPED));
        assertEquals(ManaCostFactory.create("1U"), entry.getCost());
        assertEquals(new Type("Legendary","Creature","Moonfolk Monk"), entry.getType(FlipCard.NORMAL));
        assertEquals(new Type("Legendary","Enchantment",null), entry.getType(FlipCard.FLIPPED));
        assertEquals(new PowerToughness("1/1"), entry.getPT(FlipCard.NORMAL));
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT(FlipCard.FLIPPED));
        assertSize(2, entry.getRuleText(FlipCard.NORMAL));
        assertEquals("Flying", entry.getRuleText(FlipCard.NORMAL, 0));
        assertEquals("Whenever the fourth spell of a turn is played, flip Erayo, Soratami Ascendant.", entry.getRuleText(FlipCard.NORMAL, 1));
        assertSize(1, entry.getRuleText(FlipCard.FLIPPED));
        assertEquals("Whenever an opponent plays a spell for the first time in a turn, counter that spell.", entry.getRuleText(FlipCard.FLIPPED, 0));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateCurseOfTheFirePenguin(FlipCard entry) {
        assertEquals("Curse of the Fire Penguin", entry.getName(FlipCard.NORMAL));
        assertEquals("", entry.getName(FlipCard.FLIPPED));
        assertEquals(ManaCostFactory.create("4RR"), entry.getCost());
        assertEquals(new Type("Enchant Creature"), entry.getType(FlipCard.NORMAL));
        assertEquals(new Type(null,"Creature","Penguin"), entry.getType(FlipCard.FLIPPED));
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT(FlipCard.NORMAL));
        assertEquals(new PowerToughness("6/5"), entry.getPT(FlipCard.FLIPPED));
        assertSize(1, entry.getRuleText(FlipCard.NORMAL));
        assertEquals("Curse of the Fire Penguin consumes and confuses enchanted creature.", entry.getRuleText(FlipCard.NORMAL, 0));
        assertSize(2, entry.getRuleText(FlipCard.FLIPPED));
        assertEquals("Trample", entry.getRuleText(FlipCard.FLIPPED, 0));
        assertEquals("When this creature is put into a graveyard from play, return Curse of the Fire Penguin from your graveyard to play.", entry.getRuleText(FlipCard.FLIPPED, 1));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateBoundDetermined(SplitCard entry, int collNum) {
        assertEquals("Bound", entry.getName(SplitCard.LEFT));
        assertEquals("Determined", entry.getName(SplitCard.RIGHT));
        assertEquals(ManaCostFactory.create("3BG"), entry.getCost(SplitCard.LEFT));
        assertEquals(ManaCostFactory.create("GU"), entry.getCost(SplitCard.RIGHT));
        assertEquals(new Type("Instant"), entry.getType(SplitCard.LEFT));
        assertEquals(new Type("Instant"), entry.getType(SplitCard.RIGHT));
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT(SplitCard.LEFT));
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT(SplitCard.RIGHT));
        assertSize(1, entry.getRuleText(SplitCard.LEFT));
        assertEquals("Sacrifice a creature. Return up to X cards from your graveyard to your hand, where X is the number of colors that creature was. Then remove this card from the game.", entry.getRuleText(SplitCard.LEFT,0));
        assertSize(2, entry.getRuleText(SplitCard.RIGHT));
        assertEquals("Other spells you control can't be countered by spells or abilities this turn.", entry.getRuleText(SplitCard.RIGHT,0));
        assertEquals("Draw a card.", entry.getRuleText(SplitCard.RIGHT,1));
        assertEquals("",entry.getFlavorText());
        assertEquals(Rarity.RARE,entry.getRarity());
        assertEquals("Jim Nelson",entry.getArtist());
        assertCollectorNumber(entry, collNum);
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateAzoriusChancery(StandardCard entry, int collNum) {
        assertEquals("Azorius Chancery", entry.getName());
        assertEquals(ManaCost.costless(), entry.getCost());
        assertEquals(new Type("Land"), entry.getType());
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT());
        assertSize(3, entry.getRuleText());
        assertEquals("Azorius Chancery comes into play tapped.", entry.getRuleText(0));
        assertEquals("When Azorius Chancery comes into play, return a land you control to its owner's hand.", entry.getRuleText(1));
        assertEquals("Tap: Add WU to your mana pool.", entry.getRuleText(2));
        assertEquals("",entry.getFlavorText());
        assertEquals(Rarity.COMMON,entry.getRarity());
        assertEquals("John Avon",entry.getArtist());
        assertCollectorNumber(entry, collNum);
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }
    
    private void validateAquastrandSpider(StandardCard entry, int collNum) {
        assertEquals("Aquastrand Spider", entry.getName());
        assertEquals(ManaCostFactory.create("1G"), entry.getCost());
        assertEquals(new Type(null, "Creature", "Spider Mutant"), entry.getType());
        assertEquals(new PowerToughness("0/0"), entry.getPT());
        assertSize(2, entry.getRuleText());
        assertEquals("Graft 2 (This creature comes into play with two +1/+1 counters on it. Whenever another creature comes into play, you may move a +1/+1 counter from this creature onto it.)", entry.getRuleText(0));
        assertEquals("G: Target creature with a +1/+1 counter on it can block as though it had flying this turn.", entry.getRuleText(1));
        assertEquals("",entry.getFlavorText());
        assertEquals(Rarity.COMMON,entry.getRarity());
        assertEquals("Dany Orizio",entry.getArtist());
        assertCollectorNumber(entry, collNum);
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateAdarkarValkyrie(StandardCard entry, int collNum) {
        assertEquals("Adarkar Valkyrie", entry.getName());
        assertEquals(ManaCostFactory.create("4WW"), entry.getCost());
        assertEquals(new Type("Snow", "Creature", "Angel"), entry.getType());
        assertEquals(new PowerToughness("4/5"), entry.getPT());
        assertSize(2, entry.getRuleText());
        assertEquals("Flying, vigilance", entry.getRuleText(0));
        assertEquals("Tap: When target creature other than Adarkar Valkyrie is put into a graveyard this turn, return that card to play under your control.", entry.getRuleText(1));
        assertEquals("She doesn't escort the dead to the afterlife, but instead raises them to fight and die again.",entry.getFlavorText());
        assertEquals(Rarity.RARE,entry.getRarity());
        assertEquals("Jeremy Jarvis",entry.getArtist());
        assertCollectorNumber(entry, collNum);
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateBeaconHawk(StandardCard entry, int collNum) {
        assertEquals("Beacon Hawk", entry.getName());
        assertEquals(ManaCostFactory.create("1W"), entry.getCost());
        assertEquals(new Type(null, "Creature", "Bird"), entry.getType());
        assertEquals(new PowerToughness("1/1"), entry.getPT());
        assertSize(3, entry.getRuleText());
        assertEquals("Flying", entry.getRuleText(0));
        assertEquals("Whenever Beacon Hawk deals combat damage to a player, you may untap target creature.", entry.getRuleText(1));
        assertEquals("W: Beacon Hawk gets +0/+1 until end of turn.", entry.getRuleText(2));
        assertEquals("",entry.getFlavorText());
        assertEquals(Rarity.COMMON,entry.getRarity());
        assertEquals("William Simpson",entry.getArtist());
        assertCollectorNumber(entry, collNum);
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateBronzeBombshell(StandardCard entry, int collNum) {
        assertEquals("Bronze Bombshell", entry.getName());
        assertEquals(ManaCostFactory.create("4"), entry.getCost());
        assertEquals(new Type(null, "Artifact Creature", "Construct"), entry.getType());
        assertEquals(new PowerToughness("4/1"), entry.getPT());
        assertSize(1, entry.getRuleText());
        assertEquals("When a player other than Bronze Bombshell's owner controls it, that player sacrifices it. If the player does, Bronze Bombshell deals 7 damage to him or her.", entry.getRuleText(0));
        assertEquals("\"Ooh, shiny! Let's pull off the chain and take her with us.\"\n-Ukl, Gruul raider, last words",entry.getFlavorText());
        assertEquals(Rarity.RARE,entry.getRarity());
        assertEquals("Martina Pilcerova",entry.getArtist());
        assertCollectorNumber(entry, collNum);
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateEagerCadet(StandardCard entry, boolean withFlavor) {
        assertEquals("Eager Cadet", entry.getName());
        assertEquals(ManaCostFactory.create("W"), entry.getCost());
        assertEquals(new Type(null, "Creature", "Human Soldier"), entry.getType());
        assertEquals(new PowerToughness("1/1"), entry.getPT());
        assertSize(0, entry.getRuleText());
        if (withFlavor)
            assertEquals("\"Training? Seeing my crops burnt to cinders was all the 'training' I needed.\"",entry.getFlavorText());
        else
            assertEquals("",entry.getFlavorText());
        assertEquals(Rarity.COMMON,entry.getRarity());
        assertEquals("Scott M. Fischer",entry.getArtist());
        assertCollectorNumber(entry, 1);
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateBondOfAgony(StandardCard entry, int collNum) {
        assertEquals("Bond of Agony", entry.getName());
        assertEquals(ManaCostFactory.create("XB"), entry.getCost());
        assertEquals(new Type("Sorcery"), entry.getType());
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT());
        assertSize(2, entry.getRuleText());
        assertEquals("As an additional cost to play Bond of Agony, pay X life.", entry.getRuleText(0));
        assertEquals("Each other player loses X life.", entry.getRuleText(1));
        assertEquals("The Rakdos are unique in designing torture equipment they can operate while \"suffering\" alongside their victims.",entry.getFlavorText());
        assertEquals(Rarity.UNCOMMON,entry.getRarity());
        assertEquals("Luca Zontini",entry.getArtist());
        assertCollectorNumber(entry, collNum);
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateBraceForImpact(StandardCard entry, int collNum) {
        assertEquals("Brace for Impact", entry.getName());
        assertEquals(ManaCostFactory.create("4W"), entry.getCost());
        assertEquals(new Type("Instant"), entry.getType());
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT());
        assertSize(1, entry.getRuleText());
        assertEquals("Prevent all damage that would be dealt to target multicolored creature this turn. For each 1 damage prevented this way, put a +1/+1 counter on that creature.", entry.getRuleText(0));
        assertEquals("Flesh can be tempered by spellcraft and forged ever stronger by the hammers of foes.",entry.getFlavorText());
        assertEquals(Rarity.UNCOMMON,entry.getRarity());
        assertEquals("Dan Scott",entry.getArtist());
        assertCollectorNumber(entry, collNum);
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void assertCollectorNumber(Card entry, int collNum) {
        assertEquals(Integer.toString(collNum), entry.getCollectorNumber());
    }
}
