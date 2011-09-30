package com.wf.util.classcode;

import java.util.Date;
import java.util.List;

import com.twentysix20.testutil.TestCase2620;
import com.twentysix20.util.DateUtil;

public class VersionListTestCase extends TestCase2620 {

	public void testIndustries_2States() {
		DataSmoosher smoosh = new DataSmoosher();
		smoosh.addIndustry("07","9554","Goods and Services");
		smoosh.addIndustry("02","9554","Contracting");
		smoosh.addIndustry("02","9600","Contracting");
		smoosh.addIndustry("07","9600","Manufacturing");
		List<StateClassVerTableData> versions = smoosh.getVersionList();
		assertNotNull(versions);
		assertSize(2, versions);
		assertVersion("02",200,versions.get(0));
		assertVersion("07",700,versions.get(1));
	}

	public void testIndustries_StatesWithNoLeadZero() {
		DataSmoosher smoosh = new DataSmoosher();
		smoosh.addIndustry("07","9554","Goods and Services");
		smoosh.addIndustry("2","9554","Contracting");
		smoosh.addIndustry("02","9600","Contracting");
		smoosh.addIndustry("7","9600","Manufacturing");
		List<StateClassVerTableData> versions = smoosh.getVersionList();
		assertNotNull(versions);
		assertSize(2, versions);
		assertVersion("02",200,versions.get(0));
		assertVersion("07",700,versions.get(1));
	}

	public void testIndustries_3States() {
		DataSmoosher smoosh = new DataSmoosher();
		smoosh.addIndustry("02","9554","Contracting");
		smoosh.addIndustry("09","9600","Manufacturing");
		smoosh.addIndustry("02","9600","Contracting");
		smoosh.addIndustry("07","9554","Goods and Services");
		List<StateClassVerTableData> versions = smoosh.getVersionList();
		assertNotNull(versions);
		assertSize(3, versions);
		assertVersion("02",200,versions.get(0));
		assertVersion("07",700,versions.get(1));
		assertVersion("09",900,versions.get(2));
	}

 	public void testHazards_2States() {
		DataSmoosher smoosh = new DataSmoosher();
		smoosh.addHazard("07","9554","G");
		smoosh.addHazard("02","9554","A");
		smoosh.addHazard("02","9600","B");
		smoosh.addHazard("07","9600","F");
		List<StateClassVerTableData> versions = smoosh.getVersionList();
		assertNotNull(versions);
		assertSize(2, versions);
		assertVersion("02",200,versions.get(0));
		assertVersion("07",700,versions.get(1));
	}

	public void testHazards_3States() {
		DataSmoosher smoosh = new DataSmoosher();
		smoosh.addHazard("02","9554","A");
		smoosh.addHazard("09","9600","D");
		smoosh.addHazard("02","9600","B");
		smoosh.addHazard("07","9554","C");
		List<StateClassVerTableData> versions = smoosh.getVersionList();
		assertNotNull(versions);
		assertSize(3, versions);
		assertVersion("02",200,versions.get(0));
		assertVersion("07",700,versions.get(1));
		assertVersion("09",900,versions.get(2));
	}

	public void testHazards_2Dates() {
		DataSmoosher smoosh = new DataSmoosher();
		smoosh.addHazard("02","9600","A");
		smoosh.addHazard("02","9600","B",DateUtil.getBasicDate(2001,4,4));
		List<StateClassVerTableData> versions = smoosh.getVersionList();
		assertNotNull(versions);
		assertSize(2, versions);
		assertVersion("02",200,StateData.LONG_LONG_AGO, DateUtil.getBasicDate(2001,4,4),versions.get(0));
		assertVersion("02",201, DateUtil.getBasicDate(2001,4,4),StateData.FAR_FAR_AWAY,versions.get(1));
	}

	public void testHazards_2Dates_OtherOrder() {
		DataSmoosher smoosh = new DataSmoosher();
		smoosh.addHazard("02","9600","A",DateUtil.getBasicDate(2001,4,4));
		smoosh.addHazard("02","9600","B");
		List<StateClassVerTableData> versions = smoosh.getVersionList();
		assertNotNull(versions);
		assertSize(2, versions);
		assertVersion("02",200,StateData.LONG_LONG_AGO, DateUtil.getBasicDate(2001,4,4),versions.get(0));
		assertVersion("02",201, DateUtil.getBasicDate(2001,4,4),StateData.FAR_FAR_AWAY,versions.get(1));
	}

	public void testStages_2States() {
		DataSmoosher smoosh = new DataSmoosher();
		smoosh.addStage("02","9554",0);
		smoosh.addStage("02","9600",1);
		smoosh.addStage("07","9554",2);
		smoosh.addStage("07","9600",3);
		List<StateClassVerTableData> versions = smoosh.getVersionList();
		assertNotNull(versions);
		assertSize(2, versions);
		assertVersion("02",200,versions.get(0));
		assertVersion("07",700,versions.get(1));
	}

	public void testStages_3States() {
		DataSmoosher smoosh = new DataSmoosher();
		smoosh.addStage("02","9554",1);
		smoosh.addStage("09","9600",4);
		smoosh.addStage("02","9600",2);
		smoosh.addStage("07","9554",3);
		List<StateClassVerTableData> versions = smoosh.getVersionList();
		assertNotNull(versions);
		assertSize(3, versions);
		assertVersion("02",200,versions.get(0));
		assertVersion("07",700,versions.get(1));
		assertVersion("09",900,versions.get(2));
	}

