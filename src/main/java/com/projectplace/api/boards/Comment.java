package com.projectplace.api.boards;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.projectplace.api.APISerializable;
import com.projectplace.api.helpers.DateTimeSerializer;
import com.projectplace.api.helpers.User;

public final class Comment extends APISerializable {

	public static final class Author extends User {
		public String avatar_source;
	}
	
	public int activity_id, id;
	public String text;

	@JsonDeserialize(using=DateTimeSerializer.class)
	public Date create_time;
	
	public boolean may_delete;
}
