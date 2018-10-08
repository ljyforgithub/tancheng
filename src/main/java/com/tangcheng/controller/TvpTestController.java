package com.tangcheng.controller;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tangcheng.service.TvpService;

/**
 * @author esonlee
 * 腾讯视频controller
 *
 */
@Controller
public class TvpTestController {

	private static final Logger logger = LogManager.getLogger(TvpTestController.class); 
	
	@Autowired
	private TvpService tvpService;
	
	@RequestMapping("tv")
	@ResponseBody
	String getTv() {
		
		String vid = "y0727ux5gv4";
		String tvpurl = tvpService.getTvpUrl(vid);
		logger.info(tvpurl);
		return tvpurl;
	}
}
