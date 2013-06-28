package com.projectplace.api.projects;

import java.util.List;

import com.projectplace.api.APISerializable;

public final class Group extends APISerializable {
	
	public String name, description;
	public int project_id, id;
	public List<Integer> members;

}
