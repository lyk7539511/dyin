package com.common.dbaccessframework.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.common.dbaccessframework.exception.DataBaseException;
import com.common.dbaccessframework.mapping.IRowMapper;
import com.common.dbaccessframework.util.PageModel;

public class BaseDAO<T> {

	private DBConnection dbConn = new DBConnection();
	private Logger logs = Logger.getLogger(BaseDAO.class);

	public void del(String sql, String keyName, String values) {
		StringBuffer sb = new StringBuffer();
		sb.append(sql).append(keyName).append(" in (" + values + ")");
		updateBySql(sb.toString());
	}

	public int updateBySql(String sql) {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = dbConn.getConnection();
			stmt = conn.createStatement();
			return stmt.executeUpdate(sql);
		} catch (SQLException e) {
			throw new DataBaseException(e.getMessage());
		} finally {
			dbConn.closeStatment(stmt);
			dbConn.closeConnection(conn);
		}
	}

	public int updateBySql(String sql, IParamBinding bind) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			bind.bindParam(pstmt);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			logs.error(sql, e);
			throw new DataBaseException(e.getMessage());
		} finally {
			dbConn.closeStatment(pstmt);
			dbConn.closeConnection(conn);
		}
	}

	public List<T> queryBySql(String sql, IRowMapper<T> mapper) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<T> retList = new ArrayList<T>();
		try {
			conn = dbConn.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				T obj = mapper.mappingRow(rs);
				retList.add(obj);
			}
		} catch (SQLException e) {
			logs.error(e);
			throw new DataBaseException(e.getMessage());
		} finally {
			dbConn.closeResultSet(rs);
			dbConn.closeStatment(stmt);
			dbConn.closeConnection(conn);
		}
		return retList;
	}

	public List<T> queryBySql(String sql, IParamBinding bind, IRowMapper<T> mapper) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<T> retList = new ArrayList<T>();
		try {
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			bind.bindParam(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				T obj = mapper.mappingRow(rs);
				retList.add(obj);
			}
		} catch (SQLException e) {
			throw new DataBaseException(e.getMessage());
		} finally {
			dbConn.closeResultSet(rs);
			dbConn.closeStatment(pstmt);
			dbConn.closeConnection(conn);
		}
		return retList;
	}

	public PageModel<T> split(String sql_list, String sql_count, int curpage, int pagesize, IParamBinding bind, IRowMapper<T> mapper) {
		PageModel<T> page = new PageModel<T>();
		List<T> list = getList(sql_list, curpage, pagesize, bind, mapper);
		int count = getCount(sql_count, bind);
		page.setRetList(list);
		page.setCurPage(curpage);
		page.setPageSize(pagesize);
		page.setItemCount(count);
		int totalPage = getPageNum(count, pagesize);
		page.setTotalPage(totalPage);
		return page;
	}

	public PageModel<T> split(String sql_list, String sql_count, int curpage, int pagesize, IRowMapper<T> mapper) {
		PageModel<T> page = new PageModel<T>();
		List<T> list = getList(sql_list, curpage, pagesize, mapper);
		int count = getCount(sql_count);
		page.setRetList(list);
		page.setCurPage(curpage);
		page.setPageSize(pagesize);
		page.setItemCount(count);
		int totalPage = getPageNum(count, pagesize);
		page.setTotalPage(totalPage);
		return page;
	}

	private List<T> getList(String sql_list, int curpage, int pagesize, IParamBinding bind, IRowMapper<T> mapper) {
		String sql = sql_list + " limit " + (curpage - 1) * pagesize + "," + pagesize;
		return queryBySql(sql, bind, mapper);
	}

	private List<T> getList(String sql_list, int curpage, int pagesize, IRowMapper<T> mapper) {
		String sql = sql_list + " limit " + (curpage - 1) * pagesize + "," + pagesize;
		return queryBySql(sql, mapper);
	}

	private int getPageNum(int count, int pagesize) {
		int num = count % pagesize;
		if (count == 0) {
			return 1;
		} else if (num == 0) {
			return count / pagesize;
		} else {
			return count / pagesize + 1;
		}
	}

	private int getCount(String sql_count) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(sql_count);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new DataBaseException(e.getMessage());
		} finally {
			dbConn.closeResultSet(rs);
			dbConn.closeStatment(pstmt);
			dbConn.closeConnection(conn);
		}
		return count;
	}

	private int getCount(String sql_count, IParamBinding bind) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(sql_count);
			bind.bindParam(pstmt);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new DataBaseException(e.getMessage());
		} finally {
			dbConn.closeResultSet(rs);
			dbConn.closeStatment(pstmt);
			dbConn.closeConnection(conn);
		}
		return count;
	}

	public boolean executeBatch(String[] sqls) {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			for (int i = 0; i < sqls.length; i++) {
				stmt.addBatch(sqls[i]);
			}
			stmt.executeBatch();
			return true;
		} catch (SQLException e) {
			dbConn.rollbackTrans(conn);
			logs.error(e);
			return false;
		} finally {
			dbConn.commitTrans(conn);
			dbConn.closeStatment(stmt);
			dbConn.closeConnection(conn);
		}
	}

}
