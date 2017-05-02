package com.ninjendo.rave.service;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.gmail.GmailScopes;
import com.ninjendo.rave.config.GoogleConfig;

@Service
public class GoogleAppService {

	@Autowired
	RestTemplate restTemplate;
	
	// Retrieve the CLIENT_ID and CLIENT_SECRET from an APIs Console project:
	// https://code.google.com/apis/console
	static String CLIENT_ID = "your-client-id";
	static String CLIENT_SECRET = "your-client-secret";
	// Change the REDIRECT_URI value to your registered redirect URI for web
	// applications.
	static String REDIRECT_URI = "the-redirect-uri";
	// Add other requested scopes.
	static List<String> SCOPES = Arrays.asList("https://spreadsheets.google.com/feeds");
	/** Global instance of the {@link FileDataStoreFactory}. */
	private static FileDataStoreFactory DATA_STORE_FACTORY;

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	/** Global instance of the HTTP transport. */
	private static HttpTransport HTTP_TRANSPORT;

	private static final String GMAIL_CLIENT_SECRET_FILE = "/google-api/gmail_client_secret.json";
    private static final List<String> GMAIL_SCOPES = Arrays.asList(GmailScopes.GMAIL_LABELS, GmailScopes.GMAIL_READONLY
    		, GmailScopes.GMAIL_MODIFY, GmailScopes.MAIL_GOOGLE_COM);
    
    
    @Autowired
	private GoogleConfig config;

	final static Logger logger = LoggerFactory.getLogger(GoogleService.class);

//	@Autowired
//	public GoogleAppService(GoogleConfig config) {
//		this.config = config;
//		System.out.println("this.config =" + this.config);
//	}

	
    public static Drive getDriveService() throws IOException {
        Credential credential = null;
        return new Drive.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName("HUD Email Parser Result")
                .build();
    }

    
	
	public OutputStream downloadBidAcceptanceSheet(String googleFileId) throws IOException{
		OutputStream outputStream = new ByteArrayOutputStream();
		getDriveService().files().export(googleFileId, "text/csv")
		        .executeMediaAndDownloadTo(outputStream);
		
		return outputStream;
	}
		
//    public static void main(String s[]) {
//        try {
//        String authorizationUrl =new GoogleAuthorizationCodeRequestUrl("482674941648-61s2ubettd5553qrs7tnp2tujsfmpg1h.apps.googleusercontent.com", 
//        		"http://localhost:8080/assets/download.html", GMAIL_SCOPES).build();
//        URL urldemo = new URL(authorizationUrl);
//        URLConnection yc = urldemo.openConnection();
//        BufferedReader in = new BufferedReader(new InputStreamReader(
//                yc.getInputStream()));
//        String inputLine;
//        while ((inputLine = in.readLine()) != null)
//            System.out.println(inputLine);
//        in.close();
//        }catch(Exception e) {
//            System.out.println(e);
//        }
//    }


	  /**
	   * Retrieve OAuth 2.0 credentials.
	   * 
	   * @return OAuth 2.0 Credential instance.
	   */
	  public Credential getCredentials(String redirectUri) throws IOException {
	    HttpTransport transport = new NetHttpTransport();
	    JacksonFactory jsonFactory = new JacksonFactory();

	    // Step 1: Authorize -->
	    String authorizationUrl =new GoogleAuthorizationCodeRequestUrl(config.getGmailClientId(), redirectUri, GMAIL_SCOPES).build();

	    // Point or redirect your user to the authorizationUrl.
	    System.out.println("Go to the following link in your browser:");
	    System.out.println(authorizationUrl);

	    // Read the authorization code from the standard input stream.
	    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	    System.out.println("What is the authorization code?");
	    String code = in.readLine();
	    // End of Step 1 <--

	    // Step 2: Exchange -->
	    GoogleTokenResponse response =
	        new GoogleAuthorizationCodeTokenRequest(transport, jsonFactory, CLIENT_ID, CLIENT_SECRET,
	            code, REDIRECT_URI).execute();
	    // End of Step 2 <--

	    // Build a new GoogleCredential instance and return it.
	    return new GoogleCredential.Builder().setClientSecrets(CLIENT_ID, CLIENT_SECRET)
	        .setJsonFactory(jsonFactory).setTransport(transport).build()
	     .setAccessToken(response.getAccessToken()).setRefreshToken(response.getRefreshToken());
	  }
	  
	  public Credential getGmailCredentials(String redirectUri) throws IOException {
		    HttpTransport transport = new NetHttpTransport();
		    JacksonFactory jsonFactory = new JacksonFactory();

		    // Step 1: Authorize -->
		    String authorizationUrl =new GoogleAuthorizationCodeRequestUrl(config.getGmailClientId(), redirectUri, GMAIL_SCOPES).build();

		    // Point or redirect your user to the authorizationUrl.
		    System.out.println("Go to the following link in your browser:");
		    System.out.println(authorizationUrl);

		    // Read the authorization code from the standard input stream.
		    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		    System.out.println("What is the authorization code?");
		    String code = in.readLine();
		    // End of Step 1 <--

		    // Step 2: Exchange -->
		    GoogleTokenResponse response =
		        new GoogleAuthorizationCodeTokenRequest(transport, jsonFactory, CLIENT_ID, CLIENT_SECRET,
		            code, REDIRECT_URI).execute();
		    // End of Step 2 <--

		    // Build a new GoogleCredential instance and return it.
		    return new GoogleCredential.Builder().setClientSecrets(CLIENT_ID, CLIENT_SECRET)
		        .setJsonFactory(jsonFactory).setTransport(transport).build()
		     .setAccessToken(response.getAccessToken()).setRefreshToken(response.getRefreshToken());
		  }
	  
		public GoogleCredential getGmailCredentials(String redirectUri, String code) throws IOException {

			logger.info("Retrieving Token Response... ");
	    	
	    	GoogleTokenResponse response = new GoogleAuthorizationCodeTokenRequest(HTTP_TRANSPORT, JSON_FACTORY,
					config.getGmailClientId(), config.getGmailClientSecret(), code, redirectUri).execute();

	    	logger.info("Token Response Received... ");
	    	
	    	GoogleCredential credential = null;
			try {
				credential = new GoogleCredential.Builder().setClientSecrets(config.getGmailClientId(), config.getGmailClientSecret())
						.setJsonFactory(JSON_FACTORY).setTransport(HTTP_TRANSPORT).build()
						.setAccessToken(response.getAccessToken()).setRefreshToken(response.getRefreshToken());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.info("Error creating credential: " + e.getLocalizedMessage());
				e.printStackTrace();
			}
			// Build a new GoogleCredential instance and return it.
			//return Response.ok("Credential created!").build();
			return credential;
		}
}
