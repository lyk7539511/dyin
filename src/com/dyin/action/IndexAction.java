package com.dyin.action;

import com.dyin.dao.VideoDAO;

public class IndexAction {
	private String videoString;
	private VideoDAO dao = new VideoDAO();
	public String getVideoString() {
		return videoString;
	}
	public void setVideoString(String videoString) {
		this.videoString = videoString;
	}
	public VideoDAO getDao() {
		return dao;
	}
	public void setDao(VideoDAO dao) {
		this.dao = dao;
	}

	public String index() {
		videoString = dao.getVideo();
		return "succ";
	}
}
