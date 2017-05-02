package com.ninjendo.rave.model;

public enum State {


	AL("Alabama"),
	AK("Alaska"),
	AS("American Samoa"),
	AZ("Arizona"),
	AR("Arkansas"),
	CA("California"),
	CO("Colorado"),
	CT("Connecticut"),
	DE("Delaware"),
	DC("Dist. of Columbia"),
	FL("Florida"),
	GA("Georgia"),
	GU("Guam"),
	HI("Hawaii"),
	ID("Idaho"),
	IL("Illinois"),
	IN("Indiana"),
	IA("Iowa"),
	KS("Kansas"),
	KY("Kentucky"),
	LA("Louisiana"),
	ME("Maine"),
	MD("Maryland"),
	MH("Marshall Islands"),
	MA("Massachusetts"),
	MI("Michigan"),
	FM("Micronesia"),
	MN("Minnesota"),
	MS("Mississippi"),
	MO("Missouri"),
	MT("Montana"),
	NE("Nebraska"),
	NV("Nevada"),
	NH("New Hampshire"),
	NJ("New Jersey"),
	NM("New Mexico"),
	NY("New York"),
	NC("North Carolina"),
	ND("North Dakota"),
	MP("Northern Marianas"),
	OH("Ohio"),
	OK("Oklahoma"),
	OR("Oregon"),
	PW("Palau"),
	PA("Pennsylvania"),
	PR("Puerto Rico"),
	RI("Rhode Island"),
	SC("South Carolina"),
	SD("South Dakota"),
	TN("Tennessee"),
	TX("Texas"),
	UT("Utah"),
	VT("Vermont"),
	VA("Virginia"),
	VI("Virgin Islands"),
	WA("Washington"),
	WV("West Virginia"),
	WI("Wisconsin"),
	WY("Wyoming");

	
	private String name;
	
	private State(final String stateName){
		this.name = stateName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
