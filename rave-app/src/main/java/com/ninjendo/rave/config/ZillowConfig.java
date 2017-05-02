package com.ninjendo.rave.config;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="zillow")
public class ZillowConfig {
		
    @NotNull
    private String webserviceId;
    
    private String webserviceIdAlternate;

	@NotNull
	private String searchResultsUrl;
	@NotNull
	private String compsUrl;
	private String deepSearchResultsUrl;
	private String deepCompsUrl;
	private int compsCount = 5;

	
	@NotNull
	private String zestimateUrl;
	
	public String getWebserviceId() {
		return webserviceId;
	}
	public void setWebserviceId(String webserviceId) {
		this.webserviceId = webserviceId;
	}
	public String getSearchResultsUrl() {
		return searchResultsUrl;
	}
	public void setSearchResultsUrl(String searchResultsUrl) {
		this.searchResultsUrl = searchResultsUrl;
	}
	public String getZestimateUrl() {
		return zestimateUrl;
	}
	public void setZestimateUrl(String zestimateUrl) {
		this.zestimateUrl = zestimateUrl;
	}
	public String getCompsUrl() {
		return compsUrl;
	}
	public void setCompsUrl(String compsUrl) {
		this.compsUrl = compsUrl;
	}
	public String getDeepSearchResultsUrl() {
		return deepSearchResultsUrl;
	}
	public void setDeepSearchResultsUrl(String deepSearchResultsUrl) {
		this.deepSearchResultsUrl = deepSearchResultsUrl;
	}
	public String getDeepCompsUrl() {
		return deepCompsUrl;
	}
	public void setDeepCompsUrl(String deepCompsUrl) {
		this.deepCompsUrl = deepCompsUrl;
	}
	public int getCompsCount() {
		return compsCount;
	}
	public void setCompsCount(int compsCount) {
		this.compsCount = compsCount;
	}
	public String getWebserviceIdAlternate() {
		return webserviceIdAlternate;
	}
	public void setWebserviceIdAlternate(String webserviceIdAlternate) {
		this.webserviceIdAlternate = webserviceIdAlternate;
	}
	
	
}
