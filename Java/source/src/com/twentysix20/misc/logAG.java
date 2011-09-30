package com.twentysix20.misc;

import java.io.BufferedReader;
import java.io.FileReader;


public class logAG {

//	private static String GETSTARTSTRING = "fileName: ";
	private static String GETENDSTRING = "Comms: Message: <CLIENTMSG>We have sent all of the file and flushed our buffers, now shutting down one side of socket ";
//	private static String GETERRSTRING = "Comms: Message: <CLIENTMSG>Telling server connection closed with error ";

	public static void main(String[] args)
	{
		if(args.length ==0){
			System.out.println("First parameter must be the location & name of the log file.");
			System.exit(0);
		}

		try
		{
			String line = "";
			BufferedReader rd = new BufferedReader(new FileReader(args[0]));
			while ((line = rd.readLine()) != null)
			{
/*				if(line.startsWith(GETSTARTSTRING))
				{
					System.out.println("Started: " + line.substring(line.indexOf("filename:") + 11,line.lastIndexOf(" id: ")));
				}
				else if(line.startsWith(GETERRSTRING))
				{
					if (line.charAt(71) != '0')
						System.out.println("Errored: " + line.substring(line.indexOf("fileName:") + 10,line.lastIndexOf(" id: ")));
				}
				else */if(line.startsWith(GETENDSTRING))
				{
					System.out.println("**Success: " + line.substring(line.indexOf("fileName:") + 10,line.lastIndexOf(" id: ")));
				}
			}
			rd.close();
		}catch(Throwable th){
			System.out.println("whoops. " + th.toString());
		}
/*
	if(args.length !=0){
			configPath = args[0];
		}
		AutoWebAcct aw = new AutoWebAcct();
		try{
			aw.config();
			aw.logsToProcess();
			aw.end();
		}
		catch(Exception objExp){aw.errorMail("","","","",objExp.toString());}
*/
	}

}
