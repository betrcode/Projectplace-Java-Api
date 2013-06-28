package com.projectplace.api.boards;

import java.net.URL;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.projectplace.api.APISerializable;
import com.projectplace.api.helpers.DateTimeSerializer;
import com.projectplace.api.posts.Comment.Attachment;

public final class Activity extends APISerializable {

	public static final class Creator {
		public String type, name;
		public int id;
	}
	public static final class Assignee {
		public String type, name;
		public int id;
		public boolean accepted;
	}
	
	public int board_id, comment_count, column_id, label_id, display_order, id;
	public Planlet planlet;
	public Assignee assignee;
	public String title, description;
	public URL direct_url;
	@JsonDeserialize(using=DateTimeSerializer.class)
	public Date created_time, due_date;
	public List<Attachment> attachments;
	
}
