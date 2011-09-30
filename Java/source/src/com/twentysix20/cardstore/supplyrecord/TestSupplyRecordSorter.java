package com.twentysix20.cardstore.supplyrecord;

import static com.twentysix20.testutil.TestCase2620.assertSize;
import static junit.framework.Assert.assertEquals;

import java.util.HashMap;
import java.util.Set;

import org.junit.Test;

import com.twentysix20.cardstore.postage.FlatRatePostage;
import com.twentysix20.cardstore.supplyfilter.CheapestCardPerStoreFilter;
import com.twentysix20.cardstore.vendor.Vendor;



public class TestSupplyRecordSorter {
	static private Vendor vendor1 = new Vendor("shop",new FlatRatePostage(200));
	static private Vendor vendor2 = new Vendor("shopA",new FlatRatePostage(200));
	static private Vendor vendor3 = new Vendor("shopB",new FlatRatePostage(200));

	@Test public void noRecords() {
		SupplyRecords records = new SupplyRecords();
		SupplyRecords filteredRecords = CheapestCardPerStoreFilter.instance().filterRecords(records);
		assertSize(0, filteredRecords);
	}

	@Test public void oneRecord() {
		SupplyRecords records = new SupplyRecords();
		records.add(new StandardSupplyRecord("Example Card",vendor1,"Set","Unplayed",3,"0.15"));
		SupplyRecords filteredRecords = CheapestCardPerStoreFilter.instance().filterRecords(records);
		assertSize(1, filteredRecords);
	}

	@Test public void twoRecordsTwoStores() {
		SupplyRecords records = new SupplyRecords();
		records.add(new StandardSupplyRecord("Example Card",vendor1,"Set","Unplayed",3,"0.15"));
		records.add(new StandardSupplyRecord("Example Card",vendor2,"Set","Unplayed",2,"0.18"));
		SupplyRecords filteredRecords = CheapestCardPerStoreFilter.instance().filterRecords(records);
		assertSize(2, filteredRecords);
	}

	@Test public void twoRecordsSameStoreDifferentSet() {
		SupplyRecords records = new SupplyRecords();
		records.add(new StandardSupplyRecord("Example Card",vendor1,"Set1","Unplayed",3,"0.15"));
		records.add(new StandardSupplyRecord("Example Card",vendor1,"Set2","Unplayed",2,"0.18"));
		SupplyRecords filteredRecords = CheapestCardPerStoreFilter.instance().filterRecords(records);
		
		assertSize(1, filteredRecords);
		SupplyRecord choice = filteredRecords.iterator().next();
		assertEquals("Set1",choice.getSet());
		assertEquals(15,choice.getCost());
	}

	@Test public void twoRecordsSameStoreDifferentCondition() {
		SupplyRecords records = new SupplyRecords();
		records.add(new StandardSupplyRecord("Example Card",vendor1,"Set","Unplayed",3,"0.15"));
		records.add(new StandardSupplyRecord("Example Card",vendor1,"Set","Lightly Played",2,"0.10"));
		SupplyRecords filteredRecords = CheapestCardPerStoreFilter.instance().filterRecords(records);
		
		assertSize(1, filteredRecords);
		SupplyRecord choice = filteredRecords.iterator().next();
		assertEquals("Lightly Played",choice.getCondition());
		assertEquals(10,choice.getCost());
	}

	@Test public void severalRecords() {
		SupplyRecords records = new SupplyRecords();
		records.add(new StandardSupplyRecord("Example Card",vendor1,"Set1","Unplayed",2,"0.18"));
		records.add(new StandardSupplyRecord("Example Card",vendor1,"Set2","Unplayed",3,"0.15"));
		records.add(new StandardSupplyRecord("Example Card",vendor2,"Set1","Unplayed",3,"0.15"));
		records.add(new StandardSupplyRecord("Example Card",vendor3,"Set1","Unplayed",3,"0.15"));
		records.add(new StandardSupplyRecord("Example Card",vendor3,"Set2","Unplayed",2,"0.18"));
		records.add(new StandardSupplyRecord("Example Card",vendor3,"Set2","Lightly Played",2,"0.13"));
		SupplyRecords filteredRecords = CheapestCardPerStoreFilter.instance().filterRecords(records);
		
		assertSize(3, filteredRecords);
		HashMap<String, SupplyRecord> map = convertSetToMap(filteredRecords);

		SupplyRecord choice1 = map.get(vendor1.name());
		assertEquals("Set2",choice1.getSet());
		assertEquals("Unplayed",choice1.getCondition());
		assertEquals(15,choice1.getCost());

		SupplyRecord choice2 = map.get(vendor2.name());
		assertEquals("Set1",choice2.getSet());
		assertEquals("Unplayed",choice2.getCondition());
		assertEquals(15,choice2.getCost());

		SupplyRecord choice3 = map.get(vendor3.name());
		assertEquals("Set2",choice3.getSet());
		assertEquals("Lightly Played",choice3.getCondition());
		assertEquals(13,choice3.getCost());
	}

	private HashMap<String, SupplyRecord> convertSetToMap(
			Set<SupplyRecord> set) {
		HashMap<String, SupplyRecord> map = new HashMap<String, SupplyRecord>();
		for (SupplyRecord record : set)
			map.put(record.getVendorName(), record);
		return map;
	}
}
