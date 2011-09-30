package com.wf.util.classcode;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.twentysix20.util.DateUtil;

public class StateData {
	static public Date LONG_LONG_AGO = DateUtil.getBasicDate(1960, 1, 1);
	static public Date FAR_FAR_AWAY = DateUtil.getBasicDate(2199, 12, 31);

	private Map<String,ClassCodeData> ccDataMap = new TreeMap<String,ClassCodeData>();
	private Map<Date,Map<String, String>> hazardVersionExceptions = new TreeMap<Date,Map<String, String>>();
	private String state;

	public StateData(String state) {
		this.state = state;
	}

	public void updateIndustry(String classCode, String industry) {
		ClassCodeData ccData;
		if (ccDataMap.containsKey(classCode)) {
			ccData = ccDataMap.get(classCode);
			if (ccData.industry != null) throw new RuntimeException("Egads! A duplicate classcode ("+classCode+") trying to reset industry!");
		} else {
			ccData = new ClassCodeData();
			ccDataMap.put(classCode, ccData);
		}
		ccData.industry = industry;
	}

	public void updateHazard(String classCode, String hazard) {
		ClassCodeData ccData;
		if (ccDataMap.containsKey(classCode)) {
			ccData = ccDataMap.get(classCode);
			if (ccData.hazardCode != null) throw new RuntimeException("Egads! A duplicate classcode ("+classCode+") trying to reset hazard code!");
		} else {
			ccData = new ClassCodeData();
			ccDataMap.put(classCode, ccData);
		}
		ccData.hazardCode = hazard;
	}

	public void updateStage(String classCode, int stage) {
		ClassCodeData ccData;
		if (ccDataMap.containsKey(classCode)) {
			ccData = ccDataMap.get(classCode);
			if (ccData.premiumStage != 0) throw new RuntimeException("Egads! A duplicate classcode ("+classCode+") trying to reset premium stage!");
		} else {
			ccData = new ClassCodeData();
			ccDataMap.put(classCode, ccData);
		}
		ccData.premiumStage = stage;
	}

	public void addClassCode(String classcode, ClassCodeData ccData) {
		if (ccDataMap.containsKey(classcode)) throw new RuntimeException("Classcode "+classcode+" already exists!");
		ccDataMap.put(classcode, ccData);
	}

	public void addHazardException(String classcode, String hazard, Date versionEndDate) {
		Map<String, String> ccMap;
		if (hazardVersionExceptions.containsKey(versionEndDate)) {
			ccMap = hazardVersionExceptions.get(versionEndDate);
		} else {
			 ccMap = new TreeMap<String, String>();
			 hazardVersionExceptions.put(versionEndDate, ccMap);
		}
		ccMap.put(classcode, hazard);
	}

	public List<StateClassVerTableData> getVersionList() {
		List<StateClassVerTableData> list = new ArrayList<StateClassVerTableData>();
		int v = 0;
		Date startDate = LONG_LONG_AGO;
		if (!hazardVersionExceptions.isEmpty()) {
			for (Date date : hazardVersionExceptions.keySet()) {
				StateClassVerTableData vData = new StateClassVerTableData();
				vData.startDate = startDate;
				vData.endDate = date;
				vData.state = state;
				vData.id = Integer.parseInt(state)*100 + v;
				v++;
				startDate = date;
				list.add(vData);
			}
		}
		
		StateClassVerTableData vData = new StateClassVerTableData();
		vData.startDate = startDate;
		vData.endDate = FAR_FAR_AWAY;
		vData.state = state;
		vData.id = Integer.parseInt(state)*100 + v;
		list.add(vData);

		return list;
	}

	public List<StateClassTableData> getDataList() {
		Set<String> hasExceptions = new HashSet<String>();
		for (Map<String, String> ccMap : hazardVersionExceptions.values())
			for (String exceptionCC : ccMap.keySet()) {
				if (!ccDataMap.containsKey(exceptionCC))
					throw new RuntimeException("We have a classcode with an exception, but no rule: "+exceptionCC);
				hasExceptions.add(exceptionCC);
			}

		List<StateClassTableData> list = new ArrayList<StateClassTableData>();		
		for (String cc : ccDataMap.keySet()) {
			ClassCodeData ccd = ccDataMap.get(cc);

			int version = 0;
			List<StateClassTableData> holdUntilMatch = new ArrayList<StateClassTableData>();
			for (Date date : hazardVersionExceptions.keySet()) {
				StateClassTableData data = new StateClassTableData(version, cc, ccd);
				Map<String, String> datedExceptionMap = hazardVersionExceptions.get(date);
				data.version = Integer.parseInt(state)*100 + version;
				holdUntilMatch.add(data);
				if (datedExceptionMap.containsKey(cc)) {
					for (StateClassTableData oldData : holdUntilMatch)
						oldData.hazardCode = datedExceptionMap.get(cc);
					holdUntilMatch.clear();
				}
				version++;
				list.add(data);
			}
			StateClassTableData data = new StateClassTableData(version, cc, ccd);
			data.version = Integer.parseInt(state)*100 + version;
			list.add(data);
		}

		return list;
	}

	public boolean hasClassCode(String classcode) {
		return ccDataMap.containsKey(classcode);
	}

	public ClassCodeData getClassCodeData(String classcode) {
		return ccDataMap.get(classcode);
	}
}
