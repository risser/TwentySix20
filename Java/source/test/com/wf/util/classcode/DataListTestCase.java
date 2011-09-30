	package com.wf.util.classcode;

import java.util.Date;
import java.util.List;

import com.twentysix20.testutil.TestCase2620;
import com.twentysix20.util.DateUtil;

public class DataListTestCase extends TestCase2620 {

	public void testIndustries_2States() {
		DataSmoosher smoosh = new DataSmoosher();
		smoosh.addIndustry("07","9554","Goods and Services");
		smoosh.addIndustry("02","9554","Contracting");
		smoosh.addIndustry("02","9600","Contracting");
		smoosh.addIndustry("07","9600","Manufacturing");
		List<StateClassTableData> data = smoosh.getDataList();
		assertSize(4, data);
		assertData(200,"9554","Contracting",null,0,data.get(0));
		assertData(200,"9600","Contracting",null,0,data.get(1));
		assertData(700,"9554","Goods and Services",null,0,data.get(2));
		assertData(700,"9600","Manufacturing",null,0,data.get(3));
	}

	public void testIndustries_3States() {
		DataSmoosher smoosh = new DataSmoosher();
		smoosh.addIndustry("02","9554","Contracting");
		smoosh.addIndustry("09","9600","Manufacturing");
		smoosh.addIndustry("02","9600","Contracting");
		smoosh.addIndustry("07","9554","Goods and Services");

		List<StateClassTableData> data = smoosh.getDataList();
		assertSize(4, data);
		assertData(200,"9554","Contracting",null,0,data.get(0));
		assertData(200,"9600","Contracting",null,0,data.get(1));
		assertData(700,"9554","Goods and Services",null,0,data.get(2));
		assertData(900,"9600","Manufacturing",null,0,data.get(3));
	}

 	public void testHazards_2States() {
		DataSmoosher smoosh = new DataSmoosher();
		smoosh.addHazard("07","9554","G");
		smoosh.addHazard("02","9554","A");
		smoosh.addHazard("02","9600","B");
		smoosh.addHazard("07","9600","F");

		List<StateClassTableData> data = smoosh.getDataList();
		assertSize(4, data);
		assertData(200,"9554",null,"A",0,data.get(0));
		assertData(200,"9600",null,"B",0,data.get(1));
		assertData(700,"9554",null,"G",0,data.get(2));
		assertData(700,"9600",null,"F",0,data.get(3));
	}

	public void testHazards_3States() {
		DataSmoosher smoosh = new DataSmoosher();
		smoosh.addHazard("02","9554","A");
		smoosh.addHazard("09","9600","D");
		smoosh.addHazard("02","9600","B");
		smoosh.addHazard("07","9554","C");

		List<StateClassTableData> data = smoosh.getDataList();
		assertSize(4, data);
		assertData(200,"9554",null,"A",0,data.get(0));
		assertData(200,"9600",null,"B",0,data.get(1));
		assertData(700,"9554",null,"C",0,data.get(2));
		assertData(900,"9600",null,"D",0,data.get(3));
	}

	public void testHazards_2Dates() {
		DataSmoosher smoosh = new DataSmoosher();
		smoosh.addHazard("02","9600","A");
		smoosh.addHazard("02","9600","B",DateUtil.getBasicDate(2001,4,4));

		List<StateClassTableData> data = smoosh.getDataList();
		assertSize(2, data);
		assertData(200,"9600",null,"B",0,data.get(0));
		assertData(201,"9600",null,"A",0,data.get(1));
	}

	public void testHazards_2Dates_OtherOrder() {
		DataSmoosher smoosh = new DataSmoosher();
		smoosh.addHazard("02","9600","A",DateUtil.getBasicDate(2001,4,4));
		smoosh.addHazard("02","9600","B");

		List<StateClassTableData> data = smoosh.getDataList();
		assertSize(2, data);
		assertData(200,"9600",null,"A",0,data.get(0));
		assertData(201,"9600",null,"B",0,data.get(1));
	}

