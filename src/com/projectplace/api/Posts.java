package com.projectplace.api;

import java.util.List;

import com.projectplace.api.posts.Collection;
import com.projectplace.api.posts.Post;
import com.projectplace.oauth.Consumer;

public class Posts extends BaseApi {

	Posts(Consumer consumer) {
		super(consumer);
	}
	
	class PostsCursor implements Cursor<Post> {
		private final Posts api;
		private String nextUrl;
		private int limit = 20;
		private final int projectId;
		
		public PostsCursor(Posts api, int projectId) {
			this.api = api;
			this.projectId = projectId;
		}
		
		@Override
		public void setLimit(int count) {
			limit = count;
		}
		@Override
		public List<Post> next() {
			String url = nextUrl;
			if (url == null) {
				url = String.format("/2/conversation/project/%d/posts.json?count=%d", projectId, limit);
			}
			Collection posts = api.get(url, Collection.class);
			nextUrl = posts.pagination.next_url;
			return posts.data;
		}
	}

	public final Cursor<Post> get(int project_id) {
		return new PostsCursor(this, project_id);
	}
}
