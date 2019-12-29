package com.dyin.action;

import java.util.List;

import com.dyin.bean.UmcBean;
import com.dyin.dao.UmcDao;

public class UmcAction {
	UmcDao dao = new UmcDao();
	private List<UmcBean> list;
	
	public List<UmcBean> getList() {
		return list;
	}

	public void setList(List<UmcBean> list) {
		this.list = list;
	}

	//查询出来的结果送到页面
	// localhost:8080/dyin/umc_list.action
	public String list() {
		list = dao.getUmcList();
		return "list";
	}
	
	//添加音乐
	public String add() {
		return "add";
	}
	

}
