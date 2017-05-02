package com.ninjendo.rave.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Optional;
import com.ninjendo.rave.exception.RequestLimitReachedException;
import com.ninjendo.rave.model.PropertyLead;
import com.ninjendo.rave.service.ZillowService;

@Path("/research")
@Produces(MediaType.APPLICATION_JSON)
public class LeadResearchResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(LeadResearchResource.class);

    private final ZillowService zillowService;

    @Autowired
    public LeadResearchResource(ZillowService zillowService) {
        this.zillowService = zillowService;
    }

//    @Path("/details/{id}")
//    public Response getLeadDetails(@PathParam("id") String leadId) {
//        final Optional<PropertyLead> lead = propertyLeadDAO.findById(Long.parseLong(leadId));
//
//        if (lead.isPresent()) {
//            return Response.ok(lead).build();
//        }
//        return Response.status(Response.Status.NOT_FOUND).build();
//    }  
//    
//    @Path("/mergezillow/{id}")
//    public Response mergeZillowData(@PathParam("id") String leadId) {
//        final Optional<PropertyLead> lead = propertyLeadDAO.findById(Long.parseLong(leadId));
//        try {
//			if (lead.isPresent()) {
//				zillowService.getPropertyDetails(lead.get());
//				//merge data
//			    return Response.ok(lead).build();
//			}
//		} catch (RequestLimitReachedException e) {
//			// TODO Auto-generated catch block
//			Response.status(Response.Status.FORBIDDEN).build();
//		}
//        return Response.status(Response.Status.NOT_FOUND).build();
//    }
//    
//    @GET
//    @Path("/mergezillowcomps/{id}")
//    public Response mergeZillowComparables(@PathParam("id") String leadId) {
//        final Optional<PropertyLead> lead = propertyLeadDAO.findById(Long.parseLong(leadId));
//        try {
//			if (lead.isPresent()) {
//				zillowService.getPropertyDetails(lead.get());
//				//merge data
//			    return Response.ok(lead).build();
//			}
//		} catch (RequestLimitReachedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			Response.status(Response.Status.FORBIDDEN).build();
//		}
//        return Response.status(Response.Status.NOT_FOUND).build();
//    }
}
