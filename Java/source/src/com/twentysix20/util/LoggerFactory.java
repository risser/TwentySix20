package com.twentysix20.util;

import java.util.logging.Logger;

/**
 * Convienence class for instantiating loggers.
 * 
 * @author TTXC007
 *
 */
public class LoggerFactory 
{
	public static Logger make()
	{
		Throwable t = new Throwable();
		StackTraceElement directCaller = t.getStackTrace()[1];
		
		return Logger.getLogger(directCaller.getClassName());
	}
}
