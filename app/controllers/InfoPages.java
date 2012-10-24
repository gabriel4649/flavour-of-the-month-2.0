package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

import views.html.info.*;

import models.*;

public class InfoPages extends Controller {
	
	public static Result contact()
	{
		return ok(contact.render());
	}
	
	public static Result about()
	{
		return ok(about.render("Hello Professor Eric"));
	}

}
