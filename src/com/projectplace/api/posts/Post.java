package com.projectplace.api.posts;


public class Post extends Comment {
	public int project_id;
	
	public static final Post create(int projectId) {
		Post post = new Post();
		post.project_id = projectId;
		return post;
	}
}
