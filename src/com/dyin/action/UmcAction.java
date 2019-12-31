package com.dyin.action;

import java.util.List;

import com.dyin.bean.UmcBean;
import com.dyin.dao.UmcDAO;
import com.dyin.util.FileUtil;

public class UmcAction extends BaseAction {
	private UmcDAO umcDAO = new UmcDAO();
	private List<UmcBean> list;
	private UmcBean bean;

	public List<UmcBean> getList() {
		return list;
	}

	public void setList(List<UmcBean> list) {
		this.list = list;
	}

	public UmcBean getBean() {
		return bean;
	}

	public void setBean(UmcBean bean) {
		this.bean = bean;
	}

	// 查询出来的结果送到页面
	// localhost:8080/dyin/umc_list.action
	public String list() {
		list = umcDAO.getUmcList();
		return "list";
	}

	// 添加音乐
	public String add() {
		if (isPost()) {
			//后端判空校验
			if (UmcBean.isBlank(bean)) {
				jsAlert("请完善信息", "/dyin/umc_add");		//输入为空，重定向到当前页面
				return null;
			}
			
			try {
				umcDAO.addUmc(bean);
			} catch (Exception e) {
				jsAlert(bean.getTitle() + "已存在", "/dyin/umc_add");
				return null;
			}
			// 接收提交过来的数据
			return "list";

		} else {
			return "add"; // 打开add.jsp页面
		}

	}
	
	//删除
	// localhost:8080/dyin/umc_del.action
	public String del() {
		umcDAO.delUmc(id);
		return "listAction";
		
	}

	// localhost:8080/dyin/umc_image.action
	// 上传图片
	public void image() {
		FileUtil.copy(file, "D:/STUDY/PracticalTraining/Web/JavaCodeProjects/media/image/" + fileFileName);
		outRespJson("/dyin/media/image/" + fileFileName); // 把存好文件的路径带回发起请求的jsp页面，即add.jsp

	}

	// localhost:8080/dyin/umc_video.action
	// 上传视频
	public void video() {
		FileUtil.copy(file, "D:/STUDY/PracticalTraining/Web/JavaCodeProjects/media/video/" + fileFileName);
		outRespJson("/dyin/media/video/" + fileFileName); // 把存好文件的路径带回发起请求的jsp页面，即add.jsp
	}

}
