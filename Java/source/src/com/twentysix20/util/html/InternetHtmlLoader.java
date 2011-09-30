/******************************************************************************
*
* Proprietary and Confidential
* Ohio Farmers Insurance Company
*
* This document contains material which is the proprietary and confidential
* property of Ohio Farmers Insurance Company.
*
* The right to view, reproduce, modify, distribute, or in any way display
* this work is prohibited without the express written consent of the Ohio
* Farmers Insurance Company.
*
* Copyright (c) 2008 Ohio Farmers Insurance Company
* All rights reserved.
*
* Created on Oct 29, 2008
*/
package com.twentysix20.util.html;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

public class InternetHtmlLoader implements HtmlLoader {

    public String getHtmlPage(String urlString) throws UnsupportedEncodingException, MalformedURLException, IOException {
        return getHtmlPage(new URL(urlString));
    }

    synchronized public String getHtmlPage(URL source) throws UnsupportedEncodingException, IOException {
		StringBuffer buff = new StringBuffer();
		BufferedReader in = new BufferedReader(new InputStreamReader(source.openStream(), "UTF-8"));
		String inputLine;
		while ((inputLine = in.readLine()) != null)
			buff.append(inputLine);
		in.close();
		return buff.toString();
	}
}