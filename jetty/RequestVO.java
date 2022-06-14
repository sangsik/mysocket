package com.lgcns.test;

public class RequestVO {
	private int QueueSize;
	private String Message;
	public int getQueueSize() {
		return QueueSize;
	}
	public void setQueueSize(int queueSize) {
		this.QueueSize = queueSize;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		this.Message = message;
	}
}
