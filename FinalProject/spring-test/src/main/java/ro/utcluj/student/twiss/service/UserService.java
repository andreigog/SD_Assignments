
package ro.utcluj.student.twiss.service;

import org.springframework.stereotype.Service;

import ro.utcluj.student.twiss.dto.UserDTO;

@Service
public class UserService {
	public boolean login(
		final UserDTO user) {
		return user.getUsername()
			.equals("user")
			&& user.getPassword()
				.equals("pass");
	}
}
