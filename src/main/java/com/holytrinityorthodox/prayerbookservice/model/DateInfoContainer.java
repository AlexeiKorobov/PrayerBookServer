package com.holytrinityorthodox.prayerbookservice.model;

public class DateInfoContainer {
	
	private String header;
	private String details;
	
	public DateInfoContainer() {
		
	}
	public DateInfoContainer(String header, String details) {
		this.header = header;
		this.details = details;
	}
	
	
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
}
