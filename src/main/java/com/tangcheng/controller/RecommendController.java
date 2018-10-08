package com.tangcheng.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tangcheng.dao.RecommendMapper;
import com.tangcheng.pojo.Recommend;

//推荐
@RestController
public class RecommendController {

	@Autowired
	private RecommendMapper rm;
	
	@RequestMapping("recommend")
	public List<Recommend> getRecommend(){
		List<Recommend> list = rm.selectall();
		return list;
	}
}
