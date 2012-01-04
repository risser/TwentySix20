package com.twentysix20.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.StringEscapeUtils;

/**
 *
 * @author  tpnr007
 */
public class StringUtil {
    
    private StringUtil() {}
    
    static public boolean isEmpty(String s){
        return ((s == null) || ("".equals(s.trim())));
    }
    
    static public boolean contains(String it, String token) {
        if (it == null) throw new IllegalArgumentException("Main string cannot be null.");
        return (it.indexOf(token) > -1);
    }

    static public String grab(String it, String before) {
        return grab(it,before,"");
    }
    static public String grab(String it, String before, String after) {
        return grab(it, before, after, -1);
    }
    static public String grab(String it, String before, String after, int startPos) {
        if (it == null) throw new IllegalArgumentException("Main string cannot be null.");
        if (before == null) throw new IllegalArgumentException("Before string cannot be null.");
        if (after == null) throw new IllegalArgumentException("After string cannot be null.");
        
        int bpos = (before.length() == 0 ? 0 : it.indexOf(before,startPos));
        if (bpos < 0) return null;
        bpos += before.length();
        
        int bend = (after.length() == 0 ? it.length() : it.indexOf(after,bpos));
        if (bend < 0) return null;

        return it.substring(bpos,bend);
    }

//    protected String pullFromString(String base, String starter, String ender) {
//        return pullFromString(base, starter, ender, -1);
//    }
//
//    protected String pullFromString(String base, String starter, String ender, int pos) {
//        int bpos = base.indexOf(starter, pos);
//        if (bpos < 0)
//            throw new RuntimeException("'"+starter+" not found");
//        bpos = bpos + starter.length();
//        int epos = base.indexOf(ender, bpos);
//        if (epos < 0)
//            throw new RuntimeException("'"+ender+" not found");
//        String s = base.substring(bpos,epos);
//        return s;
//    }
    
    static public String simplify(String s) {
        StringBuffer buf = new StringBuffer(s.replaceAll("ß","ss").replaceAll("Æ","AE").replaceAll("æ","ae"));
        for(int i = 0; i < buf.length(); i++) {
            char c = buf.charAt(i);
            switch (c) {
                case 'À' : 
                case 'Á' : 
                case 'Â' : 
                case 'Ã' : 
                case 'Ä' : 
                case 'Å' : 
                    buf.setCharAt(i, 'A'); break;
                case 'Ç' : 
                    buf.setCharAt(i, 'C'); break;
                case 'È' : 
                case 'É' : 
                case 'Ê' : 
                case 'Ë' : 
                    buf.setCharAt(i, 'E'); break;
                case 'Ì' : 
                case 'Í' : 
                case 'Î' : 
                case 'Ï' : 
                    buf.setCharAt(i, 'I'); break;
                case 'Ñ' : 
                    buf.setCharAt(i, 'N'); break;
                case 'Ò' : 
                case 'Ó' : 
                case 'Ô' : 
                case 'Õ' : 
                case 'Ö' : 
                case 'Ø' : 
                    buf.setCharAt(i, 'O'); break;
                case 'Ù' : 
                case 'Ú' : 
                case 'Û' : 
                case 'Ü' : 
                    buf.setCharAt(i, 'U'); break;
                case 'Ý' : 
                    buf.setCharAt(i, 'Y'); break;
                case 'à' : 
                case 'á' : 
                case 'â' : 
                case 'ã' : 
                case 'ä' : 
                case 'å' : 
                    buf.setCharAt(i, 'a'); break;
                case 'ç' : 
                    buf.setCharAt(i, 'c'); break;
                case 'è' : 
                case 'é' : 
                case 'ê' : 
                case 'ë' : 
                    buf.setCharAt(i, 'e'); break;
                case 'ì' : 
                case 'í' : 
                case 'î' : 
                case 'ï' : 
                    buf.setCharAt(i, 'i'); break;
                case 'ñ' : 
                    buf.setCharAt(i, 'n'); break;
                case 'ò' : 
                case 'ó' : 
                case 'ô' : 
                case 'õ' : 
                case 'ö' : 
                case 'ø' : 
                    buf.setCharAt(i, 'o'); break;
                case 'ù' : 
                case 'ú' : 
                case 'û' : 
                case 'ü' : 
                    buf.setCharAt(i, 'u'); break;
                case 'ý' : 
                    buf.setCharAt(i, 'y'); break;
            }
        }
        return buf.toString();
    }

