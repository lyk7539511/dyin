package com.dyin.action;

import java.io.File;

import com.dyin.dao.VideoDAO;
import com.dyin.util.FileUtil;

public class VideoAction {
	private VideoDAO dao = new VideoDAO();
	private File file;
	private String fileFileName;
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	// 访问localhost:8080/dyin/video_list
	// 打开/view/dyin/video/list.jsp
	public String list() {
		return "succ";
	}
	
	//访问localhost:8080/dyin/video_upload
	//接收上传的文件
	public void upload() {
		FileUtil.copy(file, "D:/STUDY/PracticalTraining/Web/JavaCodeProjects/video/" + fileFileName);
		dao.updateVideo("/dyin/video/" + fileFileName);
		
	}

}
