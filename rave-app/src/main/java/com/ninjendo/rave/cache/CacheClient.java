package com.ninjendo.rave.cache;

import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.MultiMap;
import com.ninjendo.rave.model.PropertyLead;
import com.ninjendo.rave.model.StatusHistory;

@Service
public class CacheClient {

	final static Logger logger = LoggerFactory.getLogger(CacheClient.class);

	public final static String PROPERTY = "PROPERTY";
	public final static String ZILLOW_ID = "ZILLOW-ID";
	public final static String HUD_ID = "HUD-ID";
	public final static String STATUS_HISTORY = "STATUS_HISTORY";
	public final static String NO_ZILLOW = "NO_ZILLOW";
	
	@Autowired
	HazelcastInstance hazelcastInstance;

	public Map<String, PropertyLead> getPropertyMap(){
		return hazelcastInstance.getMap(PROPERTY);	
	}

	public Map<Long, String> getZillowIdMap() {
		 return hazelcastInstance.getMap(ZILLOW_ID);
	}

	public Map<String, String> getHudIdMap() {
		 return hazelcastInstance.getMap(HUD_ID);
	}

	public MultiMap<String, StatusHistory> getStatusHistoryMap() {
		return hazelcastInstance.getMultiMap(STATUS_HISTORY);
	}
	
	public Set<PropertyLead> getNoZillowSet() {
		return hazelcastInstance.getSet(NO_ZILLOW);
	}
}
