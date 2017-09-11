package com.holytrinityorthodox.controller;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.holytrinityorthodox.util.ILoggerProxy;
 
@Controller
public class IndexController {

	@Inject
	@Singleton
	ILoggerProxy logger;
	
	@RequestMapping(value = {"/", "/index**"})
	public String index() {	
		try{
			return "index";
		}
		catch(Exception ex){
			logger.log(Level.ERROR, ex);
			throw ex;
		}
	}
}