package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

import java.util.*;

import controllers.Application.Login;

import models.*;

import views.html.*;

@Security.Authenticated(Secured.class)
public class Matches extends Controller {
	
	static Form<Challenge> challengeForm = form(Challenge.class);
	
	public static Result requests() {
		List<User> list = new ArrayList<User>();
		User currUser = User.findByEmail(request().username());
		User challenger = User.findByEmail(currUser.challenger);
		if (challenger != null)
		{
			list.add(challenger);
			return ok(requests.render(list));
		}
		else
		{
			return ok(norequests.render());
		}
	}

	public static Result vote() {
		long id = 1;
		Project p = Project.findById(id);
		List<User> voteList = new ArrayList<User>();
		voteList = p.members;
		return ok(vote.render(voteList));
	}

	public static Result getMatches() {
		Form<Challenge> filledForm = challengeForm.bindFromRequest();
		long id = 1;
		Project p = Project.findById(id);
		List<User> matchesList = new ArrayList<User>();
		matchesList = p.members;
		return ok(viewMatches.render(matchesList, filledForm));
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
	
	public static class Challenge {
	    public String email;
	}
	
	
	public static Result sendChallenge(String email) {
		
			User user = User.findByEmail(email);
			user.challenger = request().username();
			user.save();
			
			return redirect(
					routes.Matches.getMatches()
					);
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
