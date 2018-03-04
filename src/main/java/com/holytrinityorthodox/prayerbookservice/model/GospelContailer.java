package com.holytrinityorthodox.prayerbookservice.model;

import java.util.List;

public class GospelContailer {
	
	private String header;
	private List<GospelItem> list;
	
	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public List<GospelItem> getList() {
		return list;
	}

	public void setList(List<GospelItem> list) {
		this.list = list;
	}
}
