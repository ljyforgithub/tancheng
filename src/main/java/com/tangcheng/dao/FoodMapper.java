package com.tangcheng.dao;

import java.util.List;

import com.tangcheng.pojo.Food;

public interface FoodMapper {
	
	public List<Food> selectFoodByShopId(String shopid);

}
