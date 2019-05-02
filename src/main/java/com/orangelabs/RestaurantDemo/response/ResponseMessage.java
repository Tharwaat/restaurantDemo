package com.orangelabs.RestaurantDemo.response;

public class ResponseMessage {
	private String message;

	public ResponseMessage() {
		
	}
	
	public ResponseMessage(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ResponseMessage [message=" + message + "]";
	}
	
}
