package com.holytrinityorthodox.prayerbookservice.model;

public class GospelItem {
	
	private String number;
	private String text;
	
	public GospelItem() {
		
	};
	
	public GospelItem(String numb, String txt) {
		this.number = numb;
		this.text = txt;
	}
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
