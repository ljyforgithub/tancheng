package com.tangcheng.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tangcheng.dao.CategoryMapper;
import com.tangcheng.dao.ShopMapper;
import com.tangcheng.pojo.Label;
import com.tangcheng.pojo.ShopIndex;
import com.tangcheng.service.FilterService;
import com.tangcheng.util.DistanceUtil;

import vo.Shopindex;

/**
 * 
 * @author Administrator
 *
 */
@Controller
public class FilterController {

	
	private static Logger logger = LogManager.getLogger(FilterController.class);
	@Autowired
	private FilterService filterService; 
	
	@Autowired
	private ShopMapper shopMapper;
	
	@Autowired
	private CategoryMapper categoryMapper;
	
	@RequestMapping("filter")
	@ResponseBody
	public Map<String,List<Label>> filter() {
		
		
		List<Label> list = categoryMapper.selectall();
		List<Label> list1 = new ArrayList<Label>();
		List<Label> list2 = new ArrayList<Label>();
		List<Label> list3 = new ArrayList<Label>();
		List<Label> list4 = new ArrayList<Label>();
		List<Label> list5 = new ArrayList<Label>();
		Map<String,List<Label>> map = new HashMap<String,List<Label>>();
		for(Label label : list) {
			if(label.getType().equals("美食")) {
				list1.add(label);
			}
			if(label.getType().equals("人均")) {
				list2.add(label);
			}
			if(label.getType().equals("星级")) {
				list3.add(label);
			}
			if(label.getType().equals("商圈")) {
				list4.add(label);
			}
			if(label.getType().equals("其他")) {
				list5.add(label);
			}	
		}
		map.put("ms", list1);
		map.put("rj", list2);
		map.put("xj", list3);
		map.put("sq", list4);
		map.put("qt", list5);
		return map;
		/*
		Map<String, Object> map = filterService.filter();
		return map;@RequestParam(value="renjun",defaultValue="[{}]",required=false)
		*/
	}
	@RequestMapping("shopfilter")
	@ResponseBody
	public List<Map<String, Shopindex>> shopFilter(@RequestParam(value="meishi",required=false,defaultValue="0")int foodstyle,
			/*@RequestParam(value="renjun",defaultValue="0",required=false)int*/@RequestBody List<Integer> price,
			@RequestParam(value="xinji",defaultValue="21",required=false)int star,
			@RequestParam(value="shangquan",defaultValue="0",required=false)int region, 
			@RequestParam(value="others",defaultValue="0",required=false)int others,
			@RequestParam("distance")int distance,
			@RequestParam("sort")String sort,
			@RequestParam("longitude")double longitude,
			@RequestParam("latitude")double latitude) {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("fsid", foodstyle);
			map.put("star", star);
			map.put("region", region);
			map.put("others", others);
			map.put("pricelist", price);
			/*
			List<Integer> prelist = new ArrayList<Integer>();
			List<ShopIndex> list = shopMapper.selectbylabels(map);
			
			*/
			
			List<ShopIndex> list = shopMapper.selectbyFilter(map);
			List<Map<String, Shopindex>> paramlist = shopdeal(list,longitude,latitude,distance);
			
		return paramlist;
	}
	
	public List<Map<String, Shopindex>> shopdeal(List<ShopIndex> shoplist,double longitude,double latitude, int dists) {
		
		List<Map<String, Shopindex>> indexlist = new ArrayList<Map<String, Shopindex>>();
		for(ShopIndex shop : shoplist) {
			double lat = shop.getLatitude();
			double lng = shop.getLongitude();
			double distance = DistanceUtil.distance(lat, lng, latitude, longitude);
			DecimalFormat df = new DecimalFormat("#.00");
			String d = df.format(distance);
			double dist = Double.valueOf(d);
			logger.info(dist);
			Shopindex shopindex = new Shopindex();
			shopindex.setId(shop.getId());
			shopindex.setName(shop.getName());
			shopindex.setDistance(dist);
			shopindex.setWechat(shop.getWechat());
			shopindex.setPhone(shop.getPhone());
			shopindex.setProfile(shop.getProfile());
			shopindex.setRegion(shop.getRegion());
			shopindex.setPrice(shop.getPrice());	
			List<Map<String, Object>> lab_list = new ArrayList<Map<String, Object>>();
			for(Label l :shop.getLabels()) {
				Map<String, Object> lmap = new HashMap<String,Object>();
				lmap.put("text", l.getName());
				lab_list.add(lmap);
			}
			shopindex.setLabels(lab_list);
			shopindex.setLevel(shop.getStar());
			String star = "";
			int s = shop.getStar();
			if(s==0) {
				star = "美食推荐";
			}else if(s==1) {
				star = "美食一星";
			}else if(s==2) {
				star = "美食二星";
			}else {
				star = "美食三星";
			}
			shopindex.setStar(star);
			shopindex.setLatitude(shop.getLatitude());
			shopindex.setLongitude(shop.getLongitude());
			shopindex.setImgurl(shop.getImgs().get(0).getImgurl());
			Map<String, Shopindex> map = new HashMap<String, Shopindex>();
			map.put("GG", shopindex);
			if(dists==0) {
				indexlist.add(map);
			}
			if(dists==1) {
				if(map.get("GG").getDistance()<3) {
					indexlist.add(map);
				}
			}
			if(dists==2) {
				if(map.get("GG").getDistance()>3&&map.get("GG").getDistance()<5) {
					indexlist.add(map);
				}
			}
		}
		return indexlist;
	}
	

}
