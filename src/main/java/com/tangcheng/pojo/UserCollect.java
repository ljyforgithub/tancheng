package com.tangcheng.pojo;

import java.io.Serializable;
import java.util.List;

public class UserCollect implements Serializable{

	
	private String id;
	private String name;
	private String region;
	private String price;
	private int star;
	private List<ShopImg> imgs;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public List<ShopImg> getImgs() {
		return imgs;
	}
	public void setImgs(List<ShopImg> imgs) {
		this.imgs = imgs;
	}
}
