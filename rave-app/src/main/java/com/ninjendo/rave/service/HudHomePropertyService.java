package com.ninjendo.rave.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ninjendo.rave.cache.PropertyCacheClient;
import com.ninjendo.rave.config.HudConfig;
import com.ninjendo.rave.exception.MissingDataException;
import com.ninjendo.rave.model.HudBuyerType;
import com.ninjendo.rave.model.LeadType;
import com.ninjendo.rave.model.PropertyLead;
import com.ninjendo.rave.model.State;
import com.ninjendo.rave.parser.CsvParser;

@Service
public class HudHomePropertyService {
	

	final static Logger logger = LoggerFactory.getLogger(HudHomePropertyService.class);
	
	@Autowired
	HudConfig config;
    
    @Autowired
    private PropertyCacheClient cache;
	
    @Autowired
	private LeadCrawlerService crawlerService;
    
    @Autowired
    private GoogleAppService googleService;    
    
	@Autowired
	public HudHomePropertyService(HudConfig config) {
		this.config = config;
	}
	
	public List<PropertyLead> getProperties()
	{
		return cache.getProperties();
	}
	
	public Set<PropertyLead> getNoZillowRecords()
	{
		return cache.getNoZillowSet();
	}
	
    private Map<String, PropertyLead> parseHudNetBidResult(InputStream hudUploadedInputStream)
    {
    	Map<String, PropertyLead> hudLeadMap = new HashMap<String, PropertyLead>();
    	//String hudFileOut = uploadDirectory + this.hudNetBidFile;
    	try {
			logger.info("Parsing HUD Net Bid Result... ");
			CsvParser hudNetBidReader = new CsvParser(hudUploadedInputStream);
			
			List<PropertyLead> hudNetLeads = hudNetBidReader.parse(LeadType.HUD);
			
			for (PropertyLead propertyLead : hudNetLeads) 
			{
				if (!hudLeadMap.containsKey(propertyLead.getFhaCaseNumber())){
					hudLeadMap.put(propertyLead.getFhaCaseNumber(), propertyLead);
				}
			}
		} catch (MissingDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hudLeadMap;
    }
    
    @Scheduled(cron="0 0 10 ? * MON-FRI")
	public void downloadGaHudProperties()
	{
		downloadHudProperties(this.config.getDefaultSearchUrl());
	}
	
	public void downloadHudProperties(State stateCode, HudBuyerType buyerType) throws MissingDataException
	{
		//"http://www.hudhomestore.com/pages/ListExportToExcel.aspx?buyerType=Investor&sState=GA"
		
		StringBuilder url = new StringBuilder(this.config.getSearchUrl());
		if (stateCode == null){
			throw new MissingDataException("State Code is required.");
		}
		
		url.append(stateCode.name());
		
		if (buyerType != null){
			try {
				url.append("&buyerType=").append(URLEncoder.encode(buyerType.getDescription(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		downloadHudProperties(url.toString());
	}
	
	public void downloadHudProperties(String searchUrl) 
	{
		logger.info("Start Downloadfrom HUD Home Store.");
		
		try {
			byte[] hudSearchbytes = crawlerService.downloadResults(searchUrl);
			
			InputStream hudSearchInputStream = new ByteArrayInputStream(hudSearchbytes);
			CsvParser hudSearchParser = new CsvParser(hudSearchInputStream);
			List<PropertyLead> leads = hudSearchParser.parse(LeadType.HUD);

			if (leads != null && leads.size() > 0)
			{
				logger.info("No. of parsed HUD Search Results: " + leads.size());
			    if (leads.size() > 0) 
			    {
			    	cache.save(leads);
			    	logger.info("HUD homes saved in cache. " + leads.size());
				}
			}
			else{
				logger.warn("No HUD homes found.");
			}
		} catch (MissingDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void processAcceptedBids()
	{
		String fileId = "18eBI9jhzFA2rKJ4nNhoD_K_nwhnzGliQA4__xj2dwYk";
		try
		{
			ByteArrayOutputStream out = (ByteArrayOutputStream) googleService.downloadBidAcceptanceSheet(fileId);
			Map<String, PropertyLead> hudNetBidResults = parseHudNetBidResult(new ByteArrayInputStream(out.toByteArray()));
			
			for (Map.Entry<String,PropertyLead> hudNetBid : hudNetBidResults.entrySet()) {
				
				PropertyLead lead = cache.getByHudCaseNumber(hudNetBid.getKey());
				
				if (lead != null) {
					PropertyLead hudNetProperty = hudNetBid.getValue();
					
					if (hudNetProperty.getCounteredNetToHud() != null){
						lead.setCounteredNetToHud(hudNetProperty.getCounteredNetToHud());
						lead.setBidSubmitDate(hudNetProperty.getBidSubmitDate());
						lead.setBidAcceptanceDate(hudNetProperty.getBidAcceptanceDate());
						logger.info("Net to HUD: " + lead.getFhaCaseNumber() + " => " + lead.getCounteredNetToHud());
						
						cache.save(lead);
					}
					else
					{
						logger.error("Net to HUD is missing!!");
						break;
					}
				}				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
