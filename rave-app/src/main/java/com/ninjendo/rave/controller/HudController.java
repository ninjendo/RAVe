package com.ninjendo.rave.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ninjendo.rave.exception.MissingDataException;
import com.ninjendo.rave.model.LeadType;
import com.ninjendo.rave.model.PropertyLead;
import com.ninjendo.rave.model.State;
import com.ninjendo.rave.parser.CsvParser;
import com.ninjendo.rave.service.HudHomePropertyService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@RequestMapping("/hud")
public class HudController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HudController.class);

    @Autowired
    private HudHomePropertyService hudService;
    
    private String uploadDirectory;
    private String hudNetBidFile;
    
    @RequestMapping(method=RequestMethod.POST, value="/leads/download")
    @ApiOperation(value ="Download list of HUD homes" , notes = "Download list of HUD homes from hudhomestore.com and store them in cache.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful download of HUD homes."),
            @ApiResponse(code = 403, message = "Missing or invalid parameter"),
            @ApiResponse(code = 500, message = "Internal server error")
        })
    public void downloadHud(
    		@ApiParam(name = "state", value = "State Code", required = true) @RequestParam("state") State state) throws MissingDataException
    {
    	hudService.downloadHudProperties(state, null);
    }
    
    @RequestMapping(method=RequestMethod.POST, value="/bids/process")
    @ApiOperation(value = "Save Bid Acceptance details in cache.", notes = "Merge details from Bid Acceptance Emails into cache.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful merge of data in cache."),
            @ApiResponse(code = 500, message = "Internal server error")
        })
    public void processAcceptedBids() 
    {
    	hudService.processAcceptedBids();
    }
    


   
    private Map<String, PropertyLead> parseHudBidSearchResult()
    {
    	Map<String, PropertyLead> hudLeadMap = new HashMap<String, PropertyLead>();
    	try {
			String hudFileOut = uploadDirectory + this.hudNetBidFile;
			CsvParser hudNetBidReader = new CsvParser(hudFileOut);
			
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
    
    private Map<String, PropertyLead> parseHudNetBidResult(InputStream hudUploadedInputStream)
    {
    	Map<String, PropertyLead> hudLeadMap = new HashMap<String, PropertyLead>();
    	//String hudFileOut = uploadDirectory + this.hudNetBidFile;
    	try {
			LOGGER.info("Parsing HUD Net Bid Result... ");
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


}
