package com.dyin.action;

import org.apache.commons.lang3.StringUtils;

import com.dyin.bean.UserBean;
import com.dyin.dao.UserDAO;

public class UserAction {
	private UserDAO dao = new UserDAO();
	private UserBean bean;
	
	public UserBean getBean() {
		return bean;
	}

	public void setBean(UserBean bean) {
		this.bean = bean;
	}

	
	public void login() {
		System.out.println(bean.getUsername());
		System.out.println(bean.getPassword());
		//用户有可能不输入用户名密码直接登录
		if (StringUtils.isBlank(bean.getUsername())) {
			System.out.println("请输入用户名");
			return;
		}
		if (StringUtils.isBlank(bean.getPassword())) {
			System.out.println("请输入密码");
			return;
		}
		//去数据库查询该用户
		UserBean userBean = dao.getUserByUsernamePassword(bean);
		if (userBean == null) {	//没有找到
			System.out.println("用户名或密码错误，请重新输入");
		}else {
			System.out.println("登录成功");
		}
		
	}

}
