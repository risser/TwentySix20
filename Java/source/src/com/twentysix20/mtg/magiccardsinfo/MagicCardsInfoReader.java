package com.twentysix20.mtg.magiccardsinfo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import com.twentysix20.mtg.data.card.Card;
import com.twentysix20.mtg.spoiler.output.AbstractCardWriter;
import com.twentysix20.mtg.spoiler.output.OracleWriter;
import com.twentysix20.mtg.spoiler.output.OriginalCardWriter;
import com.twentysix20.util.html.FancyInternetHtmlLoader;

public class MagicCardsInfoReader {
    private static final String RESULT_FILE_ORACLE = "-oracle.txt";
    private static final String RESULT_FILE_ORIGINAL = "-original.txt";
    // path must end in slash
    private static final String PATH = "C:/temp/";
	public static void main(String[] args) throws Exception {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader stdin = new BufferedReader(isr);

		System.out.println("Enter 3 character set abbreviation:");
		String abbrev = stdin.readLine();
		List<Card> cardList = new MagicCardsInfoParser(new FancyInternetHtmlLoader()).parse(abbrev);

		AbstractCardWriter oracleWriter = new OracleWriter(cardList);
        AbstractCardWriter originalWriter = new OriginalCardWriter(cardList);
        String filepath = PATH+abbrev+"/";
		oracleWriter.generateFile(filepath + abbrev + RESULT_FILE_ORACLE);
        originalWriter.generateFile(filepath + abbrev + RESULT_FILE_ORIGINAL);
	}
}