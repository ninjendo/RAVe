package com.ninjendo.rave.util;

import com.ninjendo.rave.model.PropertyLead;

public class KeyGenerator {

	private KeyGenerator(){}
	
	public static String getPropertyId(PropertyLead prop){
		if (prop.getFhaCaseNumber() != null){
			return prop.getFhaCaseNumber().toUpperCase();
		}
		if (prop.getZillowId() != null)
		{
			return prop.getZillowId().toString(); 
		}
		return prop.getPropertyAddress().getStreetAddress().toString().toUpperCase();
	}
}
