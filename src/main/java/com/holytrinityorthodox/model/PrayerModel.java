package com.holytrinityorthodox.model;

import java.util.List;

public class PrayerModel {
	
	 private String header;
	 private DateInfoContainer date_info;
	 private List<GospelContailer> gospel;
	 
	 
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	
	public DateInfoContainer getDate_info() {
		return date_info;
	}
	public void setDate_info(DateInfoContainer date_info) {
		this.date_info = date_info;
	}
	
	public List<GospelContailer> getGospel() {
		return gospel;
	}
	public void setGospel(List<GospelContailer> gospel) {
		this.gospel = gospel;
	}
}

