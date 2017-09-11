package com.holytrinityorthodox.util;

import org.apache.logging.log4j.Level;

/**Proxy logging service to get the information from an exception*/
public interface ILoggerProxy {
	
	/**
	Write down an exception to the log file*/
	public void log(Level level, Exception ex);
	/**
	Write down an exception to the log file*/
	public void log(Level level, String msg);
	
}
