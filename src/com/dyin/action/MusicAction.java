package com.dyin.action;

import java.util.List;

import com.dyin.bean.MusicBean;
import com.dyin.dao.MusicDAO;
import com.dyin.util.FileUtil;

public class MusicAction extends BaseAction{
	private MusicDAO dao = new MusicDAO();
	private List<MusicBean> list;
	private MusicBean bean;
	public MusicBean getBean() {
		return bean;
	}
	public void setBean(MusicBean bean) {
		this.bean = bean;
	}
	public MusicDAO getDao() {
		return dao;
	}
	public void setDao(MusicDAO dao) {
		this.dao = dao;
	}
	public List<MusicBean> getList() {
		return list;
	}
	public void setList(List<MusicBean> list) {
		this.list = list;
	}

	public String list() {
		list = dao.getMusicList();
		return "list";
	}
	public void image() {
		FileUtil.copy(file, "D:/STUDY/PracticalTraining/Web/JavaCodeProjects/media/image/" + fileFileName);
		outRespJson("/dyin/media/image/" + fileFileName); // 把存好文件的路径带回发起请求的jsp页面，即add.jsp
	}
	public String add() {
		if (isPost()) {
			if (MusicBean.isBlank(bean)) {
				jsAlert("请完善信息", "/dyin/music_add");
				return null;
			}
			try {
				dao.addMusic(bean);
			} catch (Exception e) {
				jsAlert(bean.getMusic() + "已存在", "/dyin/music_add");
				return null;
			}
			
			return "listAction";
		}else {
			return "add";
			
		}
	}
	public String del() {
		dao.delMusic(id);
		return "listAction";
	}
}
