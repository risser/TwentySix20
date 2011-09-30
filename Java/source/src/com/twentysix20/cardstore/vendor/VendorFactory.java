package com.twentysix20.cardstore.vendor;

import java.util.HashMap;
import java.util.Map;

import com.twentysix20.cardstore.postage.AdventuresOnPostage;
import com.twentysix20.cardstore.postage.BasePlusOneAndAHalfPerCardPostage;
import com.twentysix20.cardstore.postage.BasePlusPerCardForEachInstancePostage;
import com.twentysix20.cardstore.postage.BasePlusPerCardGroupPostage;
import com.twentysix20.cardstore.postage.BasePlusPerCardPostage;
import com.twentysix20.cardstore.postage.BasePlusPerCardTypePostage;
import com.twentysix20.cardstore.postage.BasePlusPerCardWithCapPostage;
import com.twentysix20.cardstore.postage.FlatRatePostage;
import com.twentysix20.cardstore.postage.MTGChicagoPostage;
import com.twentysix20.cardstore.postage.Postage;
import com.twentysix20.cardstore.postage.PostageCache;
import com.twentysix20.cardstore.postage.TemporaryFlatRatePostage;
import com.twentysix20.cardstore.postage.TrollAndToadPostage;

public class VendorFactory {
	static private Postage DEFAULT_POSTAGE = new FlatRatePostage(200);
	static private Map<String,Vendor> vendorMap = new HashMap<String,Vendor>();
	static {
		addVendor(new Vendor("ABUGames",new FlatRatePostage(299)));
		addVendor(new Vendor("adventuresON",new PostageCache(new AdventuresOnPostage())));
		addVendor(new Vendor("AllCCG",new BasePlusPerCardTypePostage(271,1)));
		addVendor(new Vendor("Alter Reality Games",new PostageCache(new BasePlusPerCardWithCapPostage(472, 5, 734))));
//		addVendor(new Vendor("Alter Reality Games",new BasePlusPerCardWithCapPostage(184, 5, 734)));
		addVendor(new Vendor("Anthem Games",new FlatRatePostage(222)));
		addVendor(new Vendor("Cape Fear Games",new PostageCache(new BasePlusPerCardPostage(306,3))));
//		addVendor(new Vendor("Cape Fear Games",new PostageCache(new BasePlusPerCardPostage(165,3)));
		addVendor(new Vendor("CardAddiction",new FlatRatePostage(400)));
		addVendor(new Vendor("Ccg Bot",new FlatRatePostage(299)));
		addVendor(new Vendor("CCGHouse",new FlatRatePostage(304)));
		addVendor(new Vendor("ChannelFireball",new BasePlusPerCardTypePostage(239,1,4)));
		addVendor(new Vendor("CoolStuffInc",new FlatRatePostage(199)));
		addVendor(new Vendor("Pastimes",new FlatRatePostage(229)));
//		addVendor(new Vendor("Pastimes",new FlatRatePostage(122)));
		addVendor(new Vendor("Comic Asylum",new PostageCache(new BasePlusPerCardPostage(234,2))));
		addVendor(new Vendor("CoreTCG",new FlatRatePostage(299)));
		addVendor(new Vendor("EmpireGamesOnline",new FlatRatePostage(271)));
		addVendor(new Vendor("Epic Games Online",new FlatRatePostage(279)));
		addVendor(new Vendor("EpicGamesOnline",new FlatRatePostage(279)));
		addVendor(new Vendor("Game Cafe",new PostageCache(new BasePlusPerCardPostage(315, 3))));
//		addVendor(new Vendor("Game Cafe",new PostageCache(new BasePlusPerCardPostage(184, 3)));
//		addVendor(new Vendor("Gamers' Inn",new FlatRatePostage(198))); 9/17/10
		addVendor(new Vendor("Gamers' Inn",new FlatRatePostage(244)));
//		addVendor(new Vendor("GameTimeCC",new PostageCache(new BasePlusPerCardPostage(300, 25)));4/22/01
		addVendor(new Vendor("GameTimeCC",new PostageCache(new BasePlusPerCardPostage(349, 25))));
		addVendor(new Vendor("GamingETC",new FlatRatePostage(239)));
		addVendor(new Vendor("GlobalMTG",new FlatRatePostage(281)));
//		addVendor(new Vendor("GlobalMTG",new FlatRatePostage(198)));
		addVendor(new Vendor("GonetoPlaid",new FlatRatePostage(201)));
		addVendor(new Vendor("Hobby Goblins",new FlatRatePostage(299)));
		addVendor(new Vendor("HotsauceGames",new FlatRatePostage(339)));
//		addVendor(new Vendor("HotsauceGames",new FlatRatePostage(153)));
		addVendor(new Vendor("Magic on a Budget",new FlatRatePostage(195))); // not completely tested
		addVendor(new Vendor("MagicInferno",new FlatRatePostage(271)));
//		addVendor(new Vendor("MagicInferno",new FlatRatePostage(198)));
		addVendor(new Vendor("Matchplay",new FlatRatePostage(276)));
//		addVendor(new Vendor("Matchplay",new FlatRatePostage(153)));
		addVendor(new Vendor("MTGChicago",new PostageCache(new MTGChicagoPostage())));
		addVendor(new Vendor("MTGFanatic",new FlatRatePostage(198)));
		addVendor(new Vendor("OldSchoolGaming",new PostageCache(new BasePlusPerCardPostage(321,3))));
		addVendor(new Vendor("OshKoshMagic ",new FlatRatePostage(299)));
		addVendor(new Vendor("PaperHeroes",new BasePlusPerCardTypePostage(245,1)));
		addVendor(new Vendor("Raven's Nest Games",new TemporaryFlatRatePostage(300))); // not fully vetted
		addVendor(new Vendor("Ready To Play Games",new PostageCache(new BasePlusPerCardPostage(242,17))));
		addVendor(new Vendor("ShuffleandCut",new BasePlusPerCardForEachInstancePostage(254, 5, 4)));
		addVendor(new Vendor("StrikeZoneOnline",new FlatRatePostage(99)));
		addVendor(new Vendor("SuperGamesInc",new FlatRatePostage(296)));
		addVendor(new Vendor("Tara Angel’s Magic ",new FlatRatePostage(305)));
		addVendor(new Vendor("The Mana Dump",new PostageCache(new BasePlusPerCardPostage(222,1))));
		addVendor(new Vendor("The MTG Place",new PostageCache(new BasePlusPerCardPostage(180,3))));
		addVendor(new Vendor("TheManaCrypt",new PostageCache(new BasePlusPerCardPostage(371,1))));
//		addVendor(new Vendor("TheManaCrypt",new PostageCache(new BasePlusPerCardPostage(265,1)));
		addVendor(new Vendor("TJ Collectibles",new FlatRatePostage(298)));
		addVendor(new Vendor("Top Deck Hobbies",new PostageCache(new BasePlusPerCardPostage(308,1))));
//		addVendor(new Vendor("Top Deck Hobbies",new PostageCache(new BasePlusPerCardPostage(224,1)));
		addVendor(new Vendor("TrollandToad",new PostageCache(new TrollAndToadPostage())));
		addVendor(new Vendor("Untapped Games",new PostageCache(new BasePlusPerCardPostage(148,5))));
		addVendor(new Vendor("VolcanicMagic",new FlatRatePostage(122)));
		addVendor(new Vendor("XtremeGames",new FlatRatePostage(271)));
//		addVendor(new Vendor("XtremeGames",new FlatRatePostage(197)));
		addVendor(new Vendor("IDeal808",new PostageCache(new BasePlusPerCardGroupPostage(321,17,17))));
		addVendor(new Vendor("The Mana Fix",new PostageCache(new BasePlusOneAndAHalfPerCardPostage(279))));
		addVendor(new Vendor("Crossroad Games",new PostageCache(new BasePlusPerCardPostage(281,10))));
		addVendor(new Vendor("CCG Unlimited",new FlatRatePostage(571)));// This may not be exactly correct, but at 5.71 who gives a crap?
		addVendor(new Vendor("White Lion Games",new FlatRatePostage(19999)));// I don't trust them.  They have exactly four of everything??
		addVendor(new Vendor("MagicStronghold",new FlatRatePostage(400)));

		addVendor(new Vendor("Jax Card Singles",new TemporaryFlatRatePostage(389)));//50=389;55=4.15;60=4.15;70=4.42;100=4.68
		addVendor(new Vendor("Lucky's Card Shop",new TemporaryFlatRatePostage(271)));
		addVendor(new Vendor("CardKeiser",new TemporaryFlatRatePostage(317)));//43=317;44=330;55=330;60=344
		addVendor(new Vendor("MuGu Games",new TemporaryFlatRatePostage(271)));//9=271;10=2.88;11=2.88;12=305;13=305;14=322;15=322;16=339;17=339;18=357;22=373
		addVendor(new Vendor("Asgard Games",new TemporaryFlatRatePostage(271)));
		addVendor(new Vendor("Gamers Sanctuary",new TemporaryFlatRatePostage(246)));
		addVendor(new Vendor("The Only Game In Town",new TemporaryFlatRatePostage(371)));
		addVendor(new Vendor("Amazing Discoveries",new TemporaryFlatRatePostage(346)));
		addVendor(new Vendor("MTGEmporium",new TemporaryFlatRatePostage(378)));
		addVendor(new Vendor("Run MTG",new TemporaryFlatRatePostage(348)));
		addVendor(new Vendor("MTG Deals",new TemporaryFlatRatePostage(246)));
		addVendor(new Vendor("MTG Rogue Players",new TemporaryFlatRatePostage(299)));
		addVendor(new Vendor("Tabletop Arena",new TemporaryFlatRatePostage(271)));
		addVendor(new Vendor("Manawerx",new TemporaryFlatRatePostage(402)));
		addVendor(new Vendor("BattleZone Games",new TemporaryFlatRatePostage(19999))); // can't get these guys to work...
	}

	private static void addVendor(Vendor vendor) {
		vendorMap.put(vendor.name(),vendor);
	}

	static public Vendor find(String name) {
		Vendor vendor = vendorMap.get(name);
		if (vendor == null) {
			System.out.println("Vendor '"+name+"' has not been created.  Creating with default postage ("+DEFAULT_POSTAGE+").");
			vendor = new Vendor(name,DEFAULT_POSTAGE);
			vendorMap.put(name, vendor);
		}
		return vendor;
	}
}
