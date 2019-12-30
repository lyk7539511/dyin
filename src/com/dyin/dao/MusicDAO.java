package com.dyin.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.common.dbaccessframework.core.BaseDAO;
import com.common.dbaccessframework.core.IParamBinding;
import com.common.dbaccessframework.mapping.MysqlReflectMapping;
import com.dyin.bean.MusicBean;

public class MusicDAO extends BaseDAO<MusicBean> {

	public List<MusicBean> getMusicList() {
		String sqlString = "select * from tbl_music";
		return queryBySql(sqlString,new MysqlReflectMapping<MusicBean>(MusicBean.class));
	}
	
	public void addMusic(MusicBean bean) {
		
		String sqlString = "insert into tbl_music(music,author,image) value(?,?,?)";
		updateBySql(sqlString,new IParamBinding() {
			
			@Override
			public void bindParam(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, bean.getMusic());
				pstmt.setString(2, bean.getAuthor());
				pstmt.setString(3, bean.getImage());
				
			}
		});
		

	}
	public void delMusic(int id) {
		String sqlString = "delete from tbl_music where id=?";
		updateBySql(sqlString,new IParamBinding() {
			
			@Override
			public void bindParam(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, id);
				
			}
		});

	}
}
