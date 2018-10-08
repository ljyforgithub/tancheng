package com.tangcheng.service;

import java.util.List;
import java.util.Map;

import com.tangcheng.pojo.Label;
import com.tangcheng.pojo.ShopIndex;

public interface FilterService {
	
	public List<ShopIndex> shopFilter();
	
	public Map<String, Object> filter();

}
