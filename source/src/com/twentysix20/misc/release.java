package com.twentysix20.misc;

import java.io.*;
import java.util.*;

public class release
{
	public static void main (String[] args) throws Exception
	{
		BufferedReader inFile = null;
		BufferedWriter mini = null;
		BufferedWriter next = null;
		BufferedWriter open = null;
		try{
			if (args.length != 3)
				throw new Exception("Must have exactly 3 args: input file name, next version, next release date (mm/dd/yyyy).");

			inFile = new BufferedReader(new FileReader(args[0]));
			mini = new BufferedWriter(new FileWriter("psdk_mini.html"));
			next = new BufferedWriter(new FileWriter("psdk_next.html"));
			open = new BufferedWriter(new FileWriter("psdk_open.html"));

			String version = args[1];
			String release = args[2];  // mm/dd/yyyy
			String shortRelease = release.substring(0,5);  // mm/dd

			String lineIn;
			int linect = 0;

			next.write("<SPAN class=\"header\">Next Release</SPAN><BR><table width=\"100%\" border=\"1\" cellspacing=\"1\" cellpadding=\"1\"><tr bgcolor=\"#CCCCFF\"><td colspan=\"3\"><b>");
			next.write("Next Release : "+release);
			next.write("</b></td></tr>");
			open.write("<SPAN class=\"header\">Open Requests</SPAN><BR><table width=\"100%\" border=\"1\" cellspacing=\"1\" cellpadding=\"1\"><tr bgcolor=\"#CCCCFF\"><td colspan=\"3\"><b>Open Requests</b></td></tr>");
			mini.write("<TABLE CELLSPACING=\"1\" CELLPADDING=\"2\" BORDER=\"0\" ALIGN=\"CENTER\"> <TR> <TD VALIGN=\"MIDDLE\" BGCOLOR=\"#FFCC33\" COLSPAN=\"4\"> <SPAN CLASS=\"font1b\">Portal Framework ");
			mini.write("Release "+version+" ("+shortRelease+")");
			mini.write("</SPAN><BR> </TD> </TR> <TR> <TD VALIGN=\"MIDDLE\" BGCOLOR=\"#CCCCCC\" ALIGN=\"CENTER\"> <SPAN CLASS=\"fontsmallbold\">Update<BR> </TD> <TD VALIGN=\"MIDDLE\" BGCOLOR=\"#CCCCCC\" ALIGN=\"CENTER\"> <SPAN CLASS=\"fontsmallbold\">Type<BR> </TD> <TD VALIGN=\"MIDDLE\" BGCOLOR=\"#CCCCCC\" ALIGN=\"CENTER\"> <SPAN CLASS=\"fontsmallbold\">Priority<BR> </TD> <TD VALIGN=\"MIDDLE\" BGCOLOR=\"#CCCCCC\" ALIGN=\"CENTER\"> <SPAN CLASS=\"fontsmallbold\">Requested By<BR> </TD> </TR>");

			// need to skip first line with header info
			if ((lineIn = inFile.readLine()) == null)
				throw new Exception("Nuts!  No lines to read!");

			while ((lineIn = inFile.readLine()) != null)
			{
				linect++;
				StringBuffer biff = new StringBuffer(lineIn);
				int x;
				while ((x = biff.toString().indexOf("\t\t")) != -1)
				{
					biff.insert(x+1," ");
				}
				StringTokenizer bob = new StringTokenizer(biff.toString(),"\t");
				bob.nextToken();
				bob.nextToken();
				bob.nextToken();
				String priority = bob.nextToken().substring(2);
				String type = bob.nextToken();
				String name = bob.nextToken();
				bob.nextToken();
				bob.nextToken();
				String dueDate = bob.nextToken().trim();
				bob.nextToken();
				String desc = bob.nextToken();
				String who = "";
				int where = desc.indexOf(":");
				if (where != -1)
				{
					who = desc.substring(0,where);
					desc = desc.substring(where+1).trim();
				}

				if (!dueDate.equals(""))
				{

					next.write("<tr> <td> <table width=\"100%\" border=\"0\"> <tr> <td width=\"45%\"><b>");
					next.write(name);
					next.write("</b></td> <td align=\"center\" width=\"10%\"><b>");
					next.write(priority);
					next.write("</b></td> <td align=\"right\" width=\"45%\"><b>");
					next.write(who);
					next.write("</b></td> </tr> <tr> <td colspan=\"3\"><i>");
					next.write(type);
					next.write(":</i> ");
					next.write(desc);
					next.write("</td> </tr> </table> </td> </tr>");

					mini.write("<TR><TD VALIGN=\"MIDDLE\" BGCOLOR=\"#F3F3F3\" ALIGN=\"LEFT\"><SPAN CLASS=\"fontsmalldark\">");
					mini.write(name);
					mini.write("<BR></TD><TD VALIGN=\"MIDDLE\" BGCOLOR=\"#F3F3F3\" ALIGN=\"CENTER\"><SPAN CLASS=\"fontsmalldark\">");
					mini.write(type);
					mini.write("<BR></TD><TD VALIGN=\"MIDDLE\" BGCOLOR=\"#F3F3F3\" ALIGN=\"CENTER\"><SPAN CLASS=\"fontsmalldark\">");
					mini.write(priority);
					mini.write("<BR></TD><TD VALIGN=\"MIDDLE\" BGCOLOR=\"#F3F3F3\" ALIGN=\"CENTER\"><SPAN CLASS=\"fontsmalldark\">");
					mini.write(who);
					mini.write("<BR></TD></TR>");
				}
				else
				{
					open.write("<tr> <td> <table width=\"100%\" border=\"0\"> <tr> <td width=\"45%\"><b>");
					open.write(name);
					open.write("</b></td> <td align=\"center\" width=\"10%\"><b>");
					open.write(priority);
					open.write("</b></td> <td align=\"right\" width=\"45%\"><b>");
					open.write(who);
					open.write("</b></td> </tr> <tr> <td colspan=\"3\"><i>");
					open.write(type);
					open.write(":</i> ");
					open.write(desc);
					open.write("</td></tr></table></td></tr>");
				}
			}
			next.write("</table><br>");
			open.write("</table><br>");
			mini.write("</table><br>");
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
			StringWriter stackTrace = new StringWriter();
			PrintWriter stackWriter = new PrintWriter(stackTrace);
			e.printStackTrace(stackWriter);
			System.out.println("STACK TRACE::"+stackTrace.toString());
		}
		finally
		{
			if (inFile != null) inFile.close();
			if (mini != null) mini.close();
			if (next != null) next.close();
			if (open != null) open.close();
		}
	}
}