package com.projectplace.oauth;

import com.projectplace.api.BaseApi;

import oauth.signpost.OAuth;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;

public class Provider extends DefaultOAuthProvider {
	private static final long serialVersionUID = 3552221693058430838L;
	private final DefaultOAuthConsumer consumer;
	private Token reqToken;

	public Provider(DefaultOAuthConsumer consumer) {
		super(BaseApi.API_BASE_PATH + "/initiate",
				BaseApi.API_BASE_PATH + "/token",
				BaseApi.API_BASE_PATH + "/authorize");
		this.consumer = consumer;
	}
	
	public String getRequestTokenUrl() {
		try {
			String url = this.retrieveRequestToken(consumer, OAuth.OUT_OF_BAND);
			reqToken = new Token(consumer.getToken(), consumer.getTokenSecret());
			return url;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Token getRequestToken() {
		return reqToken;
	}
	
	public Token getAccessToken(String verifierCode) {
		try {
			this.retrieveAccessToken(consumer, verifierCode);
			return new Token(consumer.getToken(), consumer.getTokenSecret());
		} catch (Exception e) {
			e.printStackTrace();
			return Token.INVALID_TOKEN;
		}
	}
}
