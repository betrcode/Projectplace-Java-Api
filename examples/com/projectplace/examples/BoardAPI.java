package com.projectplace.examples;

import java.util.List;

import com.projectplace.api.ApiBuilder;
import com.projectplace.api.boards.Activity;
import com.projectplace.api.boards.Board;
import com.projectplace.api.boards.Comment;
import com.projectplace.api.services.Boards;
import com.projectplace.oauth.Consumer;
import com.projectplace.oauth.Token;

public class BoardAPI {
	private static final String consumerKey = "xxxx";
    private static final String consumerSecret = "xxxx";
    private static final String accessKey = "xxxx";
    private static final String accessSecret = "xxxx";
    private static final int projectId = 1234;

	public static void main(String[] args) {
		Token consumerToken = new Token(consumerKey, consumerSecret);
		Token accessToken = new Token(accessKey, accessSecret);
		Consumer consumer = Consumer.withAccessToken(consumerToken, accessToken);
		ApiBuilder api = ApiBuilder.useConsumer(consumer);
		Boards boards = api.service(Boards.class);
		List<Board> activeBoards = boards.active(projectId);
		System.out.println("Total of " + activeBoards.size() + " active boards are present in the project");
		List<Board> archivedBoards = boards.archived(projectId);
		System.out.println("Total of " + archivedBoards.size() + " archived boards are present in the project");
		
		Board first = boards.properties(activeBoards.get(0).id);
		System.out.println("Properties of board: " + first.id);
		System.out.println(first);
		
		List<Activity> activities = boards.activities(147485);
		Activity firstCard = activities.get(0);
		System.out.println("Properties of activity: " + firstCard.id);
		System.out.println(boards.activity(firstCard.id));
		System.out.println("Comments on activity: " + firstCard.id);
		List<Comment> comments = boards.activityComments(firstCard.id);
		System.out.println(comments);
	}
}
