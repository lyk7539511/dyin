package com.common.dbaccessframework.transaction;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.*;

import com.common.dbaccessframework.core.DBConnection;

public class TransactionTemplate implements InvocationHandler {

	//
	private Object objOriginal;
	
	/**
	 * 
	 */
	public TransactionTemplate(Object objOriginal) {
		this.objOriginal = objOriginal;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object result=null;
		DBConnection dbCon=new DBConnection();
		Connection con=dbCon.getConnection();
		con.setAutoCommit(false);
		@SuppressWarnings("rawtypes")
		Class[] parms=new Class[1]; 
		parms[0]=java.sql.Connection.class;
		try{
			Method setMethod=this.objOriginal.getClass().getMethod("setCon",parms);
			setMethod.invoke(this.objOriginal,con);
			result=method.invoke(this.objOriginal, args);
			con.commit();
		}catch(Exception ex){
			con.rollback();
		}finally{
			dbCon.closeConnection(con);
		}
		return result;
	}

}
