package com.dyin.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.common.dbaccessframework.core.BaseDAO;
import com.common.dbaccessframework.core.IParamBinding;
import com.common.dbaccessframework.mapping.MysqlReflectMapping;
import com.dyin.bean.VideoBean;

public class VideoDAO extends BaseDAO<VideoBean> {
	
	
	//查询背景视频路径
	public String getVideo() {
		String sqlString = "select * from tbl_video";
		List<VideoBean> list = queryBySql(sqlString, new MysqlReflectMapping<VideoBean>(VideoBean.class));
		return (list!=null && !list.isEmpty()) ? list.get(0).getVideo() : null;
	}

	public void updateVideo(String video) {
		String sqlString;
		if (getVideo() == null) {
			sqlString = "insert into tbl_video(video) value(?)";	//数据库中不存在视频路径，执行插入
		}else {
			sqlString = "update tbl_video set video=?";		//数据库中存在视频路径，更新视频
		}
		updateBySql(sqlString, new IParamBinding() {

			@Override
			public void bindParam(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, video);

			}
		});

	}
}
