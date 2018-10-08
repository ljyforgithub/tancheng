package com.tangcheng.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
//import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.tangcheng.dao.ShopMapper;
import com.tangcheng.util.DistanceUtil;
import vo.Level;


//首页
@RestController
@RequestMapping("index")
public class PageIndexController {
		
	private static final Logger logger = LogManager.getLogger(PageIndexController.class);
	
	@Autowired
	private ShopMapper shopMapper;
	
	@RequestMapping("/indexforStar")
	@ResponseBody
	String classifyShop(@RequestParam("longitude")String longitude, @RequestParam("latitude")String latitude) {
	
		List<Map<String, Object>> maplist = shopMapper.selectCoordinate();
		List<Map<String, Object>> jslist=new ArrayList<Map<String, Object>>();
		Map<String,List<String>> listmap = new HashMap<String, List<String>>();
		double lat_b = Double.parseDouble(latitude);
		double lng_b = Double.parseDouble(longitude);
		for(Map<String, Object> map : maplist) {
			String id = (String) map.get("id");
			double lat_f= (double) map.get("latitude");
			double lng_f= (double) map.get("longitude");
			double dist = DistanceUtil.distance(lat_f, lng_f, lat_b, lng_b);
			if(dist<5) {
				
			}
			String s = Double.toString(dist);
			Map<String, Object> mp = new HashMap<String, Object>();
			mp.put("dist", s);
			mp.put("id", id);
			jslist.add(mp);
		}
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		List<String> list3 = new ArrayList<String>();
		for(Map<String, Object> map : jslist) {
			if(5<Double.parseDouble((String) map.get("dist"))) {
				list1.add((String) map.get("id"));	
			}else if(3<Double.parseDouble((String) map.get("dist"))&&5>Double.parseDouble((String) map.get("dist"))) {
				list2.add((String) map.get("id"));
			}else {
				list3.add((String) map.get("id"));
			}
		}
		listmap.put("5", list1);
		listmap.put("3", list2);
		listmap.put("1", list3);
//		JSONObject json = new JSONObject(listmap);
//		logger.info(json);
//		logger.info(listmap.toString());
		return "";
	}
	
	/**
	 * http://localhost:8080/tangcheng/index/test?longitude=&latitude=
	 * 测试坐标：116.361731,23.569787
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	@RequestMapping("/test")
	public List<Set<Level>> shopindex(@RequestParam("longitude")double lng_b, @RequestParam("latitude")double lat_b) {
		List<Map<String, Object>> maplist = shopMapper.selectCoordinate();
		List<Map<String, String>> jslist=new ArrayList<Map<String, String>>();
//		double lat_b = Double.parseDouble(latitude);
//		double lng_b = Double.parseDouble(longitude);
		//计算距离并加入map
		for(Map<String, Object> map : maplist) {
			
			int lev = (int) map.get("star");
			String star = Integer.toString(lev);
			double lat_f= (double) map.get("latitude");
			double lng_f= (double) map.get("longitude");
			double dist = DistanceUtil.distance(lat_f, lng_f, lat_b, lng_b);
			if(0<dist&&3>dist){
				Map<String, String> mp = new HashMap<String, String>();
				mp.put("dist","3");
				mp.put("star", star);
				jslist.add(mp);
			}else if( 3<dist && 5>dist){
				Map<String, String> mp = new HashMap<String, String>();
				mp.put("dist","5");
				mp.put("star", star);
				jslist.add(mp);
				}
			
			
		}
		System.out.println(jslist.toString());
		for(Map<String, Object> map : maplist) {
			Map<String, String> mp = new HashMap<String, String>();
			int lev = (int) map.get("star");
			String star = Integer.toString(lev);
			mp.put("star", star);
			mp.put("dist", "0");
			jslist.add(mp);
		}
		System.out.println(jslist.toString());
		//计算数量分组重排
		Map<String,List<String>> shopmap = new HashMap<String,List<String>>();
		for(int j=0; j<jslist.size(); j++) {
			Map<String, String> map = jslist.get(j);
			String key = "";
			String level = map.get("star");
			String distant = map.get("dist");
			if(!"".equals(distant)&&distant != null) {
				if(level.indexOf("0")!=-1) {
					key = distant + "0";
				}else if(level.indexOf("1")!=-1) {
					key = distant + "1";
				}else if(level.indexOf("2")!=-1) {
					key = distant + "2";
				}else if(level.indexOf("3")!=-1) {
					key = distant + "3";
				}
				if(shopmap.get(key)==null) {
					List<String> list = new ArrayList<String>();
					list.add(level);
					shopmap.put(key, list);
					}else {
					List<String> paramList = shopmap.get(key);
					paramList.add(level);
					shopmap.put(key, paramList);
				}
			}
		}
		Iterator<Map.Entry<String, List<String>>> it = shopmap.entrySet().iterator();
		List<Map<String, String>> paramlist = new ArrayList<Map<String, String>>();
		while(it.hasNext()) {
			Map<String, String> paramMap = new HashMap<String,String>();
			Map.Entry<String, List<String>> entry = it.next();
			String shopstr = entry.getKey();
			String level = shopstr.substring(1, 2);
			String distant = shopstr.substring(0, 1);
			List<String> shopset = entry.getValue();
			int count = shopset.size();
			paramMap.put("level", level);
			paramMap.put("distant", distant);
			paramMap.put("num", Integer.toString(count));
			paramlist.add(paramMap);
		}
		System.out.println(paramlist.toString());
		Set<Level> set3 = new TreeSet<Level>();
		Set<Level> set5 = new TreeSet<Level>();
		Set<Level> set = new TreeSet<Level>();
		Level lev = new Level();
		Level lev1 = new Level();
		Level lev2 = new Level();
		Level lev3 = new Level();
		lev.setLevel("0");
		lev.setNumber("0");
		lev1.setLevel("1");
		lev1.setNumber("0");
		lev2.setLevel("2");
		lev2.setNumber("0");
		lev3.setLevel("3");
		lev3.setNumber("0");
		for(Map<String,String> map:paramlist) {
			if("3".equals(map.get("distant"))) {
				Level level = new Level();
				level.setLevel(map.get("level"));
				level.setNumber(map.get("num"));
				set3.add(level);
			}
			if("5".equals(map.get("distant"))) {
				Level level = new Level();
				level.setLevel(map.get("level"));
				level.setNumber(map.get("num"));
				set5.add(level);
			}
			if("0".equals(map.get("distant"))) {
				Level level = new Level();
				level.setLevel(map.get("level"));
				level.setNumber(map.get("num"));
				set.add(level);
			}	
		}
		set3.add(lev);
		set3.add(lev1);
		set3.add(lev2);
		set3.add(lev3);
		set5.add(lev);
		set5.add(lev1);
		set5.add(lev2);
		set5.add(lev3);
		set.add(lev);
		set.add(lev1);
		set.add(lev2);
		set.add(lev3);
		List<Set<Level>> nlist = new ArrayList<Set<Level>>();
		nlist.add(set);
		nlist.add(set3);
		nlist.add(set5);
		
		return nlist;
		
	}
}
