package com.twentysix20.go;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoServlet extends HttpServlet
{

	private String url_root;
	private String app_root;
	private String mailserv;
	private String maillogin;
	private String mailpass;
	private String iBoard;
	private String iPiece;
	private String iCross;
	static private String imgDir = "img/";

    public GoServlet()
    {
    }

    public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
		url_root = getServletConfig().getInitParameter("url_root");
		app_root = getServletConfig().getInitParameter("app_root");
		mailserv = getServletConfig().getInitParameter("mailserv");
		mailpass = getServletConfig().getInitParameter("pass");
		maillogin = getServletConfig().getInitParameter("login");
		iBoard = getServletConfig().getInitParameter("board_img");
		iPiece = getServletConfig().getInitParameter("piece_img");
		iCross = getServletConfig().getInitParameter("cross_img");
System.out.println(url_root);
System.out.println(app_root);
System.out.println(iBoard);
System.out.println(iPiece);
System.out.println(iCross);
System.out.println("--------------");

		if ((url_root == null) || url_root.equals(""))
			System.out.println("No URL Root included.");
		if ((app_root == null) || app_root.equals(""))
			System.out.println("No App Root included.");
		if ((mailserv != null) && !mailserv.equals(""))
		{
			if ((maillogin== null) || maillogin.equals(""))
				System.out.println("No mail login included.");
			if ((app_root == null) || app_root.equals(""))
				System.out.println("No mail password included.");
		}
		if ((iBoard == null) || iBoard.equals(""))
			System.out.println("No board image included.");
		if ((iPiece == null) || iPiece.equals(""))
			System.out.println("No piece image included.");
		if ((iCross == null) || iCross.equals(""))
			System.out.println("No crosshatch image included.");

		String bdesc  = req.getParameter("board");
		String action = req.getParameter("do");
		if (null == bdesc && null == action)
			action = "form";
		if (null == action || action.equals(""))
			action = "update";
		try
		{
			if (action.equals("form"))
				doForm(req,resp);
			else
			{
				if (null == req.getParameter("be"))
					doError(req,resp,"Black's Email not included.");
				if (null == req.getParameter("we"))
					doError(req,resp,"White's Email not included.");
				if (null == req.getParameter("turn"))
					doError(req,resp,"Turn not included.");
				if (null == req.getParameter("x") ^ null == req.getParameter("y"))
					doError(req,resp,"Must have both x and y, or neither, but not only one.");

				if (action.equals("new"))
					doNew(req,resp);
				if (action.equals("update"))
					doUpdate(req,resp,bdesc);
				if (action.equals("mail"))
					doMail(req,resp,bdesc);
			}
		}
		catch (Exception e)
		{
			doHardError(req,resp,e);
		}
    }


    public void doError(HttpServletRequest req, HttpServletResponse resp, String message) throws IOException
	{
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");

		out.println("<html><head><title>");
		out.println("20six20 Go Server: Error Page");
		out.println("</title></head><body>");
		out.println("<h2><font color=red>Configuration Error</font></h2>");
		out.println("<ul><li>"+message+"</ul>");
		out.println("<br></body></html>");
	}

    public void doHardError(HttpServletRequest req, HttpServletResponse resp, Exception e) throws IOException
	{
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");

		out.println("<html><head><title>");
		out.println("20six20 Go Server: Error Page");
		out.println("</title></head><body>");

		out.println("<b>Parameters:</b><br>");
		out.println("board=" + req.getParameter("board"));
		out.println("<br>do=" + req.getParameter("do"));
		out.println("<br>be=" + req.getParameter("be"));
		out.println("<br>we=" + req.getParameter("we"));
		out.println("<br>note=" + req.getParameter("note"));
		out.println("<br>name=" + req.getParameter("name"));
		out.println("<br>turn=" + req.getParameter("turn"));
		out.println("<br>x=" + req.getParameter("x"));
		out.println("<br>y=" + req.getParameter("y"));
		out.println("<br><br><b>Exception:</b><br>"+e+"<br><br>");
		out.println("<b>Stack Trace:</b><br>");
		StringWriter stackTrace = new StringWriter();
		PrintWriter stackWriter = new PrintWriter(stackTrace);
		e.printStackTrace(stackWriter);
		out.println(stackTrace.toString());
		out.println("<br></body></html>");
	}

    public void doNew(HttpServletRequest req, HttpServletResponse resp) throws Exception
	{
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");

		int size = Integer.parseInt(req.getParameter("size"));
		boolean isWhite = (null == req.getParameter("black"));
		GoBoard bobo = new GoBoard(size);
		String bdesc = bobo.encode();
/*
		out.println("<b>Parameters:</b><br>");
		out.println("board=" + bdesc);
		out.println("<br>do=" + req.getParameter("do"));
		out.println("<br>be=" + req.getParameter("be"));
		out.println("<br>we=" + req.getParameter("we"));
		out.println("<br>note=" + req.getParameter("note"));
		out.println("<br>name=" + req.getParameter("name"));
		out.println("<br>turn=" + req.getParameter("turn"));
		out.println("<br>x=" + req.getParameter("x"));
		out.println("<br>y=" + req.getParameter("y"));

		out.println("<br>button=" + req.getParameter("button"));
		out.println("<br>black=" + req.getParameter("black"));
		out.println("<br>size=" + req.getParameter("size"));
*/
		if (isWhite)  // that is, the checkbox is not selected
		{
			doMail(req,resp,bdesc);
		}
		else
		{
			doUpdate(req,resp,bdesc);
		}

	}

    public void doForm(HttpServletRequest req, HttpServletResponse resp) throws Exception
	{
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");

		out.println("<html><head><title>20Six20 Go Server: New Game Form</title></head>");
		out.println("<body><h2>Set up your game:</h2>");
		out.println("<form>");
		out.println("<input type=\"hidden\" name=\"do\" value=\"new\">");
		out.println("<input type=\"hidden\" name=\"turn\" value=\"0\">");
		out.println("<table border=\"0\">");
		out.println("<tr> <td align=\"right\">Name:</td> <td><input type=\"text\" name=\"name\" size=\"20\"></td> </tr><tr>");
		out.println("<td align=\"right\">Board Size:</td>");
		out.println("<td><select name=\"size\"> <option>9</option> <option>13</option> <option>19</option> </select></td>");
		out.println("</tr><tr> <td align=\"right\">Black's eMail:</td> <td><input type=\"text\" name=\"be\" size=\"20\"></td>");
		out.println("</tr><tr> <td align=\"right\">White's eMail:</td> <td><input type=\"text\" name=\"we\" size=\"20\"></td>");
		out.println("</tr><tr><td></td><td>");
		out.println("<input type=\"checkbox\" checked name=\"black\">I will play first (Black)<br>");
		out.println("</td></tr><tr><td colspan=\"2\" align=\"center\"> <br>");
		out.println("<textarea name=\"note\" rows=\"5\" cols=\"40\">If you are NOT going to be black, you may add a note for Black here.</textarea> <br>");
		out.println("<input type=\"submit\" name=\"button\" value=\"Start Game\">");
		out.println("</table> </form> </body> </html>");
	}

    public void doMail(HttpServletRequest req, HttpServletResponse resp, String bdesc) throws Exception
	{

		String bemail = req.getParameter("be");
		String wemail = req.getParameter("we");
		String name   =  req.getParameter("name");
		String note   =  req.getParameter("note");

		if (null == name)
			name = "<no name>";

		int turn = Integer.parseInt(req.getParameter("turn"));

		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");

		out.println("<html><head><title>");
		out.println("20six20 Go Server: " + name + ", Mailing Page");
		out.println("</title></head><body>");
		out.println("<h2>Mailing:</h2>");

		String toMail = null;
		String fromMail = null;
		turn++;
		if (1 == (turn % 2))
		{
			toMail = bemail;
			fromMail = wemail;
		}
		else
		{
			toMail = wemail;
			fromMail = bemail;
		}

		out.print("&nbsp;<b>To:</b> "+toMail);

		String subject = "Game: " + name + ", " + turnToColor(turn) + "'s turn ("+turn+")";
		out.println("<br><b>Subject:</b><br>"+subject+"<br>");
		out.println("<br><b>Note:</b><br>"+note+"<br>");

		String url = "http://"+url_root+"/"+app_root+"/go";
		url += "?board="+bdesc+"&turn="+turn+"&be="+bemail+"&we="+wemail+"&name="+java.net.URLEncoder.encode(name);

		out.print("<b>Link:</b><br><a href=\""+url+"\">"+url+"</a><br><br>");

//		if (mailserv != null)
//		{
//			java.util.Properties properties=System.getProperties();
//			Session session=Session.getInstance(properties, null);
//			// construct a msg
//			MimeMessage msg=new MimeMessage(session);
//
//			msg.setFrom(new InternetAddress(fromMail));
//			msg.setRecipient (Message.RecipientType.TO, new InternetAddress(toMail));
//			msg.setSubject(subject);
//			msg.setSentDate(new Date());
//			msg.setText(note + "\n\n" + "Link:\n" + url);
//
//			Transport transport=session.getTransport("smtp");
//
//			transport.connect(mailserv,maillogin,mailpass);
//			// send the msg and close the connection
//			transport.sendMessage(msg, msg.getAllRecipients());
//			transport.close();
//
//			out.print("<br><br><b>Mail Sent.</b>");
//System.out.println("Mail Sent: "+subject+"; To: "+toMail);
//		}
		out.print("</body></html>");

	}

    public void doUpdate(HttpServletRequest req, HttpServletResponse resp, String bdesc) throws Exception
	{
		String bemail = req.getParameter("be");
		String wemail = req.getParameter("we");
		String name   =  req.getParameter("name");

		if (null == name)
			name = "<no name>";

		int turn = Integer.parseInt(req.getParameter("turn"));
		if (0 == turn)
			turn = 1;
		int where_x = -1;
		if (null != req.getParameter("x"))
			where_x = Integer.parseInt(req.getParameter("x"));
		int where_y = -1;
		if (null != req.getParameter("y"))
			where_y = Integer.parseInt(req.getParameter("y"));

		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");

		out.println("<html><head><title>");
		out.println("20six20 Go Server: " + name + ", Turn " + turn + " ("+turnToColor(turn)+")");
		out.println("</title></head><body>");
		out.println("<table border=0><tr><td><b>"+turnToColor(turn)+"'s Turn:<br>");

		GoBoard bobo = new GoBoard(bdesc);
		if (-1 != where_x && -1 != where_y)
			if (bobo.isEmpty(where_x,where_y))
			{
				if (1 == (turn % 2))
					bobo.setPiece(where_x,where_y,GoBoard.BLACK);
				else
					bobo.setPiece(where_x,where_y,GoBoard.WHITE);
			}
			else
				bobo.removePiece(where_x,where_y);

		String new_board = bobo.encode();

		StringBuffer img = null;
		String color = "error";
		int pos = -1;

		for (int y = 0; y < bobo.getSize(); y++)
		{
			for (int x = 0; x < bobo.getSize(); x++)
			{
				switch (bobo.getPiece(x,y)){
					case GoBoard.EMPTY :
						img = new StringBuffer(imgDir + iCross);
						StringBuffer suffix = new StringBuffer("_");
						if (y==0)
							suffix.append("N");
						if (y==bobo.getSize()-1)
							suffix.append("S");
						if (x==0)
							suffix.append("W");
						if (x==bobo.getSize()-1)
							suffix.append("E");
						if (suffix.length() > 1)
						{
							pos = img.toString().lastIndexOf('.');
							if (pos == -1)
								doError(req,resp,"Missing '.' in Cross image name: "+img.toString());
							img.insert(pos,suffix.toString());
						}
						color = "empty";
						break;
					case GoBoard.BLACK :
						img = new StringBuffer(imgDir + iPiece);
						pos = img.toString().lastIndexOf('.');
						if (pos == -1)
							doError(req,resp,"Missing '.' in Piece image name: "+img.toString());
						img.insert(pos,"_black");
						color = "black";
						break;
					case GoBoard.WHITE :
						img = new StringBuffer (imgDir + iPiece);
						pos = img.toString().lastIndexOf('.');
						if (pos == -1)
							doError(req,resp,"Missing '.' in Piece image name: "+img.toString());
						img.insert(pos,"_white");
						color = "white";
						break;
				}

				out.print("<a href=\"/"+app_root+"/go?board="+bobo.encode()
					+"&turn="+turn+"&x="+x+"&y="+y
					+"&be="+bemail+"&we="+wemail
					+"&name="+java.net.URLEncoder.encode(name)+" \">");
				out.print("<img src=\""+img.toString()+"\" border=0 alt=\"("+(x+1)+","+(y+1)+") "+color+"\">");
				out.print("</a>");
			}
			out.println("<br>");
		}
		out.println("</td><td width=\"25\">&nbsp;</td><td><b>When you are done, send a note:</b><br>");
		out.println("<center><form action=\"/"+app_root+"/go\" >");
System.out.println("APP:"+app_root);
		out.println("<input type=\"hidden\" name=\"board\" value=\""+bobo.encode()+"\">");
		out.println("<input type=\"hidden\" name=\"do\" value=\"mail\">");
		out.println("<input type=\"hidden\" name=\"name\" value=\""+name+"\">");
		out.println("<input type=\"hidden\" name=\"turn\" value=\""+turn+"\">");
		out.println("<input type=\"hidden\" name=\"be\" value=\""+bemail+"\">");
		out.println("<input type=\"hidden\" name=\"we\" value=\""+wemail+"\">");
		out.println("<textarea name=\"note\" wrap=\"soft\" rows=\"20\" cols=\"20\"></textarea>");
		out.println("<br><input type=\"submit\" name=\"mail\" value=\"Mail to "+turnToColor(turn+1)+"!\"></form>");


		out.println("</center></table>");
		out.println("</body></html>");
	}

	private String turnToColor(int turn)
	{
		if (1 == (turn % 2))
			return "Black";
		else
			return "White";
	}
}
