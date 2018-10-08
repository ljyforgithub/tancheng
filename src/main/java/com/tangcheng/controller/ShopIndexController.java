package com.tangcheng.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
//import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tangcheng.dao.ShopMapper;
import com.tangcheng.dao.UserMapper;
import com.tangcheng.pojo.Label;
import com.tangcheng.pojo.Shop;
import com.tangcheng.pojo.ShopIndex;
import com.tangcheng.service.LoginService;
import com.tangcheng.util.DistanceUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import vo.Shopindex;
@Api(tags="店铺列表")
@RestController
public class ShopIndexController {
	
	private static Logger logger = LogManager.getLogger(ShopIndexController.class);
	
	@Autowired
	private ShopMapper shopMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired 
	private CacheManager cachemanager;
	
	@ApiOperation(value="店铺列表", httpMethod="GET")
	@RequestMapping("shopindex")
	@ResponseBody
	List<Map<String, Shopindex>> getShopInfo(@RequestParam("sort")String sort, @RequestParam("level")int lev, 
			@RequestParam("latitude")double latitude, @RequestParam("longitude")double longitude,@RequestParam("distance")int dists,HttpServletRequest req) {
		List<ShopIndex> shoplist = new ArrayList<ShopIndex>();

		shoplist = shopMapper.selectBylevel(lev);
		List<Map<String, Shopindex>> indexlist = new ArrayList<Map<String, Shopindex>>();
		indexlist = shopdeal(shoplist,longitude,latitude,dists);
		indexlist = shopsort(indexlist,sort);
		
/*		String session = req.getHeader("sessionId");
		if(!session.equals("")) {
			if(cachemanager.getCache("cacheTest").get(session)!=null) {
				String openid = (String)cachemanager.getCache("cacheTest").get(session).get();
				List<String> list = userMapper.findcollect(openid);
				list.contains(arg0)
			}
		}
		*/
		return indexlist;
	}

	//分类shop查询
	@ApiOperation(value="分类店铺查询", httpMethod="GET")
	@RequestMapping("shopCate")
	@ResponseBody
	public List<Map<String, Shopindex>> shopCategory(@RequestParam("sort")String sort, @RequestParam("labelid")int labelid, 
			@RequestParam("latitude")double latitude, @RequestParam("longitude")double longitude){
		List<ShopIndex> shoplist = new ArrayList<ShopIndex>();
		shoplist = shopMapper.selectbylabel(labelid);
		List<Map<String, Shopindex>> indexlist = new ArrayList<Map<String, Shopindex>>();
		
		int dists=0;
		indexlist = shopdeal(shoplist,longitude,latitude,dists);
		indexlist = shopsort(indexlist,sort);
		return indexlist;
	}
	
	//deal
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
	
	//sort
	public List<Map<String, Shopindex>> shopsort(List<Map<String, Shopindex>> indexlist,String sort){
		if("distance".equals(sort)) {
			 Collections.sort(indexlist, new Comparator<Map<String,Shopindex>>() {
			      @Override
			      public int compare(Map<String,Shopindex> o1, Map<String,Shopindex> o2) {
			        if(o1.get("GG").getDistance() >= o2.get("GG").getDistance()) {
			          return 1;
			        }
			        else {
			          return -1;
			        }
			      }
			    });
			}else if("level".equals(sort)) {
				Collections.sort(indexlist, new Comparator<Map<String,Shopindex>>() {
				      @Override
				      public int compare(Map<String,Shopindex> o1, Map<String,Shopindex> o2) {
				        if(o1.get("GG").getLevel() >= o2.get("GG").getLevel()) {
				          return 1;
				        }
				        else {
				          return -1;
				        }
				      }
				    });
			}else if("price".equals(sort)){
				Collections.sort(indexlist, new Comparator<Map<String,Shopindex>>() {
				      @Override
				      public int compare(Map<String,Shopindex> o1, Map<String,Shopindex> o2) {
				        if(Integer.parseInt(o1.get("GG").getPrice()) >= Integer.parseInt(o2.get("GG").getPrice())) {
				          return 1;
				        }
				        else {
				          return -1;
				        }
				      }
				    });
			}
		return indexlist;
	}
	
	
	public String ucs(String openid,String id) {
		List<String> ids = userMapper.findcollect(openid);
		ids.contains(id);
		return "";
	}
}
