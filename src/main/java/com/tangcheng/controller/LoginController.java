package com.tangcheng.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tangcheng.Constants;
import com.tangcheng.http.HttpUtil;
import com.tangcheng.service.LoginService;


/**
 * 
 * @author esonlee
 * loginController
 *
 */
@Controller
public class LoginController {
	
	private static final Logger logger = LogManager.getLogger(LoginController.class); 
	
@Autowired
private LoginService loginservice;
	
	@RequestMapping("login")
	@ResponseBody
	String login(HttpServletRequest req) {
		String code = req.getHeader("code");
		String rawData = req.getHeader("RAWDATA");
		String sign = req.getHeader("sign");
		logger.info(code);
		logger.info(sign);
		String json = loginservice.doLogin(rawData, sign, code);
		return json;
	}

}
