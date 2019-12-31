package com.dyin.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.common.dbaccessframework.core.BaseDAO;
import com.common.dbaccessframework.core.IParamBinding;
import com.common.dbaccessframework.mapping.MysqlReflectMapping;
import com.dyin.bean.StarBean;

public class StarDAO extends BaseDAO<StarBean>{
	
	public List<StarBean> getStarList() {
		String sqlString = "select * from tbl_star";
		return queryBySql(sqlString,new MysqlReflectMapping<StarBean>(StarBean.class));
	}
	
	public void addStar(StarBean bean) {
		
		String sqlString = "insert into tbl_star(name,image) value(?,?)";
		updateBySql(sqlString,new IParamBinding() {
			
			@Override
			public void bindParam(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, bean.getName());
				pstmt.setString(2, bean.getImage());
				
			}
		});
		

	}
	public void delStar(int id) {
		String sqlString = "delete from tbl_star where id=?";
		updateBySql(sqlString,new IParamBinding() {
			
			@Override
			public void bindParam(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, id);
				
			}
		});

	}

}
