package com.common.dbaccessframework.mapping;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.common.dbaccessframework.annotation.TableBean;
import com.common.dbaccessframework.util.StringTool;

public class MysqlReflectMapping<T> implements IRowMapper<T>{
	
	private Class<T> beanClass;

	public MysqlReflectMapping(Class<T> beanClass) {
		super();
		this.beanClass = beanClass;
	}

	
	private void setBeanValues(Object obj, ResultSet rs) throws SQLException {
		Method[] methods = obj.getClass().getDeclaredMethods();
		for (Method m : methods) {
			String methodName = m.getName();
			if (methodName.startsWith("set")
					&& m.getParameterTypes().length == 1) {


				TableBean table=m.getAnnotation(TableBean.class);
				String fieldName=null;
				if(table==null){
					
					fieldName = StringTool.removeSet(methodName);
					try {
						if (m.getParameterTypes()[0].getName().equals(
							"java.sql.Date"))
							m.invoke(obj, rs.getDate(fieldName));
						else if(m.getParameterTypes()[0].getName().equals(
							"int"))
							m.invoke(obj, rs.getInt(fieldName));
						else if(m.getParameterTypes()[0].getName().equals(
							"long"))
							m.invoke(obj, rs.getLong(fieldName));
						else if(m.getParameterTypes()[0].getName().equals(
							"double"))
							m.invoke(obj, rs.getDouble(fieldName));
						else if(m.getParameterTypes()[0].getName().equals(
							"float"))
							m.invoke(obj, rs.getFloat(fieldName));
						else
							m.invoke(obj, rs.getString(fieldName));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					
					boolean flag=table.ignore();
					fieldName=table.fieldName();
					if(!flag){
						
						if("default".equals(fieldName)){
							
							fieldName = StringTool.removeSet(methodName);
						}
						try {
							if (m.getParameterTypes()[0].getName().equals(
								"java.sql.Date"))
								m.invoke(obj, rs.getDate(fieldName));
							else if(m.getParameterTypes()[0].getName().equals(
								"int"))
								m.invoke(obj, rs.getInt(fieldName));
							else if(m.getParameterTypes()[0].getName().equals(
								"long"))
								m.invoke(obj, rs.getLong(fieldName));
							else if(m.getParameterTypes()[0].getName().equals(
								"double"))
								m.invoke(obj, rs.getDouble(fieldName));
							else
								m.invoke(obj, rs.getString(fieldName));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}

			}
		}
	}

	public T mappingRow(ResultSet rs) throws SQLException {
		T obj=null;
		try {
			obj = this.beanClass.newInstance();
			this.setBeanValues(obj, rs);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return obj;
	}

}
