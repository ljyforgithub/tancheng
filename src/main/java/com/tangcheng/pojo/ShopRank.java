package com.tangcheng.pojo;

import java.io.Serializable;
import java.util.List;

public class ShopRank implements Serializable{
	
	private String id;
	private String name;
	private String profile;
	private List<ShopImg> imgs;
	private int userview;
	private int collection;
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
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public List<ShopImg> getImgs() {
		return imgs;
	}
	public void setImgs(List<ShopImg> imgs) {
		this.imgs = imgs;
	}
	public int getUserview() {
		return userview;
	}
	public void setUserview(int userview) {
		this.userview = userview;
	}
	public int getCollection() {
		return collection;
	}
	public void setCollection(int collection) {
		this.collection = collection;
	}

}
