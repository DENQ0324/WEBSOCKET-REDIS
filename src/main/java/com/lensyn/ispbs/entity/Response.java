package com.lensyn.ispbs.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;

public class Response<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Meta meta;
	private T data;
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean checkState() {
		return Boolean.valueOf(this.meta.getCode() == ResponseState.SUCCESS.getCode());
	}

	public Response<T> success() {
		this.meta = new Meta(ResponseState.SUCCESS);
		return this;
	}

	public Response<T> warning() {
		this.meta = new Meta(ResponseState.WARNING);
		return this;
	}

	public Response<T> success(T data) {
		success();
		this.data = data;
		return this;
	}

	public Response<T> message(String message) {
		getMeta().setMessage(message);
		return this;
	}

	public Response<T> data(T data) {
		this.data = data;
		return this;
	}

	@JsonIgnore
	public boolean isSuccess() {
		return getMeta().getCode() == ResponseState.SUCCESS.getCode();
	}

	public Response<T> failure() {
		this.meta = new Meta(ResponseState.FAIL);
		return this;
	}

	public Response<T> failure(String message) {
		this.meta = new Meta(ResponseState.FAIL, message);
		return this;
	}

	public Response<T> failure(T data, String message) {
		failure(message);
		this.data = data;
		return this;
	}

	public Meta getMeta() {
		return this.meta;
	}

	public T getData() {
		return this.data;
	}

	public Response<T> setData(T data) {
		this.data = data;
		return this;
	}

	public static class Meta implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int code;
		private String message;

		public Meta() {
		}

		public Meta(ResponseState responseState) {
			setCode(responseState.getCode());
			this.message = responseState.getCodeInfo();
		}

		public Meta(ResponseState responseState, String message) {
			if (message == null) {
				message = responseState.getCodeInfo();
			}
			this.message = message;
		}

		public int getCode() {
			return this.code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		public void setCode(ResponseState state) {
			this.code = state.getCode();
		}

		public String getMessage() {
			return this.message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}
}

enum ResponseState implements Serializable {
	FAIL(0, "失败"), SUCCESS(1, "成功"), WARNING(2, "警告");

	private int code;
	private String codeInfo;

	private ResponseState(int code, String codeInfo) {
		this.code = code;
		this.codeInfo = codeInfo;
	}

	public int getCode() {
		return this.code;
	}

	public String getCodeInfo() {
		return this.codeInfo;
	}

	public String toString() {
		return super.toString().toLowerCase();
	}

	public static ResponseState byCode(int code) {
		ResponseState[] states = values();
		for (int i = 0; i < states.length; i++) {
			ResponseState state = states[i];
			if (state.getCode() == code) {
				return state;
			}
		}
		return FAIL;
	}
}