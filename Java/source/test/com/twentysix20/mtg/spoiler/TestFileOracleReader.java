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
package com.twentysix20.mtg.spoiler;

import java.util.List;
import com.twentysix20.mtg.data.card.*;
import com.twentysix20.mtg.data.mana.ManaCost;
import com.twentysix20.mtg.data.mana.ManaCostFactory;
import com.twentysix20.mtg.data.pt.PowerToughness;
import com.twentysix20.mtg.data.type.Type;
import com.twentysix20.mtg.spoiler.reader.*;
import com.twentysix20.testutil.TestCase2620;
import com.twentysix20.util.FileUtil;

public class TestFileOracleReader extends TestCase2620 {
//  negative-test self-validation     

    public void testGleemax() throws Exception {
        CardReader oRead = new FileOracleReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/oracle-gleemax.txt"));
        List cards = oRead.getEntryList();
        assertSize(1, cards);
        validateGleemax((StandardCard)cards.get(0));
    }

    public void testSplit() throws Exception {
        CardReader oRead = new FileOracleReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/oracle-split.txt"));
        List cards = oRead.getEntryList();
        assertSize(1, cards);
        validateSplit((SplitCard)cards.get(0));
    }

    public void testBrokenSplit() throws Exception {
        CardReader oRead = new FileOracleReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/oracle-split.txt"));
        List cards = oRead.getEntryList();
        assertSize(1, cards);
        validateSplit((SplitCard)cards.get(0));
    }

    public void testOldSplit() throws Exception {
        CardReader oRead = new FileOracleReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/oracle-oldsplit.txt"));
        List cards = oRead.getEntryList();
        assertSize(1, cards);
        validateSplit((OldSplitCard)cards.get(0));
    }

    public void testInstant() throws Exception {
        CardReader oRead = new FileOracleReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/oracle-absorb.txt"));
        List cards = oRead.getEntryList();
        assertSize(1, cards);
        validateAbsorb((StandardCard)cards.get(0));
    }

    public void test2Instants() throws Exception {
        CardReader oRead = new FileOracleReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/oracle-2instants.txt"));
        List cards = oRead.getEntryList();
        assertSize(2, cards);
        validateAbsorb((StandardCard)cards.get(0));
        validateActiveVolcano((StandardCard)cards.get(1));
    }

    public void testSorcery() throws Exception {
        CardReader oRead = new FileOracleReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/oracle-acidrain.txt"));
        List cards = oRead.getEntryList();
        assertSize(1, cards);
        validateAcidRain((StandardCard)cards.get(0));
    }

    public void testNoBracketsOnCost() throws Exception {
        CardReader oRead = new FileOracleReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/oracle-nobrackets.txt"));
        List cards = oRead.getEntryList();
        assertSize(2, cards);
        validateAcidRain((StandardCard)cards.get(0));
        validateAbsorb((StandardCard)cards.get(1));
    }

    public void testOneOfEach() throws Exception {
        CardReader oRead = new FileOracleReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/oracle-oneofeach.txt"));
        List cards = oRead.getEntryList();
        assertSize(2, cards);
        validateAbsorb((StandardCard)cards.get(0));
        validateAcidRain((StandardCard)cards.get(1));
    }

    public void testMultipleEmptyLines() throws Exception {
        CardReader oRead = new FileOracleReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/oracle-multipleemptylines.txt"));
        List cards = oRead.getEntryList();
        assertSize(2, cards);
        validateAbsorb((StandardCard)cards.get(0));
        validateAcidRain((StandardCard)cards.get(1));
    }

    public void testNoEmptyLines() throws Exception {
        CardReader oRead = new FileOracleReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/oracle-noemptylines.txt"));
        List cards = oRead.getEntryList();
        assertSize(1, cards);
        validateAbsorb((StandardCard)cards.get(0));
    }

    public void testEmptyLineTop() throws Exception {
        CardReader oRead = new FileOracleReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/oracle-emptylinetop.txt"));
        List cards = oRead.getEntryList();
        assertSize(1, cards);
        validateAbsorb((StandardCard)cards.get(0));

        oRead = new FileOracleReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/oracle-emptylinestop.txt"));
        cards = oRead.getEntryList();
        assertSize(1, cards);
        validateAbsorb((StandardCard)cards.get(0));
    }

    public void testEmptyLineBottom() throws Exception {
        CardReader oRead = new FileOracleReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/oracle-emptylinebottom.txt"));
        List cards = oRead.getEntryList();
        assertSize(1, cards);
        validateAbsorb((StandardCard)cards.get(0));

        oRead = new FileOracleReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/oracle-emptylinesbottom.txt"));
        cards = oRead.getEntryList();
        assertSize(1, cards);
        validateAbsorb((StandardCard)cards.get(0));
    }

    public void testMultiLineAbilities() throws Exception {
        CardReader oRead = new FileOracleReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/oracle-twomultilines.txt"));
        List cards = oRead.getEntryList();
        assertSize(2, cards);
        validateRouse((StandardCard)cards.get(0));
        validatePrimalBoost((StandardCard)cards.get(1));
    }

    public void testLandsWithAbilities() throws Exception {
        CardReader oRead = new FileOracleReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/oracle-landswithabilities.txt"));
        List cards = oRead.getEntryList();
        assertSize(2, cards);
        validatePollutedDelta((StandardCard)cards.get(0));
        validateWirewoodLodge((StandardCard)cards.get(1));
    }

