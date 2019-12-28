package com.common.dbaccessframework.mapping;

import java.sql.CallableStatement;
import java.sql.SQLException;

public interface ProcRowMapper<T>{

	public T mappingRow(CallableStatement cs) throws SQLException;
}
