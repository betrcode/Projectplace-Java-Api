package com.projectplace.api.project;

import java.util.List;

import com.projectplace.api.APIResponse;

public final class Group extends APIResponse {
	
	public String name, description;
	public int project_id, id;
	public List<Integer> members;

}
