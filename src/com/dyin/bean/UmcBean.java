package com.dyin.bean;

import org.apache.commons.lang3.StringUtils;

public class UmcBean {
	private int id;
	private String title;
	private String author;
	private String image;
	private String video;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	
	public static boolean isBlank(UmcBean bean) {
		return StringUtils.isBlank(bean.getTitle())||
				StringUtils.isBlank(bean.getAuthor())||
				StringUtils.isBlank(bean.getImage())||
				StringUtils.isBlank(bean.getVideo());
	}

}
