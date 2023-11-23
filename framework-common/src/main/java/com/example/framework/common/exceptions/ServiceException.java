package com.example.framework.common.exceptions;

public class ServiceException extends RuntimeException {

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Exception e) {
		super(e);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}
