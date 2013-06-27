package com.projectplace.examples;

import java.util.List;

import com.projectplace.api.ApiBuilder;
import com.projectplace.api.Project;
import com.projectplace.api.project.Group;
import com.projectplace.api.project.Member;
import com.projectplace.oauth.Consumer;
import com.projectplace.oauth.Token;

public class ProjectAPI {
	
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
		Project projectApi = api.service(Project.class);
		
		List<Member> members = projectApi.getMembers(projectId);
		System.out.println(String.format("There are %d members in the project %d", members.size(), projectId));
		System.out.println("Raw response:");
		System.out.println(members);
		
		List<Group> groups = projectApi.getGroups(projectId);
		System.out.println(String.format("There are %d groups in the project %d", groups.size(), projectId));
		System.out.println("Raw response:");
		System.out.println(groups);
		
	}
}
