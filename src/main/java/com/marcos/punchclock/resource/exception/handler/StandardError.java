package com.marcos.punchclock.resource.exception.handler;

import java.io.Serializable;

public class StandardError implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer status;
	private String msg;
	private long timeStamp;
	
	public StandardError() {}

	public StandardError(Integer status, String msg, long timeStamp) {
		super();
		this.status = status;
		this.msg = msg;
		this.timeStamp = timeStamp;
	}

	public Integer getStatus() {
		return status;
	}

	public String getMsg() {
		return msg;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	
	

}
