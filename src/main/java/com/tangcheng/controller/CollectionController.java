package com.tangcheng.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.tangcheng.pojo.Collection;
import com.tangcheng.service.CollectionService;

//收藏

@RestController
public class CollectionController {
	@Autowired
	private CollectionService cs;

	@RequestMapping("collection")
	public String collection(@RequestParam("userid")String userid,@RequestParam("shopid")String shopid) {
		Collection col = new Collection();
		col.setShopid(shopid);
		col.setUserid(userid);
		col.setTime(new Date());
		cs.colletion(col, shopid);
		return "success";
	}
	@RequestMapping("delectCol")
	public String delectCol(@RequestParam("col_id")String id, @RequestParam("shopid")String shopid) {
		cs.delectcol(id, shopid);
		return "success";
	}
}
