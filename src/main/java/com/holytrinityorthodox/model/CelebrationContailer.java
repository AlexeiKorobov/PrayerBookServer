package com.holytrinityorthodox.model;

public class CelebrationContailer {
	private String text;
	private String link;
	
	public CelebrationContailer() {
		
	}
	
	public CelebrationContailer(String itext, String ilink) {
		this.text = itext;
		this.link = ilink;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
}