	public void testStageIndustryHazard_Same() {
		DataSmoosher smoosh = new DataSmoosher();
		smoosh.addIndustry("09","9600","Manufacturing");
		smoosh.addStage("02","9554",1);
		smoosh.addStage("02","9600",2);
		smoosh.addHazard("09","9600","D");
		smoosh.addIndustry("02","9600","Contracting");
		smoosh.addStage("07","9554",3);
		smoosh.addHazard("02","9600","B");
		smoosh.addStage("09","9600",4);
		smoosh.addIndustry("02","9554","Contracting");
		smoosh.addHazard("07","9554","C");
		smoosh.addIndustry("07","9554","Goods and Services");
		smoosh.addHazard("02","9554","A");
		List<StateClassVerTableData> versions = smoosh.getVersionList();
		assertNotNull(versions);
		assertSize(3, versions);
		assertVersion("02",200,versions.get(0));
		assertVersion("07",700,versions.get(1));
		assertVersion("09",900,versions.get(2));
	}

	public void testStageIndustryHazard_RoundRobin() {
		DataSmoosher smoosh = new DataSmoosher();
		smoosh.addStage("02","9554",1);
		smoosh.addStage("07","9554",3);
		smoosh.addHazard("07","9554","C");
		smoosh.addHazard("08","9600","D");
		smoosh.addIndustry("08","9554","Goods and Services");
		smoosh.addIndustry("09","9600","Manufacturing");
		smoosh.addStage("09","9600",4);
		List<StateClassVerTableData> versions = smoosh.getVersionList();
		assertNotNull(versions);
		assertSize(4, versions);
		assertVersion("02",200,versions.get(0));
		assertVersion("07",700,versions.get(1));
		assertVersion("08",800,versions.get(2));
		assertVersion("09",900,versions.get(3));
	}

	public void testStageIndustryHazardDates_RoundRobin() {
		DataSmoosher smoosh = new DataSmoosher();
		smoosh.addStage("02","9554",1);
		smoosh.addStage("07","9554",3);
		smoosh.addHazard("07","9554","C");
		smoosh.addHazard("08","9600","D",DateUtil.getBasicDate(2002,2,2));
		smoosh.addIndustry("08","9554","Goods and Services");
		smoosh.addHazard("07","9554","G",DateUtil.getBasicDate(2000,1,1));
		smoosh.addIndustry("09","9600","Manufacturing");
		smoosh.addStage("09","9600",4);
		List<StateClassVerTableData> versions = smoosh.getVersionList();
		assertNotNull(versions);
		assertSize(6, versions);
		assertVersion("02",200,versions.get(0));
		assertVersion("07",700,StateData.LONG_LONG_AGO,DateUtil.getBasicDate(2000,1,1),versions.get(1));
		assertVersion("07",701,DateUtil.getBasicDate(2000,1,1),StateData.FAR_FAR_AWAY,versions.get(2));
		assertVersion("08",800,StateData.LONG_LONG_AGO,DateUtil.getBasicDate(2002,2,2),versions.get(3));
		assertVersion("08",801,DateUtil.getBasicDate(2002,2,2),StateData.FAR_FAR_AWAY,versions.get(4));
		assertVersion("09",900,versions.get(5));
	}

	public void testStageIndustryHazard_AllDifferent() {
		DataSmoosher smoosh = new DataSmoosher();
		smoosh.addIndustry("10","9600","Manufacturing");
		smoosh.addStage("02","9554",1);
		smoosh.addStage("03","9600",2);
		smoosh.addHazard("09","9600","D");
		smoosh.addIndustry("11","9600","Contracting");
		smoosh.addStage("07","9554",3);
		smoosh.addHazard("13","9600","B");
		smoosh.addStage("12","9600",4);
		smoosh.addIndustry("01","9554","Contracting");
		smoosh.addHazard("34","9554","C");
		smoosh.addIndustry("36","9554","Goods and Services");
		smoosh.addHazard("37","9554","A");
		List<StateClassVerTableData> versions = smoosh.getVersionList();
		assertNotNull(versions);
		assertSize(12, versions);
		assertVersion("01",100,versions.get(0));
		assertVersion("02",200,versions.get(1));
		assertVersion("03",300,versions.get(2));
		assertVersion("07",700,versions.get(3));
		assertVersion("09",900,versions.get(4));
		assertVersion("10",1000,versions.get(5));
		assertVersion("11",1100,versions.get(6));
		assertVersion("12",1200,versions.get(7));
		assertVersion("13",1300,versions.get(8));
		assertVersion("34",3400,versions.get(9));
		assertVersion("36",3600,versions.get(10));
		assertVersion("37",3700,versions.get(11));
	}

	private void assertVersion(String state, int version, Date startDate, Date endDate, StateClassVerTableData data) {
		assertEquals(version, data.id);
		assertEquals(state, data.state);
		assertEquals(startDate, data.startDate);
		assertEquals(endDate, data.endDate);
	}
	private void assertVersion(String state, int version, StateClassVerTableData data) {
		assertVersion(state,version, StateData.LONG_LONG_AGO, StateData.FAR_FAR_AWAY, data);
	}
}