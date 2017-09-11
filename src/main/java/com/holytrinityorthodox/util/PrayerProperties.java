package com.holytrinityorthodox.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.stereotype.Service;

import com.holytrinityorthodox.controller.PrayerRestController;

@Service
public class PrayerProperties implements IPrayerProperties{

	private final static Properties prop = new Properties();
	
	private PrayerProperties() throws IOException{	
		InputStream  input =  PrayerRestController.class.getClassLoader().getResourceAsStream("prayer.properties");
		prop.load(input);
	}
	
	@Override
	public String getDefaulLent() {
		return prop.getProperty("lent");
	}

	@Override
	public String getAddress() {
		return prop.getProperty("address");
	}
}
