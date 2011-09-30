package com.twentysix20.misc;
// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3)
// Source File Name:   timeservlet.java

import java.io.*;
import java.text.*;
import java.util.Date;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.*;

public class timeservlet extends HttpServlet
{

    public timeservlet()
    {
    }

    public void service(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException
    {
System.out.println("Before");
        String s = httpservletrequest.getParameter("do");
System.out.println("After: "+s);
        if(s == null)
            s = "ask";
System.out.println("After: "+s);
        if(s.equals("ask"))
            doAsk(httpservletrequest, httpservletresponse);
        else
        if(s.equals("record"))
            doRecord(httpservletrequest, httpservletresponse);
        else
            doError(httpservletrequest, httpservletresponse);
    }

    void doAsk(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException
    {
        String s = null;
        Cookie acookie[] = httpservletrequest.getCookies();
        for(int i = 0; i < acookie.length; i++)
        {
            Cookie cookie = acookie[i];
            if(!cookie.getName().equals("goofyass_last_hit"))
                continue;
            s = cookie.getValue();
            break;
        }

        if(s == null)
            s = "Never Before";
        else
            s = s.substring(0, 4) + "-" + s.substring(4, 6) + "-" + s.substring(6, 8) + " " + s.substring(8, 10) + ":" + s.substring(10, 12) + ":" + s.substring(12, 14);
        Date date = null;
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try
        {
            date = simpledateformat.parse(s);
            System.out.println("olddate: " + s);
            System.out.println("mydate: " + date);
        }
        catch(ParseException parseexception)
        {
            System.out.println("Whoops. Parse Exception");
            date = new Date();
        }
        PrintWriter printwriter = httpservletresponse.getWriter();
        printwriter.println("<html><body>");
        printwriter.println("Last time you updated was: " + s + "<br>");
        printwriter.println("Please tell me what you've been doing since then: <br>");
        printwriter.println("<form method=\"POST\" action=\"/wls/play/time?do=record\">");
        printwriter.println("<input type=\"hidden\" name=\"millis\" value=\"" + date.getTime() + "\">");
        printwriter.println("<input type=\"text\" name=\"did\" value=\"Nuthin'\">");
        printwriter.println("<input name=\"submit\" type=\"submit\">");
        printwriter.println("</form></body></html>");
    }

    void doRecord(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException
    {
        String s = httpservletrequest.getParameter("did");
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
        FileWriter filewriter = null;
        try
        {
            filewriter = new FileWriter("c:\\users\\time" + simpledateformat.format(new Date()) + ".log", true);
            simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String s1 = httpservletrequest.getParameter("millis");
System.out.println("ms: " + s1);
            long l = Long.parseLong(s1);
System.out.println("millis: " + l);
            Date date = new Date();
            long l1 = (date.getTime() - l) / 1000L / 60L;
System.out.println("now: " + date.getTime());
System.out.println("now: " + date);
System.out.println("mins: " + l1);
            Date date1 = new Date();
            date1.setTime(l);
System.out.println("olddate: " + date1);
            filewriter.write(simpledateformat.format(new Date()) + " (" + l1 + ")");
            filewriter.write(" -> " + s + "\n");
        }
        finally
        {
            if(filewriter != null)
                filewriter.close();
        }
        simpledateformat = new SimpleDateFormat("yyyyMMddHHmmss");
        Cookie cookie = new Cookie("goofyass_last_hit", simpledateformat.format(new Date()));
        cookie.setMaxAge(0x1e28500);
        httpservletresponse.addCookie(cookie);
        PrintWriter printwriter = httpservletresponse.getWriter();
        printwriter.println("<html><body>");
        printwriter.println("Done! " + simpledateformat.format(new Date()));
        printwriter.println("</body></html>");
    }

    void doError(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException
    {
        PrintWriter printwriter = httpservletresponse.getWriter();
        printwriter.println("<html><body>Huh.  You gave me a suggestion that I plain don't understand.  Sorry!</body></html>");
    }
}
