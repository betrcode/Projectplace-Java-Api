package com.projectplace.api;

import java.util.List;

import com.projectplace.api.project.Group;
import com.projectplace.api.project.Member;
import com.projectplace.oauth.Consumer;

public class Project extends BaseApi {

	Project(Consumer consumer) {
		super(consumer);
		// TODO Auto-generated constructor stub
	}
	
	public List<Member> getMembers(int projectId) {
		String url = String.format("/project/%s/members.json", projectId);
		return this.getAsList(url, Member.class);
	}
	
	public List<Group> getGroups(int projectId) {
		String url = String.format("/project/%s/groups.json", projectId);
		return this.getAsList(url, Group.class);
	}

}
