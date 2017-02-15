package com.config;

public class Response {

	private static final String OK = "ok";
	private static final String ERROR = "error";

	private Object data;

	private boolean success;
	private String message;
	private int code;

	public Response success() {
		this.message = OK;
		this.success = true;
		return this;
	}

	public Response success(Object data) {
		this.data = data;
		this.message = OK;
		this.success = true;
		return this;
	}

	public Response failure() {
		this.message = ERROR;
		this.success = false;
		return this;
	}

	public Response failure(String message) {
		this.message = message;
		this.success = false;
		return this;
	}

	public Response failure(String message, int code) {
		this.code = code;
		this.message = message;
		this.success = false;
		return this;
	}

	public Object getData() {
		return data;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public int getCode() {
		return code;
	}

}