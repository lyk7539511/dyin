package com.dyin.bean;

import org.apache.commons.lang3.StringUtils;

public class StarBean {
	private int id;
	private String name;
	private String image;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	public static boolean isBlank(StarBean bean) {
		return StringUtils.isBlank(bean.getName()) ||
				StringUtils.isBlank(bean.getImage());
	}
}
