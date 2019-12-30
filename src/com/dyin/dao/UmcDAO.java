package com.dyin.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.common.dbaccessframework.core.BaseDAO;
import com.common.dbaccessframework.core.IParamBinding;
import com.common.dbaccessframework.mapping.MysqlReflectMapping;
import com.dyin.bean.UmcBean;

public class UmcDAO extends BaseDAO<UmcBean> {
	
	//查询
	public List<UmcBean> getUmcList() {
		String sqlString = "select * from tbl_umc";
		return queryBySql(sqlString, new MysqlReflectMapping<UmcBean>(UmcBean.class));
		
	}
	//增加你的音乐
	public void addUmc(UmcBean bean) {
		String sqlString = "insert into tbl_umc(title,author,image,video) value(?,?,?,?)";
		updateBySql(sqlString,new IParamBinding() {
			
			@Override
			public void bindParam(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, bean.getTitle());
				pstmt.setString(2, bean.getAuthor());
				pstmt.setString(3, bean.getImage());
				pstmt.setString(4, bean.getVideo());
				
			}
		});
		
	}
	//删除你的音乐,按主键
	public void delUmc(int id) {
		String sqlString = "delete from tbl_umc where id=?";
		updateBySql(sqlString,new IParamBinding() {
			
			@Override
			public void bindParam(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, id);
				
			}
		});
	}

}
