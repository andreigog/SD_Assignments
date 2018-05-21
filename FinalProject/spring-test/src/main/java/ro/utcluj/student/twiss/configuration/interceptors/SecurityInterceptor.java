package ro.utcluj.student.twiss.configuration.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import ro.utcluj.student.twiss.utill.AppConstants;

public class SecurityInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(
		final HttpServletRequest request,
		final HttpServletResponse response,
		final Object handler) throws Exception {
		if (this.isLoggedIn(request)) {
			return true;
		} else {
			response.sendRedirect("/login-page");
			return false;
		}
	}

	private boolean isLoggedIn(
		final HttpServletRequest request) {
		final String loggedInUser = (String) request.getSession()
			.getAttribute(AppConstants.USER_ATTRIBUTE);
		return loggedInUser != null && !loggedInUser.isEmpty();
	}
}