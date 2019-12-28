package com.common.dbaccessframework.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IRowMapper<T>{
	
	public T mappingRow(ResultSet rs) throws SQLException;

}
