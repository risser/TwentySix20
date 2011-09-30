package com.twentysix20.util;

import java.rmi.server.UID;

public class UidUtil {
	static public String getUid() {
		return new UID().toString();
	}
}