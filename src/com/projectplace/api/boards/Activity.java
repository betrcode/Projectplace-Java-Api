package com.projectplace.api.boards;

import java.net.URL;
import java.util.Date;

import com.projectplace.api.APISerializable;

public final class Activity extends APISerializable {

	public int board_id, comment_count, column_id, assignee, label_id, display_order, id;
	public String title, description;
	public URL direct_url;
	public Date created_time;
	
}
