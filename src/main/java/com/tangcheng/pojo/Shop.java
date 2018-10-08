package com.tangcheng.pojo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author ljy
 * prifile 简介       time 营业时间     region 地域    userview 查看人数
 */
public class Shop implements Serializable{
	private String id;
	private String name;
	private String profile;
	private String address;
	private String price;
	private double longitude;
	private double latitude;
	private String phone;
	private String time;
	private int userview;
	private int collection;
	private int star;
	private String vidurl;
	private String region;
	private String food_style;
	private String wechat;
	private String article;
	private List<Food> foods;
	private List<ShopEnvironment> shopEnvironments;
	private List<Label> labels;
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
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getUserview() {
		return userview;
	}
	public void setUserview(int userview) {
		this.userview = userview;
	}
	
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}

	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getFood_style() {
		return food_style;
	}
	public void setFood_style(String food_style) {
		this.food_style = food_style;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public List<Food> getFoods() {
		return foods;
	}
	public void setFoods(List<Food> foods) {
		this.foods = foods;
	}
	public List<ShopEnvironment> getShopEnvironments() {
		return shopEnvironments;
	}
	public void setShopEnvironments(List<ShopEnvironment> shopEnvironments) {
		this.shopEnvironments = shopEnvironments;
	}
	public int getCollection() {
		return collection;
	}
	public void setCollection(int collection) {
		this.collection = collection;
	}
	public String getVidurl() {
		return vidurl;
	}
	public void setVidurl(String vidurl) {
		this.vidurl = vidurl;
	}
	public List<Label> getLabels() {
		return labels;
	}
	public void setLabels(List<Label> labels) {
		this.labels = labels;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public List<ShopImg> getImgs() {
		return imgs;
	}
	public void setImgs(List<ShopImg> imgs) {
		this.imgs = imgs;
	}
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}


}
