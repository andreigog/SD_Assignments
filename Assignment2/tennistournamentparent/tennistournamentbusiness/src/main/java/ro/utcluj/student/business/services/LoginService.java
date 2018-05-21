package ro.utcluj.student.business.services;

import ro.utcluj.student.business.entities.UserEntity;
import ro.utcluj.student.business.utils.Mapper;
import ro.utcluj.student.dao.FunctionalitiesDAO.UserDAO;

public class LoginService {

    public enum ACCESS_RIGHT {PLAYER, ORGANIZER}

    private UserDAO userDAO;

    private Mapper mapper;

    public LoginService(UserDAO userDAO, Mapper mapper){
        this.userDAO = userDAO;
        this.mapper = mapper;
    }

    public UserEntity checkUser(String userName, String password, String userType){
        UserEntity userEntity = mapper.mapUserToEntUser(userDAO.getUser(userName));
        System.out.println(userEntity.getPassword());
        ACCESS_RIGHT accessRight;
        if (userType.equals("organizer"))
            accessRight=ACCESS_RIGHT.ORGANIZER;
        else
            accessRight=ACCESS_RIGHT.PLAYER;
        if (userEntity.getPassword().equals(password) && userEntity.getAccessRight().ordinal()==accessRight.ordinal())
            return userEntity;
        else
            return null;
    }
}
