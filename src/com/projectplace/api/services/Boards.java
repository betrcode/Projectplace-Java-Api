package com.projectplace.api.services;

import java.util.List;

import com.projectplace.api.BaseApi;
import com.projectplace.api.boards.Activity;
import com.projectplace.api.boards.Board;
import com.projectplace.oauth.Consumer;

public class Boards extends BaseApi {

	public Boards(Consumer consumer) {
		super(consumer);
	}
	
	public List<Board> active(int projectId) {
		String url = String.format("/1/project/%d/boards.json", projectId);
		return getAsList(url, Board.class);
	}
	
	public List<Board> archived(int projectId) {
		String url = String.format("/1/project/%d/archived-boards.json", projectId);
		return getAsList(url, Board.class);
	}
	
	public Board properties(int boardId) {
		String url = String.format("/1/board/%d/properties.json", boardId);
		return get(url, Board.class);
	}
	
	public List<Activity> activities(int boardId) {
		String url = String.format("/1/board/%d/activities.json", boardId);
		return getAsList(url, Activity.class);
	}
}
