package com.dyin.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.common.dbaccessframework.core.BaseDAO;
import com.common.dbaccessframework.core.IParamBinding;
import com.common.dbaccessframework.mapping.MysqlReflectMapping;
import com.dyin.bean.ImageBean;

public class ImageDAO extends BaseDAO<ImageBean>{
	public List<ImageBean> getImageList(int id) {	//此处的id是music的id
		
		String sqlString = "select * from tbl_image where mid=?";
		return queryBySql(sqlString, new IParamBinding() {
			
			@Override
			public void bindParam(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, id);
				
			}
		},new MysqlReflectMapping<ImageBean>(ImageBean.class));
	}
	
	public void addImage(ImageBean bean) {
		
		String sqlString = "insert into tbl_image(mid,image) value(?,?)";
		updateBySql(sqlString,new IParamBinding() {
			
			@Override
			public void bindParam(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, bean.getMid());
				pstmt.setString(2, bean.getImage());
				
			}
		});
		

	}
	public void delImage(int id) {
		String sqlString = "delete from tbl_image where id=?";
		updateBySql(sqlString,new IParamBinding() {
			
			@Override
			public void bindParam(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, id);
				
			}
		});

	}
}
