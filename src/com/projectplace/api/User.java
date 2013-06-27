package com.projectplace.api;

import java.net.URL;
import java.util.List;

import com.projectplace.api.user.Assignment;
import com.projectplace.api.user.Profile;
import com.projectplace.api.user.Project;
import com.projectplace.oauth.Consumer;

public class User extends BaseApi {

	User(Consumer consumer) {
		super(consumer);
	}
	
	public Profile getMyProfile() {
		return this.get("/user/me/profile.json", Profile.class);
	}
	
	public Profile getProfile(int userId) {
		String url = String.format("/user/%s/profile.json", userId);
		return this.get(url, Profile.class);
	}

	public List<Project> getMyProjects() {
		return this.getAsList("/user/me/projects.json", Project.class);
	}
	
	public List<Project> getProjects(int userId) {
		String url = String.format("/user/%s/projects.json", userId);
		return this.getAsList(url, Project.class);
	}
	
	public URL getAvatar(int userId) {
		Consumer consumer = getConsumer();
		String url = String.format("/avatar/%s/%s", userId, consumer.getToken());
		try {
			return new URL(API_BASE_PATH + url);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public List<Assignment> getMyAssignments() {
		return this.getAsList("/user/me/assignments.json", Assignment.class);
	}

}
