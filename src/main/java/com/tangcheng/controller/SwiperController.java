package com.tangcheng.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tangcheng.dao.SwiperMapper;
import com.tangcheng.pojo.Swiper;

@RestController
public class SwiperController {

	
	@Autowired
	private SwiperMapper sm;
	
	@RequestMapping("swiper")
	public List<Swiper> swiper(){
		List<Swiper> list = sm.selectswiper();
		return list;
	}
}
