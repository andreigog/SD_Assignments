
package ro.utcluj.student.twiss.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ro.utcluj.student.twiss.dto.UserDTO;
import ro.utcluj.student.twiss.model.User;
import ro.utcluj.student.twiss.service.UserService;
import ro.utcluj.student.twiss.utill.AppConstants;

import java.io.IOException;


@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@ModelAttribute("user")
	public User getUserObject() {
		return new User();
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String login(
			final User user,
			final HttpServletRequest request) {
		if (this.userService.login(user)) {
			request.getSession()
					.setAttribute(AppConstants.USER_ATTRIBUTE, user.getUsername());
			return "home";
		} else {
			return "login-page";
		}
	}
}
