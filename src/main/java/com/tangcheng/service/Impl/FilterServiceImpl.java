package com.tangcheng.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tangcheng.dao.LabelMapper;
import com.tangcheng.pojo.Label;
import com.tangcheng.pojo.ShopIndex;
import com.tangcheng.service.FilterService;

@Service

public class FilterServiceImpl implements FilterService {
	
	@Autowired
	private LabelMapper lm;
	
	


	@Override
	public List<ShopIndex> shopFilter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Map<String, Object> filter() {
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("ms", lm.selectFoodstyle());
		map.put("rj", lm.selectPrice());
		map.put("xj", lm.selectLevel());
		map.put("sq", lm.selectRegion());
		map.put("qt", lm.selectOthers());
		return map;
	}

}
