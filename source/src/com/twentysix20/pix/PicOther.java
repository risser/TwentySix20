package com.twentysix20.pix;

import java.io.*;
import java.lang.*;
import java.util.*;
import com.twentysix20.pix.*;

public class PicOther extends PicFile
{
	String icon = "";
	String mime = "";

	public PicOther(String p, String n) throws Exception
	{
		setPath(p);
		setName(n);
	}

	public void setIconPath(String c)
	{
		icon = c;
	}
	public String getIconPath()
	{
		return icon;
	}
	public void setMimeType(String c)
	{
		mime = c;
	}
	public String getMimeType()
	{
		return mime;
	}
}