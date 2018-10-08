package com.tangcheng.pojo;

import java.io.Serializable;

public class ShopImg implements Serializable{
		private int id;
		private String imgurl;
		private String shopid;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getImgurl() {
			return imgurl;
		}
		public void setImgurl(String imgurl) {
			this.imgurl = imgurl;
		}
		public String getShopid() {
			return shopid;
		}
		public void setShopid(String shopid) {
			this.shopid = shopid;
		}
}
