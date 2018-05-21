package ro.utcluj.student.business.services;

import com.google.gson.Gson;
import ro.utcluj.student.business.utils.Mapper;
import ro.utcluj.student.business.utils.ModelMapper;
import ro.utcluj.student.dao.FunctionalitiesDAO.*;
import ro.utcluj.student.dao.connection.AbstractDAOFactory;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ServiceProvider {

    private MatchDAO matchDAO;
    private UserDAO userDAO;
    private TournamentDAO tournamentDAO;
    private EnrollmentDAO enrollmentDAO;
    private PlayerDAO playerDAO;
    private ScoreDAO scoreDAO;

    private Mapper mapper;

    private LoginService loginService;
    private AdminService adminService;
    private PlayerService playerService;

    public ServiceProvider(){
        Gson gson = new Gson();
        Settings settings = null;
        try {
            FileReader fileReader = new FileReader("C:\\Users\\andrei\\IdeaProjects\\tennistournamentparent\\tennistournamentbusiness\\src\\main\\resources\\settings.json");
            settings = gson.fromJson(fileReader, Settings.class);
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AbstractDAOFactory abstractDAOFactory=null;
        if (settings.getConnectionType()==CONNECTION_TYPE.HIBERNATE)
            abstractDAOFactory = AbstractDAOFactory.getInstance(AbstractDAOFactory.Type.HIBERNATE);
        else if (settings.getConnectionType()==CONNECTION_TYPE.JDBC)
            abstractDAOFactory = AbstractDAOFactory.getInstance(AbstractDAOFactory.Type.JDBC);

        this.matchDAO = abstractDAOFactory.getMatchDAO();
        this.userDAO = abstractDAOFactory.getUserDAO();
        this.tournamentDAO = abstractDAOFactory.getTournamentDAO();
        this.enrollmentDAO = abstractDAOFactory.getEnrollmentDAO();
        this.playerDAO = abstractDAOFactory.getPlayerDAO();
        this.scoreDAO = abstractDAOFactory.getScoreDAO();
        this.mapper = new ModelMapper();
    }

    public LoginService getLoginService(){
        if(loginService == null){
            loginService = new LoginService(userDAO, mapper);
        }
        return loginService;
    }

    public AdminService getAdminService(){
        if(adminService == null){
            adminService = new AdminService(userDAO, playerDAO, enrollmentDAO, tournamentDAO, scoreDAO, matchDAO, mapper);
        }
        return adminService;
    }

    public PlayerService getPlayerService(){
        if(playerService == null){
            playerService = new PlayerService(userDAO, playerDAO, enrollmentDAO, tournamentDAO, scoreDAO, matchDAO, mapper);
        }
        return playerService;
    }
}
