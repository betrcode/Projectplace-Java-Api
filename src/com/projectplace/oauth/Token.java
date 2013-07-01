package com.projectplace.oauth;

import com.projectplace.api.APISerializable;

public class Token extends APISerializable {
	
	public static final Token INVALID_TOKEN = new Token(null, null);
	public String key, val;
	
	public Token(String k, String v) {
		key = k;
		val = v;
	}
	
	public static boolean isValid(Token t) {
		return t.key != null && t.val != null && t.key != "null" && t.val != "null";
	}
	
}
