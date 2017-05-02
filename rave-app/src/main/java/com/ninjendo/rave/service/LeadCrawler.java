package com.ninjendo.rave.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ninjendo.rave.model.PropertyLead;

public class LeadCrawler {

	
	public static String GOOGLE_SEARCH_URL = "https://www.google.com/#q=";
	public static String NUMBER_OF_QUERY_RESULTS="&num=5";

	private static Pattern patternDomainName;
	private Matcher matcher;
	private static final String DOMAIN_NAME_PATTERN = "([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}";
	private static final String ZILLOW_DOMAIN_NAME_PATTERN = "([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}zpid";
	private static final String ZILLOW_URL_PATTERN = "http[s]{0,1}://www\\.zillow.+_zpid";
	static {
		patternDomainName = Pattern.compile(ZILLOW_URL_PATTERN);
	}

    
	public Set<String> searchPropertyFromGoogle(String propertyUrlStr){
		String searchUrl = GOOGLE_SEARCH_URL + propertyUrlStr + NUMBER_OF_QUERY_RESULTS;
        Document doc;
        Set<String> result = new HashSet<String>();	
		try {

			// need http protocol, set this as a Google bot agent :)
			doc = Jsoup.connect(searchUrl).userAgent(
				  "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)")
				.timeout(5000).get();

			// get all links
			Elements links = doc.select("a[href]");
			for (Element link : links) {

				String temp = link.attr("href");		
				if(temp.startsWith("/url?q=")){
                    //use regex to get domain name
					result.add(getURL(temp));
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
			
		return result;
	}
	
	  public String getURL(String url){
			
			String domainName = "";
			
			matcher = patternDomainName.matcher(url);
			if (matcher.find()) {
				domainName = matcher.group(0).toLowerCase().trim();
			}
			System.out.println(domainName);
			return domainName;
				
		  }
	  private Set<String> getDataFromGoogle(String query) {
			
			Set<String> result = new HashSet<String>();	
			String request = "https://www.google.com/search?q=" + query + "&num=20";
			System.out.println("Sending request..." + request);
				
			try {

				// need http protocol, set this as a Google bot agent :)
				Document doc = Jsoup
					.connect(request)
					.userAgent(
					  "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)")
					.timeout(5000).get();

				// get all links
				Elements links = doc.select("a[href]");
				for (Element link : links) {

					String temp = link.attr("href");		
					if(temp.startsWith("/url?q=")){
		                                //use regex to get domain name
						result.add(getURL(temp));
					}

				}

			} catch (IOException e) {
				e.printStackTrace();
			}
				
			return result;
		  }

	  public String getUrlFromGoogle(PropertyLead lead) {
		  String urlStr = "";
			String query = lead.getPropertyAddress().toString();
			String request = "https://www.google.com/search?q=" + query + "&num=30";
			System.out.println("Sending request..." + request);
				
			try {

				// need http protocol, set this as a Google bot agent :)
				Document doc = Jsoup
					.connect(request)
					.userAgent(
					  "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)")
					.timeout(5000).get();

				// get all links
				Elements links = doc.select("a[href]");
				for (Element link : links) {

					String temp = link.attr("href");		
					if(temp.startsWith("/url?q=")){
						urlStr = urlStr.toUpperCase();
						String result = getURL(temp).toUpperCase();
						if (result.indexOf(lead.getPropertyAddress().getStreetAddress().getStreetNumber().toUpperCase()) > -1
								&& result.indexOf(lead.getPropertyAddress().getStreetAddress().getStreetName().toUpperCase()) > -1){
							return urlStr;
						}
					}

				}

			} catch (IOException e) {
				e.printStackTrace();
			}
				
			return null;
		  }

	  
//	  public static void main(String[] args) {
//
//		  LeadCrawler obj = new LeadCrawler();
//			Set<String> result = obj.getDataFromGoogle("115-leeward-ln-30076-zillow");
//			for(String temp : result){
//				System.out.println(temp);
//			}
//			System.out.println(result.size());
//		  }
}
