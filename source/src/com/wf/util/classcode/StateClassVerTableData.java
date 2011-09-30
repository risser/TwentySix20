package com.wf.util.classcode;

import java.util.Date;

import com.twentysix20.util.StringUtil;

public class StateClassVerTableData {
	public int id;
	public String state;
	public Date startDate;
	public Date endDate;

	public String toString() {
		return id + ","+state+","+StringUtil.formatDate(startDate,"MM/dd/yyyy")+","+StringUtil.formatDate(endDate,"MM/dd/yyyy")+"\n";
	}
}
