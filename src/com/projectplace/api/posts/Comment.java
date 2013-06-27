package com.projectplace.api.posts;

import java.net.URL;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.projectplace.api.APISerializable;

public class Comment extends APISerializable {
	public String id, text, sent_from;
	
	@JsonDeserialize(using=DateTimeSerializer.class)
	public Date created_time_local_iso;
	public static final class Mentioned extends APISerializable {
		public int id;
		public String name, type;
	}
	
	public static final class User extends APISerializable {
		public int id;
		public String first_name, last_name;
		public URL image;
	}
	
	public static final class Attachment extends APISerializable {
		public int id;
		public String name, mime_type;
		
	}
	
	public List<Mentioned> mentioned_names;
	public User creator;
	public List<User> like;
	public List<Attachment> attachments;
}
