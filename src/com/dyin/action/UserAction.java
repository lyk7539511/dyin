package com.dyin.action;

import org.apache.commons.lang3.StringUtils;

import com.dyin.bean.UserBean;
import com.dyin.dao.UserDAO;

public class UserAction {
	private UserDAO dao = new UserDAO();
	private UserBean bean;
	private String msgString;
	
	public UserDAO getDao() {
		return dao;
	}

	public void setDao(UserDAO dao) {
		this.dao = dao;
	}
	
	public UserBean getBean() {
		return bean;
	}

	public void setBean(UserBean bean) {
		this.bean = bean;
	}
	
	public String getMsgString() {
		return msgString;
	}

	public void setMsgString(String msgString) {
		this.msgString = msgString;
	}
	
	public String login() {
		//用户有可能不输入用户名密码直接登录
		if (StringUtils.isBlank(bean.getUsername())) {	//isBlank()校验三种状态：null(不存在) 空   空格
			msgString = "请输入用户名";
			return "fail";
		}
		if (StringUtils.isBlank(bean.getPassword())) {
			msgString = "请输入密码";
			return "fail";
		}
		//去数据库查询该用户
		UserBean userBean = dao.getUserByUsernamePassword(bean);
		if (userBean == null) {	//没有找到
			msgString = "用户名或密码错误，请重输";
			return "fail";
		}
		return "succ";
		
	}

}
