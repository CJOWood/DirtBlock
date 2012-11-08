package org.chriswood.plugin.CraftStatsPlus;

import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;

public class CraftStatsPlusHolder {
	public Map<String, Map<String, Map<String, Integer>>> stats = new HashMap<String, Map<String, Map<String, Integer>>>();
	//     Map<"Event", Map<"user", Map<"data", amount>>>
	public CraftStatsPlusHolder() {}
	
	public void addData(String event, String user, String data, int dataAmt){
		if(stats.containsKey(event)){
			if(stats.get(event).containsKey(user)){
				if(stats.get(event).get(user).containsKey(data)){
					stats.get(event).get(user).put(data, stats.get(event).get(user).get(data)+dataAmt);
				}else{;
					stats.get(event).get(user).put(data, dataAmt);
				}
			}else{
				stats.get(event).put(user, new HashMap<String, Integer>());
				stats.get(event).get(user).put(data, dataAmt);
			}
		}else{
			stats.put(event, new HashMap<String, Map<String, Integer>>());
			stats.get(event).put(user, new HashMap<String, Integer>());
			stats.get(event).get(user).put(data, dataAmt);
		}
	}
	
	@SuppressWarnings("unchecked")
	public String toJson()
	{
		JSONObject json = new JSONObject();
		json.putAll(stats);
		return json.toString();
	}
	
	public void clearData()
	{
		stats.clear();
	}
	
	public String onSeverShutdown()
	{
		//drop, or encrypt and save?
		return "";
	}
}
