package com.twentysix20.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class FileUtils {
    static Charset determineCharSet(String filename) throws IOException {
        return determineCharSet(filename, 10); // check only the first 10 lines
    }
    static Charset determineCharSet(String filename, int maxLines) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        
        int count = 0;
        String line;
        String charsetName = "";
        while ((++count <= maxLines) && "".equals(charsetName) && ((line = reader.readLine()) != null)) {
            int p = line.toLowerCase().indexOf("charset");
            if (p > -1) {
                int pEq = line.indexOf('=',p)+1;
                if (pEq == -1)
                    pEq = p + 7;
                int pSemi = line.indexOf(';',p);
                if (pSemi == -1)
                    pSemi = line.length();
                int pQu = line.indexOf('\"',p);
                if (pQu == -1)
                    pQu = line.length();
                int pLast = (pQu > pSemi ? pSemi : pQu); // take the smallest
                charsetName = line.substring(pEq,pLast).trim();
            }
        }
        if ("".equals(charsetName))
            charsetName = new InputStreamReader(new FileInputStream(filename)).getEncoding(); // return default
        return Charset.forName(charsetName);
    }
}
