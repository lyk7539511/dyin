package com.dyin.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.common.dbaccessframework.core.BaseDAO;
import com.common.dbaccessframework.core.IParamBinding;
import com.common.dbaccessframework.mapping.MysqlReflectMapping;
import com.dyin.bean.UserBean;

public class UserDAO extends BaseDAO<UserBean>{
	
	/*
	 * 传入bean，去数据库查询，如果查到了返回UserBean
	 * @return
	 */
	public UserBean getUserByUsernamePassword(UserBean bean) {
		String sqlString = "select * from tbl_user where username=? and password=?";
		
		
		List<UserBean> list = queryBySql(sqlString, new IParamBinding() {
			@Override
			//填充占位符
			public void bindParam(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, bean.getUsername());
				pstmt.setString(2, bean.getPassword());
				
			}
		}, new MysqlReflectMapping<UserBean>(UserBean.class));
		
//		if (list != null && !list.isEmpty()) {
//			return list.get(0);
//		}else {
//			return null;
//		}
		
		return (list != null && !list.isEmpty()) ? list.get(0) : null;
		
	}

}
