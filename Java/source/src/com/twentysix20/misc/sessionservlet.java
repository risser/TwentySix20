package com.twentysix20.misc;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;



public class sessionservlet extends HttpServlet
{
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		HttpSession bing = request.getSession();

		if (request.getParameter("stuff")==null)
		{
			String band = (String)bing.getAttribute("band");
			if (band == null)
				response.sendRedirect("sessionform.html");
			else
			{
				PrintWriter dirk = response.getWriter();
				dirk.println("<html><body>Your most favored band is <b>"+band+"</b>!</body></html>");
			}
		}
		else
		{ //stuff it
			bing.setAttribute("band",request.getParameter("name"));
			PrintWriter dirk = response.getWriter();
			dirk.println("<html><body><b>Stuffed: "+request.getParameter("name")+"</b>!</body></html>");
		}
	}
} // class