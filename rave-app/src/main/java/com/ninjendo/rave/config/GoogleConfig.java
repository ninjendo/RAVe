package com.ninjendo.rave.config;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="google")
public class GoogleConfig {


	@NotNull
    private String gsheetApiKey;
	@NotNull
	private String gsheetClientId;
	@NotNull
	private String gsheetClientSecret;
	@NotNull
	private String gmailClientId;
	@NotNull
	private String gmailClientSecret;
	@NotNull
    private String gmailApiKey;
	private String responseType;
	private String tokenType;
	private String approvalPrompt;
  
	public String getGsheetApiKey() {
		return gsheetApiKey;
	}
	public void setGsheetApiKey(String gsheetApiKey) {
		this.gsheetApiKey = gsheetApiKey;
	}

	public String getGmailApiKey() {
		return gmailApiKey;
	}
	public void setGmailApiKey(String gmailApiKey) {
		this.gmailApiKey = gmailApiKey;
	}
	public String getResponseType() {
		return responseType;
	}
	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	public String getApprovalPrompt() {
		return approvalPrompt;
	}
	public void setApprovalPrompt(String approvalPrompt) {
		this.approvalPrompt = approvalPrompt;
	}
	public String getGsheetClientId() {
		return gsheetClientId;
	}
	public void setGsheetClientId(String gsheetClientId) {
		this.gsheetClientId = gsheetClientId;
	}
	public String getGsheetClientSecret() {
		return gsheetClientSecret;
	}
	public void setGsheetClientSecret(String gsheetClientSecret) {
		this.gsheetClientSecret = gsheetClientSecret;
	}
	public String getGmailClientId() {
		return gmailClientId;
	}
	public void setGmailClientId(String gmailClientId) {
		this.gmailClientId = gmailClientId;
	}
	public String getGmailClientSecret() {
		return gmailClientSecret;
	}
	public void setGmailClientSecret(String gmailClientSecret) {
		this.gmailClientSecret = gmailClientSecret;
	}

	
}
