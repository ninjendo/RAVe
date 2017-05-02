package com.ninjendo.rave.model;

import java.io.Serializable;

public class Contact implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3023774140251660202L;

	private Long id;
	
	private String mobilePhone;
	private String homePhone;
	private String email;
	private String bestTimeToCall;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBestTimeToCall() {
		return bestTimeToCall;
	}
	public void setBestTimeToCall(String bestTimeToCall) {
		this.bestTimeToCall = bestTimeToCall;
	}
	
	

}
