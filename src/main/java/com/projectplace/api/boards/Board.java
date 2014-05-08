package com.projectplace.api.boards;

import java.util.List;

import com.projectplace.api.APISerializable;

public final class Board extends APISerializable {
	
	public int activity_count, attachment_container_id, done_count, id, project_id, project_step;
	public boolean is_archived;
	public String name;
	
	public List<Label> labels;
	public List<Progress> progresses;

}
