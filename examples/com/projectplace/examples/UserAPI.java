package com.projectplace.examples;

import com.projectplace.api.ApiBuilder;
import com.projectplace.api.User;
import com.projectplace.api.user.Profile;
import com.projectplace.oauth.Consumer;
import com.projectplace.oauth.Token;

public class UserAPI {
	private static final String consumerKey = "xxxx";
    private static final String consumerSecret = "xxxx";
    private static final String accessKey = "xxxx";
    private static final String accessSecret = "xxxx";
    private static final int otherUserId = 1234;
    
	public static void main(String[] args) {
		Token consumerToken = new Token(consumerKey, consumerSecret);
		Token accessToken = new Token(accessKey, accessSecret);
		Consumer consumer = Consumer.withAccessToken(consumerToken, accessToken);
		ApiBuilder api = ApiBuilder.useConsumer(consumer);
		User user = api.service(User.class);

		Profile profile = user.getMyProfile();
		System.out.println("My profile:");
		System.out.println(profile);
		
		System.out.println("Others profile:");
		System.out.println(user.getProfile(otherUserId));
		
		System.out.println("My projects:");
		System.out.println(user.getMyProjects());
		
		// the other user should have authorized your application else it would be a 403
		System.out.println("User projects:");
		System.out.println(user.getProjects(otherUserId));
		
		System.out.println("User avatar link:");
		System.out.println(user.getAvatar(otherUserId));
		
		System.out.println("My assignments:");
		System.out.println(user.getMyAssignments());
	}

}
