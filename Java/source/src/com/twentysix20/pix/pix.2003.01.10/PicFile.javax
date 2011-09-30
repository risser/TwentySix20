package com.twentysix20.pix;

import java.io.*;
import java.lang.*;
import java.util.*;
import com.twentysix20.pix.*;

public class PicFile{

	File pic = null;
	String name = "";
	String code = "";
	PicFile parent = null;

	public PicFile(){}

	public String getPath()
	{
		return pic.getPath();
	}
	protected void setPath(String p) throws Exception
	{
		pic = new File(p);
		code = new MD5Encoder(p).toString();
//		if (!pathExists())
//			throw new Exception("Path ("+p+") does not exist.");
	}
	protected boolean pathExists() throws Exception
	{
		return pic.exists();
	}

	public String getName()
	{
		return name;
	}
	protected void setName(String id)
	{
		name = id;
	}

	public PicFile getParent()
	{
		return parent;
	}
	protected void setParent(PicFile id)
	{
		parent = id;
	}

	public String getCode()
	{
		return code;
	}
}
