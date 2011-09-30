package com.twentysix20.pix;

import java.io.*;
import java.lang.*;
import java.util.*;
import com.twentysix20.pix.*;

public class PicFolder extends PicFile
{
	Vector list = new Vector();

	public PicFolder(String p, String n) throws Exception
	{
		setPath(p);
		setName(n);
	}

	public void addPicFile(PicFile p)
	{
		list.add(p);
	}
	public PicFile getPicFile(int i)
	{
		return (PicFile)list.get(i);
	}
	public Picture getPicture(int i)
	{
		return (Picture)list.get(i);
	}
	public PicOther getPicOther(int i)
	{
		return (PicOther)list.get(i);
	}
	public PicFolder getPicFolder(int i)
	{
		return (PicFolder)list.get(i);
	}
	public boolean isFolder(int i)
	{
		return list.get(i) instanceof PicFolder;
	}
	public boolean isOther(int i)
	{
		return list.get(i) instanceof PicOther;
	}
	public boolean isPicture(int i)
	{
		return list.get(i) instanceof Picture;
	}
	public void clear()
	{
		list.clear();
	}
	public int size()
	{
		return list.size();
	}
	public Vector getVector()
	{
		return list;
	}
}
