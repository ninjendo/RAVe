package com.ninjendo.rave.model;

public class ZillowSearchRequest {

	private String webserviceId;
	private String propertyId;
	private String cityStateZip;
	private boolean showRentEstimate;
	private String streetAddress;
	private int compsCount;
	
	public String getWebserviceId() {
		return webserviceId;
	}
	public void setWebserviceId(String webserviceId) {
		this.webserviceId = webserviceId;
	}
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	public String getCityStateZip() {
		return cityStateZip;
	}
	public void setCityStateZip(String cityStateZip) {
		this.cityStateZip = cityStateZip;
	}

	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public int getCompsCount() {
		return compsCount;
	}
	public void setCompsCount(int compsCount) {
		this.compsCount = compsCount;
	}
	public boolean isShowRentEstimate() {
		return showRentEstimate;
	}
	public void setShowRentEstimate(boolean showRentEstimate) {
		this.showRentEstimate = showRentEstimate;
	}
}
