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
package com.twentysix20.testutil;

import java.io.IOException;

import com.twentysix20.util.FileUtil;
import com.twentysix20.util.html.HtmlLoader;

public class TestSingleHtmlLoader implements HtmlLoader {
    private String page;
    private String fileName;

    public TestSingleHtmlLoader(Object sourceObject, String fileName) throws IOException {
        StringBuffer buff = FileUtil.readFile(sourceObject, fileName);
        this.page = buff.toString();
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public String getHtmlPage(String url) throws Exception {
        return page;
    }
}
