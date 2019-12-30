package com.dyin.action;

import java.util.List;

import com.dyin.bean.ImageBean;
import com.dyin.bean.MusicBean;
import com.dyin.bean.UmcBean;
import com.dyin.dao.ImageDAO;
import com.dyin.dao.MusicDAO;
import com.dyin.dao.UmcDAO;
import com.dyin.dao.VideoDAO;

public class IndexAction {
	private String videoString;
	private VideoDAO videoDAO = new VideoDAO();
	private UmcDAO umcDao = new UmcDAO();
	private MusicDAO musicDAO = new MusicDAO();
	private List<UmcBean> umcList;
	private List<MusicBean> musicList;
	private int musicSize = 0;
	private MusicBean musicBean;
	private List<ImageBean> imageList;
	private ImageDAO imageDAO = new ImageDAO();
	private int id;
	
	public MusicBean getMusicBean() {
		return musicBean;
	}

	public void setMusicBean(MusicBean musicBean) {
		this.musicBean = musicBean;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<ImageBean> getImageList() {
		return imageList;
	}

	public void setImageList(List<ImageBean> imageList) {
		this.imageList = imageList;
	}

	public int getMusicSize() {
		return musicSize;
	}

	public void setMusicSize(int musicSize) {
		this.musicSize = musicSize;
	}

	public List<MusicBean> getMusicList() {
		return musicList;
	}

	public void setMusicList(List<MusicBean> musicList) {
		this.musicList = musicList;
	}

	public String getVideoString() {
		return videoString;
	}

	public void setVideoString(String videoString) {
		this.videoString = videoString;
	}

	public List<UmcBean> getUmcList() {
		return umcList;
	}

	public void setUmcList(List<UmcBean> umcList) {
		this.umcList = umcList;
	}

	public VideoDAO getDao() {
		return videoDAO;
	}

	public void setDao(VideoDAO dao) {
		this.videoDAO = dao;
	}

	public VideoDAO getVideoDAO() {
		return videoDAO;
	}

	public void setVideoDAO(VideoDAO videoDAO) {
		this.videoDAO = videoDAO;
	}

	public UmcDAO getUmcDao() {
		return umcDao;
	}

	public void setUmcDao(UmcDAO umcDao) {
		this.umcDao = umcDao;
	}

	public String index() {
		videoString = videoDAO.getVideo();
		return "dindex";
	}

	public String dmusician() {
		umcList = umcDao.getUmcList();
		if (umcList!=null && !umcList.isEmpty()) {
			videoString = umcList.get(0).getVideo();
		}
		musicList = musicDAO.getMusicList();
		if (musicList != null) {
			musicSize = musicList.size();
			System.out.println();
		}
		return "dmusician";
	}
	

	public String dapp() {
		musicBean = musicDAO.getMusicBean(id);
		int mid = musicBean.getId();
		imageList = imageDAO.getImageList(mid);
		return "dapp";
	}

}
