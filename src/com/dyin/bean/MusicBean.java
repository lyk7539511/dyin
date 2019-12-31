package com.dyin.bean;

import org.apache.commons.lang3.StringUtils;

public class MusicBean {
	
	private int id;
	private String music;
	private String author;
	private String image;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMusic() {
		return music;
	}
	public void setMusic(String music) {
		this.music = music;
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
	
	public static boolean isBlank(MusicBean bean) {
		return StringUtils.isBlank(bean.getMusic())||
				StringUtils.isBlank(bean.getAuthor())||
				StringUtils.isBlank(bean.getImage());
	}

}
