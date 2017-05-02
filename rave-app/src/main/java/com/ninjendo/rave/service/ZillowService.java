package com.ninjendo.rave.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ninjendo.rave.cache.PropertyCacheClient;
import com.ninjendo.rave.common.CommonConstants;
import com.ninjendo.rave.config.ZillowConfig;
import com.ninjendo.rave.exception.RequestLimitReachedException;
import com.ninjendo.rave.gen.zillow.Searchresults;
import com.ninjendo.rave.gen.zillow.SimpleProperty;
import com.ninjendo.rave.model.PropertyLead;
import com.ninjendo.rave.model.ZillowSearchRequest;
import com.ninjendo.rave.util.DateParser;
import com.ninjendo.rave.util.GAMillageRate;
import com.ninjendo.rave.util.KeyGenerator;
import com.ninjendo.rave.util.NumberParser;
import com.socrata.api.Soda2Consumer;
import com.socrata.exceptions.LongRunningQueryException;
import com.socrata.exceptions.SodaError;
import com.sun.jersey.api.client.ClientResponse;

@Service
public class ZillowService {
    private static final String SEARCH_RESULTS = "SearchResults";
    private static final String COMPS = "Comps";
    private static final String ZESTIMATE = "Zestimate";
    private static final String ZWSID_QUERY = "zws-id=";
    private static final String PROPERTYID_QUERY = "zpid=";
    private static final String ADDRESS_QUERY = "address=";
    private static final String COUNT_QUERY = "count=";
    private static final String CITYSTATEZIP_QUERY = "citystatezip=";
    private static final String RENTESTIMATE_QUERY = "rentzestimate=";
    
    private static final String WORD_DELIMETER = "\\W";    // regex delimeter - matches any non-word char
    private static final int MAX_DAILY_REQUEST_LIMIT = 1000;
    private static final int ALTERNATE_MAX_DAILY_REQUEST_LIMIT = MAX_DAILY_REQUEST_LIMIT * 1;
    private final ZillowConfig config;
    private final static String DATE_FORMAT = "yyyymmdd";
    private static boolean stopRequest= false;
    private static Map<String, Integer> dailyRequestCounter = new HashMap<String, Integer>();
    private final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
    
    final static Logger logger = LoggerFactory.getLogger(ZillowService.class);
    
    @Autowired
    private PropertyCacheClient propertyCache;
    
    @Autowired
    public ZillowService(ZillowConfig config) {
        this.config = config;
    }

    public int incrementRequestCounter()
    {
    	String today = sdf.format(new Date());
    	int newValue = getRequestCount() + 1;
    	dailyRequestCounter.put(today, newValue);
    	
    	return newValue;
    }
    
    public int getRequestCount()
    {
    	String today = sdf.format(new Date());
    	if (!dailyRequestCounter.containsKey(today))
    	{
    		stopRequest = false;
    		dailyRequestCounter.put(today, 0);
    	}
    	return dailyRequestCounter.get(today);
    }
    
    public boolean useAlternateZillowId() throws RequestLimitReachedException
    {
    	if (stopRequest || getRequestCount() >= ALTERNATE_MAX_DAILY_REQUEST_LIMIT){
    		stopRequest = true;
    		throw new RequestLimitReachedException();
    	}
    	return MAX_DAILY_REQUEST_LIMIT <= getRequestCount();
    }
    
    public SimpleProperty getPropertyDetails(PropertyLead lead) throws RequestLimitReachedException
    {
    	SimpleProperty zillowProperty = null;
        Soda2Consumer consumer = Soda2Consumer.newConsumer(config.getDeepSearchResultsUrl());

        ZillowSearchRequest rq = new ZillowSearchRequest();
        rq.setStreetAddress(lead.getPropertyAddress().getStreetAddress().toStringUrl());
        rq.setCityStateZip(lead.getPropertyAddress().getZipCode());
        
        String requestURL = buildRequestUrl(config.getDeepSearchResultsUrl(), rq);
        
        Searchresults searchResp = null;

        URI uri = null;
        ClientResponse response = null;

        try {
            uri = new URI(requestURL);
        } catch (URISyntaxException e) {
            logger.error(e.getMessage());
        }
        try {
        	logger.info("Search Result URL : {}", requestURL);
            response = consumer.getHttpLowLevel().queryRaw(uri, MediaType.APPLICATION_XML_TYPE);
            incrementRequestCounter();
            
            searchResp = response.getEntity(Searchresults.SINGLE_TYPE);
            
            if (searchResp  != null && searchResp.getResponse() !=null
            		&& searchResp.getResponse().getResults() != null
            		&& searchResp.getResponse().getResults().getResult() != null){
            	
            	if (searchResp.getResponse().getResults().getResult().size() > 1){
            		logger.error("There are more than 1 result for " + lead.getPropertyAddress().toString());
            	}
            	if (searchResp.getResponse().getResults().getResult().size() == 0){
            		logger.info("No Zillow result found for " + lead.getPropertyAddress().toString());
            	}
            	zillowProperty = searchResp.getResponse().getResults().getResult().get(0);
            }
            
        } catch (LongRunningQueryException e) {
            logger.error(e.getMessage());
        } catch (SodaError sodaError) {
            logger.error(sodaError.getMessage());
        } finally {
            if (response != null) {
                response.close();
            }
        }
        
        return zillowProperty;
    }
    
    private String getZillowId() throws RequestLimitReachedException
    {
    	if (useAlternateZillowId()){
    		return config.getWebserviceIdAlternate();
    	}
    	
    	return config.getWebserviceId();
    }
    
