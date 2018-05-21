package ro.utcluj.student.business.services;

import ro.utcluj.student.business.entities.PlayerEntity;
import ro.utcluj.student.business.entities.TournamentEntity;
import ro.utcluj.student.business.entities.UserEntity;
import ro.utcluj.student.business.utils.Mapper;
import ro.utcluj.student.dao.FunctionalitiesDAO.*;
import ro.utcluj.student.dao.model.Enrollment;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerService {

    private UserDAO userDAO;

    private TournamentDAO tournamentDAO;

    private PlayerDAO playerDAO;

    private EnrollmentDAO enrollmentDAO;

    private ScoreDAO scoreDAO;

    private MatchDAO matchDAO;

    private Mapper mapper;

    public PlayerService(UserDAO userDAO, PlayerDAO playerDAO, EnrollmentDAO enrollmentDAO, TournamentDAO tournamentDAO, ScoreDAO scoreDAO, MatchDAO matchDAO, Mapper mapper) {
        this.userDAO = userDAO;
        this.playerDAO = playerDAO;
        this.enrollmentDAO = enrollmentDAO;
        this.tournamentDAO = tournamentDAO;
        this.scoreDAO = scoreDAO;
        this.matchDAO = matchDAO;
        this.mapper = mapper;
    }

    public PlayerEntity getPlayer(UserEntity user){
        return mapper.mapPlayerToEntPlayer(playerDAO.getUserPlayer(mapper.mapEntUserToUser(user)));
    }

    public List<TournamentEntity> getTournaments(String name, String category){
        return tournamentDAO.getAllTournaments(name, category).stream().map(t -> mapper.mapTournamentToEntTournament(t)).collect(Collectors.toList());
    }

    public List<TournamentEntity> getAllTournaments(){
        return tournamentDAO.getAllTournaments().stream().map(t -> mapper.mapTournamentToEntTournament(t)).collect(Collectors.toList());
    }

    public void enroll(PlayerEntity player, TournamentEntity tournament) {
        Enrollment enrollment = new Enrollment(mapper.mapEntPlayerToPlayer(player), mapper.mapEntTournamentToTournament(tournament));
        tournament.setRegisteredPlayers(tournament.getRegisteredPlayers() + 1);
        tournamentDAO.updateTournament(mapper.mapEntTournamentToTournament(tournament));
        player.setBalance(player.getBalance() - tournament.getFee());
        playerDAO.updatePlayer(mapper.mapEntPlayerToPlayer(player));
        enrollmentDAO.createEnrollment(enrollment);
    }

}