    public void testBasicLand() throws Exception {
        CardReader oRead = new FileOracleReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/oracle-plains.txt"));
        List cards = oRead.getEntryList();
        assertSize(1, cards);
        validatePlains((StandardCard)cards.get(0));
    }

    public void testSummonTheSchool() throws Exception {
        CardReader oRead = new FileOracleReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/oracle-summonschool.txt"));
        List cards = oRead.getEntryList();
        assertSize(1, cards);
        validateSummonTheSchool((StandardCard)cards.get(0));
    }

    public void testSnowCoveredLand() throws Exception {
        CardReader oRead = new FileOracleReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/oracle-snowcovered.txt"));
        List cards = oRead.getEntryList();
        assertSize(1, cards);
        validateSnowCoveredPlains((StandardCard)cards.get(0));
    }

    public void testDualLand() throws Exception {
        CardReader oRead = new FileOracleReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/oracle-plateau.txt"));
        List cards = oRead.getEntryList();
        assertSize(1, cards);
        validatePlateau((StandardCard)cards.get(0));
    }

    public void testZeroCostArtifact() throws Exception {
        CardReader oRead = new FileOracleReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/oracle-zerocost.txt"));
        List cards = oRead.getEntryList();
        assertSize(2, cards);
        validateBlackLotus((StandardCard)cards.get(0));
        validateOrnithopter((StandardCard)cards.get(1));
    }

    public void testArtifact() throws Exception {
        CardReader oRead = new FileOracleReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/oracle-solring.txt"));
        List cards = oRead.getEntryList();
        assertSize(1, cards);
        validateSolRing((StandardCard)cards.get(0));
    }

    public void testEquipment() throws Exception {
        CardReader oRead = new FileOracleReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/oracle-equipment.txt"));
        List cards = oRead.getEntryList();
        assertSize(1, cards);
        validateFireshrieker((StandardCard)cards.get(0));
    }

    public void testExtraSpaces() throws Exception {
        CardReader oRead = new FileOracleReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/oracle-extraspaces.txt"));
        List cards = oRead.getEntryList();
        assertSize(1, cards);
        validateSolRing((StandardCard)cards.get(0));
    }

    public void testLegendaryArtifact() throws Exception {
        CardReader oRead = new FileOracleReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/oracle-krarksthumb.txt"));
        List cards = oRead.getEntryList();
        assertSize(1, cards);
        validateKrarksThumb((StandardCard)cards.get(0));
    }

    public void testNoCost() throws Exception {
        CardReader oRead = new FileOracleReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/oracle-evermind.txt"));
        List cards = oRead.getEntryList();
        assertSize(1, cards);
        validateEverMind((StandardCard)cards.get(0));
    }

    public void testExtendedCharacters() throws Exception {
        CardReader oRead = new FileOracleReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/oracle-extendedchars.txt"));
        List cards = oRead.getEntryList();
        assertSize(4, cards);
        validateEvincarsJustice((StandardCard)cards.get(0));
        validateAchHansRun((StandardCard)cards.get(1));
        validate_____((StandardCard)cards.get(2));
        validateAetherBurst((StandardCard)cards.get(3));
    }

    public void testEnchantment() throws Exception {
        CardReader oRead = new FileOracleReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/oracle-enchantment.txt"));
        List cards = oRead.getEntryList();
        assertSize(1, cards);
        validateEnchantment((StandardCard)cards.get(0));
    }

    public void testEnchantmentSubtypes() throws Exception {
        CardReader oRead = new FileOracleReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/oracle-enchantmentsubtypes.txt"));
        List cards = oRead.getEntryList();
        assertSize(2, cards);
        validateEnchantmentAura((StandardCard)cards.get(0));
        validateEnchantmentShrine((StandardCard)cards.get(1));
    }

    public void testEnchantWorld() throws Exception {
        CardReader oRead = new FileOracleReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/oracle-enchantworld.txt"));
        List cards = oRead.getEntryList();
        assertSize(1, cards);
        validateEnchantWorld((StandardCard)cards.get(0));
    }

    public void testEnchantPlayer() throws Exception {
        CardReader oRead = new FileOracleReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/oracle-enchantplayer.txt"));
        List cards = oRead.getEntryList();
        assertSize(2, cards);
        validateParadoxHaze((StandardCard)cards.get(0));
        validateCharmSchool((StandardCard)cards.get(1));
    }

    public void testCreatures() throws Exception {
        CardReader oRead = new FileOracleReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/oracle-creatures.txt"));
        List cards = oRead.getEntryList();
        assertSize(8, cards);
        validateJungleTroll((StandardCard)cards.get(0));
        validateDakkonBlackblade((StandardCard)cards.get(1));
        validateHauntingApparition((StandardCard)cards.get(2));
        validateLhurgoyf((StandardCard)cards.get(3));
        validateCrawWurm((StandardCard)cards.get(4));
        validateChaoticGoo((StandardCard)cards.get(5));
        validatePitImp((StandardCard)cards.get(6));
        validatePolarKraken((StandardCard)cards.get(7));
    }

    public void testIgpay() throws Exception {
        CardReader oRead = new FileOracleReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/oracle-uncreatures.txt"));
        List cards = oRead.getEntryList();
        assertSize(4, cards);
        validateIgpay((StandardCard)cards.get(0));
        validateInfernalSpawn((StandardCard)cards.get(1));
        validateOldFogey((StandardCard)cards.get(2));
        validateChickenEgg((StandardCard)cards.get(3));
    }

