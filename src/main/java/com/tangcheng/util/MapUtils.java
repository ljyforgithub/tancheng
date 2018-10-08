package com.tangcheng.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class MapUtils {
	
	public String MapUtil(List<Map<String, Object>> maplist,double lng_b,double lat_b) {
		List<Map<String, String>> jslist=new ArrayList<Map<String, String>>();
		for(Map<String, Object> map : maplist) {
			String id = (String) map.get("id");
			Map<String, String> mp = new HashMap<String, String>();
			int lev = (int) map.get("star");
			String star = Integer.toString(lev);
			double lat_f= (double) map.get("latitude");
			double lng_f= (double) map.get("longitude");
			double dist = DistanceUtil.distance(lat_f, lng_f, lat_b, lng_b);
			if(0<dist&&3>dist){
				mp.put("dist","3");
				mp.put("star", star);
			}else if( 3<dist && 5>dist){
				mp.put("dist","5");
				mp.put("star", star);
				}
			jslist.add(mp);	
		}
	
		return "";
	}
	
	public String mapUtil(List<Map<String, Object>> maplist,double lng_b,double lat_b) {
		Multimap<String, Object> multimap = ArrayListMultimap.create();
		
		return "";
	}

}
