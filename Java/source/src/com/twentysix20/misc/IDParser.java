package com.twentysix20.misc;

import java.util.Vector;

public class IDParser
{
	public static Vector parse(String ids)
	{
		Vector strings = new Vector();
		int last = -1;
		for (int i = 0; i < ids.length(); i++)
		{
			char pos = ids.charAt(i);
			if (((pos >= 'A') && (pos <= 'Z')) ||
				((pos >= 'a') && (pos <= 'z')) ||
				((pos >= '0') && (pos <= '9')) ||
				 (pos == '_'))
			{
				if (last == -1)
					last = i;
			}
			else
			{
				if (last > -1)
				{
					strings.add(ids.substring(last,i));
					last = -1;
				}
			}
		}
		if (last > -1)
			strings.add(ids.substring(last,ids.length()));
		return strings;
	}

	public static void testPrint(Vector strings)
	{
		System.out.println("\nStrings:");
		for (int i = 0; i < strings.size(); i++)
		{
			String hud = (String)strings.get(i);
			System.out.println("::"+hud+"::");
		}
	}

	public static void main (String[] args)
	{
		testPrint(parse("a25972t"));
		testPrint(parse("a25972t "));
		testPrint(parse(" a25972t"));
		testPrint(parse(""));
		testPrint(parse("a"));
		testPrint(parse("\nn"));
		testPrint(parse("n\n"));
		testPrint(parse("a 2 5 9 7 2 t "));
		testPrint(parse(" a 2 5 9 7 2 t"));
		testPrint(parse(" \n  %$$#%%#@a25972t$$#############nagnagt&*&$*&$*&*&&*&77 ngh00st  &$@!&\t\t\n)%(*&    !%(^)!(&^ %t"));
	}
}