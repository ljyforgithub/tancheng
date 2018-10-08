package com.tangcheng.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tangcheng.common.EsUtil;
import com.tangcheng.util.DistanceUtil;

import vo.Shopindex;

@Controller
public class SearchController {
	
	private static Logger log = LogManager.getLogger(SearchController.class);
	
	@RequestMapping("search")
	@ResponseBody
	public List<Map<String,Shopindex>>  test(@RequestParam("keyword")String search,@RequestParam("latitude")double latitude,
			@RequestParam("longitude")double longitude,@RequestParam("sort")String sort) {
		try(RestHighLevelClient client = EsUtil.init()){
			SearchRequest searchRequest = new SearchRequest("test"); 
			searchRequest.types("shop");
			SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder(); 
			
			MultiMatchQueryBuilder matchQueryBuilder = new 	
					MultiMatchQueryBuilder(search,"shopdetail.name","shopdetail.foods.name")
					.operator(Operator.AND);
			searchSourceBuilder.query(matchQueryBuilder); 
			String[] includeFields = new String[] 
					{"shopdetail.id","shopdetail.name","shopdetail.region","shopdetail.profile",
					 "shopdetail.star","shopdetail.imgs","shopdetail.labels","shopdetail.phone",
					 "shopdetail.wechat","shopdetail.longitude","shopdetail.latitude","shopdetail.price"};
			String[] excludeFields = new String[] {"_type"};
			searchSourceBuilder.fetchSource(includeFields, excludeFields);
			searchRequest.source(searchSourceBuilder); 
			SearchResponse searchResponse = client.search(searchRequest);
			SearchHits hits = searchResponse.getHits();
			SearchHit[] searchHits = hits.getHits();
			List<Map<String,Shopindex>> list = shopdeal(searchHits,longitude,latitude);
			list = shopsort(list,sort);
			return list;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@RequestMapping("hotword")
	public String hotword() {
		try(RestHighLevelClient client = EsUtil.init()){
			 
	           
		}catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	//deal
	public List<Map<String,Shopindex>> shopdeal(SearchHit[] searchHits,double longitude,double latitude){
		List<Map<String,Shopindex>> paramlist = new ArrayList<Map<String,Shopindex>>();
		for (SearchHit hit : searchHits) {
			String sourceAsString = hit.getSourceAsString();
			log.info(sourceAsString);
			Map<String, Object> sourceAsMap = hit.getSourceAsMap();
			Map<String, Object> map = (Map<String,Object>)sourceAsMap.get("shopdetail");
			double lng = (double)map.get("longitude");
			double lat = (double)map.get("latitude");
			int s = (Integer)map.get("star");
			double distance = DistanceUtil.distance(lat, lng, latitude, longitude);
			DecimalFormat df = new DecimalFormat("#.00");
			String d = df.format(distance);
			double dist = Double.valueOf(d);
			log.info(dist);
			List<Map<String, Object>> list = (List<Map<String, Object>>)map.get("imgs");
			String imgurl = (String)list.get(0).get("imgurl");
			
			Shopindex shopindex = new Shopindex();
			shopindex.setId((String)map.get("id"));
			shopindex.setName((String)map.get("name"));
			shopindex.setLatitude(lat);
			shopindex.setLongitude(lng);
			shopindex.setDistance(dist);
			shopindex.setLevel(s);
			shopindex.setPrice((String)map.get("price"));
			shopindex.setPhone((String)map.get("phone"));
			shopindex.setWechat((String)map.get("wechat"));
			shopindex.setRegion((String)map.get("region"));
			shopindex.setProfile((String)map.get("profile"));
			shopindex.setImgurl(imgurl);
			List<Map<String, Object>> lablist = (List<Map<String, Object>>)map.get("labels");
			List<Map<String, Object>> lab_list = new ArrayList<Map<String,Object>>();
			for(Map<String, Object> l : lablist) {
				Map<String, Object> lmap = new HashMap<String,Object>();
				lmap.put("text", l.get("name"));
				lab_list.add(lmap);
			}
			shopindex.setLabels(lab_list);
			String star = "";
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
			log.info(imgurl);
			Map<String, Shopindex> smap = new HashMap<String, Shopindex>();
			smap.put("GG", shopindex);
			paramlist.add(smap);
		}
		
		return paramlist;
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

}