	public void testStages_2States() {
		DataSmoosher smoosh = new DataSmoosher();
		smoosh.addStage("02","9554",0);
		smoosh.addStage("02","9600",1);
		smoosh.addStage("07","9554",2);
		smoosh.addStage("07","9600",3);

		List<StateClassTableData> data = smoosh.getDataList();
		assertSize(4, data);
		assertData(200,"9554",null,null,0,data.get(0));
		assertData(200,"9600",null,null,1,data.get(1));
		assertData(700,"9554",null,null,2,data.get(2));
		assertData(700,"9600",null,null,3,data.get(3));
	}

	public void testStages_3States() {
		DataSmoosher smoosh = new DataSmoosher();
		smoosh.addStage("02","9554",1);
		smoosh.addStage("09","9600",4);
		smoosh.addStage("02","9600",2);
		smoosh.addStage("07","9554",3);

		List<StateClassTableData> data = smoosh.getDataList();
		assertSize(4, data);
		assertData(200,"9554",null,null,1,data.get(0));
		assertData(200,"9600",null,null,2,data.get(1));
		assertData(700,"9554",null,null,3,data.get(2));
		assertData(900,"9600",null,null,4,data.get(3));
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

		List<StateClassTableData> data = smoosh.getDataList();
		assertSize(4, data);
		assertData(200,"9554","Contracting","A",1,data.get(0));
		assertData(200,"9600","Contracting","B",2,data.get(1));
		assertData(700,"9554","Goods and Services","C",3,data.get(2));
		assertData(900,"9600","Manufacturing","D",4,data.get(3));
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

		List<StateClassTableData> data = smoosh.getDataList();
		assertSize(5, data);
		assertData(200,"9554",null,null,1,data.get(0));
		assertData(700,"9554",null,"C",3,data.get(1));
		assertData(800,"9554","Goods and Services",null,0,data.get(2));
		assertData(800,"9600",null,"D",0,data.get(3));
		assertData(900,"9600","Manufacturing",null,4,data.get(4));
	}

	public void testStageIndustryHazardDates_RoundRobin() {
		DataSmoosher smoosh = new DataSmoosher();
		smoosh.addStage("02","9554",1);
		smoosh.addStage("07","9554",3);
		smoosh.addHazard("08","9600","D",DateUtil.getBasicDate(2002,2,2));
		smoosh.addHazard("07","9554","C");
		smoosh.addHazard("08","9600","E");
		smoosh.addIndustry("08","9554","Goods and Services");
		smoosh.addHazard("07","9554","G",DateUtil.getBasicDate(2000,1,1));
		smoosh.addIndustry("09","9600","Manufacturing");
		smoosh.addStage("09","9600",4);

		List<StateClassTableData> data = smoosh.getDataList();
		assertSize(8, data);
		assertData(200,"9554",null,null,1,data.get(0));
		assertData(700,"9554",null,"G",3,data.get(1));
		assertData(701,"9554",null,"C",3,data.get(2));
		assertData(800,"9554","Goods and Services",null,0,data.get(3));
		assertData(801,"9554","Goods and Services",null,0,data.get(4));
		assertData(800,"9600",null,"D",0,data.get(5));
		assertData(801,"9600",null,"E",0,data.get(6));
		assertData(900,"9600","Manufacturing",null,4,data.get(7));
	}