    public void testArtifactCreatures() throws Exception {
        CardReader oRead = new FileOracleReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/oracle-artifactcreatures.txt"));
        List cards = oRead.getEntryList();
        assertSize(4, cards);
        validateEtchedOracle((StandardCard)cards.get(0));
        validateSpinalParasite((StandardCard)cards.get(1));
        validateShapeshifter((StandardCard)cards.get(2));
        validateBoshIronGolem((StandardCard)cards.get(3));
    }

    public void testHybrids() throws Exception {
        CardReader oRead = new FileOracleReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/oracle-hybrids.txt"));
        List cards = oRead.getEntryList();
        assertSize(10, cards);
        validateDebtorsKnell((StandardCard)cards.get(0));
        validateDovescape((StandardCard)cards.get(1));
        validateIzzetGuildmage((StandardCard)cards.get(2));
        validateLurkingInformant((StandardCard)cards.get(3));
        validateGleancrawler((StandardCard)cards.get(4));
        validateRiotSpikes((StandardCard)cards.get(5));
        validateBorosGuildmage((StandardCard)cards.get(6));
        validateWildCantor((StandardCard)cards.get(7));
        validateCentaurSafeguard((StandardCard)cards.get(8));
        validatePlax((StandardCard)cards.get(9));
    }

    public void testFlip() throws Exception {
        CardReader oRead = new FileOracleReader(FileUtil.getFullPathFileName(this.getClass(),"test/data/oracle-flips.txt"));
        List cards = oRead.getEntryList();
        assertSize(3, cards);
        validateAkkiLavarunner((FlipCard)cards.get(0));
        validateErayo((FlipCard)cards.get(1));
        validateCurseOfTheFirePenguin((FlipCard)cards.get(2));
    }

////////////////////////////////////////////////////////////////////    
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

