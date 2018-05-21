package ro.utcluj.student.twiss.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.utcluj.student.twiss.utill.AppConstants;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

	private final String message = "Hello World";

	@RequestMapping("/home")
	public String welcome(
		final Map<String, Object> model) {
		model.put("message", this.message);
		return "home";
	}
	@RequestMapping(value = "/logout")
	public String logout(final HttpServletRequest request) {
		request.getSession().setAttribute(AppConstants.USER_ATTRIBUTE,null);
		return "login-page";
	}

	@RequestMapping("/login-page")
	public String geLoginPage() {
		return "login-page";
	}

	@RequestMapping("/about")
	public String about(
		final Map<String, Object> model) {
		model.put("message", "Andrei Gog");
		return "about";
	}

}