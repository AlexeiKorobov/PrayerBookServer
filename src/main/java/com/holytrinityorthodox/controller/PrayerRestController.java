package com.holytrinityorthodox.controller;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.logging.log4j.Level;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.holytrinityorthodox.model.PrayerModel;
import com.holytrinityorthodox.service.IPrayerService;
import com.holytrinityorthodox.util.ILoggerProxy;
import com.holytrinityorthodox.util.IPrayerProperties;

@RestController
public class PrayerRestController {
	
	@Inject
	@Singleton
	ILoggerProxy logger;
	
	@Inject
	IPrayerService service;
	
	@Inject
	@Singleton
	IPrayerProperties prop;
	
	@RequestMapping("/pbs")
	public PrayerModel greeting(@RequestParam(value="d", required=true) String day,
			                    @RequestParam(value="m", required=true) String month,
			                    @RequestParam(value="y", required=true) String year) {
		try {
			return service.getJson(day, month, year, prop.getDefaulLent(), prop.getAddress());
		} catch (Exception ex) {
			logger.log(Level.ERROR, ex);
			return null;
		}
	}
}
