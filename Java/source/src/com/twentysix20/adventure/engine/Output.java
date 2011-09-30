package com.twentysix20.adventure.engine;

import java.io.Writer;
import java.util.Enumeration;
import com.twentysix20.adventure.engine.*;

public class Output
{
	private Writer out;
	private String itemDelimiter;


	public Output (Writer writer)
	{
		out = writer;
	}

	public void print(String s)
	{
		try
		{
			out.write(s);
		}
		catch (java.io.IOException ioe)
		{
			System.err.println("Unable to write output string to Writer.  String follows:\n===\n"+s+"\n===\n");
		}
	}

	public void println(String s)
	{
		print(s + "\n");
	}

	public void newLine()
	{
		print("\n");
	}

	public void printObjectNames(Enumeration objects, String tag, String delimiter, String nothing)
	{
		int count = 0;
		boolean showNothing = (nothing != null) && nothing.trim().equals("");
		StringBuffer bob = new StringBuffer(tag);
		while (objects.hasMoreElements())
		{
			if (count++ > 0)
				bob.append(delimiter);
			Base billy = (Base)objects.nextElement();
			bob.append(billy.getName());
		}
		if (count == 0 && showNothing)
			bob.append(nothing);
		if (count > 0 || showNothing)
			println(bob.toString());
	}



	public static void main(String[] Args) throws Exception
	{
		System.out.println("adventure.engine.Output main");
	}
}