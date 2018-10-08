package com.tangcheng.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpRequest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;
//import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tangcheng.dao.UserMapper;
import com.tangcheng.pojo.User;
import com.tangcheng.pojo.UserCollect;
import com.tangcheng.service.UserService;

/*
 * 
 */
@RequestMapping("user")
@Controller
public class UserInfoController {
	
	private static final Logger logger = LogManager.getLogger(UserInfoController.class);
	
	@Autowired
	private UserMapper usermapper;
	@Autowired
	private UserService us;
	@Autowired
	private CacheManager cachemanager;
	
	@RequestMapping("saveuserinfo")
	@ResponseBody
	String SetUserInfo(@RequestParam("userinfo") String userInfo, @RequestParam("openid") String openid) {
	
		JSONObject user = new JSONObject(userInfo);
		String name = user.getString("nickName");
		String city = user.getString("city");
		String country = user.getString("country");
		String language = user.getString("language");
		String avatarUrl = user.getString("avatarUrl");
		logger.info(name +"\n"+ city+ "\n"+country+"\n"+language+"\n"+avatarUrl);
		User usr = new User();
		usr.setName(name);
		usr.setOpenid(openid);
		usermapper.insertUser(usr);
		return "save success";	
	}
	@RequestMapping("getcollection")
	@ResponseBody
	public List<UserCollect> findcollection(@RequestParam("shopid")String openid,HttpServletRequest req) {
		List<UserCollect> list = usermapper.findCollection(openid);
		
		return list;
	}

	
	@RequestMapping("getUsername")
	@ResponseBody
	public String getUsername(String userid) {
		logger.info(userid);
		if(cachemanager.getCache("cacheTest").get(userid)!=null) {
			String name = (String)cachemanager.getCache("cacheTest").get(userid).get();
			logger.info(name);
		}
		
		String name = us.get(userid);
		return name;
	}
}
