package com.dyin.action;

import com.dyin.dao.VideoDAO;
import com.dyin.util.FileUtil;

public class VideoAction extends BaseAction{
	private VideoDAO dao = new VideoDAO();
	private String video;

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	// 访问localhost:8080/dyin/video_list
	// 打开/view/dyin/video/list.jsp
	public String list() {
		video = dao.getVideo();	// 数据库中取出的地址现在无法访问，需要做映射
		return "succ";
	}
	
	//访问localhost:8080/dyin/video_upload
	//接收上传的文件
	public void upload() {
		FileUtil.copy(file, "D:/STUDY/PracticalTraining/Web/JavaCodeProjects/media/video/" + fileFileName);
		dao.updateVideo("/dyin/media/video/" + fileFileName);
		
	}

}
