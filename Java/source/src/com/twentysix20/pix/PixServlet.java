/*
TODO:
 - allow emailing of pictures (half/full size, and thumbs?)
 - allow saving of pictures to disk (send just picture, not in HTML)
 - allow captioning
 - move colors and other config to a separate file (columns, colors, img sizes, etc.)
 - show dates & large size (in K) -> put in float over text for thumbnails
 - columns for folders
 - smaller font for folders/other files
 - fix MP3
 - "ignore" character, like ~ to ignore images/folders/files
 - strip extensions & anything before ! or other symbol... captioning!
 - create "slideshow" links for small photos (< prev, ^thumb, > next)
*/

package com.twentysix20.pix;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PixServlet extends HttpServlet
{
	Hashtable Pics = new Hashtable();
	Hashtable Oldpics = new Hashtable();

	PicFolder rootFolder;

	String rootdir = "";
	String thumbdir = "";
	String url_root = "";
	String app_root = "";
	String thumbprefix = "";
	String showprefix = "";
	String rootFolderName = "Image Root";

	int thumbSize = 100;
	int showSize = 640;
	int maxColumns = 6;

	String sz_THUMB = "t";
	String sz_MID   = "m";
	String sz_FULL  = "f";

	String TUNE_ICON = "/pix/tune.gif";
	String FILM_ICON = "/pix/film.gif";
	String FOLDER_ICON = "/pix/folder.gif";

    public PixServlet()
    {
    }

    public void init(ServletConfig config) throws ServletException
    {
		rootdir = config.getInitParameter("rootdir");
		if (rootdir == null)
			throw new ServletException("rootdir init parameter (in web.xml) cannot be NULL!");
		thumbdir = config.getInitParameter("thumbdir");
		if (thumbdir == null)
			throw new ServletException("thumbdir init parameter (in web.xml) cannot be NULL!");
		thumbprefix = config.getInitParameter("thumbprefix");
		if (thumbprefix == null)
			throw new ServletException("thumbprefix init parameter (in web.xml) cannot be NULL!");
		showprefix = config.getInitParameter("showprefix");
		if (showprefix == null)
			throw new ServletException("showprefix init parameter (in web.xml) cannot be NULL!");
		url_root = config.getInitParameter("url_root");
		if (url_root == null)
			throw new ServletException("url_root init parameter (in web.xml) cannot be NULL!");
		app_root = config.getInitParameter("app_root");
		if (app_root == null)
			throw new ServletException("app_root init parameter (in web.xml) cannot be NULL!");

		File dirtest = new File(rootdir);
		if (!dirtest.exists())
			throw new ServletException("rootdir ("+rootdir+") does not exist!");
		dirtest = new File(thumbdir);
		if (!dirtest.exists())
			throw new ServletException("thumbdir ("+thumbdir+") does not exist!");

		try
		{
			refreshFiles();
		}
		catch (Exception e)
		{
			throw new ServletException(e);
		}
    }

    public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {

		String action = req.getParameter("do");
		String item = req.getParameter("item");
		String size = req.getParameter("size");

		if (item == null)
		{
			item = new MD5Encoder(rootdir).toString();
		}

		if (null == action || action.equals(""))
			action = "cat";

		try
		{
			if (action.equals("refresh"))
			{
				refreshFiles();
				action = "cat";
			}
			if (action.equals("cat"))
				doCat(req,resp,item);
			else if (action.equals("show"))
				doShow(req,resp,item,size);
			else if (action.equals("view"))
				doOther(req,resp,item);
			else if (action.equals("img"))
				doImage(req,resp,item,size);
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

		out.println("<br><br><b>Exception:</b><br>"+e+"<br><br>");
		out.println("<b>Stack Trace:</b><br>");
		StringWriter stackTrace = new StringWriter();
		PrintWriter stackWriter = new PrintWriter(stackTrace);
		e.printStackTrace(stackWriter);
		out.println(stackTrace.toString());
		out.println("<br></body></html>");
	}

    public void refreshFiles() throws Exception
	{
//		rootFolder = new PicFolder(rootdir,rootFolderName);
//		rootFolder.setParent(rootFolderName);
		buildThumbList();
		buildFileList(rootdir.substring(0,rootdir.lastIndexOf("\\")),rootdir.substring(rootdir.lastIndexOf("\\")+1), null);
		removeOldThumbs();
//		Pics.put(rootFolderName,rootFolder);
	}

	private void buildThumbList() throws Exception
	{
		File path = new File(thumbdir);
		String[] fileNames = path.list();
		Oldpics.clear();
		String hold = null;

		for (int i = 0; i < fileNames.length; i++)
		{
			if (fileNames[i].startsWith(thumbprefix))
				hold = fileNames[i].substring(thumbprefix.length());
			else if (fileNames[i].startsWith(showprefix))
				hold = fileNames[i].substring(showprefix.length());
			else
				hold = fileNames[i];

			if (Oldpics.get(hold) == null)
			{
				Oldpics.put(hold,hold);
			}
		}
	}

	private void removeOldThumbs() throws Exception
	{
		for (Enumeration e = Oldpics.keys(); e.hasMoreElements();)
		{
			String code = (String)e.nextElement();
			String thumb = thumbprefix + code;
			String show = showprefix + code;
//System.out.println("::"+code+"::");
//System.out.println("::"+thumb+"::");
//System.out.println("::"+show+"::");
//System.out.println(">>"+thumbdir + "\\" + thumb+"<<");
//System.out.println(">>"+thumbdir + "\\" + show+"<<");
			File deleteMe = new File(thumbdir + "\\" + thumb);
			if (deleteMe.exists())
			{
System.out.println("Deleting " + thumb);
				deleteMe.delete();
			}
			deleteMe = new File(thumbdir + "\\" + show);
			if (deleteMe.exists())
			{
System.out.println("Deleting " + show);
				deleteMe.delete();
			}
			deleteMe = new File(thumbdir + "\\" + code);
			if (deleteMe.exists())
			{
System.out.println("Deleting " + code);
				deleteMe.delete();
			}
		}
	}

	private void buildFileList(String p, String name, PicFolder parent) throws Exception
	{
		String pathName = p + (p.equals("") ? "" : "\\") + name;
		File path = new File(pathName);
		String[] fileNames = path.list();

		if ((!pathName.toLowerCase().equals(thumbdir.toLowerCase())) && (fileNames != null))
		{
			PicFolder folder = new PicFolder(pathName,name);
			for (int i = 0; i < fileNames.length; i++)
				buildFileList(pathName, fileNames[i], folder);
			if (folder.size() > 0)	// only keep folders with pictures in them
			{
				if (parent != null)
					parent.addPicFile(folder);
				folder.setParent(parent);
				Pics.put(folder.getCode(),folder);
			}
		}
		else if (pathName.toLowerCase().indexOf(".mov") != -1)
		{
System.out.println("Recognized Quicktime Video " + name);
			PicOther pic = new PicOther(pathName,name);
			pic.setIconPath(FILM_ICON);
			pic.setMimeType("video/quicktime");
			parent.addPicFile(pic);
			pic.setParent(parent);
			Pics.put(pic.getCode(),pic);
		}
		else if (pathName.toLowerCase().indexOf(".mp3") != -1)
		{
System.out.println("Recognized MP3 " + name);
			PicOther pic = new PicOther(pathName,name);
			pic.setIconPath(TUNE_ICON);
			pic.setMimeType("audio/mpeg3");
			parent.addPicFile(pic);
			pic.setParent(parent);
			Pics.put(pic.getCode(),pic);
		}
		else if ((pathName.toLowerCase().indexOf(".gif") != -1) ||
				 (pathName.toLowerCase().indexOf(".jpg") != -1) ||
				 (pathName.toLowerCase().indexOf(".jpeg") != -1))
		{
			Picture pic = new Picture(pathName,name);
			String jpgCode = pic.getCode()+".jpg";
			pic.setThumb(thumbdir + "\\" + thumbprefix + jpgCode);
			pic.setShow(thumbdir + "\\" + showprefix + jpgCode);
			if (!pic.thumbExists() || !pic.thumbCurrent())
			{
System.out.println((pic.thumbExists() ? "Upd" : "Cre" ) + "ating thumb for " + name);
				Thumbnail.createThumbnail(pathName,pic.getThumbPath(),thumbSize);
			}
			if (!pic.showExists() || !pic.showCurrent())
			{
System.out.println((pic.showExists() ? "Upd" : "Cre" ) + "ating small for " + name);
				Thumbnail.createThumbnail(pathName,pic.getShowPath(),showSize);
			}
			parent.addPicFile(pic);
			pic.setParent(parent);
			Pics.put(pic.getCode(),pic);
			Oldpics.remove(jpgCode);
		}
	}

    public void doCat(HttpServletRequest req, HttpServletResponse resp, String item) throws Exception
	{
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");

		PicFolder f = (PicFolder)Pics.get(item);

		out.println("<html><head><title>20Six20 Pix Server: "+f.getName()+"</title><style>");
		out.println("<!-- ");
		out.println("td.caption { text-decoration: none; color: Black; font-family: sans-serif; font-size: xx-small; } ");
    	out.println("img.thumb { border-style: double; border-width: 3; }");
		out.println("--> </style> </head>");
		out.println("<body bgcolor=\"#FFDD99\"><table><tr><td>");
		out.println("<h2>"+f.getName()+"</h2></td><td>&nbsp;</td><td>");
		if (f.getParent() != null)
			out.println("<h6><a href=\""+app_root+"/pix?do=cat&item="+f.getParent().getCode()+"\">(BACK)</a></h6>");
		out.println("</td></tr></table>");
		for (int i=0;i < f.size(); i++)
			if (f.isFolder(i))
			{
				out.println("<img src=\""+app_root+FOLDER_ICON+"\">&nbsp;");
				out.println("<a href=\""+app_root+"/pix?do=cat&item="+f.getPicFolder(i).getCode()+"\">"+f.getPicFolder(i).getName()+"</a><br>");
			}
		out.println("<br>");

		boolean twee = false;
		for (int i=0;i < f.size(); i++)
			if (f.isOther(i))
			{
				PicOther other = f.getPicOther(i);
				out.println("<img src=\""+app_root+other.getIconPath()+"\">&nbsp;");
				out.println("<a href=\""+app_root+"/pix?do=view&item="+other.getCode()+"\">"+other.getName()+"</a><br>");
				twee = true;
			}
		if (twee)
			out.println("<br>");

		out.println("<table border=0><tr>");

		int r = 0;
		for (int i=0;i < f.size(); i++)
			if (f.isPicture(i))
			{
				out.println("<td align=\"center\"valign=\"top\">");
				out.println("<table bgcolor=\"#FFFFFF\" border=1 width=100><tr><td align=\"center\">");
				out.println("<a href=\""+app_root+"/pix?do=show&size="+sz_MID+"&item="+f.getPicture(i).getCode()+"\">");
				out.println("<img class=\"thumb\"src=\""+app_root+"/pix?do=img&size="+sz_THUMB+"&item="+f.getPicture(i).getCode()+"\"></a>");
				out.println("</td></tr><tr><td class=\"caption\" align=\"center\" valign=\"top\">"+f.getPicture(i).getName()+"</td></tr>");
				out.println("</table>");
				out.println("</td>");
				if ((++r % maxColumns) == 0)
					out.println("</tr><tr>");
			}
		out.println("</tr>");
		int yr = new Date().getYear() + 1900;
		if (r > 0)
			out.println("<tr><td align=\"center\" colspan="+(r < 6 ? r : 6)+"><b><font color=\"gray\" size=1>click image to enlarge &middot; all images copyright 1997-"+yr+"</font></b></td></tr>");
		out.println("</table>");
		out.println("</body></html>");
	}

    public void doShow(HttpServletRequest req, HttpServletResponse resp, String code, String size) throws Exception
	{
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");

		Picture p = (Picture)Pics.get(code);
		out.println("<html><head><title>20Six20 Pix Server: "+p.getName()+"</title><style>");
		out.println("<!-- ");
		out.println("td.caption { text-decoration: none; color: Black; font-family: sans-serif; font-size: xx-small; } ");
    	out.println("img.thumb { border-style: double; border-width: 3; }");
		out.println("--> </style> </head>");
		out.println("<body bgcolor=\"#FFDD99\"><table><tr><td>");
		out.println("<h2>"+p.getName()+"</h2></td><td>&nbsp;</td><td>");
		if (p.getParent() != null)
			out.println("<h6><a href=\""+app_root+"/pix?do=cat&item="+p.getParent().getCode()+"\">(BACK)</a></h6>");
		out.println("</td></tr></table>");

		out.println("<table><tr><td>");
		if (size.equals(sz_MID))
			out.println("<a href=\""+app_root+"/pix?do=show&size="+sz_FULL+"&item="+p.getCode()+"\">");
		out.println("<img class=\"thumb\" src=\""+app_root+"/pix?do=img&size="+size+"&item="+code+"\"></a>");
		if (size.equals(sz_MID))
			out.println("</a>");
		int yr = new Date().getYear() + 1900;
		out.println("</td></tr><tr><td "+(size.equals(sz_MID)?"align=\"center\"":"")+"><b><font color=\"gray\" size=1>");
		if (size.equals(sz_MID))
			out.println("click image to enlarge &middot; ");
		out.println("all images copyright 1997-"+yr+"</font></b></td></tr>");
		out.println("</body></html>");
	}

    public void doImage(HttpServletRequest req, HttpServletResponse resp, String code, String size) throws Exception
	{
		Picture pic = (Picture)Pics.get(code);
		if (size.equals(sz_THUMB))
			image(req,resp,pic.getThumbPath());
		if (size.equals(sz_FULL))
			image(req,resp,pic.getPath());
		if (size.equals(sz_MID))
			image(req,resp,pic.getShowPath());
	}

    public void doOther(HttpServletRequest req, HttpServletResponse resp, String code) throws Exception
	{
		PicOther pic = (PicOther)Pics.get(code);
		view(req,resp,pic.getPath(),pic.getMimeType());
	}

    public void image(HttpServletRequest req, HttpServletResponse resp, String path) throws Exception
	{
		String format = path.substring(path.lastIndexOf(".")+1);
		view (req,resp,path,"image/"+format);
	}

    public void view(HttpServletRequest req, HttpServletResponse resp, String path, String type) throws Exception
	{
		resp.setContentType(type);
		BufferedInputStream image = new BufferedInputStream(new FileInputStream(path));
		BufferedOutputStream out = new BufferedOutputStream(resp.getOutputStream()); //ServletOutputStream
		int b;
		while ((b = image.read()) != -1) {
			out.write(b);
		}
		out.close();
		image.close();
	}

}
