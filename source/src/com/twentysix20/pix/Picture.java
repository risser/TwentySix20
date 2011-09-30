package com.twentysix20.pix;

import java.io.*;
import java.lang.*;
import java.util.*;
import com.twentysix20.pix.*;

public class Picture extends PicFile
{
	File thumb = null;
	File show = null;
	String caption = "";

	public Picture(String p, String n) throws Exception
	{
		setPath(p);
		setName(n);
	}

	public String getThumbPath()
	{
		return thumb.getPath();
	}
	public boolean thumbExists()
	{
		return thumb.exists();
	}
	public boolean thumbCurrent()
	{
		return thumb.lastModified() >= pic.lastModified();
	}

	public void setThumb(String t) throws Exception
	{
		thumb = new File(t);
//		if (!thumbExists())
//			throw new Exception("Thumb ("+t+") does not exist.");
	}

	public String getShowPath()
	{
		return show.getPath();
	}
	public boolean showExists()
	{
		return show.exists();
	}
	public boolean showCurrent()
	{
		return show.lastModified() >= pic.lastModified();
	}

	public void setShow(String t) throws Exception
	{
		show = new File(t);
//		if (!thumbExists())
//			throw new Exception("Thumb ("+t+") does not exist.");
	}

	public void setCaption(String c)
	{
		caption = c;
	}
	public String getCaption()
	{
		return caption;
	}
}