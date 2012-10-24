package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

import java.util.*;

import models.*;

import views.html.*;

@Security.Authenticated(Secured.class)
public class Matches extends Controller {
	
	public static Result requests() {
		String user1 = "mkerr@sample.com";
		String user2 = "laldridge@sample.com";
		String user3 = "aambrosio@sample.com";
		User u1 = User.findByEmail(user1);
		User u2 = User.findByEmail(user2);
		User u3 = User.findByEmail(user3);
		List<User> list = new ArrayList<User>();
		list.add(u1);
		list.add(u2);
		list.add(u3);
		return ok(requests.render(list));
	}

	public static Result vote() {
		long id = 1;
		Project p = Project.findById(id);
		List<User> voteList = new ArrayList<User>();
		voteList = p.members;
		return ok(vote.render(voteList));
	}

	public static Result getMatches() {
		long id = 1;
		Project p = Project.findById(id);
		List<User> matchesList = new ArrayList<User>();
		matchesList = p.members;
		return ok(viewMatches.render(matchesList));
	}

	public static Result myProfile() {
		String user1 = "edilbert@sample.com";
		User u1 = User.find.byId(request().username());
		return ok(myProfile.render(u1));
	}
	
	public static Result viewProfile(String username) {
		User u = User.findByEmail(username);
		return ok(myProfile.render(u));
	}
	
	public static Result sendRequest(String username) {
		User u = User.findByEmail(username);
		return ok(sendRequest.render(u));
	}
	
	public static Result uploadVideo(String username) {
		User u = User.findByEmail(username);
		return ok(uploadVideo.render(u));
	}

	public static Result viewVideos() {
		List<String> list = new ArrayList<String>();
		list.add("intro.swf");
		list.add("intro.swf");list.add("intro.swf");
		return ok(viewVideos.render(list));
	}

}
