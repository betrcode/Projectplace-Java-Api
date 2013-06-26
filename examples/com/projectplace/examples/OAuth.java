package com.projectplace.examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.projectplace.oauth.Consumer;
import com.projectplace.oauth.Provider;
import com.projectplace.oauth.Token;

public class OAuth {
	
	private static final String consumerKey = "xxxx";
	private static final String consumerSecret = "xxxx";

	public static void main(String[] args) throws IOException {
		Consumer consumer = new Consumer(consumerKey, consumerSecret);
		Provider provider = new Provider(consumer);
		
		System.out.println("Open this url in a browser: " + provider.getRequestTokenUrl());
		Token reqToken = provider.getRequestToken();
		System.out.println("Request token key: " + reqToken.key);
		System.out.println("Request token secret: " + reqToken.val);
		
		System.out.println("Paste the oauth verifer and press enter");
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String oauth_verifier = reader.readLine();
		Token accessToken = provider.getAccessToken(oauth_verifier);
		
		System.out.println("Access token key: " + accessToken.key);
		System.out.println("Access token secret: " + accessToken.val);
	}
}
