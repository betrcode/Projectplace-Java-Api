package com.projectplace.oauth;

public class Token {
	
	public static final Token INVALID_TOKEN = new Token(null, null);
	public final String key, val;
	
	public Token(String k, String v) {
		key = k;
		val = v;
	}
	
	@Override
	public String toString() {
		return String.format("Key: %s, Secret: %s", key, val); 
	}
	
	public static boolean isValid(Token t) {
		return t.key != null && t.val != null;
	}
	
}
