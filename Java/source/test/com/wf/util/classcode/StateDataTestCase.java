package com.wf.util.classcode;

import java.util.Date;
import java.util.List;

import com.twentysix20.testutil.TestCase2620;
import com.twentysix20.util.DateUtil;

public class StateDataTestCase extends TestCase2620 {
	public void testPutBasicCCData() {
		StateData data = new StateData("34");
		ClassCodeData ccData = new ClassCodeData();
		ccData.hazardCode = "A";
		ccData.industry = "Manufacturing";
		ccData.premiumStage = 3;
		data.addClassCode("1234",ccData);

		List<StateClassVerTableData> versionList = data.getVersionList();
		assertSize(1,versionList);
		assertVersion("34",3400,StateData.LONG_LONG_AGO,StateData.FAR_FAR_AWAY,versionList.get(0));

		List<StateClassTableData> dataList = data.getDataList();
		assertSize(1,dataList);
		assertData(3400,"1234","Manufacturing","A",3,dataList.get(0));
	}

	public void testPutExistingClassCode() {
		StateData data = new StateData("34");
		ClassCodeData ccData = new ClassCodeData();
		ccData.hazardCode = "A";
		data.addClassCode("1234",ccData);
		try {
			data.addClassCode("1234",ccData);
			fail("Should have thrown exception.");
		} catch (RuntimeException e) {
			//yay
		}
	}

	public void testTwoVersions() {
		StateData data = new StateData("34");
		ClassCodeData ccData = new ClassCodeData();
		ccData.hazardCode = "B";
		ccData.industry = "Manufacturing";
		ccData.premiumStage = 3;
		data.addHazardException("1234","A",DateUtil.getBasicDate(2004, 4, 4));
		data.addClassCode("1234",ccData);	

		List<StateClassVerTableData> versionList = data.getVersionList();
		assertSize(2,versionList);
		assertVersion("34",3400,StateData.LONG_LONG_AGO,DateUtil.getBasicDate(2004, 4, 4),versionList.get(0));
		assertVersion("34",3401,DateUtil.getBasicDate(2004, 4, 4),StateData.FAR_FAR_AWAY,versionList.get(1));

		List<StateClassTableData> dataList = data.getDataList();
		assertSize(2,dataList);
		assertData(3400,"1234","Manufacturing","A",3,dataList.get(0));
		assertData(3401,"1234","Manufacturing","B",3,dataList.get(1));
	}

	public void testMoreVersions() {
		StateData data = new StateData("34");
		ClassCodeData ccData = new ClassCodeData();
		ccData.hazardCode = "B";
		ccData.industry = "Manufacturing";
		ccData.premiumStage = 3;
		data.addClassCode("1234",ccData);	
		data.addHazardException("1234","A",DateUtil.getBasicDate(2004, 4, 4));
		data.addHazardException("1234","C",DateUtil.getBasicDate(1999, 10, 13));

		List<StateClassVerTableData> versionList = data.getVersionList();
		assertSize(3,versionList);
		assertVersion("34",3400,StateData.LONG_LONG_AGO,DateUtil.getBasicDate(1999, 10, 13),versionList.get(0));
		assertVersion("34",3401,DateUtil.getBasicDate(1999, 10, 13),DateUtil.getBasicDate(2004, 4, 4), versionList.get(1));
		assertVersion("34",3402,DateUtil.getBasicDate(2004, 4, 4),StateData.FAR_FAR_AWAY,versionList.get(2));

		List<StateClassTableData> dataList = data.getDataList();
		assertSize(3,dataList);
		assertData(3400,"1234","Manufacturing","C",3,dataList.get(0));
		assertData(3401,"1234","Manufacturing","A",3,dataList.get(1));
		assertData(3402,"1234","Manufacturing","B",3,dataList.get(2));
	}

