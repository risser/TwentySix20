	package com.wf.util.classcode;

import java.util.List;

import com.twentysix20.testutil.TestCase2620;
import com.twentysix20.util.DateUtil;

public class StageDefaultTestCase extends TestCase2620 {

	public void testIndustryHazardDefaultStage() {
		DataSmoosher smoosh = new DataSmoosher();
		smoosh.addDefaultStage("1111",8);
		smoosh.addHazard("08","1111","A");
		smoosh.addIndustry("08","1111","Goods");
		List<StateClassTableData> data = smoosh.getDataList();
		assertSize(1, data);
		assertData(800,"1111","Goods","A",8,data.get(0));
	}

	public void testIndustryHazardDefaultStageAndZeroStage() {
		DataSmoosher smoosh = new DataSmoosher();
		smoosh.addDefaultStage("1111",8);
		smoosh.addHazard("08","1111","A");
		smoosh.addIndustry("08","1111","Goods");
		smoosh.addHazard("08","1112","B");
		smoosh.addIndustry("08","1112","Services");
		List<StateClassTableData> data = smoosh.getDataList();
		assertSize(2, data);
		assertData(800,"1111","Goods","A",8,data.get(0));
		assertData(800,"1112","Services","B",0,data.get(1));
	}

	public void testIndustryHazardDefaultStageAndException() {
		DataSmoosher smoosh = new DataSmoosher();
		smoosh.addDefaultStage("1111",8);
		smoosh.addDefaultStage("1112",8);
		smoosh.addHazard("08","1111","A");
		smoosh.addIndustry("08","1111","Goods");
		smoosh.addStage("08","1111",3);
		smoosh.addHazard("08","1112","B");
		smoosh.addIndustry("08","1112","Services");
		List<StateClassTableData> data = smoosh.getDataList();
		assertSize(2, data);
		assertData(800,"1111","Goods","A",3,data.get(0));
		assertData(800,"1112","Services","B",8,data.get(1));
	}

	public void testIndustryHazardDefaultStage_TwoDates() {
		DataSmoosher smoosh = new DataSmoosher();
		smoosh.addDefaultStage("1111",8);
		smoosh.addHazard("08","1111","A",DateUtil.getBasicDate(2000,1,1));
		smoosh.addHazard("08","1111","B");
		smoosh.addIndustry("08","1111","Factory");
		List<StateClassTableData> data = smoosh.getDataList();
		assertSize(2, data);
		assertData(800,"1111","Factory","A",8,data.get(0));
		assertData(801,"1111","Factory","B",8,data.get(1));
	}

	private void assertData(int version, String classCode, String industry, String hazard, int stage, StateClassTableData data) {
		assertEquals(version, data.version);
		assertEquals(classCode, data.classcode);
		assertEquals(industry, data.industry);
		assertEquals(hazard, data.hazardCode);
		assertEquals(stage, data.premiumStage);		
	}
}