package com.dyin.action;

import com.dyin.bean.User;

public class UserAction {
	private User bean;
	
	public User getBean() {
		return bean;
	}

	public void setBean(User bean) {
		this.bean = bean;
	}

	
	public void login() {
		System.out.println(bean.getUsername());
		System.out.println(bean.getPassword());
		
	}

}
