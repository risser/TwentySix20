package com.twentysix20.misc;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class flintservlet extends HttpServlet
{
	private String list = " FRED BARNEY WILMA DINO PEBBLES BAMBAM BAM-BAM BETTY GREAT GAZOO MR. SLATE ";
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException
	{
		String pus = request.getParameter("name").toUpperCase();

System.out.println(pus);
System.out.println(list);
System.out.println(list.indexOf(pus));
		if (list.indexOf(" " + pus + " ") > -1)
			response.sendRedirect("http://www.flintstones.com");
		else
			response.sendRedirect("http://www.cartoonnetwork.com");
	}
//	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
} // class