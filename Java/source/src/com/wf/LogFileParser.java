package com.wf;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class LogFileParser {
//	private static final String FILE_NAME = "C:\\temp\\svmpwase03-7_CL_SystemOut.log";
//	private static final String FILE_NAME = "C:\\temp\\svmpwase03-7_CL_SystemOut_10.10.05_16.11.23[1].log";
	private static final String FILE_NAME = "C:\\temp\\svmpwase03-7_CL_SystemOut_10.10.06_11.24.02[1].log";
	

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_NAME)));
        String line;
        while ((line = reader.readLine()) != null) {
        	if (!line.startsWith("[")) continue;
        	if (line.trim().endsWith("Received")) {
                String subline;
                while ((subline = reader.readLine()) != null) {
                	if (subline.startsWith("[") || subline.startsWith("02BOP")) {
                		break;
                	}
                }
                line += subline;
        	}
        	System.out.println(formatted(line));
        }
	}

	private static String formatted(String line) {
		String[] ss = line.split("\\s+", 7);
		return String.format("%s %s %s\t%s\t%s\t%s", ss[0].substring(1),ss[1],"EDT",ss[3],ss[4],ss[6]);
//		return line;
	}

}
