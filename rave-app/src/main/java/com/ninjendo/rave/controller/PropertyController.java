package com.ninjendo.rave.controller;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ninjendo.rave.model.PropertyLead;
import com.ninjendo.rave.service.HudHomePropertyService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@RequestMapping("/properties")
public class PropertyController {
    private static final Logger logger = LoggerFactory.getLogger(PropertyController.class);

    @Autowired
    private HudHomePropertyService service;
    
    @GetMapping(value="/all")
    @ApiOperation(value = "Get properties.", notes = "List of properties.", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful.", response = List.class),
            @ApiResponse(code = 500, message = "Internal server error")
        })
    public @ResponseBody List<PropertyLead> getProperties() 
    {
    	return service.getProperties();
    }
    
    @GetMapping(value="/nozillow")
    @ApiOperation(value = "Get No Zillow data properties.", notes = "List of properties that has no records from Zillow.", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful.", response = List.class),
            @ApiResponse(code = 500, message = "Internal server error")
        })
    public @ResponseBody Set<PropertyLead> getNoZillow() 
    {
    	return service.getNoZillowRecords();
    }
    
    @GetMapping(value="/nozillow/count")
    @ApiOperation(value = "Count No Zillow data properties.", notes = "Count properties that has no records from Zillow.", response = Integer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful.", response = List.class),
            @ApiResponse(code = 500, message = "Internal server error")
        })
    public @ResponseBody Integer getNoZillowCount() 
    {
    	return service.getNoZillowRecords().size();
    }
}
