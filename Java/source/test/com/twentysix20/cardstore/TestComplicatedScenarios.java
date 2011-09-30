package com.twentysix20.cardstore;


import static junit.framework.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;

import com.twentysix20.cardstore.list.ShoppingList;
import com.twentysix20.cardstore.order.MultiVendorOrder;
import com.twentysix20.cardstore.order.Order;
import com.twentysix20.cardstore.postage.BasePlusPerCardPostage;
import com.twentysix20.cardstore.postage.FlatRatePostage;
import com.twentysix20.cardstore.shopper.Shopper;
import com.twentysix20.cardstore.strategy.Unfiltered;
import com.twentysix20.cardstore.supplyfilter.CheapestCardPerStoreFilter;
import com.twentysix20.cardstore.supplyrecord.StandardSupplyRecord;
import com.twentysix20.cardstore.supplyrecord.SupplyRecords;
import com.twentysix20.cardstore.vendor.Vendor;


public class TestComplicatedScenarios {
	private static final String MOMIR_SIMIC_VISIONARY = "Momir, Simic Visionary";
	private static final String EXPERIMENT_KRAJ = "Experiment Kraj";
	private static final String KARMIC_JUSTIC = "Karmic Justic";
	private static final String DRAGON_BROODMOTHER = "Dragon Broodmother";
	private static final String WRATH_OF_GOD = "Wrath of God";
	private static final String DAY_OF_JUDGMENT = "Day of Judgment";
	private static final String STORM_HERD = "Storm Herd";

