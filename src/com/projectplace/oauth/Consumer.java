package com.projectplace.oauth;

import oauth.signpost.basic.DefaultOAuthConsumer;

public class Consumer extends DefaultOAuthConsumer {

	private static final long serialVersionUID = -6609792477648216332L;

	public Consumer(String key, String secret) {
		super(key, secret);
	}

	public static Consumer instance(Token consumer) {
		if (Token.isValid(consumer)) {
			return new Consumer(consumer.key, consumer.val);
		}
		return null;
	}
	
	public static Consumer withAccessToken(Token consumer, Token access) {
		if (Token.isValid(access) && Token.isValid(consumer)) {
			Consumer c = new Consumer(consumer.key, consumer.val);
			c.setTokenWithSecret(access.key, access.val);
			return c;
		}
		return null;
	}
}
