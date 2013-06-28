package com.projectplace.api.services;

import java.net.URL;
import java.util.List;

import com.projectplace.api.BaseApi;
import com.projectplace.api.users.Assignment;
import com.projectplace.api.users.Profile;
import com.projectplace.api.users.Project;
import com.projectplace.oauth.Consumer;

public class Users extends BaseApi {

	Users(Consumer consumer) {
		super(consumer);
	}
	
	public Profile getMyProfile() {
		return this.get("/1/user/me/profile.json", Profile.class);
	}
	
	public Profile getProfile(int userId) {
		String url = String.format("/1/user/%s/profile.json", userId);
		return this.get(url, Profile.class);
	}

	public List<Project> getMyProjects() {
		return this.getAsList("/1/user/me/projects.json", Project.class);
	}
	
	public List<Project> getProjects(int userId) {
		String url = String.format("/1/user/%s/projects.json", userId);
		return this.getAsList(url, Project.class);
	}
	
	public URL getAvatar(int userId) {
		Consumer consumer = getConsumer();
		String url = String.format("/1/avatar/%s/%s", userId, consumer.getToken());
		try {
			return new URL(API_BASE_PATH + url);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public List<Assignment> getMyAssignments() {
		return this.getAsList("/1/user/me/assignments.json", Assignment.class);
	}

}