	@Test public void bigListOfStuff() {
		Vendor vendorStrikeZoneOnline = new Vendor("StrikeZoneOnline",new FlatRatePostage(99));
		Vendor vendorGameTimeCC = new Vendor("GameTimeCC",new BasePlusPerCardPostage(300, 25));
		Vendor vendorShuffleAndCut = new Vendor("ShuffleAndCut",new FlatRatePostage(254));
		Vendor vendorTrollAndToad = new Vendor("TrollAndToad",new FlatRatePostage(99));
		Vendor vendorChannelFireball = new Vendor("ChannelFireball",new FlatRatePostage(239));
		Vendor vendorAlterReality = new Vendor("AlterReality",new BasePlusPerCardPostage(184, 5));

		SupplyRecords records = new SupplyRecords();
		records.add(new StandardSupplyRecord(STORM_HERD, vendorStrikeZoneOnline, "set", "mint", 1, "0.44"));
		records.add(new StandardSupplyRecord(STORM_HERD, vendorGameTimeCC, "set", "mint", 1, "0.50"));
		records.add(new StandardSupplyRecord(STORM_HERD, vendorShuffleAndCut, "set", "mint", 1, "0.58"));
		records.add(new StandardSupplyRecord(STORM_HERD, vendorTrollAndToad, "set", "mint", 1, "0.63"));
		
		records.add(new StandardSupplyRecord(EXPERIMENT_KRAJ, vendorGameTimeCC, "set", "mint", 1, "0.50"));
		records.add(new StandardSupplyRecord(EXPERIMENT_KRAJ, vendorShuffleAndCut, "set", "mint", 1, "0.68"));
		records.add(new StandardSupplyRecord(EXPERIMENT_KRAJ, vendorTrollAndToad, "set", "mint", 1, "0.85"));
		records.add(new StandardSupplyRecord(EXPERIMENT_KRAJ, vendorStrikeZoneOnline, "set", "mint", 1, "0.45"));

		records.add(new StandardSupplyRecord(DAY_OF_JUDGMENT, vendorShuffleAndCut, "M2011", "mint", 1, "3.95"));
		records.add(new StandardSupplyRecord(DAY_OF_JUDGMENT, vendorShuffleAndCut, "Zendikar", "mint", 1, "4.94"));
		records.add(new StandardSupplyRecord(DAY_OF_JUDGMENT, vendorTrollAndToad, "Zendikar", "mint", 1, "4.27"));
		records.add(new StandardSupplyRecord(DAY_OF_JUDGMENT, vendorTrollAndToad, "M2011", "mint", 1, "3.73"));
		records.add(new StandardSupplyRecord(DAY_OF_JUDGMENT, vendorStrikeZoneOnline, "M2011", "mint", 1, "4.26"));
		records.add(new StandardSupplyRecord(DAY_OF_JUDGMENT, vendorStrikeZoneOnline, "Zendikar", "mint-foil", 1, "7.26"));
		records.add(new StandardSupplyRecord(DAY_OF_JUDGMENT, vendorChannelFireball, "Zendikar", "mint", 1, "3.40"));
		records.add(new StandardSupplyRecord(DAY_OF_JUDGMENT, vendorChannelFireball, "M2011", "mint", 1, "3.49"));
		records.add(new StandardSupplyRecord(DAY_OF_JUDGMENT, vendorAlterReality, "Zendikar", "mint", 1, "3.41"));
		records.add(new StandardSupplyRecord(DAY_OF_JUDGMENT, vendorAlterReality, "M2011", "mint", 1, "2.98"));

		records.add(new StandardSupplyRecord(WRATH_OF_GOD, vendorGameTimeCC, "Seventh", "mint", 1, "8.95"));

		records.add(new StandardSupplyRecord(WRATH_OF_GOD, vendorShuffleAndCut, "Fourth", "mint", 1, "10.99"));
		records.add(new StandardSupplyRecord(WRATH_OF_GOD, vendorShuffleAndCut, "Seventh", "mint", 1, "11.99"));
		records.add(new StandardSupplyRecord(WRATH_OF_GOD, vendorShuffleAndCut, "Eighth", "mint", 1, "9.99"));
		records.add(new StandardSupplyRecord(WRATH_OF_GOD, vendorShuffleAndCut, "Ninth", "mint", 1, "9.99"));
		records.add(new StandardSupplyRecord(WRATH_OF_GOD, vendorShuffleAndCut, "Tenth", "mint", 1, "9.99"));

		records.add(new StandardSupplyRecord(WRATH_OF_GOD, vendorTrollAndToad, "Fourth", "mint", 1, "9.62"));
		records.add(new StandardSupplyRecord(WRATH_OF_GOD, vendorTrollAndToad, "Seventh", "mint", 1, "8.55"));
		records.add(new StandardSupplyRecord(WRATH_OF_GOD, vendorTrollAndToad, "Eighth", "mint", 1, "10.69"));
		records.add(new StandardSupplyRecord(WRATH_OF_GOD, vendorTrollAndToad, "Ninth", "mint", 1, "9.62"));

		records.add(new StandardSupplyRecord(WRATH_OF_GOD, vendorStrikeZoneOnline, "Fourth","mint", 1, "10.69"));
		records.add(new StandardSupplyRecord(WRATH_OF_GOD, vendorStrikeZoneOnline, "Seventh","mint", 1, "13.36"));
		records.add(new StandardSupplyRecord(WRATH_OF_GOD, vendorStrikeZoneOnline, "Eighth","mint", 1, "11.76"));
		records.add(new StandardSupplyRecord(WRATH_OF_GOD, vendorStrikeZoneOnline, "Ninth", "mint", 1, "21.40"));
		records.add(new StandardSupplyRecord(WRATH_OF_GOD, vendorStrikeZoneOnline, "Tenth", "mint", 1, "9.42"));

		records.add(new StandardSupplyRecord(WRATH_OF_GOD, vendorChannelFireball, "Fourth", "mint", 1, "9.99"));
		records.add(new StandardSupplyRecord(WRATH_OF_GOD, vendorChannelFireball, "Seventh", "mint", 1, "9.99"));
		records.add(new StandardSupplyRecord(WRATH_OF_GOD, vendorChannelFireball, "Eighth", "mint", 1, "9.99"));
		records.add(new StandardSupplyRecord(WRATH_OF_GOD, vendorChannelFireball, "Ninth", "mint", 1, "9.99"));
		records.add(new StandardSupplyRecord(WRATH_OF_GOD, vendorChannelFireball, "Tenth", "mint", 1, "9.49"));

		records.add(new StandardSupplyRecord(WRATH_OF_GOD, vendorAlterReality, "Fourth", "mint", 1, "9.99"));
		records.add(new StandardSupplyRecord(WRATH_OF_GOD, vendorAlterReality, "Eighth", "mint", 1, "9.99"));
		records.add(new StandardSupplyRecord(WRATH_OF_GOD, vendorAlterReality, "Ninth", "mint", 1, "9.99"));
		records.add(new StandardSupplyRecord(WRATH_OF_GOD, vendorAlterReality, "Tenth", "mint", 1, "11.99"));

		records.add(new StandardSupplyRecord(MOMIR_SIMIC_VISIONARY, vendorShuffleAndCut, "set", "mint", 1, "0.99"));
		records.add(new StandardSupplyRecord(MOMIR_SIMIC_VISIONARY, vendorStrikeZoneOnline, "set", "mint", 1, "0.66"));

		records.add(new StandardSupplyRecord(KARMIC_JUSTIC, vendorShuffleAndCut, "set", "mint", 1, "3.26"));
		records.add(new StandardSupplyRecord(KARMIC_JUSTIC, vendorStrikeZoneOnline, "set", "mint", 1, "3.20"));
		records.add(new StandardSupplyRecord(KARMIC_JUSTIC, vendorChannelFireball, "set", "mint", 1, "2.49"));
		records.add(new StandardSupplyRecord(KARMIC_JUSTIC, vendorAlterReality, "set", "mint", 1, "2.48"));

		records.add(new StandardSupplyRecord(DRAGON_BROODMOTHER, vendorTrollAndToad, "set", "mint", 1, "3.20"));
		records.add(new StandardSupplyRecord(DRAGON_BROODMOTHER, vendorStrikeZoneOnline, "set", "mint", 1, "5.61"));
		records.add(new StandardSupplyRecord(DRAGON_BROODMOTHER, vendorChannelFireball, "set", "mint", 1, "2.47"));

		Shopper shopper = new Shopper(new Unfiltered(), CheapestCardPerStoreFilter.instance().filterRecords(records));
		Set<MultiVendorOrder> results = shopper.shop(new ShoppingList(DRAGON_BROODMOTHER,KARMIC_JUSTIC,MOMIR_SIMIC_VISIONARY,STORM_HERD,EXPERIMENT_KRAJ,DAY_OF_JUDGMENT,WRATH_OF_GOD));

		assertEquals(1,results.size());
		Order result = results.iterator().next();

		Order expectedOrder = new MultiVendorOrder(
				new StandardSupplyRecord(DRAGON_BROODMOTHER, vendorTrollAndToad, "set", "mint", 1, "3.20"),
				new StandardSupplyRecord(KARMIC_JUSTIC, vendorStrikeZoneOnline, "set", "mint", 1, "3.20"),
				new StandardSupplyRecord(MOMIR_SIMIC_VISIONARY, vendorStrikeZoneOnline, "set", "mint", 1, "0.66"),
				new StandardSupplyRecord(DAY_OF_JUDGMENT, vendorTrollAndToad, "M2011", "mint", 1, "3.73"),
				new StandardSupplyRecord(STORM_HERD, vendorStrikeZoneOnline, "set", "mint", 1, "0.44"),
				new StandardSupplyRecord(EXPERIMENT_KRAJ, vendorStrikeZoneOnline, "set", "mint", 1, "0.45"),
				new StandardSupplyRecord(WRATH_OF_GOD, vendorTrollAndToad, "Seventh", "mint", 1, "8.55")
				);
		assertEquals(expectedOrder,result);
	}
}
