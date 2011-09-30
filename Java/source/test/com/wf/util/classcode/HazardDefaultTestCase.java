	package com.wf.util.classcode;

import java.util.List;

import com.twentysix20.testutil.TestCase2620;

public class HazardDefaultTestCase extends TestCase2620 {

	public void testStageIndustryDefaultHazard() {
		DataSmoosher smoosh = new DataSmoosher();
		smoosh.addDefaultStage("1111",8);
		smoosh.addHazard("13","1111","A");
		smoosh.addIndustry("13","1111","Goods");
		smoosh.addIndustry("08","1111","Goods");
		List<StateClassTableData> data = smoosh.getDataList();
		assertSize(2, data);
		assertData(800,"1111","Goods","A",8,data.get(0));
		assertData(1300,"1111","Goods","A",8,data.get(1));
	}

	private void assertData(int version, String classCode, String industry, String hazard, int stage, StateClassTableData data) {
		assertEquals(version, data.version);
		assertEquals(classCode, data.classcode);
		assertEquals(industry, data.industry);
		assertEquals(hazard, data.hazardCode);
		assertEquals(stage, data.premiumStage);		
	}
}