package com.dyin.action;

import java.util.List;

import com.dyin.bean.ImageBean;
import com.dyin.dao.ImageDAO;
import com.dyin.util.FileUtil;

public class ImageAction extends BaseAction {

	private int mid;
	private String image;
	private ImageDAO dao = new ImageDAO();
	private List<ImageBean> list;
	private ImageBean bean;

	public List<ImageBean> getList() {
		return list;
	}

	public void setList(List<ImageBean> list) {
		this.list = list;
	}

	public ImageBean getBean() {
		return bean;
	}

	public void setBean(ImageBean bean) {
		this.bean = bean;
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

	public String list() {
		list = dao.getImageList(id);	//此处id是music的id
		return "list";
	}

	public void image() {
		FileUtil.copy(file, "D:/STUDY/PracticalTraining/Web/JavaCodeProjects/media/image/" + fileFileName);
		outRespJson("/dyin/media/image/" + fileFileName); // 把存好文件的路径带回发起请求的jsp页面，即add.jsp
	}

	//
	public String add() {
		if (isPost()) {
			if (ImageBean.isBlank(bean)) {
				jsAlert("请完善信息", "/dyin/image_add");
				return null;
			}
			dao.addImage(bean);
			id = bean.getMid();
			return "listAction";
		} else {
			//把id传到add.jsp
			return "add";

		}
	}

	public String del() {
		dao.delImage(id);
		id = mid;
		return "listAction";
	}

}
