package com.bt.exception;

public class SmsException extends RuntimeException {
	
	private static final long serialVersionUID = -1167231300665647109L;
	
	private String smsCode;
	
	public SmsException(String smsCode, String message) {
		super(message);
		this.smsCode = smsCode;
	}

	public String getSmsCode() {
		return smsCode;
	}

}
