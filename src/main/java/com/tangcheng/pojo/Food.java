package com.tangcheng.pojo;

import java.io.Serializable;

public class Food implements Serializable{
	private int  id;
	private String name;
	private String profile;
	private String image_url;
	private String shop_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getShop_id() {
		return shop_id;
	}
	public void setShopid(String shopid) {
		this.shop_id = shopid;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

}
