package com.wf.util.classcode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.twentysix20.util.DateUtil;
import com.twentysix20.util.StringUtil;


public class Crammer {
	public static final String HAZARD = "C:\\TEMP\\Hazard.csv";
	public static final String INDUSTRY = "C:\\TEMP\\industry.csv";
	public static String PREMIUM_STAGE = "C:\\TEMP\\premiumstages.csv";

	public static final String VERSION = "C:\\TEMP\\versions.csv";
	public static final String DATA = "C:\\TEMP\\ccdata.csv";

	public static void main(String[] args) throws IOException, ParseException {
		DataSmoosher smoosh = new DataSmoosher();

		BufferedReader hazardReader = new BufferedReader(new InputStreamReader(new FileInputStream(HAZARD)));
        String hazardLine = hazardReader.readLine();
        while ((hazardLine = hazardReader.readLine()) != null) {
        	String[] ss = hazardLine.split(",");
        	String state = ss[0];
        	String cc = StringUtil.padLeft(ss[1], 4, '0');
        	Date date = null;
        	if (!"12/31/2199".equals(ss[2]))
        		date = DateUtil.parseDate(ss[2], "MM/dd/yyyy");
        	String hazard = ss[3];
        	if (date != null)
        		smoosh.addHazard(state, cc, hazard, date);
        	else
        		smoosh.addHazard(state, cc, hazard);
        }

		BufferedReader industryReader = new BufferedReader(new InputStreamReader(new FileInputStream(INDUSTRY)));
        String industryLine = industryReader.readLine();
        while ((industryLine = industryReader.readLine()) != null) {
        	String[] ss = industryLine.split(",");
        	String state = ss[0];
        	String cc = StringUtil.padLeft(ss[1], 4, '0');
        	String industry = ss[2];
       		smoosh.addIndustry(state, cc, industry);
        }

        Set<String> stateSet = new HashSet<String>();
        for (StateClassVerTableData ver : smoosh.getVersionList())
        	stateSet.add(ver.state);

		BufferedReader stageReader = new BufferedReader(new InputStreamReader(new FileInputStream(PREMIUM_STAGE)));
        String stageLine = stageReader.readLine();
        while ((stageLine = stageReader.readLine()) != null) {
        	String[] ss = stageLine.split(",");
        	String cc = StringUtil.padLeft(ss[0], 4, '0');
        	int stage = Integer.parseInt(ss[1]);
			smoosh.addDefaultStage(cc, stage);
       		for (String state : stateSet)
       			if (!("13".equals(state) && "0935".equals(cc)) && !("22".equals(state) && "9046".equals(cc)))
       				smoosh.addStage(state, cc, stage);
        }
        smoosh.addStage("13", "0935", 9);
        smoosh.addStage("22", "9046", 3);

        List<StateClassVerTableData> versionList = smoosh.getVersionList();
        BufferedWriter versionFile = new BufferedWriter(new FileWriter(VERSION));
        for (StateClassVerTableData versionData : versionList)
        	versionFile.write(versionData.toString());
        versionFile.close();

        List<StateClassTableData> dataList = smoosh.getDataList();
        BufferedWriter dataFile = new BufferedWriter(new FileWriter(DATA));
        for (StateClassTableData dataData : dataList)
        	dataFile.write(dataData.toString());
        dataFile.close();

//        System.out.println(versionList);
//        System.out.println("====");
//        System.out.println(dataList);
	}
}
