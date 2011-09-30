package com.twentysix20.util.html;

import com.twentysix20.util.FileUtil;

public class FileHtmlLoader implements HtmlLoader {

	@Override
	public String getHtmlPage(String url) throws Exception {
		return FileUtil.readFileToString(url);
	}

}
