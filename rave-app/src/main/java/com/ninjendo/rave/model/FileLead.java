package com.ninjendo.rave.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public class FileLead implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 870721795638559042L;
	private Long id;
	private String name;
	private String description;
	
	//map of propertyLead attribute to file header
	private Map<String, String> leadAttributeHeaderMap;
	private String fileType;
	
	private List<AttributeFilter> filters;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public List<AttributeFilter> getFilters() {
		return filters;
	}

	public void setFilters(List<AttributeFilter> filters) {
		this.filters = filters;
	}

	public Map<String, String> getLeadAttributeHeaderMap() {
		return leadAttributeHeaderMap;
	}

	public void setLeadAttributeHeaderMap(Map<String, String> leadAttributeHeaderMap) {
		this.leadAttributeHeaderMap = leadAttributeHeaderMap;
	}
	
	
}
