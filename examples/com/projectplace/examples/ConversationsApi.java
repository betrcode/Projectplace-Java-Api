package com.projectplace.examples;

import java.util.List;

import com.projectplace.api.ApiBuilder;
import com.projectplace.api.Cursor;
import com.projectplace.api.posts.Comment;
import com.projectplace.api.posts.Post;
import com.projectplace.api.services.Posts;
import com.projectplace.oauth.Consumer;
import com.projectplace.oauth.Token;

public class ConversationsApi {

	private static final String consumerKey = "xxxx";
    private static final String consumerSecret = "xxxx";
    private static final String accessKey = "xxxx";
    private static final String accessSecret = "xxxx";
    private static final int projectId = 1234;
    
    public static void main(String[] args) {
    	Token consumerToken = new Token(consumerKey, consumerSecret);
		Token accessToken = new Token(accessKey, accessSecret);
		Consumer consumer = Consumer.withAccessToken(consumerToken, accessToken);
		ApiBuilder api = ApiBuilder.useConsumer(consumer);
		Posts posts = api.service(Posts.class);
		Post first = Post.create(projectId);
		first.text = "example post from java-sdk";
		first = posts.create(first);
		
		System.out.println(first);
		Comment com = Comment.create(first.id);
		com.text = "from api";
		com = posts.comment(com);
		System.out.println(com);
		
		Cursor<Post> allPosts = posts.get(projectId);
		allPosts.setLimit(10);
		List<Post> top10 = allPosts.next();
		System.out.println(top10.get(0));
		
    }
}
