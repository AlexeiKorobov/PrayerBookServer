package com.holytrinityorthodox.service;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.holytrinityorthodox.model.CelebrationContailer;
import com.holytrinityorthodox.model.DateInfoContainer;
import com.holytrinityorthodox.model.GospelContailer;
import com.holytrinityorthodox.model.GospelItem;
import com.holytrinityorthodox.model.PrayerModel;

/**
 * The class is responsible for parsing html to json
 * */
@Service
public class PrayerService implements IPrayerService {
	
	public PrayerModel getJson(String day, String month, String year, String defaultLent, String inAddress) throws Exception {
		PrayerModel model = new PrayerModel();
		String address = String.format(inAddress, month, day, year);
		Document doc = Jsoup.connect(address).get();
		
		//1 MAIN HEADER
		Elements mainHeader = doc.select(".dataheader");
		model.setHeader(mainHeader.html());
		
		//2 DATE-INFO
		Elements dateinfoHeaderElement = doc.select(".headerheader");
		//*******WRONG INNER HTML!!! header was not declared in a tag!!!*****
		String dataInfoHeader = dateinfoHeaderElement.get(0).childNode(0).toString();
		Elements dateInfoDetails = doc.select(".headerfast");
		//check for a lent
		String lent = defaultLent;
		if(dateInfoDetails.text().isEmpty() == false) {
			lent = dateInfoDetails.text();
		}
		
		DateInfoContainer dateinfo = new DateInfoContainer(dataInfoHeader, lent);
		model.setDate_info(dateinfo);
		
		//3 GOSPEL
		List<GospelContailer> gospelList = new ArrayList<GospelContailer>();
		//select all texts from current day
		Elements tables = doc.select("table");
		for(Element blockGospel:tables) {
			GospelContailer gospel = new GospelContailer();
			//header (located before current text)
			Element headerGospel =  blockGospel.previousElementSibling();
			gospel.setHeader(headerGospel.text());
			//text of Gospel
			List<GospelItem> listItems = new ArrayList<GospelItem>();
			Elements textGospel = blockGospel.select("tr");
			for (Element el: textGospel) {
				String number = el.select(".versenumbercolor sup").html();
				if(!checkInt(number)) {
					number = "0";
				}
				String text = el.select("td:nth-child(2) span").text();
				listItems.add(new GospelItem(number, text));
			}
			gospel.setList(listItems);
			gospelList.add(gospel);
		}
		model.setGospel(gospelList);
		
		//4 CELEBRATION
		List<CelebrationContailer> celebrlList = new ArrayList<CelebrationContailer>();
		//select main span with all text which is "first span with class normaltext" 
		List<Node> celebrItems = doc.select("span.normaltext").first().childNodes();
		List<String> celebrStrings = new ArrayList<String>();
		String buildString = "";
		for(Node blockCelebs:celebrItems) {
			String tag = "string";
			String str = blockCelebs.toString();
			if (blockCelebs instanceof Element) {
				tag = ((Element) blockCelebs).tag().toString();	
			}
			System.out.println(tag + ": " +str);
		}	
		return model;
	}
	
	/**
	 * Temporary solution for checking is it int or not
	 * TODO: make it more appropriate
	 * */
	private boolean checkInt(String number) {
		try{
		    @SuppressWarnings("unused")
			Integer check = Integer.parseInt(number);
		    return true;
		}catch (NumberFormatException ex) {
		    return false;
		}
	}	
}