    private void validateSplit(SplitCard entry) {
        assertEquals("Assault", entry.getName(SplitCard.LEFT));
        assertEquals("Battery", entry.getName(SplitCard.RIGHT));
        assertEquals(ManaCostFactory.create("R"), entry.getCost(SplitCard.LEFT));
        assertEquals(ManaCostFactory.create("3G"), entry.getCost(SplitCard.RIGHT) );
        assertEquals(new Type("Sorcery"), entry.getType(SplitCard.LEFT));
        assertEquals(new Type("Sorcery"), entry.getType(SplitCard.RIGHT));
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT(SplitCard.LEFT));
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT(SplitCard.RIGHT));
        assertSize(1, entry.getRuleText(SplitCard.LEFT));
        assertEquals("Assault deals 2 damage to target creature or player.", entry.getRuleText(SplitCard.LEFT, 0));
        assertSize(1, entry.getRuleText(SplitCard.RIGHT));
        assertEquals("Put a 3/3 green Elephant creature token into play.", entry.getRuleText(SplitCard.RIGHT, 0));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateDebtorsKnell(StandardCard entry) {
        assertEquals("Debtors' Knell", entry.getName());
        assertEquals(ManaCostFactory.create("4(W/B)(W/B)(W/B)"), entry.getCost());
        assertEquals(new Type("Enchantment"), entry.getType());
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT());
        assertSize(2, entry.getRuleText());
        assertEquals("((W/B) can be paid with either W or B.)", entry.getRuleText(0));
        assertEquals("At the beginning of your upkeep, put target creature card in a graveyard into play under your control.", entry.getRuleText(1));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateGleemax(StandardCard entry) {
        assertEquals("Gleemax", entry.getName());
        assertEquals(ManaCostFactory.create("1000000"), entry.getCost());
        assertEquals(new Type("Legendary Artifact"), entry.getType());
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT());
        assertSize(1, entry.getRuleText());
        assertEquals("You choose all targets for all spells and abilities.", entry.getRuleText(0));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateDovescape(StandardCard entry) {
        assertEquals("Dovescape", entry.getName());
        assertEquals(ManaCostFactory.create("3(W/U)(W/U)(W/U)"), entry.getCost());
        assertEquals(new Type("Enchantment"), entry.getType());
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT());
        assertSize(2, entry.getRuleText());
        assertEquals("((W/U) can be paid with either W or U.)", entry.getRuleText(0));
        assertEquals("Whenever a player plays a noncreature spell, counter that spell. That player puts X 1/1 white and blue Bird creature tokens with flying into play, where X is the spell's converted mana cost.", entry.getRuleText(1));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateIzzetGuildmage(StandardCard entry) {
        assertEquals("Izzet Guildmage", entry.getName());
        assertEquals(ManaCostFactory.create("(U/R)(U/R)"), entry.getCost());
        assertEquals(new Type(null,"Creature","Human Wizard"), entry.getType());
        assertEquals(new PowerToughness("2/2"), entry.getPT());
        assertSize(3, entry.getRuleText());
        assertEquals("((U/R) can be paid with either U or R.)", entry.getRuleText(0));
        assertEquals("2U: Copy target instant spell you control with converted mana cost 2 or less. You may choose new targets for the copy.", entry.getRuleText(1));
        assertEquals("2R: Copy target sorcery spell you control with converted mana cost 2 or less. You may choose new targets for the copy.", entry.getRuleText(2));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateLurkingInformant(StandardCard entry) {
        assertEquals("Lurking Informant", entry.getName());
        assertEquals(ManaCostFactory.create("1(U/B)"), entry.getCost());
        assertEquals(new Type(null, "Creature", "Human Rogue"), entry.getType());
        assertEquals(new PowerToughness("1/2"), entry.getPT());
        assertSize(2, entry.getRuleText());
        assertEquals("((U/B) can be paid with either U or B.)", entry.getRuleText(0));
        assertEquals("2, T: Look at the top card of target player's library. You may put that card into that player's graveyard.", entry.getRuleText(1));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateGleancrawler(StandardCard entry) {
        assertEquals("Gleancrawler", entry.getName());
        assertEquals(ManaCostFactory.create("3(B/G)(B/G)(B/G)"), entry.getCost());
        assertEquals(new Type(null, "Creature", "Insect Horror"), entry.getType());
        assertEquals(new PowerToughness("6/6"), entry.getPT());
        assertSize(3, entry.getRuleText());
        assertEquals("((B/G) can be paid with either B or G.)", entry.getRuleText(0));
        assertEquals("Trample", entry.getRuleText(1));
        assertEquals("At the end of your turn, return to your hand all creature cards in your graveyard that were put into your graveyard from play this turn.", entry.getRuleText(2));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateRiotSpikes(StandardCard entry) {
        assertEquals("Riot Spikes", entry.getName());
        assertEquals(ManaCostFactory.create("(B/R)"), entry.getCost());
        assertEquals(new Type(null, "Enchantment", "Aura"), entry.getType());
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT());
        assertSize(3, entry.getRuleText());
        assertEquals("((B/R) can be paid with either B or R.)", entry.getRuleText(0));
        assertEquals("Enchant creature", entry.getRuleText(1));
        assertEquals("Enchanted creature gets +2/-1.", entry.getRuleText(2));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateBorosGuildmage(StandardCard entry) {
        assertEquals("Boros Guildmage", entry.getName());
        assertEquals(ManaCostFactory.create("(R/W)(R/W)"), entry.getCost());
        assertEquals(new Type(null, "Creature","Human Wizard"), entry.getType());
        assertEquals(new PowerToughness("2/2"), entry.getPT());
        assertSize(3, entry.getRuleText());
        assertEquals("((R/W) can be paid with either R or W.)", entry.getRuleText(0));
        assertEquals("1R: Target creature gains haste until end of turn.", entry.getRuleText(1));
        assertEquals("1W: Target creature gains first strike until end of turn.", entry.getRuleText(2));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateWildCantor(StandardCard entry) {
        assertEquals("Wild Cantor", entry.getName());
        assertEquals(ManaCostFactory.create("(R/G)"), entry.getCost());
        assertEquals(new Type(null, "Creature", "Human Druid"), entry.getType());
        assertEquals(new PowerToughness("1/1"), entry.getPT());
        assertSize(2, entry.getRuleText());
        assertEquals("((R/G) can be paid with either R or G.)", entry.getRuleText(0));
        assertEquals("Sacrifice Wild Cantor: Add one mana of any color to your mana pool.", entry.getRuleText(1));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateCentaurSafeguard(StandardCard entry) {
        assertEquals("Centaur Safeguard", entry.getName());
        assertEquals(ManaCostFactory.create("2(G/W)"), entry.getCost());
        assertEquals(new Type(null,"Creature","Centaur Warrior"), entry.getType());
        assertEquals(new PowerToughness("3/1"), entry.getPT());
        assertSize(2, entry.getRuleText());
        assertEquals("((G/W) can be paid with either G or W.)", entry.getRuleText(0));
        assertEquals("When Centaur Safeguard is put into a graveyard from play, you may gain 3 life.", entry.getRuleText(1));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validatePlax(StandardCard entry) {
        assertEquals("Shielding Plax", entry.getName());
        assertEquals(ManaCostFactory.create("2(G/U)"), entry.getCost());
        assertEquals(new Type(null,"Enchantment","Aura"), entry.getType());
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT());
        assertSize(4, entry.getRuleText());
        assertEquals("((G/U) can be paid with either G or U.)", entry.getRuleText(0));
        assertEquals("Enchant creature", entry.getRuleText(1));
        assertEquals("When Shielding Plax comes into play, draw a card.", entry.getRuleText(2));
        assertEquals("Enchanted creature can't be the target of spells or abilities your opponents control.", entry.getRuleText(3));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }
  
    private void validateEtchedOracle(StandardCard entry) {
        assertEquals("Etched Oracle", entry.getName());
        assertEquals(ManaCostFactory.create("4"), entry.getCost());
        assertEquals(new Type("Artifact Creature"), entry.getType());
        assertEquals(new PowerToughness("0/0"), entry.getPT());
        assertSize(2, entry.getRuleText());
        assertEquals("Sunburst (This comes into play with a +1/+1 counter on it for each color of mana used to pay its cost.)", entry.getRuleText(0));
        assertEquals("1, Remove four +1/+1 counters from Etched Oracle: Target player draws three cards.", entry.getRuleText(1));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateSpinalParasite(StandardCard entry) {
        assertEquals("Spinal Parasite", entry.getName());
        assertEquals(ManaCostFactory.create("5"), entry.getCost());
        assertEquals(new Type(null, "Artifact Creature", "Insect"), entry.getType());
        assertEquals(new PowerToughness("-1/-1"), entry.getPT());
        assertSize(2, entry.getRuleText());
        assertEquals("Sunburst (This comes into play with a +1/+1 counter on it for each color of mana used to pay its cost.)", entry.getRuleText(0));
        assertEquals("Remove two +1/+1 counters from Spinal Parasite: Remove a counter from target permanent.", entry.getRuleText(1));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateShapeshifter(StandardCard entry) {
        assertEquals("Shapeshifter", entry.getName());
        assertEquals(ManaCostFactory.create("6"), entry.getCost());
        assertEquals(new Type("Artifact Creature"), entry.getType());
        assertEquals(new PowerToughness("*/7-*"), entry.getPT());
        assertSize(3, entry.getRuleText());
        assertEquals("As Shapeshifter comes into play, choose a number between 0 and 7.", entry.getRuleText(0));
        assertEquals("At the beginning of your upkeep, you may choose a number between 0 and 7.", entry.getRuleText(1));
        assertEquals("Shapeshifter's power is equal to the last chosen number and its toughness is equal to 7 minus that number.", entry.getRuleText(2));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateOrnithopter(StandardCard entry) {
        assertEquals("Ornithopter", entry.getName());
        assertEquals(ManaCostFactory.create("0"), entry.getCost());
        assertEquals(new Type(null, "Artifact Creature", "Thopter"), entry.getType());
        assertEquals(new PowerToughness("0/2"), entry.getPT());
        assertSize(1, entry.getRuleText());
        assertEquals("Flying (This creature can't be blocked except by creatures with flying.)", entry.getRuleText(0));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateBoshIronGolem(StandardCard entry) {
        assertEquals("Bosh, Iron Golem", entry.getName());
        assertEquals(ManaCostFactory.create("8"), entry.getCost());
        assertEquals(new Type("Legendary", "Artifact Creature", "Golem"), entry.getType());
        assertEquals(new PowerToughness("6/7"), entry.getPT());
        assertSize(2, entry.getRuleText());
        assertEquals("Trample", entry.getRuleText(0));
        assertEquals("3R, Sacrifice an artifact: Bosh, Iron Golem deals damage equal to the sacrificed artifact's converted mana cost to target creature or player.", entry.getRuleText(1));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateJungleTroll(StandardCard entry) {
        assertEquals("Jungle Troll", entry.getName());
        assertEquals(ManaCostFactory.create("1RG"), entry.getCost());
        assertEquals(new Type(null, "Creature", "Troll"), entry.getType());
        assertEquals(new PowerToughness("2/1"), entry.getPT());
        assertSize(2, entry.getRuleText());
        assertEquals("R: Regenerate Jungle Troll.", entry.getRuleText(0));
        assertEquals("G: Regenerate Jungle Troll.", entry.getRuleText(1));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateDakkonBlackblade(StandardCard entry) {
        assertEquals("Dakkon Blackblade", entry.getName());
        assertEquals(ManaCostFactory.create("2WUUB"), entry.getCost());
        assertEquals(new Type("Legendary", "Creature", null), entry.getType());
        assertEquals(new PowerToughness("*/*"), entry.getPT());
        assertSize(1, entry.getRuleText());
        assertEquals("Dakkon Blackblade's power and toughness are each equal to the number of lands you control.", entry.getRuleText(0));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateHauntingApparition(StandardCard entry) {
        assertEquals("Haunting Apparition", entry.getName());
        assertEquals(ManaCostFactory.create("1UB"), entry.getCost());
        assertEquals(new Type(null, "Creature", "Spirit"), entry.getType());
        assertEquals(new PowerToughness("1+*/2"), entry.getPT());
        assertSize(3, entry.getRuleText());
        assertEquals("Flying", entry.getRuleText(0));
        assertEquals("As Haunting Apparition comes into play, choose an opponent.", entry.getRuleText(1));
        assertEquals("Haunting Apparition's power is equal to 1 plus the number of green creature cards in the chosen player's graveyard.", entry.getRuleText(2));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateLhurgoyf(StandardCard entry) {
        assertEquals("Lhurgoyf", entry.getName());
        assertEquals(ManaCostFactory.create("2GG"), entry.getCost());
        assertEquals(new Type(null, "Creature", "Lhurgoyf"), entry.getType());
        assertEquals(new PowerToughness("*/1+*"), entry.getPT());
        assertSize(1, entry.getRuleText());
        assertEquals("Lhurgoyf's power is equal to the number of creature cards in all graveyards and its toughness is equal to that number plus 1.", entry.getRuleText(0));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateCrawWurm(StandardCard entry) {
        assertEquals("Craw Wurm", entry.getName());
        assertEquals(ManaCostFactory.create("4GG"), entry.getCost());
        assertEquals(new Type(null, "Creature", "Wurm"), entry.getType());
        assertEquals(new PowerToughness("6/4"), entry.getPT());
        assertSize(0, entry.getRuleText());
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateChaoticGoo(StandardCard entry) {
        assertEquals("Chaotic Goo", entry.getName());
        assertEquals(ManaCostFactory.create("2RR"), entry.getCost());
        assertEquals(new Type(null, "Creature", "Ooze"), entry.getType());
        assertEquals(new PowerToughness("0/0"), entry.getPT());
        assertSize(2, entry.getRuleText());
        assertEquals("Chaotic Goo comes into play with three +1/+1 counters on it.", entry.getRuleText(0));
        assertEquals("At the beginning of your upkeep, you may flip a coin. If you win the flip, add a +1/+1 counter to Chaotic Goo. If you lose the flip, remove a +1/+1 counter from Chaotic Goo.", entry.getRuleText(1));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validatePitImp(StandardCard entry) {
        assertEquals("Pit Imp", entry.getName());
        assertEquals(ManaCostFactory.create("B"), entry.getCost());
        assertEquals(new Type(null, "Creature", "Imp"), entry.getType());
        assertEquals(new PowerToughness("0/1"), entry.getPT());
        assertSize(2, entry.getRuleText());
        assertEquals("Flying", entry.getRuleText(0));
        assertEquals("B: Pit Imp gets +1/+0 until end of turn. Play this ability no more than twice each turn.", entry.getRuleText(1));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validatePolarKraken(StandardCard entry) {
        assertEquals("Polar Kraken", entry.getName());
        assertEquals(ManaCostFactory.create("8UUU"), entry.getCost());
        assertEquals(new Type(null, "Creature", "Kraken"), entry.getType());
        assertEquals(new PowerToughness("11/11"), entry.getPT());
        assertSize(3, entry.getRuleText());
        assertEquals("Polar Kraken comes into play tapped.", entry.getRuleText(0));
        assertEquals("Cumulative upkeep-Sacrifice a land.", entry.getRuleText(1));
        assertEquals("Trample", entry.getRuleText(2));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validatePollutedDelta(StandardCard entry) {
        assertEquals("Polluted Delta", entry.getName());
        assertEquals(ManaCost.costless(), entry.getCost());
        assertEquals(new Type("Land"), entry.getType());
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT());
        assertSize(1, entry.getRuleText());
        assertEquals("{T}, Pay 1 life, Sacrifice Polluted Delta: Search your library for an Island or Swamp card and put it into play. Then shuffle your library.", entry.getRuleText(0));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateWirewoodLodge(StandardCard entry) {
        assertEquals("Wirewood Lodge", entry.getName());
        assertEquals(ManaCost.costless(), entry.getCost());
        assertEquals(new Type("Land"), entry.getType());
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT());
        assertSize(2, entry.getRuleText());
        assertEquals("{T}: Add {1} to your mana pool.", entry.getRuleText(0));
        assertEquals("{G}, {T}: Untap target Elf.", entry.getRuleText(1));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validatePlateau(StandardCard entry) {
        assertEquals("Plateau", entry.getName());
        assertEquals(ManaCost.costless(), entry.getCost());
        assertEquals(new Type(null,"Land","Plains Mountain"), entry.getType());
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT());
        assertSize(0, entry.getRuleText());
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validatePrimalBoost(StandardCard entry) {
        assertEquals("Primal Boost", entry.getName());
        assertEquals(ManaCostFactory.create("2G"), entry.getCost());
        assertEquals(new Type("Instant"), entry.getType());
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT());
        assertSize(3, entry.getRuleText());
        assertEquals("Target creature gets +4/+4 until end of turn.", entry.getRuleText(0));
        assertEquals("Cycling {2}{G} ({2}{G}, Discard this card from your hand: Draw a card.)", entry.getRuleText(1));
        assertEquals("When you cycle Primal Boost, you may have target creature get +1/+1 until end of turn.", entry.getRuleText(2));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validatePlains(StandardCard entry) {
        assertEquals("Plains", entry.getName());
        assertEquals(ManaCost.costless(), entry.getCost());
        assertEquals(new Type("Basic","Land","Plains"), entry.getType());
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT());
        assertSize(0, entry.getRuleText());
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateSnowCoveredPlains(StandardCard entry) {
        assertEquals("Snow-Covered Plains", entry.getName());
        assertEquals(ManaCost.costless(), entry.getCost());
        assertEquals(new Type("Basic","Land","Plains"), entry.getType());
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT());
        assertSize(1, entry.getRuleText());
        assertEquals("Snow-covered", entry.getRuleText(0));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateRouse(StandardCard entry) {
        assertEquals("Rouse", entry.getName());
        assertEquals(ManaCostFactory.create("1B"), entry.getCost());
        assertEquals(new Type("Instant"), entry.getType());
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT());
        assertSize(2, entry.getRuleText());
        assertEquals("If you control a Swamp, you may pay 2 life rather than pay Rouse's mana cost.", entry.getRuleText(0));
        assertEquals("Target creature gets +2/+0 until end of turn.", entry.getRuleText(1));
        assertTrue("Card doesn't self-validate.", entry.isValid());
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateAbsorb(StandardCard entry) {
        assertEquals("Absorb", entry.getName());
        assertEquals(ManaCostFactory.create("WUU"), entry.getCost());
        assertEquals(new Type("Instant"), entry.getType());
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT());
        assertSize(1, entry.getRuleText());
        assertEquals("Counter target spell. You gain 3 life.", entry.getRuleText(0));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateActiveVolcano(StandardCard entry) {
        assertEquals("Active Volcano", entry.getName());
        assertEquals(ManaCostFactory.create("R"), entry.getCost());
        assertEquals(new Type("Instant"), entry.getType());
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT());
        assertSize(1, entry.getRuleText());
        assertEquals("Choose one -- Destroy target blue permanent; or return target Island to its owner's hand.", entry.getRuleText(0));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateAcidRain(StandardCard entry) {
        assertEquals("Acid Rain", entry.getName());
        assertEquals(ManaCostFactory.create("3U"), entry.getCost());
        assertEquals(new Type("Sorcery"), entry.getType());
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT());
        assertSize(1, entry.getRuleText());
        assertEquals("Destroy all Forests.", entry.getRuleText(0));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }
    private void validateBlackLotus(StandardCard entry) {
        assertEquals("Black Lotus", entry.getName());
        assertEquals(ManaCostFactory.create("0"), entry.getCost());
        assertEquals(new Type("Artifact"), entry.getType());
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT());
        assertSize(1, entry.getRuleText());
        assertEquals("{T}, Sacrifice Black Lotus: Add three mana of any one color to your mana pool.", entry.getRuleText(0));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateSolRing(StandardCard entry) {
        assertEquals("Sol Ring", entry.getName());
        assertEquals(ManaCostFactory.create("1"), entry.getCost());
        assertEquals(new Type("Artifact"), entry.getType());
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT());
        assertSize(1, entry.getRuleText());
        assertEquals("{T}: Add {2} to your mana pool.", entry.getRuleText(0));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateKrarksThumb(StandardCard entry) {
        assertEquals("Krark's Thumb", entry.getName());
        assertEquals(ManaCostFactory.create("2"), entry.getCost());
        assertEquals(new Type("Legendary", "Artifact", null), entry.getType());
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT());
        assertSize(1, entry.getRuleText());
        assertEquals("If you would flip a coin, instead flip two coins and ignore one.", entry.getRuleText(0));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateEverMind(StandardCard entry) {
        assertEquals("Evermind", entry.getName());
        assertEquals(ManaCost.costless(), entry.getCost());
        assertEquals(new Type(null, "Instant", "Arcane"), entry.getType());
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT());
        assertSize(4, entry.getRuleText());
        assertEquals("(Spells without mana costs can't be played.)", entry.getRuleText(0));
        assertEquals("Draw a card.", entry.getRuleText(1));
        assertEquals("Evermind is blue.", entry.getRuleText(2));
        assertEquals("Splice onto Arcane 1U (As you play an Arcane spell, you may reveal this card from your hand and pay its splice cost. If you do, add this card's effects to that spell.)", entry.getRuleText(3)); 
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validate_____(StandardCard entry) {
        assertEquals("_____", entry.getName());
        assertEquals(ManaCostFactory.create("1U"), entry.getCost());
        assertEquals(new Type(null, "Creature", "Shapeshifter"), entry.getType());
        assertEquals(new PowerToughness("1/1"), entry.getPT());
        assertSize(1, entry.getRuleText());
        assertEquals("1: This card's name becomes the name of your choice. Play this ability anywhere, anytime.", entry.getRuleText(0));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateAetherBurst(StandardCard entry) {
        assertEquals("Æther Burst", entry.getName());
        assertEquals(ManaCostFactory.create("1U"), entry.getCost());
        assertEquals(new Type("Instant"), entry.getType());
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT());
        assertSize(1, entry.getRuleText());
        assertEquals("Return up to X target creatures to their owners' hands, where X is one plus the number of cards named Æther Burst in all graveyards as you play Æther Burst.", entry.getRuleText(0));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateAchHansRun(StandardCard entry) {
        assertEquals("\"Ach! Hans, Run!\"", entry.getName());
        assertEquals(ManaCostFactory.create("2RRGG"), entry.getCost());
        assertEquals(new Type(null, "Enchantment", null), entry.getType());
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT());
        assertSize(1, entry.getRuleText());
        assertEquals("At the beginning of your upkeep, you may say \"Ach! Hans, run! It's the . . .\" and name a creature card. If you do, search your library for the named card, put it into play, then shuffle your library. That creature has haste. Remove it from the game at end of turn.", entry.getRuleText(0));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateFireshrieker(StandardCard entry) {
        assertEquals("Fireshrieker", entry.getName());
        assertEquals(ManaCostFactory.create("3"), entry.getCost());
        assertEquals(new Type(null, "Artifact", "Equipment"), entry.getType());
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT());
        assertSize(2, entry.getRuleText());
        assertEquals("Equipped creature has double strike. (It deals both first-strike and regular combat damage.)", entry.getRuleText(0));
        assertEquals("Equip 2 (2: Attach to target creature you control. Equip only as a sorcery. This card comes into play unattached and stays in play if the creature leaves play.)", entry.getRuleText(1));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateEvincarsJustice(StandardCard entry) {
        assertEquals("Evincar's Justice", entry.getName());
        assertEquals(ManaCostFactory.create("2BB"), entry.getCost());
        assertEquals(new Type("Sorcery"), entry.getType());
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT());
        assertSize(2, entry.getRuleText());
        assertEquals("Buyback 3 (You may pay 3 in addition to any other costs as you play this spell. If you do, put Evincar's Justice into your hand instead of your graveyard as part of its resolution.)", entry.getRuleText(0));
        assertEquals("Evincar's Justice deals 2 damage to each creature and each player.", entry.getRuleText(1));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateEnchantment(StandardCard entry) {
        assertEquals("Fires of Yavimaya", entry.getName());
        assertEquals(ManaCostFactory.create("1RG"), entry.getCost());
        assertEquals(new Type("Enchantment"), entry.getType());
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT());
        assertSize(2, entry.getRuleText());
        assertEquals("Creatures you control have haste.", entry.getRuleText(0));
        assertEquals("Sacrifice Fires of Yavimaya: Target creature gets +2/+2 until end of turn.", entry.getRuleText(1));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateEnchantmentShrine(StandardCard entry) {
        assertEquals("Honden of Cleansing Fire", entry.getName());
        assertEquals(ManaCostFactory.create("3W"), entry.getCost());
        assertEquals(new Type("Legendary", "Enchantment", "Shrine"), entry.getType());
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT());
        assertSize(1, entry.getRuleText());
        assertEquals("At the beginning of your upkeep, you gain 2 life for each Shrine you control.", entry.getRuleText(0));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateEnchantmentAura(StandardCard entry) {
        assertEquals("Fishliver Oil", entry.getName());
        assertEquals(ManaCostFactory.create("1U"), entry.getCost());
        assertEquals(new Type(null, "Enchantment", "Aura"), entry.getType());
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT());
        assertSize(2, entry.getRuleText());
        assertEquals("Enchant creature (Target a creature as you play this. This card comes into play attached to that creature.)", entry.getRuleText(0));
        assertEquals("Enchanted creature has islandwalk. (This creature is unblockable as long as defending player controls an Island.)", entry.getRuleText(1));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateEnchantWorld(StandardCard entry) {
        assertEquals("Living Plane", entry.getName());
        assertEquals(ManaCostFactory.create("2GG"), entry.getCost());
        assertEquals(new Type("World", "Enchantment", null), entry.getType());
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT());
        assertSize(1, entry.getRuleText());
        assertEquals("All lands are 1/1 creatures that are still lands.", entry.getRuleText(0));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateCharmSchool(StandardCard entry) {
        assertEquals("Charm School", entry.getName());
        assertEquals(ManaCostFactory.create("2W"), entry.getCost());
        assertEquals(new Type(null, "Enchantment", "Aura"), entry.getType());
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT());
        assertSize(4, entry.getRuleText());
        assertEquals("Enchant Player", entry.getRuleText(0));
        assertEquals("When Charm School comes into play, choose a color and balance Charm School on your head.", entry.getRuleText(1));
        assertEquals("Prevent all damage to you of the chosen color.", entry.getRuleText(2));
        assertEquals("If Charm School falls off your head, sacrifice Charm School.", entry.getRuleText(3));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateParadoxHaze(StandardCard entry) {
        assertEquals("Paradox Haze", entry.getName());
        assertEquals(ManaCostFactory.create("2U"), entry.getCost());
        assertEquals(new Type(null, "Enchantment", "Aura"), entry.getType());
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT());
        assertSize(2, entry.getRuleText());
        assertEquals("Enchant player", entry.getRuleText(0));
        assertEquals("At the beginning of enchanted player's first upkeep each turn, that player gets an additional upkeep step after this step.", entry.getRuleText(1));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateSummonTheSchool(StandardCard entry) {
        assertEquals("Summon the School", entry.getName());
        assertEquals(ManaCostFactory.create("3W"), entry.getCost());
        assertEquals(new Type("Tribal", "Sorcery", "Merfolk"), entry.getType());
        assertEquals(PowerToughness.noPowerToughness(), entry.getPT());
        assertSize(2, entry.getRuleText());
        assertEquals("Put two 1/1 blue Merfolk Wizard creature tokens into play.", entry.getRuleText(0));
        assertEquals("Tap four untapped Merfolk you control: Return Summon the School from your graveyard to your hand.", entry.getRuleText(1));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateIgpay(StandardCard entry) {
        assertEquals("Atinlay Igpay", entry.getName());
        assertEquals(ManaCostFactory.create("5W"), entry.getCost());
        assertEquals(new Type(null, "Creature", "Pig"), entry.getType());
        assertEquals(new PowerToughness("3/3"), entry.getPT());
        assertSize(2, entry.getRuleText());
        assertEquals("Oubleday ikestray", entry.getRuleText(0));
        assertEquals("Eneverwhay Atinlay Igpay's ontrollercay eaksspay ay onnay-Igpay-Atinlay ordway, acrificesay Atinlay Igpay.", entry.getRuleText(1));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateInfernalSpawn(StandardCard entry) {
        assertEquals("Infernal Spawn of Evil", entry.getName());
        assertEquals(ManaCostFactory.create("6BBB"), entry.getCost());
        assertEquals(new Type(null, "Creature", "Beast"), entry.getType());
        assertEquals(new PowerToughness("7/7"), entry.getPT());
        assertSize(2, entry.getRuleText());
        assertEquals("Flying, first strike", entry.getRuleText(0));
        assertEquals("1B, Reveal Infernal Spawn of Evil from your hand, Say \"It's coming!\": Infernal Spawn of Evil deals 1 damage to target opponent. Use this ability only during your upkeep and only once each upkeep.", entry.getRuleText(1));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateOldFogey(StandardCard entry) {
        assertEquals("Old Fogey", entry.getName());
        assertEquals(ManaCostFactory.create("GG"), entry.getCost());
        assertEquals(new Type(null, "Creature", "Dinosaur"), entry.getType());
        assertEquals(new PowerToughness("7/7"), entry.getPT());
        assertSize(1, entry.getRuleText());
        assertEquals("Phasing, cumulative upkeep 1, echo, fading 3, bands with other Dinosaurs, protection from Homarids, snow-covered plainswalk, flanking, rampage 2", entry.getRuleText(0));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }

    private void validateChickenEgg(StandardCard entry) {
        assertEquals("Chicken Egg", entry.getName());
        assertEquals(ManaCostFactory.create("1R"), entry.getCost());
        assertEquals(new Type(null, "Creature", "Egg"), entry.getType());
        assertEquals(new PowerToughness("0/1"), entry.getPT());
        assertSize(1, entry.getRuleText());
        assertEquals("During your upkeep, roll a six-sided die. On a 6, sacrifice Chicken Egg and put a Giant Chicken token into play. Treat this token as a 4/4 red creature that counts as a Chicken.", entry.getRuleText(0));
        assertTrue("Card doesn't self-validate.", entry.isValid());
    }
}
