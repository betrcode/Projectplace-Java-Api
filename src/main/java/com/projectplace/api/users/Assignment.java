package com.projectplace.api.users;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.projectplace.api.APISerializable;

public final class Assignment extends APISerializable {

	public static final class Assignee {
		public String name;
		public int id;
	}
	
	public static final class Container {
		public int access, id;
		public String type;
	}
	
	public String project_name, name, type;
	public int project_id, id;
	public Assignee assignee;
	public String due_date;
	public String due_time;
	
	public Date getDueDate() {
		try {
			if (due_date == "") {
				return null;
			}
			due_time = due_time == ""? "00:00": due_time;
			return new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(due_date + " " + due_time);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
