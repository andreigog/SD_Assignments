
package ro.utcluj.student.twiss.service;

import org.springframework.stereotype.Service;

import ro.utcluj.student.twiss.dto.UserDTO;
import ro.utcluj.student.twiss.model.User;

@Service
public class UserService {
	public boolean login(
		final User user) {
		return user.getUsername()
			.equals("user")
			&& user.getPassword()
				.equals("pass");
	}
}
