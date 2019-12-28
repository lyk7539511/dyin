package com.common.dbaccessframework.core;

import java.sql.CallableStatement;
import java.sql.SQLException;



public interface ProcParamBinding{

	public void bindParam(CallableStatement pstmt) throws SQLException; 
}
