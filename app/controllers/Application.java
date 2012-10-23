package controllers;

import java.util.ArrayList;
import java.util.List;

import play.*;
import play.mvc.*;
import play.data.*;

import models.*;
import views.html.*;

public class Application extends Controller {

	// -- Authentication

	public static class Login {

		public String email;
		public String password;

		public String validate() {
			if(User.authenticate(email, password) == null) {
				return "Invalid user or password";
			}
			return null;
		}

	}

	/**
	 * Login page.
	 */
	public static Result login() {
		return ok(
				login.render(form(Login.class))
				);
	}

	/**
	 * Handle login form submission.
	 */
	public static Result authenticate() {
		Form<Login> loginForm = form(Login.class).bindFromRequest();
		if(loginForm.hasErrors()) {
			return badRequest(login.render(loginForm));
		} else {
			session("email", loginForm.get().email);
			return redirect(
					routes.Application.index()
					);
		}
	}

	/**
	 * Logout and clean the session.
	 */
	public static Result logout() {
		session().clear();
		flash("success", "You've been logged out");
		return redirect(
				routes.Application.login()
				);
	}
	
	public static Result signup()
	{
		return ok(signup.render());
		
	}

	// -- For testing only, will be removed later

	public static Result index() {
		return ok(index.render("Your new application is ready."));
	}

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

	public static Result chat() {
		return ok(chat.render("Your new application is ready."));
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
		User u1 = User.findByEmail(user1);
		return ok(myProfile.render(u1));
	}
	
	public static Result viewProfile(String username) {
		User u = User.findByEmail(username);
		return ok(myProfile.render(u));
	}
	

}
