package com.twentysix20.misc;

public class binCoder
{
	public static String encode(String msg)
	{
		java.util.Random steve = new java.util.Random();
		boolean caps = steve.nextBoolean();
		char c = (char)(65 + steve.nextInt(26) + (caps ? 0 : 32));
		String s = "" + c;

		for (int sct=0; sct < msg.length(); sct++)
		{
			byte b = (byte)msg.charAt(sct);
			int flag = 128;
			for (int bct = 7; bct >= 0; bct--)
			{
				caps ^= ((b & flag) > 0);
				s += (char)(65 + steve.nextInt(26) + (caps ? 0 : 32));
				flag >>>= 1;
			}
		}
		return s;
	}

	public static String decode(String msg)
	{
		String s = "";

		int first;
		char c = ' ';
		for (first=0;first < msg.length() && ((c < 'A') || (c > 'z') || ((c > 'Z') && (c < 'a'))); first++)
			c = msg.charAt(first);

		boolean caps = Character.isUpperCase(msg.charAt(first));
		byte b = 0;
		int bct = 0;

		for (int sct=first; sct < msg.length(); sct++)
		{
			c = msg.charAt(sct);

			if ((c < 'A') || (c > 'z') || ((c > 'Z') && (c < 'a'))) continue;

			b += (caps == Character.isUpperCase(c) ? 0 : 1);
			caps = Character.isUpperCase(c);

			if (++bct < 8)
				b <<= 1;
			else
			{
				s = s + (char)b;
				bct = 0;
				b = 0;
			}
		}
		return s;
	}

	public static void main(String[] Args)
	{
		String t = encode(Args[0]);
		System.out.println(t);
		System.out.println(t.length());
		System.out.println(decode(t));
	}
}