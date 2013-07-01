package com.projectplace.api.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.projectplace.api.BaseApi;
import com.projectplace.api.Cursor;
import com.projectplace.api.Volatile;
import com.projectplace.api.posts.Collection;
import com.projectplace.api.posts.Comment;
import com.projectplace.api.posts.Like;
import com.projectplace.api.posts.Post;
import com.projectplace.api.posts.Unlike;
import com.projectplace.oauth.Consumer;

public class Posts extends BaseApi {

	Posts(Consumer consumer) {
		super(consumer);
	}
	
	class PostsCursor implements Cursor<Post> {
		private final Posts api;
		private String nextUrl;
		private String prevUrl;
		private int limit = 20;
		
		public PostsCursor(Posts api, int projectId) {
			this.api = api;
			prevUrl = String.format("/2/conversation/project/%d/posts.json?count=%d", projectId, limit);
		}
		
		@Override
		public void setLimit(int count) {
			limit = count;
		}
		
		private Collection getCollection(String url) {
			Collection coll = api.get(url, Collection.class);
			prevUrl = coll.pagination.prev_url;
			nextUrl = coll.pagination.next_url;
			return coll;
		}
		
		@Override
		public List<Post> next() {
			// here next means to move towards oldest
			return getCollection(prevUrl).data;
		}

		@Override
		public List<Post> prev() {
			// here prev means to move towards latest
			return getCollection(nextUrl).data;
		}
	}

	public final Cursor<Post> get(int project_id) {
		return new PostsCursor(this, project_id);
	}

	@Volatile
	public final Like like(String postId, int userId) {
		Map<String, String> params = new HashMap<String, String>();
		String url = String.format("/2/conversation/post/%s/user/%d/like.json", postId, userId);
		return this.post(url, params, Like.class); 
	}

	@Volatile
	public final Unlike unlike(String postId, int userId) {
		String url = String.format("/2/conversation/post/%s/user/%d/unlike.json", postId, userId);
		return this.post(url, new HashMap<String, String>(), Unlike.class);
	}
	
	@Volatile
	public final Post create(Post post) {
		String url = String.format("/2/conversation/project/%d/post/create.json", post.project_id);
		Map<String, String> params = new HashMap<String, String>();
		params.put("text", post.text);
		params.put("sent_from", "java-sdk");
		params.put("artifact_name", "wall");
		return this.post(url, params, Post.class);
	}
	
	@Volatile
	public final Comment comment(Comment comm) {
		String url = String.format("/2/conversation/post/%s/comment/create.json", comm.post_id);
		Map<String, String> params = new HashMap<String, String>();
		params.put("sent_from", "java-sdk");
		params.put("text", comm.text);
		return this.post(url, params, Comment.class);
	}
}
