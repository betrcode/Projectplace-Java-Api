package com.projectplace.api.services;

import java.util.List;

import com.projectplace.api.BaseApi;
import com.projectplace.api.projects.Group;
import com.projectplace.api.projects.Member;
import com.projectplace.oauth.Consumer;

public class Projects extends BaseApi {

	Projects(Consumer consumer) {
		super(consumer);
	}
	
	public List<Member> getMembers(int projectId) {
		String url = String.format("/1/project/%s/members.json", projectId);
		return this.getAsList(url, Member.class);
	}
	
	public List<Group> getGroups(int projectId) {
		String url = String.format("/1/project/%s/groups.json", projectId);
		return this.getAsList(url, Group.class);
	}

}
