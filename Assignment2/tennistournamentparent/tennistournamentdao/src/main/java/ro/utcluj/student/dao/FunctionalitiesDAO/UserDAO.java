package ro.utcluj.student.dao.FunctionalitiesDAO;

import ro.utcluj.student.dao.model.User;

import java.util.List;

public interface UserDAO {

    User getUser(String username);

    User getUser(int id);

    User createUser(User user);

    List<User> getAllUsers();

    void deactivateUser(User user);

    void updateUser(User user);

}
