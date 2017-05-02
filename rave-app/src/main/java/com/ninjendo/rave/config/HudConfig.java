package com.ninjendo.rave.config;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="hud")
public class HudConfig {
		
    @NotNull
    private String defaultSearchUrl;
    private String searchUrl;
    private String stateFilter;
    private String buyerTypeFilter;
    private String statusFilter;
    
	public String getDefaultSearchUrl() {
		return defaultSearchUrl;
	}
	public void setDefaultSearchUrl(String defaultSearchUrl) {
		this.defaultSearchUrl = defaultSearchUrl;
	}
	public String getStateFilter() {
		return stateFilter;
	}
	public void setStateFilter(String stateFilter) {
		this.stateFilter = stateFilter;
	}
	public String getBuyerTypeFilter() {
		return buyerTypeFilter;
	}
	public void setBuyerTypeFilter(String buyerTypeFilter) {
		this.buyerTypeFilter = buyerTypeFilter;
	}
	public String getStatusFilter() {
		return statusFilter;
	}
	public void setStatusFilter(String statusFilter) {
		this.statusFilter = statusFilter;
	}
	public String getSearchUrl() {
		return searchUrl;
	}
	public void setSearchUrl(String searchUrl) {
		this.searchUrl = searchUrl;
	}
    
}
