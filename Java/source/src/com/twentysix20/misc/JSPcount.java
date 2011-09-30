package com.twentysix20.misc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Vector;

public class JSPcount
{

	private static Vector buildFileList(String pathName)
	{

		String[] fileNames = null;
		Vector victor = new Vector();

		File path = new File(pathName);
		fileNames = path.list();

		if (fileNames != null)
		{
			System.out.print(".");
			for (int i = 0; i < fileNames.length; i++)
				victor.addAll(buildFileList(pathName + "\\" + fileNames[i]));
		}
		else if (pathName.toLowerCase().indexOf(".jsp") != -1)
		{
			System.out.print("*");
			victor.add(pathName);
		}
		return victor;
	}

	private static void process(String fileName) throws java.io.FileNotFoundException, java.io.IOException
	{
//System.out.println("\n\n\nFILE: "+fileName);
		String lineIn;
		BufferedReader inFile = new BufferedReader(new FileReader(fileName));
		int lineCt = 0;
		int javaCt = 0;
		int lefty = -1;
		int righty = -1;
		boolean inJavaCode = false;
		boolean addMe = false;

		while ((lineIn = inFile.readLine()) != null)
		{
//System.out.println(lineIn);
			StringBuffer line = new StringBuffer(lineIn.trim());
			lineCt++;
			addMe = false;
			do
			{
	//System.out.println(line.toString());
				lefty = line.toString().indexOf("<%");
				righty = line.toString().indexOf("%>");
				if ((lefty != -1) && ((lefty < righty) || (righty == -1)))
				{
					if (inJavaCode)
						System.out.println("\nNuts! An open, which was already open!");
					addMe = true;
					inJavaCode = true;
					line.setCharAt(lefty,'!');
				} else if (righty != -1)  // we know it's closest to the beginning of the string, because if it wasn't the lefty would have run.
				{
					if (!inJavaCode)
						System.out.println("\nNuts! A close, which was already closed!");
					inJavaCode = false;
					addMe = true;
					line.setCharAt(righty,'!');
				}
			} while ((lefty != -1) || (righty != -1));
	//System.out.println("incode: " + inJavaCode + "  addme: " + addMe);
			if (inJavaCode || addMe)
				javaCt++;

//System.out.println(lineCt + " lines; " + javaCt + " java lines");
			line.setLength(0);
		}
		inFile.close();
		System.out.println(lineCt + " lines; " + javaCt + " java lines : " + fileName);
	}


	public static void main (String[] args) throws Exception
	{
		if (args.length == 0)
		{
			System.out.println("Need directory or file to scan.");
			System.exit(1);
		}
		String fileParam = args[0];
/*
		Hashtable user_IP = new Hashtable();
		Hashtable IP_filelist = new Hashtable();
		TreeMap file_count = new TreeMap();
		Vector uploadList = new Vector();
*/
		System.out.print("Searching for JSPs");
		Vector JSPlist = buildFileList(fileParam);
		System.out.println("\n\n");

		String fileName = null;
		Iterator ike = JSPlist.iterator();
		while (ike.hasNext())
		{
			fileName = (String)ike.next();
			process(fileName);
		}
	}
}