package com.dyin.bean;

import org.apache.commons.lang3.StringUtils;

public class ImageBean {
	private int id;
	private int mid;	//外键
	private String image;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public static boolean isBlank(ImageBean bean) {
		return StringUtils.isBlank(bean.getImage());
	}

}
