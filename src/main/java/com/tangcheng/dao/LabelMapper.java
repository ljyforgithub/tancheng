package com.tangcheng.dao;

import java.util.List;

import com.tangcheng.pojo.Foodstyle;
import com.tangcheng.pojo.Level;
import com.tangcheng.pojo.Others;
import com.tangcheng.pojo.Price;
import com.tangcheng.pojo.Region;

public interface LabelMapper{
	
	public List<Price> selectPrice();
	public List<Region> selectRegion();
	public List<Level> selectLevel();
	public List<Others> selectOthers();
	public List<Foodstyle> selectFoodstyle();
}
