package com.tangcheng.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tangcheng.pojo.Shop;
import com.tangcheng.pojo.ShopIndex;
import com.tangcheng.pojo.ShopRank;

public interface ShopMapper {
	
	//查询shops表id和坐标
  public List<Map<String, Object>> selectCoordinate();
  
  //根据ID查询shops
  public Shop selectAllById(String id);

  //根据星级查询shops表
  public List<ShopIndex> selectBylevel(int star);

  public List<Shop> selectall();
  
  public List<ShopIndex> selectbylabel(int id);
  
  public List<ShopIndex> selectbylabels(Map<String,Object> map);
  
  public List<ShopIndex> selectbyFilter(Map<String, Object> map);
  
  public List<ShopIndex> selectShopIndex();
  
  public List<ShopRank> selectforRank();
  
  public void updateView(String shopid);
  
  public void updateCol(String shopid);
  
  public void deleteCol(String shopid);
}
