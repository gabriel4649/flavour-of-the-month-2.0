package controllers;

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
    
    // -- For testing only, will be removed later
    
    public static Result index() {
        return ok(index.render("Your new application is ready."));
      }

    public static Result requests() {
    	    return ok(requests.render(User.findAll()));
    	  }
      
    public static Result chat() {
    	    return ok(chat.render("Your new application is ready."));
    	  }
      
    public static Result vote() {
    	    return ok(vote.render(User.findAll()));
    	  }
      
    public static Result getMatches() {
    	    return ok(viewMatches.render(User.findAll()));
    	  }
      
    public static Result myProfile() {
    	    return ok(myProfile.render("Your new application is ready."));
    	  }

}
