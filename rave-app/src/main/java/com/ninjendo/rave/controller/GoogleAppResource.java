package com.ninjendo.rave.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListLabelsResponse;
import com.google.api.services.gmail.model.ListThreadsResponse;
import com.google.api.services.gmail.model.Message;
import com.ninjendo.rave.config.GoogleConfig;

@RequestMapping("/googleapp")
public class GoogleAppResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleAppResource.class);
    /** Application name. */
    private static final String APPLICATION_NAME = "Real Estate Lead Manager";

	/** Directory to store user credentials for this application. */
	private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"),".credentials/releadmanager");

	/** Global instance of the {@link FileDataStoreFactory}. */
	private static FileDataStoreFactory DATA_STORE_FACTORY;

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	/** Global instance of the HTTP transport. */
	private static HttpTransport HTTP_TRANSPORT;

	private static final String GMAIL_CLIENT_SECRET_FILE = "/google-api/gmail_client_secret.json";

	private GoogleCredential gCredential = null;
	/**
	 * Global instance of the scopes required by this quickstart.
	 *
	 * If modifying these scopes, delete your previously saved credentials at
	 * ~/.credentials/gmail-java-quickstart
	 */
	private static final List<String> GMAIL_SCOPES = Arrays.asList(GmailScopes.GMAIL_LABELS, GmailScopes.GMAIL_READONLY,
			GmailScopes.GMAIL_MODIFY, GmailScopes.MAIL_GOOGLE_COM);
 
    private GoogleConfig config = null;
    
    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }
    
    
    
    public GoogleAppResource(GoogleConfig config) {
        super();
        this.config = config;
    }

    
    @RequestMapping(value="/storeauthcode/{code}")
    public Response processAccessToken(@PathVariable("code") String authCode) {

    	LOGGER.info("Processing Google Token response...");
    	String REDIRECT_URI = "http://localhost:8080/assets/download.html";
    	
    	// (Receive authCode via HTTPS POST)

    	// Set path to the Web application client_secret_*.json file you downloaded from the
    	// Google API Console: https://console.developers.google.com/apis/credentials
    	// You can also find your Web application client ID and client secret from the
    	// console and specify them directly when you create the GoogleAuthorizationCodeTokenRequest
    	// object.

    	// Exchange auth code for access token
    	GoogleTokenResponse tokenResponse = null;
		try {
			BufferedReader br = null;
			try {
				//reader = new FileReader(CLIENT_SECRET_FILE);
				InputStream in = GoogleAppResource.class.getResourceAsStream(GMAIL_CLIENT_SECRET_FILE);
				br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(), br);
			
			tokenResponse = new GoogleAuthorizationCodeTokenRequest(
			      new NetHttpTransport(),
			      JacksonFactory.getDefaultInstance(),
			      "https://www.googleapis.com/oauth2/v4/token",
			      clientSecrets.getDetails().getClientId(),
			      clientSecrets.getDetails().getClientSecret(),
			      authCode,REDIRECT_URI)  // Specify the same redirect URI that you use with your web
			                     // app. If you don't have a web version of your app, you can
			                     // specify an empty string.
			      .execute();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	String accessToken = tokenResponse.getAccessToken();

    	// Use access token to call API
    	gCredential = new GoogleCredential().setAccessToken(accessToken);
    	printLabels();
//    	Drive drive =
//    	    new Drive.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), credential)
//    	        .setApplicationName("Auth Code Exchange Demo")
//    	        .build();
//    	File file = drive.files().get("appfolder").execute();
//
//    	// Get profile info from ID token
//    	GoogleIdToken idToken = tokenResponse.parseIdToken();
//    	GoogleIdToken.Payload payload = idToken.getPayload();
//    	String userId = payload.getSubject();  // Use this value as a key to identify a user.
//    	String email = payload.getEmail();
//    	boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
//    	String name = (String) payload.get("name");
//    	String pictureUrl = (String) payload.get("picture");
//    	String locale = (String) payload.get("locale");
//    	String familyName = (String) payload.get("family_name");
//    	String givenName = (String) payload.get("given_name");
    	
        return Response.ok("OK").build();
    }
    
    public Gmail getGmailService(){
    	if (gCredential == null){
    	
    		LOGGER.error("GOOGLE AUTHENTICATION IS NEEDED!!!!");
    	}
    	else
    	{
            return new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, gCredential)
                    .setApplicationName(APPLICATION_NAME)
                    .build();
    	}
    	return null;
    } 
    
    public void printLabels(){
        Gmail service = getGmailService();
        if (service != null){
            try {
				// Print the labels in the user's account.
				String user = "me";
				ListLabelsResponse listResponse =
				    service.users().labels().list(user).execute();
				List<Label> labels = listResponse.getLabels();
				if (labels.size() == 0) {
				    System.out.println("No labels found.");
				} else {
				    System.out.println("Labels:");
				    for (Label label : labels) {
				        System.out.printf("- %s\n", label.getName());
				    }
				}
			} catch (IOException e) {
				LOGGER.error("Error: " + e.getLocalizedMessage());
				// TODO Auto-generated catch block
				e.printStackTrace();
			}        	
        }
    }
    
    public void processHudResponse() throws IOException {

        Gmail service = getGmailService();
        
        if (service != null)
        {
        	LOGGER.info("star email parsing ...");
            String user = "me";
            ListThreadsResponse response = service.users().threads().list(user).setQ("label:HUD subject:'Bid Counter Offer Notice'").execute();
            List<com.google.api.services.gmail.model.Thread> threads = new ArrayList<com.google.api.services.gmail.model.Thread>();
            while(response.getThreads() != null) {
              threads.addAll(response.getThreads());
              if(response.getNextPageToken() != null) {
                String pageToken = response.getNextPageToken();
                response = service.users().threads().list(user).setQ("subject:'Bid Counter Offer Notice'").setPageToken(pageToken).execute();
              } else {
                break;
              }
            }

            for(com.google.api.services.gmail.model.Thread thread : threads) {
              System.out.println(thread.toPrettyString());
              
              if(!thread.getMessages().isEmpty())
              {
            	  Message message = thread.getMessages().get(0);
            	  LOGGER.info("message -> " + message.toPrettyString());
              }
            }
        }
    }

    @RequestMapping(value="/authurl")
	public String getGmailAuthorizationUrl(@RequestParam("redirectUri") String redirectUri) throws IOException {
    	LOGGER.info("config.getGmailClientId() => " +  (config != null? config.getGmailClientId(): "GoogleConfig is null!!"));
		return new GoogleAuthorizationCodeRequestUrl(config.getGmailClientId(), redirectUri, GMAIL_SCOPES).build();
	}

    @RequestMapping(value="/credentials")
    @Produces("application/json")
	public GoogleCredential getGmailCredentials(@QueryParam("redirectUri") String redirectUri, @QueryParam("code") String code) throws IOException {

    	LOGGER.info("Retrieving Token Response... ");
    	
    	GoogleTokenResponse response = new GoogleAuthorizationCodeTokenRequest(HTTP_TRANSPORT, JSON_FACTORY,
				config.getGmailClientId(), config.getGmailClientSecret(), code, redirectUri).execute();

    	LOGGER.info("Token Response Received... ");
    	
    	GoogleCredential credential = null;
		try {
			credential = new GoogleCredential.Builder().setClientSecrets(config.getGmailClientId(), config.getGmailClientSecret())
					.setJsonFactory(JSON_FACTORY).setTransport(HTTP_TRANSPORT).build()
					.setAccessToken(response.getAccessToken()).setRefreshToken(response.getRefreshToken());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.info("Error creating credential: " + e.getLocalizedMessage());
			e.printStackTrace();
		}
		// Build a new GoogleCredential instance and return it.
		//return Response.ok("Credential created!").build();
		return credential;
	}

}
