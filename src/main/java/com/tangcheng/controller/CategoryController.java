package com.tangcheng.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tangcheng.dao.CategoryMapper;
import com.tangcheng.pojo.Label;

//分类列表
@RestController
public class CategoryController {
	@Autowired
	private CategoryMapper categoryMapper; 
	
	@RequestMapping("category")
	public Map<String,List<Label>> getCategory(){
		
		List<Label> list = categoryMapper.selectall();
		Map<String,List<Label>> map = new HashMap<String,List<Label>>();
		List<Label> list1 = new ArrayList<Label>();
		List<Label> list2 = new ArrayList<Label>();
		List<Label> list3 = new ArrayList<Label>();
		for(Label label : list) {
			if(label.getType().equals("商圈")) {
				list1.add(label);
			}
			if(label.getType().equals("美食")) {
				list2.add(label);
			}
		}
		map.put("ms", list2);
		map.put("sq", list1);
		return map;
	}
}