    private String buildRequestUrl(String hostUrl, ZillowSearchRequest rq) throws RequestLimitReachedException
    {
    	String fullUrl = hostUrl;
    	if (hostUrl.contains(SEARCH_RESULTS))
    	{
    		//http://www.zillow.com/webservice/GetDeepSearchResults.htm?zws-id=<ZWSID>&address=2114+Bigelow+Ave&citystatezip=Seattle%2C+WA
    		fullUrl = hostUrl + CommonConstants.QUESTION_MARK
    			+ ZWSID_QUERY + getZillowId() 
    			+ CommonConstants.AMPERSAND + ADDRESS_QUERY + rq.getStreetAddress() 
    			+ CommonConstants.AMPERSAND + CITYSTATEZIP_QUERY + rq.getCityStateZip()
    			+ CommonConstants.AMPERSAND + RENTESTIMATE_QUERY + CommonConstants.TRUE;
    	}
    	else if (hostUrl.contains(ZESTIMATE))
    	{
    		//http://www.zillow.com/webservice/GetZestimate.htm?zws-id=<ZWSID>&zpid=48749425
    		fullUrl = hostUrl + CommonConstants.QUESTION_MARK
    			+ ZWSID_QUERY + getZillowId() 
    			+ CommonConstants.AMPERSAND + PROPERTYID_QUERY + rq.getPropertyId()
    			+ CommonConstants.AMPERSAND + RENTESTIMATE_QUERY + CommonConstants.TRUE;
    	}
    	else if (hostUrl.contains(COMPS))
    	{
    		//http://www.zillow.com/webservice/GetDeepComps.htm?zws-id=<ZWSID<&zpid=48749425&count=5
    		fullUrl = hostUrl + CommonConstants.QUESTION_MARK
    			+ ZWSID_QUERY + getZillowId()  
    			+ CommonConstants.AMPERSAND + PROPERTYID_QUERY + rq.getPropertyId()
    			+ CommonConstants.AMPERSAND + COUNT_QUERY + rq.getCompsCount();
    	}
    	return fullUrl;
    }
    
    public void mergeData(PropertyLead lead) throws RequestLimitReachedException
    {
    	SimpleProperty zProperty = getPropertyDetails(lead); 
    	
    	if (zProperty != null){
    		lead.setZillowId(zProperty.getZpid());
    		lead.setZillowProperty(zProperty);
    		
    		lead.setLastPurchaseDate(DateParser.parse(zProperty.getLastSoldDate()));
    		if (zProperty.getLastSoldPrice() != null && zProperty.getLastSoldPrice().getValue() !=null) {
				lead.setLastPurchasePrice(NumberParser.parseBigDecimal(zProperty.getLastSoldPrice().getValue().intValue()));
			}
			if (zProperty.getTaxAssessment() != null){
    			lead.setAssessedValue(NumberParser.parseBigDecimal(zProperty.getTaxAssessment()));
    		}
    		
    		if (lead.getAssessedValue() != null){
    			lead.setAnnualTax(GAMillageRate.getAnnualTaxAmount(lead.getPropertyAddress().getCity(), lead.getAssessedValue()));
    		}
    		
    		if (zProperty.getTaxAssessmentYear() != null){
    			lead.setAssessedValueYear(NumberParser.parseInteger(zProperty.getTaxAssessmentYear()));	
    		}
    		
    		lead.setUse(zProperty.getUseCode());
    		if (zProperty.getRentzestimate() != null && zProperty.getRentzestimate().getAmount() != null && zProperty.getRentzestimate().getAmount().getValue() != null){
    			lead.setRentEstimate(NumberParser.parseBigDecimal(zProperty.getRentzestimate().getAmount().getValue().intValue()));
    		}
    		if (zProperty.getZestimate() != null && zProperty.getZestimate().getAmount() != null && zProperty.getZestimate().getAmount().getValue() != null){
    			lead.setZillowMarketValue(NumberParser.parseBigDecimal(zProperty.getZestimate().getAmount().getValue().intValue()));
    		}
    		if(zProperty.getAddress() != null){
    			lead.setLongitude(zProperty.getAddress().getLongitude());
    			lead.setLatitude(zProperty.getAddress().getLatitude());
    		}
    		logger.debug("Zestimate: " + lead.getZillowMarketValue() + " rent=" + lead.getRentEstimate());
    	}
    }

    
    
	@Scheduled(cron="0 15 11,6 * * MON-FRI")
    public void downloadHudLeads() throws IOException 
	{
    	logger.info("Start Zillow property record retrieval.");
    	
    	Set<PropertyLead> noZProps = propertyCache.getNoZillowSet();
    	
		if (noZProps != null && noZProps.size() > 0)
		{
			for (Iterator<PropertyLead> i = noZProps.iterator(); i.hasNext();) 
			{
				PropertyLead noZ = i.next();
				String oldPropertyId = noZ.getId();
			
				try
				{
					if (noZ.getPropertyAddress() != null) 
					{
						logger.info("Address for zillow: " + noZ.getPropertyAddress());

						mergeData(noZ);
						noZ.setId(KeyGenerator.getPropertyId(noZ));
						
						propertyCache.replace(oldPropertyId, noZ);
					}
				} catch (RequestLimitReachedException re) {
					logger.error(
							"Daily request limit in webservice Zillow has been reached! No more zillow request will be sent"
									+ " until tomorrow.");
					break;
				} catch (Exception e) {
					logger.error("Unable to parse leads: " + e.getMessage());
					e.printStackTrace();
				}

				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
    }
}