    static public String stackTrace(Throwable t) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        t.printStackTrace(printWriter);
        return stringWriter.toString();
    }

    static public String printException(Throwable t) {
        return t + "; " + stackTrace(t);
    }
    
    public static final int PAD_LEFT = 0;
    public static final int PAD_RIGHT = 1;
    public static String padLeft(String s, int length, char fillCharacter) {
        return pad(s, length, PAD_LEFT, fillCharacter);
    }
    public static String padRight(String s, int length, char fillCharacter) {
        return pad(s, length, PAD_RIGHT, fillCharacter);
    }
    public static String pad(String s, int length, int padSide, char fillCharacter) {
        StringBuffer sb = null;

        if (s == null) s = "";

        sb = new StringBuffer(s.trim());

        while (sb.length() < length)
            if (padSide == PAD_LEFT)
                sb.insert(0, fillCharacter);
            else
                sb.append(fillCharacter);

        return sb.toString();
    }

    public static String shorten(String s, int len) {
        if (len < 5) throw new IllegalArgumentException("Can't shorten to less than 5 chars.");
        if (s == null) return null;
        if (s.length() <= len) return s;

        int half = ((len    + 1) - 3) / 2;
        int otherHalf = s.length() - half + ((len + 1) % 2);
        return s.substring(0,half)+"..."+s.substring(otherHalf);
    }

    static public String replaceIndexes(String errorText, String...varTexts) {
        for (int i = varTexts.length - 1; i >= 0 ; i--)
            errorText = errorText.replaceAll("%"+i, varTexts[i] == null ? "null" : varTexts[i]);
        return errorText;
    }

	static public boolean isNumeric(String s) {
		return (s == null) ? false : s.matches("\\-?[0-9]+(\\.[0-9]+)?"); // fails for .001 or 100.
	}
	
	static public String toHTML(String s) {
		String html = StringEscapeUtils.escapeHtml4(s).replaceAll("\n","<br>").replaceAll("\t","&nbsp; &nbsp; ").replaceAll("  ","&nbsp; ").replaceAll("  ","&nbsp; ");
		return html;
	}
	
	static public String unescapeXml(String s) {
		String html = StringEscapeUtils.unescapeXml(s);
		return html;
	}
	
	static public String escapeXml(String s) {
		String html = StringEscapeUtils.escapeXml(s);
		return html;
	}
	
    public static String formatNumber(String value, String pattern) {
        if (value.trim().equals("")) return "";
        return formatNumber(new BigDecimal(value.replaceAll(",","")),pattern);
    }
    
    public static String formatNumber(Number value, String pattern) {
        return formatNumber(new BigDecimal(value.toString()),pattern);
    }
    
    public static String formatNumber(double value, String pattern) {
        return formatNumber(new BigDecimal(value), pattern);
    }
    
    public static String formatNumber(BigDecimal value, String pattern) {
        if (value == null) throw new IllegalArgumentException("Value cannot be null");
        if (pattern == null) throw new IllegalArgumentException("Pattern cannot be null");
        DecimalFormat formatter = new DecimalFormat(pattern);
        return formatter.format(value.doubleValue());
    }

    /**
     * Return a new string that contains only the digits (0-9) in the
     * parameter number.  E.g. numbersOnly("(111)-222-1234" returns
     * "1112221234"
     * @param number
     * @return Return a new string that contains only the digits (0-9) in the
     * parameter number.  Null parameter returns null.
     */
	public static String numbersOnly(String number) {
        String result = null;   
        if(number != null)
        {
    		StringBuffer digits = new StringBuffer();
    		for (int i = 0; i < number.length(); i++) {
    			char c = number.charAt(i);
    			if ((c >= '0') && (c <= '9'))
    				digits.append(c);
            }
            result = digits.toString();
        }
		return result;
	}
    
    public static String formatDate(Date d, String pattern) {
        if (d == null) throw new IllegalArgumentException("Date cannot be null");
        if (pattern == null) throw new IllegalArgumentException("Pattern cannot be null");
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(d);
    }

	public static boolean isEqual(String s1, String s2) {
		if (s1 == null && s2 == null) return true;
		if (s1 == null || s2 == null) return false;
		return s1.equals(s2);
	}
}
