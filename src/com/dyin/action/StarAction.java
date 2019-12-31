package com.dyin.action;

import java.util.List;

import com.dyin.bean.StarBean;
import com.dyin.dao.StarDAO;
import com.dyin.util.FileUtil;

public class StarAction extends BaseAction {
	private StarDAO starDAO = new StarDAO();
	private List<StarBean> starList;
	private StarBean bean;
	
	public StarDAO getStarDAO() {
		return starDAO;
	}
	public void setStarDAO(StarDAO starDAO) {
		this.starDAO = starDAO;
	}
	public List<StarBean> getStarList() {
		return starList;
	}
	public void setStarList(List<StarBean> starList) {
		this.starList = starList;
	}
	public StarBean getBean() {
		return bean;
	}
	public void setBean(StarBean bean) {
		this.bean = bean;
	}
	
	public String list() {
		starList = starDAO.getStarList();
		System.out.println(starList.get(0).getName());
		return "list";
	}
	
	public String add() {
		if (isPost()) {
			
			//后端判空校验
			if (StarBean.isBlank(bean)) {
				jsAlert("请完善信息", "/dyin/star_add");		//输入为空，重定向到当前页面
				return null;
			}
			
			try {
				starDAO.addStar(bean);
			} catch (Exception e) {
				jsAlert(bean.getName() + "已存在", "/dyin/star_add");
				return null;
			}
			// 接收提交过来的数据
			return "listAction";

		} else {
			return "add"; // 打开add.jsp页面
		}
	}
	
	//删除
	// localhost:8080/dyin/star_del.action
	public String del() {
		starDAO.delStar(id);
		return "listAction";
		
	}
	
	// localhost:8080/dyin/umc_image.action
	// 上传图片
	public void image() {
		FileUtil.copy(file, "D:/STUDY/PracticalTraining/Web/JavaCodeProjects/media/image/" + fileFileName);
		outRespJson("/dyin/media/image/" + fileFileName); // 把存好文件的路径带回发起请求的jsp页面，即add.jsp

	}
}