	public void testCarryOver() {
		StateData data = new StateData("34");
		ClassCodeData ccData = new ClassCodeData();
		ccData.hazardCode = "B";
		ccData.industry = "Manufacturing";
		ccData.premiumStage = 3;
		data.addClassCode("1234",ccData);	
		data.addHazardException("1234","A",DateUtil.getBasicDate(2004, 4, 4));
		data.updateHazard("3333", "C");
		data.updateIndustry("3333", "Grocery");

		List<StateClassVerTableData> versionList = data.getVersionList();
		assertSize(2,versionList);
		assertVersion("34",3400,StateData.LONG_LONG_AGO,DateUtil.getBasicDate(2004, 4, 4),versionList.get(0));
		assertVersion("34",3401,DateUtil.getBasicDate(2004, 4, 4),StateData.FAR_FAR_AWAY,versionList.get(1));

		List<StateClassTableData> dataList = data.getDataList();
		assertSize(4,dataList);
		assertData(3400,"1234","Manufacturing","A",3,dataList.get(0));
		assertData(3401,"1234","Manufacturing","B",3,dataList.get(1));
		assertData(3400,"3333","Grocery","C",0,dataList.get(2));
		assertData(3401,"3333","Grocery","C",0,dataList.get(3));
	}

	public void testCarryOver_DifferentDates() {
		StateData data = new StateData("34");
		data.addHazardException("9600","D",DateUtil.getBasicDate(2002,2,2));
		data.updateHazard("9600","E");
		data.updateIndustry("9600","Factory");
		data.updateStage("9600",2);

		data.addHazardException("9655","A",DateUtil.getBasicDate(2000,1,1));
		data.updateHazard("9655","B");
		data.updateIndustry("9655","Factory");
		data.updateStage("9655",2);

		List<StateClassVerTableData> versionList = data.getVersionList();
		assertSize(3,versionList);
		assertVersion("34",3400,StateData.LONG_LONG_AGO,DateUtil.getBasicDate(2000, 1, 1),versionList.get(0));
		assertVersion("34",3401,DateUtil.getBasicDate(2000, 1, 1),DateUtil.getBasicDate(2002, 2, 2),versionList.get(1));
		assertVersion("34",3402,DateUtil.getBasicDate(2002, 2, 2),StateData.FAR_FAR_AWAY,versionList.get(2));

		List<StateClassTableData> dataList = data.getDataList();
		assertSize(6,dataList);
		assertData(3400,"9600","Factory","D",2,dataList.get(0));
		assertData(3401,"9600","Factory","D",2,dataList.get(1));
		assertData(3402,"9600","Factory","E",2,dataList.get(2));

		assertData(3400,"9655","Factory","A",2,dataList.get(3));
		assertData(3401,"9655","Factory","B",2,dataList.get(4));
		assertData(3402,"9655","Factory","B",2,dataList.get(5));
	}

	public void testDuplicateIndustry() {
		StateData data = new StateData("34");
		data.updateIndustry("1234", "Factory");

		try {
			data.updateIndustry("1234", "Grocery");
			fail("Should have thrown exception.");
		} catch (RuntimeException e) {
			//YAY
		}
	}

	public void testIndustry() {
		StateData data = new StateData("34");
		data.updateIndustry("1234", "Factory");

		List<StateClassTableData> dataList = data.getDataList();
		assertSize(1,dataList);
		assertData(3400,"1234","Factory",null,0,dataList.get(0));
	}

	public void testIndustry_TwoCodes() {
		StateData data = new StateData("34");
		data.updateIndustry("2222", "Grocery");
		data.updateIndustry("1234", "Factory");

		List<StateClassTableData> dataList = data.getDataList();
		assertSize(2,dataList);
		assertData(3400,"1234","Factory",null,0,dataList.get(0));
		assertData(3400,"2222","Grocery",null,0,dataList.get(1));
	}

	public void testDuplicateHazard() {
		StateData data = new StateData("34");
		data.updateHazard("1234", "G");

		try {
			data.updateHazard("1234", "D");
			fail("Should have thrown exception.");
		} catch (RuntimeException e) {
			//YAY
		}
	}

