package com.tangcheng.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.json.JSONObject;
//import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tangcheng.common.EsUtil;
import com.tangcheng.dao.ShopMapper;
import com.tangcheng.pojo.Shop;
import com.tangcheng.util.DistanceUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
//店铺详情页
@Api(tags="店铺详情")
@RestController
public class ShopDetailController {
	
	private static Logger log = LogManager.getLogger(ShopDetailController.class);
	@Autowired
	private ShopMapper shopMapper;
	
	@ApiOperation(value="店铺详情",httpMethod="GET")
	@RequestMapping("shopDetail")
	Map<String,Object> getShopDetail(@RequestParam("shopid") String shopid, 
			@RequestParam("longitude")double longitude,@RequestParam("latitude")double latitude) {
			Shop shop = shopMapper.selectAllById(shopid);
			shopMapper.updateView(shopid);
			double lat = shop.getLatitude();
			double lng = shop.getLongitude();
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
			double distance = DistanceUtil.distance(lat, lng, latitude, longitude);
			DecimalFormat df = new DecimalFormat("#.00");
			String dist = df.format(distance);
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("distance", dist);
			map.put("level", star);
			map.put("shopdetail", shop);
			

		return map;
	}

}
