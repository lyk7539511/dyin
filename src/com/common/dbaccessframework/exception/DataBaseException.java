package com.common.dbaccessframework.exception;

public class DataBaseException extends RuntimeException{
	
	private static final long serialVersionUID = -7064299972354714582L;

	public DataBaseException(String message){
		super("SystemError:"+message);
	}
	
}
