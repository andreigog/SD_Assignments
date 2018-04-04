package ro.utcluj.student.business;

import ro.utcluj.student.dao.UserDAO;

public class LoginService {
    public Integer checkUser(String userName, String password, String userType){
        Integer userValid = UserDAO.checkUsername(userName,password,userType);
        return  userValid;
    }
}
