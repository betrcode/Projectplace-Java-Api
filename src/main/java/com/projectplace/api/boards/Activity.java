package com.projectplace.api.boards;

import java.net.URL;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.projectplace.api.APISerializable;
import com.projectplace.api.helpers.DateTimeSerializer;
import com.projectplace.api.helpers.User;
import com.projectplace.api.posts.Comment.Attachment;

public final class Activity extends APISerializable {

	public static final class Creator extends User {
		public String type;
	}
	public static final class Assignee extends User {
		public String type;
		public boolean accepted;
	}
	
	public int board_id, comment_count, column_id, label_id, display_order, id;
	public Planlet planlet;
	public Assignee assignee;
	public String title, description;
	public URL direct_url;

	@JsonDeserialize(using=DateTimeSerializer.class)
	public Date created_time, due_date;
	
	@JsonProperty(value="all_attachments")
	public List<Attachment> attachments;
	
}
