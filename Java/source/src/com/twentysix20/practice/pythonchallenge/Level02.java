package com.twentysix20.practice.pythonchallenge;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Level02 {

    public static void main(String[] args) throws Exception {
        InputStreamReader isr = new InputStreamReader( System.in );
        BufferedReader stdin = new BufferedReader( isr );
        System.out.println("Enter:");
        String input;
        StringBuffer buff = new StringBuffer();
        while(!"".equals(input = stdin.readLine())) {
            buff.append(input);
        }
        String s = buff.toString();
        for (int i = 0; i < s.length(); i++)
        	if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z')
        		System.out.print(s.charAt(i));
    }
}
