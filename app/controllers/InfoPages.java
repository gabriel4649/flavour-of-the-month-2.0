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
		return ok(about.render());
	}
	
	public static Result async()
	{
		return ok(async.render());
	}
	
	public static Result flavour()
	{
		return ok(flavour.render());
	}
	
	public static Result profiles()
	{
		return ok(profiles.render());
	}

}
