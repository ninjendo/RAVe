package com.ninjendo.rave.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ninjendo.rave.config.GoogleConfig;

public class GoogleService {

	private static final String OAuthScopeKey = "scope";
	private static final String OAuth2ClientId = "client_id";
	private static final String OAuth2RedirectUri = "redirect_uri";
	private static final String OAuth2AccessType = "access_type";
	private static final String OAuth2ResponseType = "response_type";
	private static final String OAuth2ApprovalPrompt = "approval_prompt";

	private static final String GOOGLE_API_HOST = "https://www.googleapis.com/";

	private final GoogleConfig config;

	final static Logger logger = LoggerFactory.getLogger(GoogleService.class);

	@Autowired
	public GoogleService(GoogleConfig config) {
		this.config = config;
		System.out.println("this.config =" + this.config);
	}

	// authUrl is https://accounts.google.com/o/oauth2/auth

	private String buildGsheetAuthorizationUrl(String authUrl) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(authUrl);

		stringBuilder.append(String.format("?%s=%s", OAuth2ResponseType, config.getResponseType()));
		stringBuilder.append(String.format("&%s=%s", OAuth2ClientId, config.getGsheetClientId()));
		// stringBuilder.append(String.format("&%s=%s", OAuth2RedirectUri,
		// config.redirectUri));
		// stringBuilder.append(String.format("&%s=%s", OAuthScopeKey,
		// config.scope));
		// stringBuilder.append(String.format("&%s=%s", OAuth2AccessType,
		// config.accessType));
		stringBuilder.append(String.format("&%s=%s", OAuth2ApprovalPrompt, config.getApprovalPrompt()));

		return stringBuilder.toString();
	}

/*	private void refreshAccessToken(SpreadsheetService service) throws Exception {
		String accessToken = callGetAccessTokenApi();

		// save access token

		service.getRequestFactory().setAuthToken(
				new GoogleAuthTokenFactory.OAuth2Token(new GoogleCredential().setAccessToken(accessToken)));
	}

	public String getRefreshToken(String accessCode) throws Exception {

		String url = String.format(
				"%soauth2/v3/token?client_id=%s&client_secret=%s&code=%s&grant_type=authorization_code&redirect_uri=%s",
				GOOGLE_API_HOST, this.config.getGsheetOauthClientId(), this.config.getGmailOauthClientSecret(),
				accessCode, REDIRECT_URL);
		HttpPost post = new HttpPost(url);
		post.addHeader(ACCEPT_HEADER_NAME, "application/x-www-form-urlencoded");

		try {
			HttpResponse response = client.execute(post);

			JSONObject object = readJson(response);

			return object.getString("refresh_token");
		} finally {
			post.releaseConnection();
		}
	}

	private String callGetAccessTokenApi() throws Exception {
		HttpClient client = HttpClients.createDefault();

		String url = String.format(
				"%soauth2/v3/token?client_id=%s&client_secret=%s&refresh_token=%s&grant_type=refresh_token",
				GOOGLE_API_HOST, googleAuthorization.getClientId(), googleAuthorization.getClientSecret(),
				googleAuthorization.getRefreshToken());
		HttpPost post = new HttpPost(url);
		post.addHeader(ACCEPT_HEADER_NAME, "application/x-www-form-urlencoded");

		try {
			HttpResponse response = client.execute(post);

			JSONObject object = readJson(response);

			return object.getString("access_token");
		} finally {
			post.releaseConnection();
		}
	}

	private Service.GDataRequestFactory makeAuthorization() {
		Service.GDataRequestFactory requestFactory = new HttpGDataRequest.Factory();

		// load access token

		requestFactory.setAuthToken(
				new GoogleAuthTokenFactory.OAuth2Token(new GoogleCredential().setAccessToken(accessToken)));

		return requestFactory;
	}

	private JSONObject readJson(HttpResponse response) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuilder result = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			result.append(line);
		}

		return new JSONObject(result.toString());
	}*/
}
