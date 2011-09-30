package com.wf.util.classcode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.twentysix20.util.StringUtil;

public class DataSmoosher {
	static public String INDIANA = "13";
	private Map<String,StateData> stateMap = new TreeMap<String,StateData>();
	private Map<String,Integer> defaultStageMap = new TreeMap<String,Integer>();

	public void addIndustry(String state, String classCode, String industry) {
		StateData stateData = getStateData(state);		
		stateData.updateIndustry(classCode, industry);
	}

	public void addHazard(String state, String classCode, String hazardCode) {
		StateData stateData = getStateData(state);
		stateData.updateHazard(classCode, hazardCode);
	}

	public void addHazard(String state, String classCode, String hazardCode, Date versionEndDate) {
		StateData stateData = getStateData(state);
		stateData.addHazardException(classCode, hazardCode, versionEndDate);
	}

	public void addStage(String state, String classCode, int stage) {
		StateData stateData = getStateData(state);
		stateData.updateStage(classCode, stage);
	}

	public List<StateClassVerTableData> getVersionList() {
		List<StateClassVerTableData> versionList = new ArrayList<StateClassVerTableData>();
		for (String state : stateMap.keySet())
			versionList.addAll(stateMap.get(state).getVersionList());
		return versionList;
	}

	private StateData getStateData(String state) {
		state = StringUtil.padLeft(state, 2, '0');
		if (stateMap.containsKey(state))
			return stateMap.get(state);
		else {
			StateData stateData = new StateData(state);
			stateMap.put(state,stateData);
			return stateData;
		}
	}

	public List<StateClassTableData> getDataList() {
		List<StateClassTableData> dataList = new ArrayList<StateClassTableData>();
		for (String state : stateMap.keySet()) {
			dataList.addAll(stateMap.get(state).getDataList());
		}

		StateData indianaData = stateMap.containsKey(INDIANA) ? stateMap.get(INDIANA) : null;

		for (StateClassTableData data : dataList) {
			if (defaultStageMap.containsKey(data.classcode) && data.premiumStage == 0)
				data.premiumStage = defaultStageMap.get(data.classcode);
			if ((data.industry == null) && (indianaData != null) && (indianaData.hasClassCode(data.classcode))) {
				data.industry = indianaData.getClassCodeData(data.classcode).industry;
System.out.println("Pulled industry for "+data.classcode+" in "+data.version+" from IN");				
			}
			if ((data.hazardCode == null) && (indianaData != null) && (indianaData.hasClassCode(data.classcode))) {
				data.hazardCode = indianaData.getClassCodeData(data.classcode).hazardCode;
System.out.println("Pulled hazard for "+data.classcode+" in "+data.version+" from IN");				
			}
		}

		return dataList;
	}

	public void addDefaultStage(String classCode, int stage) {
		defaultStageMap.put(classCode,stage);
	}

}