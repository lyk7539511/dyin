package com.common.dbaccessframework.util;

public class StringTool {
	
	public static String parseFirstUpperCase(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	
	public static String parseFirstLowerCase(String str) {
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}

	
	public static String removeGet(String methodName) {
		if (!methodName.startsWith("get"))
			return methodName;
		return StringTool.parseFirstLowerCase(methodName.substring(3));
	}

	
	public static String removeSet(String methodName) {
		if (!methodName.startsWith("set"))
			return methodName;
		return StringTool.parseFirstLowerCase(methodName.substring(3));
	}

	
	public static String addGet(String fieldName) {
		return "get" + StringTool.parseFirstUpperCase(fieldName);
	}

	
	public static String addSet(String fieldName) {
		return "set" + StringTool.parseFirstUpperCase(fieldName);
	}

}
