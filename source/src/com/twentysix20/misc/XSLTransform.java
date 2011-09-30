package com.twentysix20.misc;

import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;

public class XSLTransform
{

	static public void main(String[] args)
	{
		if (args.length != 3)
		{
			System.out.println("Need three args: StyleSheetFile(XSL) InputFile(XML) OutputFile(?)");
			System.exit(1);
		}
		try{
			File styleFile = new File(args[0]);
			File inFile = new File(args[1]);
			File outFile = new File(args[2]);

			DocumentBuilderFactory dfact = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbuild = dfact.newDocumentBuilder();
			Document document = dbuild.parse(inFile);

			StreamSource stylesource = new StreamSource(styleFile);

			TransformerFactory tfact = TransformerFactory.newInstance();
			Transformer trans = tfact.newTransformer(stylesource);

			StreamResult result = new StreamResult(outFile);
			DOMSource domsrc = new DOMSource(document);
			trans.transform(domsrc,result);
		} catch (Exception everything){
			everything.printStackTrace();
		}
	}
}