package com.tangcheng.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tangcheng.dao.ShopMapper;
import com.tangcheng.pojo.ShopRank;

import vo.Rank;

@Controller
public class RankController {

	@Autowired
	private ShopMapper shopmapper;
	
	@RequestMapping("ranking")
	@ResponseBody
	public List<Rank> rank() {
		List<ShopRank> list = shopmapper.selectforRank();
		List<Rank> paramlist = new ArrayList<Rank>();
		for(ShopRank sr:list) {
			Rank rk = new Rank();
			rk.setId(sr.getId());
			rk.setName(sr.getName());
			rk.setProfile(sr.getProfile());
			rk.setUserview(sr.getUserview());
			rk.setCollection(sr.getCollection());
			rk.setImgurl(sr.getImgs().get(0).getImgurl());
			paramlist.add(rk);
		}
		return paramlist;
	}
}
