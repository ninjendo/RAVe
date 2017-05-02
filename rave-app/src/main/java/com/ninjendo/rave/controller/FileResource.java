package com.ninjendo.rave.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ninjendo.rave.config.UploadConfig;
import com.ninjendo.rave.model.PropertyLead;
import com.ninjendo.rave.parser.CsvParser;
import com.ninjendo.rave.parser.HudCsvWriter;
import com.ninjendo.rave.service.LeadCrawlerService;
import com.ninjendo.rave.service.ZillowService;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.FormDataParam;


public class FileResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileResource.class);

    private final ZillowService zillowService;
    private final LeadCrawlerService crawlerService;
    private String uploadDirectory;
    private String hudNetBidFile;
    private String leadType;


    public FileResource(UploadConfig upload,ZillowService zService, LeadCrawlerService cService) {
        super();
        this.uploadDirectory = upload.getUploadPath();
        this.hudNetBidFile = upload.getHudNetBidFile();
        this.zillowService = zService;
        this.crawlerService = cService;
        
        LOGGER.info("Upload path: " + uploadDirectory);
    }


    public Response uploadFile(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException {

        ensureParentDirectory(uploadDirectory);

        String uploadedFileLocation = uploadDirectory + fileDetail.getFileName();
        LOGGER.info("Uploaded file : " + uploadedFileLocation);

        writeToFile(uploadedInputStream, uploadDirectory, fileDetail.getFileName());

        String output = "File uploaded to : " + uploadedFileLocation;
        return Response.ok(output).build();
    }
    

    public Response uploadAndSaveFile(
            @FormDataParam("leadFile") InputStream mainLeadFile,
            @FormDataParam("leadFile") FormDataContentDisposition fileDetail,
            @FormDataParam("hudNetBid") InputStream hudUploadedInputStream,
            @FormDataParam("hudNetBid") FormDataContentDisposition hudFileDetail,
            @FormDataParam("outputFileType") String outputType) throws IOException {

        try {
			ensureParentDirectory(uploadDirectory);

			this.leadType = outputType;
			String uploadedFileLocation = uploadDirectory + fileDetail.getFileName();
			LOGGER.info("Uploaded file : " + uploadedFileLocation);
			
			
			CsvParser reader = new CsvParser(mainLeadFile);
			
			//retrieve HUD results for investor
			//byte[] bytes = this.crawlerService.downloadFile(linkUrl);
        	//InputStream is = new ByteArrayInputStream(decodedBytes);
			
			List<PropertyLead> leads = reader.parse();
		    String fileOut = uploadDirectory + fileDetail.getFileName();
			if (leads != null)
			{
				//Map<String, PropertyLead> hudSearchResult = parseHudBidSearchResult();
				Map<String, PropertyLead> hudNetBid = parseHudNetBidResult(hudUploadedInputStream);
				
			    LOGGER.info("No. of parsed property leads: " + leads.size());

			    
			    
			    for (PropertyLead propertyLead : leads) {
			        try {
			        	
			        	PropertyLead hudNetProperty = hudNetBid.get(propertyLead.getFhaCaseNumber());
			        	
			        	if (hudNetProperty != null){
			        		propertyLead.setCounteredNetToHud(hudNetProperty.getCounteredNetToHud());
			        	}
			        	
			        	//filter out properties: check if recently sold/listed (redfin), 
			        	//TODO: don't use this for testing since it will run against zillow PROD and we will runout
			        	//of credits. We only get 1000 requests per day from zillow
						this.zillowService.mergeData(propertyLead);
			        	
						System.out.println("URL from google => " + this.crawlerService.getUrlFromGoogle(propertyLead));
//			        }catch (RequestLimitReachedException re) {
//			        	LOGGER.error("Daily request limit in webservice Zillow has been reached! No more zillow request will be sent"
//			        			+ " until tomorrow.");
//			        	break;
			        }catch (Exception e) {
						System.err.println("Unable to get data from zillow: " + e.getMessage());
					}
			        
			        try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			    
			    //propertyLeadDAO.batchInsert(leads);

			    LOGGER.info("Writing leads in output file: " + fileOut);
			    HudCsvWriter writer = new HudCsvWriter(leads, fileOut);
			    writer.write();
			    
			    LOGGER.info("Property leads saved in file: " + fileOut);
			}

			String output = leads.size() + " property leads saved in " + fileOut;
			return Response.ok(output).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
    }

    private void writeToFile(InputStream uploadedInputStream, String folder, String fileName) throws IOException {
        java.nio.file.Path outputPath = FileSystems.getDefault().getPath(folder, fileName);
        Files.copy(uploadedInputStream, outputPath);
    }

    private void ensureParentDirectory(String parentDirectory) {
        File parentDir;
        if (parentDirectory != null) {
            parentDir = new File(parentDirectory);
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }
        } else {
            throw new WebApplicationException(Response.Status.PRECONDITION_FAILED);
        }
    }
    
    private Map<String, PropertyLead> parseHudBidSearchResult()
    {
    	Map<String, PropertyLead> hudLeadMap = new HashMap<String, PropertyLead>();
    	String hudFileOut = uploadDirectory + this.hudNetBidFile;
		CsvParser hudNetBidReader = new CsvParser(hudFileOut);
		
		List<PropertyLead> hudNetLeads = hudNetBidReader.parse();
		
		for (PropertyLead propertyLead : hudNetLeads) 
		{
			if (!hudLeadMap.containsKey(propertyLead.getFhaCaseNumber())){
				hudLeadMap.put(propertyLead.getFhaCaseNumber(), propertyLead);
			}
		}
		return hudLeadMap;
    }
    
    private Map<String, PropertyLead> parseHudNetBidResult(InputStream hudUploadedInputStream)
    {
    	Map<String, PropertyLead> hudLeadMap = new HashMap<String, PropertyLead>();
    	//String hudFileOut = uploadDirectory + this.hudNetBidFile;
    	LOGGER.info("Parsing HUD Net Bid Result... ");
		CsvParser hudNetBidReader = new CsvParser(hudUploadedInputStream);
		
		List<PropertyLead> hudNetLeads = hudNetBidReader.parse();
		
		for (PropertyLead propertyLead : hudNetLeads) 
		{
			if (!hudLeadMap.containsKey(propertyLead.getFhaCaseNumber())){
				hudLeadMap.put(propertyLead.getFhaCaseNumber(), propertyLead);
			}
		}
		return hudLeadMap;
    }


    public Response uploadFile(FormDataMultiPart multiPart) {

        ensureParentDirectory(uploadDirectory);

        List<FormDataBodyPart> bodyParts = multiPart.getFields("file");
        List<String> fileNames = new ArrayList<>();

        for (FormDataBodyPart part : bodyParts) {
            InputStream uploadedInputStream = part.getValueAs(InputStream.class);
            FormDataContentDisposition fileDetail = part.getFormDataContentDisposition();
            try {
                writeToFile(uploadedInputStream, uploadDirectory, fileDetail.getFileName());
            } catch (IOException e) {
                e.printStackTrace();
            }
            fileNames.add(fileDetail.getFileName());
        }

        return Response.ok(fileNames.toString()).build();
    }
}
