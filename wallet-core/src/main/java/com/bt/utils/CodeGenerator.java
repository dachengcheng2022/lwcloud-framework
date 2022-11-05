package com.bt.utils;

import java.util.Random;

public class CodeGenerator {

	private static Random r = new Random();
	
	private static String[] codeArr = new String[] {"0","1","2","3","4","5","6","7","8","9","a","b","c","d",
			"e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	
	public static String getSmsCode(int len) {
		StringBuffer smsCode = new StringBuffer();
		for (int i=0;i<len;i++) {
			smsCode.append(codeArr[r.nextInt(10)]);
		}
		return smsCode.toString();
	}

	public static String getInviteCode(int len) {
		StringBuffer smsCode = new StringBuffer();
		for (int i=0;i<len;i++) {
			smsCode.append(codeArr[r.nextInt(36)]);
		}
		return smsCode.toString();
	}
}
