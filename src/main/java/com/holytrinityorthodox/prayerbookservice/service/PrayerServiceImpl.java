package com.holytrinityorthodox.prayerbookservice.service;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.holytrinityorthodox.prayerbookservice.model.CelebrationContailer;
import com.holytrinityorthodox.prayerbookservice.model.DateInfoContainer;
import com.holytrinityorthodox.prayerbookservice.model.GospelContailer;
import com.holytrinityorthodox.prayerbookservice.model.GospelItem;
import com.holytrinityorthodox.prayerbookservice.model.PrayerModel;
import org.springframework.util.StringUtils;

/**
 * The class is responsible for parsing html to json
 * */
@Service
public class PrayerServiceImpl implements PrayerService {

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
		CelebrationContailer celebItem = new CelebrationContailer("","");
		for(Node blockCelebs:celebrItems) {
			if (blockCelebs instanceof Element) {
				Element el = (Element)blockCelebs;
				String text = celebItem.getText();
				switch(el.tagName()) {
				    case "img": 
						break;
				    case "a": 
				    	text = text + " " + el.text();
				    	celebItem.setText(text.trim());
				    	String relHref = el.attr("href").toString(); 
				    	celebItem.setLink(relHref);
						break;
					case "span":
						text = text + " " + el.text();
						celebItem.setText(text.trim());
						break;
					case "br": 
						celebrlList.add(celebItem);
						celebItem = new CelebrationContailer("","");
						break;
					default: 
					    break;
				}
			}
			else {
				String text = celebItem.getText() + " " + blockCelebs.toString();
				celebItem.setText(text.trim());
			}
		}	
		model.setCelebration(celebrlList);
		return model;
	}
	
	/**
	 * Check is it int or not
	 * */
	private boolean checkInt(String number) {
		if (StringUtils.isEmpty(number)) {
			return false;
		}
		int sz = number.length();
		for (int i = 0; i < sz; i++) {
			if (!Character.isDigit(number.charAt(i))) {
				return false;
			}
		}
		return true;
	}	
}
