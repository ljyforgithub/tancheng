package com.tangcheng.pojo;

import java.io.Serializable;
import java.util.List;

public class ShopIndex implements Serializable{
	
	private String id;
	private String name;
	private int star;
	private String profile;
	private String region;
	private String phone;
	private String wechat;
	private String price;
	private double longitude;
	private double latitude;
	private List<ShopImg> imgs;
	private List<Label> labels;
	public String getShopid() {
		return id;
	}
	public void setShopid(String id) {
		this.id = id;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<ShopImg> getImgs() {
		return imgs;
	}
	public void setImgs(List<ShopImg> imgs) {
		this.imgs = imgs;
	}
	public List<Label> getLabels() {
		return labels;
	}
	public void setLabels(List<Label> labels) {
		this.labels = labels;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
}
