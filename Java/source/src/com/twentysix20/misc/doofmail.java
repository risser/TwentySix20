package com.twentysix20.misc;

//import geae.messaging.*;
//import geae.util.*;
import java.math.*;

public class doofmail {
	static public void main (String[] args) throws Exception {
        BigDecimal a = new BigDecimal("0");
        BigDecimal b = new BigDecimal("3");
        
        System.out.println("Comparing A & B: "+a.compareTo(null));
        System.out.println("Comparing B & A: "+b.compareTo(a));
        
/*		GEAEMailMessage billy = new GEAEMailMessage();
		billy.setSender("epassadministrator@ae.ge.com");
		billy.setSubject("Email for you!");
		billy.setRecipient (GEAEMailMessage.TO, "peter.risser@ae.ge.com");
		billy.setText ("Hi there.  I'm Billy-Mail!");
		billy.send();
		*/
//		byte[] raw = GEAEBase64.decode("bGl2ZWxpbmtkZXZsAAAA");
//		System.out.println(new String(raw,"UTF8"));
	}
}