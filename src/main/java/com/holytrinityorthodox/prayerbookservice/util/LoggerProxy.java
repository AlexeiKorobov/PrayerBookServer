package com.holytrinityorthodox.prayerbookservice.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;


@Service
public final class LoggerProxy implements ILoggerProxy{

	private final static Logger logger = LogManager.getLogger(LoggerProxy.class.getName());
	
	@Override
	public void log(Level level, Exception ex) {
		try{
			StackTraceElement stm = ex.getStackTrace()[0];
			if(stm != null){
				String err = "File: '" + stm.getFileName() +"' Class: '" + stm.getClassName() 
						  	 + "' Method: '" + stm.getMethodName() + "' Line: '" + stm.getLineNumber() 
						   	 + "' Exception: '" + ex + "'";
				logger.log(level, err);
				logger.catching(ex);
			}
			else{
				logger.catching(ex);
			}
		}
		catch(Exception _ex){
			logger.catching(_ex);
		}
	}

	@Override
	public void log(Level level, String msg) {
		try{
			logger.log(level, msg);
		}
		catch(Exception _ex){
			logger.catching(_ex);
		}
	}
}
