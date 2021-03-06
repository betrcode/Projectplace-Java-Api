package com.projectplace.api.posts;

import java.net.URL;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.projectplace.api.APISerializable;
import com.projectplace.api.helpers.DateTimeSerializer;

public class Comment extends APISerializable {
	public String id, text, sent_from;
	public String post_id;
	
	@JsonDeserialize(using=DateTimeSerializer.class)
	@JsonProperty(value="created_time_local_iso")
	public Date created_time;

	public static final class Mentioned extends com.projectplace.api.helpers.User {
		public String type;
	}
	
	public static final class User extends com.projectplace.api.helpers.User {
		public String first_name, last_name;
		public URL image;
	}
	
	public static final class Attachment extends APISerializable {
		public int id;
		public String name, mime_type;
		@JsonProperty(value="url")
		public String path;
		
	}
	
	public static final class Link extends APISerializable {
		public String link, favicon_url;
		public String title;
		public int video_id;
	}
	
	public List<Mentioned> mentioned_names;
	public User creator;
	public List<User> like;
	public List<Attachment> attachments;
	
	public List<String> tags;
	public List<Link> links;
	
	public static final Comment create(String postId) {
		Comment cmt = new Comment();
		cmt.post_id = postId;
		return cmt;
	}
}
