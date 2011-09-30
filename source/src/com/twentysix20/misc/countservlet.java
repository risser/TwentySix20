package com.twentysix20.misc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class countservlet extends HttpServlet
{

	private int count;
	private String FILENAME = "c:/temp/count.txt";

	public void init()
	{
		String line = null;

		try
		{
			BufferedReader inFile = new BufferedReader(new FileReader(FILENAME));
			line = inFile.readLine();
			inFile.close();
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}

		if (line == null)
			count = 0;
		else
		{
			try
			{
				count = Integer.valueOf(line).intValue();
			}
			catch (Exception e)
			{
				count = 0;
			}
		}
	}
	public void service(HttpServletRequest request, HttpServletResponse response)
	{
		count++;
		try
		{
			PrintWriter foo = response.getWriter();
			foo.println(count);
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
		try
		{
			PrintWriter outFile = new PrintWriter(new FileWriter(FILENAME));
			outFile.println(count);
			outFile.close();
		}
		catch (Exception e)
		{
			// oh well
			System.out.println(e.toString());
		}
	}
	public void destroy()
	{
		try
		{
			PrintWriter outFile = new PrintWriter(new FileWriter(FILENAME));
			outFile.println(count);
			outFile.close();
		}
		catch (Exception e)
		{
			// oh well
			System.out.println(e.toString());
		}
	}
}