	public void testHazard() {
		StateData data = new StateData("34");
		data.updateHazard("1234", "A");

		List<StateClassTableData> dataList = data.getDataList();
		assertSize(1,dataList);
		assertData(3400,"1234",null,"A",0,dataList.get(0));
	}

	public void testHazard_TwoCodes() {
		StateData data = new StateData("34");
		data.updateHazard("2222", "A");
		data.updateHazard("1234", "G");

		List<StateClassTableData> dataList = data.getDataList();
		assertSize(2,dataList);
		assertData(3400,"1234",null,"G",0,dataList.get(0));
		assertData(3400,"2222",null,"A",0,dataList.get(1));
	}

	public void testHazard_SameCode_DifferentDates() {
		StateData data = new StateData("34");
		data.addHazardException("1234", "A", DateUtil.getBasicDate(2000, 2, 2));
		data.updateHazard("1234", "G");

		List<StateClassTableData> dataList = data.getDataList();
		assertSize(2,dataList);
		assertData(3400,"1234",null,"A",0,dataList.get(0));
		assertData(3401,"1234",null,"G",0,dataList.get(1));
	}

	public void testHazard_SameCode_DifferentDatesOrder() {
		StateData data = new StateData("34");
		data.updateHazard("1234", "G");
		data.addHazardException("1234", "A", DateUtil.getBasicDate(2000, 2, 2));

		List<StateClassTableData> dataList = data.getDataList();
		assertSize(2,dataList);
		assertData(3400,"1234",null,"A",0,dataList.get(0));
		assertData(3401,"1234",null,"G",0,dataList.get(1));
	}

	public void testHazard_FailOnExceptionOnly() {
		StateData data = new StateData("34");
		data.addHazardException("2222", "A", DateUtil.getBasicDate(2000, 2, 2));

		try {
			List<StateClassTableData> dataList = data.getDataList();
			fail("Should have thrown exception.");
		} catch (RuntimeException e) {
			//YAY
		}
	}

	public void testDuplicateStage() {
		StateData data = new StateData("34");
		data.updateStage("1234", 3);

		try {
			data.updateStage("1234", 5);
			fail("Should have thrown exception.");
		} catch (RuntimeException e) {
			//YAY
		}
	}

	public void testStage() {
		StateData data = new StateData("34");
		data.updateStage("1234", 3);

		List<StateClassTableData> dataList = data.getDataList();
		assertSize(1,dataList);
		assertData(3400,"1234",null,null,3,dataList.get(0));
	}

	public void testStageZeroWhenNotSet() {
		StateData data = new StateData("34");
		data.updateIndustry("1234", "Factory");

		List<StateClassTableData> dataList = data.getDataList();
		assertSize(1,dataList);
		assertData(3400,"1234","Factory",null,0,dataList.get(0));
	}

	public void testStage_TwoCodes() {
		StateData data = new StateData("34");
		data.updateStage("2222", 3);
		data.updateStage("1234", 4);

		List<StateClassTableData> dataList = data.getDataList();
		assertSize(2,dataList);
		assertData(3400,"1234",null,null,4,dataList.get(0));
		assertData(3400,"2222",null,null,3,dataList.get(1));
	}

	private void assertVersion(String state, int version, Date startDate, Date endDate, StateClassVerTableData data) {
		assertEquals(version, data.id);
		assertEquals(state, data.state);
		assertEquals(startDate, data.startDate);
		assertEquals(endDate, data.endDate);
	}

	private void assertData(int version, String classCode, String industry, String hazard, int stage, StateClassTableData data) {
		assertEquals(version, data.version);
		assertEquals(classCode, data.classcode);
		assertEquals(industry, data.industry);
		assertEquals(hazard, data.hazardCode);
		assertEquals(stage, data.premiumStage);		
	}

}