package com.wf.util.classcode;

public class StateClassTableData {
	public int version = 0;
	public String classcode = null;
	public String hazardCode = null;
	public String industry = null;
	public int premiumStage = 0;

	public StateClassTableData(int version2, String cc, ClassCodeData ccd) {
		version = version2;
		classcode = cc;
		hazardCode = ccd.hazardCode;
		industry = ccd.industry;
		premiumStage = ccd.premiumStage;
	}

	public StateClassTableData() {}

	public String toString() {
		return version + ","+classcode+","+hazardCode+","+premiumStage+","+industry+"\n";
	}
}