	public void testAllClassCodesForEachDate() {
		DataSmoosher smoosh = new DataSmoosher();

		smoosh.addHazard("08","9554","A");
		smoosh.addIndustry("08","9554","Goods");

		smoosh.addHazard("08","9600","D",DateUtil.getBasicDate(2002,2,2));
		smoosh.addHazard("08","9600","E");
		smoosh.addIndustry("08","9600","Factory");
		smoosh.addStage("08","9600",2);

		smoosh.addHazard("08","9655","A",DateUtil.getBasicDate(2000,1,1));
		smoosh.addHazard("08","9655","B");
		smoosh.addIndustry("08","9655","Factory");
		smoosh.addStage("08","9655",2);

		smoosh.addStage("08","9701",2);
		smoosh.addHazard("08","9701","A");
		smoosh.addIndustry("08","9701","Goods");

		List<StateClassTableData> data = smoosh.getDataList();
		assertSize(12, data);
		assertData(800,"9554","Goods","A",0,data.get(0));
		assertData(801,"9554","Goods","A",0,data.get(1));
		assertData(802,"9554","Goods","A",0,data.get(2));

		assertData(800,"9600","Factory","D",2,data.get(3));
		assertData(801,"9600","Factory","D",2,data.get(4));
		assertData(802,"9600","Factory","E",2,data.get(5));

		assertData(800,"9655","Factory","A",2,data.get(6));
		assertData(801,"9655","Factory","B",2,data.get(7));
		assertData(802,"9655","Factory","B",2,data.get(8));

		assertData(800,"9701","Goods","A",2,data.get(9));
		assertData(801,"9701","Goods","A",2,data.get(10));
		assertData(802,"9701","Goods","A",2,data.get(11));
	}

	public void testCarryOverIndustryStageBetweenDates() {
		DataSmoosher smoosh = new DataSmoosher();
		smoosh.addIndustry("08", "9600", "Factory");
		smoosh.addStage("08", "9600", 4);
		smoosh.addHazard("08","9600","E");
		smoosh.addHazard("08","9600","D",DateUtil.getBasicDate(2002,2,2));
		smoosh.addHazard("07","9554","C");
		smoosh.addIndustry("07", "9554", "Goods");
		smoosh.addStage("07", "9554", 3);
		smoosh.addHazard("07","9554","G",DateUtil.getBasicDate(2000,1,1));
		smoosh.addHazard("09","9554","C");
		smoosh.addHazard("09","9554","G",DateUtil.getBasicDate(2000,1,1));
		smoosh.addIndustry("09", "9554", "Goods");
		smoosh.addStage("09", "9554", 3);

		List<StateClassTableData> data = smoosh.getDataList();
		assertSize(6, data);
		assertData(700,"9554","Goods","G",3,data.get(0));
		assertData(701,"9554","Goods","C",3,data.get(1));
		assertData(800,"9600","Factory","D",4,data.get(2));
		assertData(801,"9600","Factory","E",4,data.get(3));
		assertData(900,"9554","Goods","G",3,data.get(4));
		assertData(901,"9554","Goods","C",3,data.get(5));
	}

	public void testStageIndustryHazard_AllDifferent() {
		DataSmoosher smoosh = new DataSmoosher();
		smoosh.addIndustry("02","9600","Manufacturing");
		smoosh.addStage("02","9554",1);
		smoosh.addStage("02","9600",2);
		smoosh.addHazard("02","9600","D");
		smoosh.addIndustry("11","9600","Contracting");
		smoosh.addIndustry("12","9600","Grocery");
		smoosh.addStage("11","9554",3);
		smoosh.addHazard("11","9600","B");
		smoosh.addStage("11","9600",4);
		smoosh.addIndustry("02","9554","Contracting");
		smoosh.addHazard("02","9554","C");
		smoosh.addIndustry("11","9554","Goods and Services");
		smoosh.addHazard("11","9554","A");
		smoosh.addHazard("12","9600","B");

		List<StateClassTableData> data = smoosh.getDataList();
		assertSize(5, data);
		assertData(200,"9554","Contracting","C",1,data.get(0));
		assertData(200,"9600","Manufacturing","D",2,data.get(1));
		assertData(1100,"9554","Goods and Services","A",3,data.get(2));
		assertData(1100,"9600","Contracting","B",4,data.get(3));
		assertData(1200,"9600","Grocery","B",0,data.get(4));
	}

	private void assertData(int version, String classCode, String industry, String hazard, int stage, StateClassTableData data) {
		assertEquals(version, data.version);
		assertEquals(classCode, data.classcode);
		assertEquals(industry, data.industry);
		assertEquals(hazard, data.hazardCode);
		assertEquals(stage, data.premiumStage);		
	}
}