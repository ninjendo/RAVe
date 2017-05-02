package com.ninjendo.rave.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hazelcast.core.MultiMap;
import com.ninjendo.rave.model.PropertyLead;
import com.ninjendo.rave.model.StatusHistory;

@Service
public class PropertyCacheClient extends CacheClient{

	final static Logger logger = LoggerFactory.getLogger(PropertyCacheClient.class);

	/**
	 * @param args
	 *            the command line arguments
	 */
	public void save(List<PropertyLead> leads) {
		for (PropertyLead propertyLead : leads) {
			save(propertyLead);
		}
	}

	public PropertyLead findById(String propertyId) {

		return super.getPropertyMap().get(propertyId);
	}
	
	public List<PropertyLead> getProperties() {

		return new ArrayList<>(super.getPropertyMap().values());
	}
	
	/**
	 * @param args
	 *            the command line arguments
	 */
	public void save(PropertyLead lead) {

		Map<String, PropertyLead> cacheMap = super.getPropertyMap();
		
		PropertyLead cachedLead = cacheMap.get(lead.getId());
		if (cachedLead == null)
		{
			cacheMap.put(lead.getId(), lead);
		}
		
		if (lead.getFhaCaseNumber() != null) {
			saveToHudMap(lead);
		}
		
		if (lead.getZillowId() != null) {
			saveToZillowMap(lead);
		}
		else
		{
			super.getNoZillowSet().add(lead);
		}
		
		
		List<StatusHistory> histories = getStatusHistory(lead);
		StatusHistory prevStatus = null;
		
		if (histories != null && histories.size() > 0){
			prevStatus = histories.get(0);
		}
		
		StatusHistory newStatus = lead.getNewStatus(prevStatus);
		if (newStatus != null)
		{
			saveStatusHistory(newStatus);
		}
	}
	
	public void replace(String oldPropertyId, PropertyLead lead) {

		Map<String, PropertyLead> cacheMap = super.getPropertyMap();
		
		cacheMap.put(lead.getId(), lead);
		
		if (lead.getFhaCaseNumber() != null) {
			saveToHudMap(lead);
		}
		
		if (lead.getZillowId() != null) {
			saveToZillowMap(lead);
		}
		
		List<StatusHistory> histories = getStatusHistory(oldPropertyId);
		
		for (StatusHistory statusHistory : histories) {
			statusHistory.setPropertyId(lead.getId());
			saveStatusHistory(statusHistory);
		}
		
		if (!oldPropertyId.equals(lead.getId()))
		{
			super.getZillowIdMap().remove(oldPropertyId);
			super.getHudIdMap().remove(oldPropertyId);
			super.getStatusHistoryMap().remove(oldPropertyId);
			cacheMap.remove(oldPropertyId);
		}
	}

	public void saveToZillowMap(PropertyLead lead) {
		Map<Long, String> cacheMap = super.getZillowIdMap();
		cacheMap.put(lead.getZillowId(), lead.getId());
	}

	public void saveToHudMap(PropertyLead lead) {
		Map<String, String> cacheMap = super.getHudIdMap();
		cacheMap.put(lead.getFhaCaseNumber(), lead.getId());
	}

	public void saveStatusHistory(StatusHistory history) {
		MultiMap<String, StatusHistory> cacheMap = super.getStatusHistoryMap();
		cacheMap.put(history.getPropertyId(), history);
	}

	public List<StatusHistory> getStatusHistory(PropertyLead lead) {
		MultiMap<String, StatusHistory> cacheMap = super.getStatusHistoryMap();

		List<StatusHistory> histories = new ArrayList<>(cacheMap.get(lead.getId()));
		Collections.sort(histories, Collections.reverseOrder());
		return histories;
	}
	
	public List<StatusHistory> getStatusHistory(String propertyId) {
		MultiMap<String, StatusHistory> cacheMap = super.getStatusHistoryMap();

		List<StatusHistory> histories = new ArrayList<>(cacheMap.get(propertyId));
		Collections.sort(histories, Collections.reverseOrder());
		return histories;
	}
	
	public PropertyLead getByHudCaseNumber(String hudCaseNumber) {
		PropertyLead prop = null;
		
		Map<String, String> hudMap = super.getHudIdMap();
		Map<String, PropertyLead> propMap = super.getPropertyMap();
		
		String propId = hudMap.get(hudCaseNumber);
		
		if (propId != null){
			prop = propMap.get(propId);
		}
		
		return prop;
	}
	
	public PropertyLead getByZillowId(Long zillowId) {
		PropertyLead prop = null;
		
		Map<Long, String> zMap = super.getZillowIdMap();
		Map<String, PropertyLead> propMap = super.getPropertyMap();
		
		String propId = zMap.get(zillowId);
		
		if (propId != null){
			prop = propMap.get(propId);
		}
		
		return prop;
	}

}
