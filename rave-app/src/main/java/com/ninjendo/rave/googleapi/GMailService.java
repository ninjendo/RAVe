package com.ninjendo.rave.googleapi;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
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


public class GMailService {
	private final GoogleConfig config;
	
    @Autowired
    public GMailService(GoogleConfig config) {
        super();
        this.config = config;
    }
    
	private Gmail service = null;
	private static final Logger LOGGER = LoggerFactory.getLogger(GMailService.class);
	
	   /** Application name. */
    private static final String APPLICATION_NAME =
        "Gmail API Java Quickstart";

    /** Directory to store user credentials for this application. */
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
        System.getProperty("user.home"), ".credentials/gmail-java-quickstart");

    /** Global instance of the {@link FileDataStoreFactory}. */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY =
        JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;
    
    private static final String CLIENT_SECRET_FILE = "/google-api/gmail_client_secret.json";

    /** Global instance of the scopes required by this quickstart.
     *
     * If modifying these scopes, delete your previously saved credentials
     * at ~/.credentials/gmail-java-quickstart
     */
    private static final List<String> SCOPES = Arrays.asList(GmailScopes.GMAIL_LABELS, GmailScopes.GMAIL_READONLY
    		, GmailScopes.GMAIL_MODIFY, GmailScopes.MAIL_GOOGLE_COM);
    
    private static String hostname = null;
    private static String hostUrl = null;
    private static String callbackMethod = "hud/process";

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
        
        try {
        	InetAddress ip = InetAddress.getLocalHost();
        	hostname = ip.toString();
        	hostUrl = hostname + ":8080"; 
        	LOGGER.info("hostUrl -> " + hostUrl);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     * @throws IOException
     */
    public static Credential authorize(String authCode) throws IOException {
        // Load client secrets.
        InputStream in =
        		GMailService.class.getResourceAsStream("/google-api/gmail_client_secret.json");
        GoogleClientSecrets clientSecrets =
            GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(DATA_STORE_FACTORY)
                .setAccessType("offline")
                .build();
        
        LOGGER.info("GMAIL SERVICE hostUrl -> " + hostUrl);
        //Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
        Credential credential = null;
        if (authCode == null){
        	credential = new AuthorizationCodeInstalledApp(flow, new LocalCallBackServer("http://localhost:8080","assets/download.html")).authorize("user");	
        }
        else
        {
        	credential = getCredential(authCode);
        }
        
        
        System.out.println("Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }
    
    public static Credential getCredential(String authCode) throws IOException {
    	String REDIRECT_URI = "http://localhost:8080/assets/download.html";
    	// Exchange auth code for access token
        InputStream in = GMailService.class.getResourceAsStream(CLIENT_SECRET_FILE);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
    	
    	String accessToken = null;
		try {
			GoogleTokenResponse tokenResponse =
			          new GoogleAuthorizationCodeTokenRequest(
			              new NetHttpTransport(),
			              JacksonFactory.getDefaultInstance(),
			              "https://www.googleapis.com/oauth2/v4/token",
			              clientSecrets.getDetails().getClientId(),
			              clientSecrets.getDetails().getClientSecret(),
			              authCode,
			              REDIRECT_URI)  // Specify the same redirect URI that you use with your web
			                             // app. If you don't have a web version of your app, you can
			                             // specify an empty string.
			              .execute();

			accessToken = tokenResponse.getAccessToken();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	// Use access token to call API
    	return new GoogleCredential().setAccessToken(accessToken);
    }
    
    /**
     * Build and return an authorized Gmail client service.
     * @return an authorized Gmail client service
     * @throws IOException
     */
    public static Gmail getGmailService(String authCode) throws IOException {
        Credential credential = authorize(authCode);
        return new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    /**
     * Build and return an authorized Gmail client service.
     * @return an authorized Gmail client service
     * @throws IOException
     */
    public static Gmail getGmailService(GoogleCredential credential) throws IOException {
        
        return new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
    
//    public static void main(String[] args) throws IOException {
//        // Build a new authorized API client service.
//        Gmail service = getGmailService(null);
//
//        // Print the labels in the user's account.
//        String user = "me";
//        ListLabelsResponse listResponse =
//            service.users().labels().list(user).execute();
//        List<Label> labels = listResponse.getLabels();
//        if (labels.size() == 0) {
//            System.out.println("No labels found.");
//        } else {
//            System.out.println("Labels:");
//            for (Label label : labels) {
//                System.out.printf("- %s\n", label.getName());
//            }
//        }
//    }
    
    public void printLabels(String redirectUri, String authCode) throws IOException {
        // Build a new authorized API client service.
    	Gmail gservice = getGmailService(getGmailCredentials(redirectUri, authCode));

        // Print the labels in the user's account.
        String user = "me";
        ListLabelsResponse listResponse = gservice.users().labels().list(user).execute();
        List<Label> labels = listResponse.getLabels();
        if (labels.size() == 0) {
            System.out.println("No labels found.");
        } else {
            System.out.println("Labels:");
            for (Label label : labels) {
                System.out.printf("- %s\n", label.getName());
            }
        }
    }
    
    public void processHudResponse(String redirectUri, String authCode) throws IOException {

        // Build a new authorized API client service.
        Gmail gservice = getGmailService(getGmailCredentials(redirectUri, authCode));
        
    	LOGGER.info("star email parsing ...");
        String user = "me";
        ListThreadsResponse response = gservice.users().threads().list(user).setQ("label:HUD subject:'Bid Counter Offer Notice'").execute();
        List<com.google.api.services.gmail.model.Thread> threads = new ArrayList<com.google.api.services.gmail.model.Thread>();
        while(response.getThreads() != null) {
          threads.addAll(response.getThreads());
          if(response.getNextPageToken() != null) {
            String pageToken = response.getNextPageToken();
            response = gservice.users().threads().list(user).setQ("subject:'Bid Counter Offer Notice'").setPageToken(pageToken).execute();
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
    

	public GoogleCredential getGmailCredentials(String redirectUri, String code) throws IOException {

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
