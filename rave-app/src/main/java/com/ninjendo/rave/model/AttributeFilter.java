package com.ninjendo.rave.model;

import java.util.List;


public class AttributeFilter 
{
	private String filterName;
	private int minimum;
	private int maximum;
	
	private List<String> compareValues;
	private Boolean isExclude;
	private Boolean isInclude;
	public String getFilterName() {
		return filterName;
	}
	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}
	public int getMinimum() {
		return minimum;
	}
	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}
	public int getMaximum() {
		return maximum;
	}
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
	public List<String> getCompareValues() {
		return compareValues;
	}
	public void setCompareValues(List<String> compareValues) {
		this.compareValues = compareValues;
	}
	public Boolean getIsExclude() {
		return isExclude;
	}
	public void setIsExclude(Boolean isExclude) {
		this.isExclude = isExclude;
	}
	public Boolean getIsInclude() {
		return isInclude;
	}
	public void setIsInclude(Boolean isInclude) {
		this.isInclude = isInclude;
	}
	
	
	
}
