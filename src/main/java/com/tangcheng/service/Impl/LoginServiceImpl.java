package com.tangcheng.service.Impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.tangcheng.Constants;
import com.tangcheng.controller.LoginController;
import com.tangcheng.http.HttpUtil;
import com.tangcheng.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	
	private static final Logger logger = LogManager.getLogger(LoginServiceImpl.class); 
	
	@Autowired
	private HttpUtil httpUtil;

	@Override
	@Cacheable(value="cacheTest", key="#{code}")
	public String doLogin(String rawData, String sign,String code) {
		String url = "https://api.weixin.qq.com/sns/jscode2session";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appid", Constants.appid);
		map.put("secret", Constants.appsecrect);
		map.put("js_code", code);
		map.put("grant_type", "authorization_code");
		String json = httpUtil.Https(map, url);
		JSONObject js = new JSONObject(json);
		String openid = js.getString("openid");
		String session_key = js.getString("session_key");
		try {
			String data = URLDecoder.decode(rawData, "UTF-8");
			String sign2 = DigestUtils.shaHex(data+session_key);
			if(sign2.equals(sign)) {
				JSONObject dataObject = new JSONObject(data);
				String nickName = dataObject.getString("nickName");
				logger.info(nickName);
				return openid;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

}
