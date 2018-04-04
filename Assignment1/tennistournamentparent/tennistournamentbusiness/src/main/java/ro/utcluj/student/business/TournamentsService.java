package ro.utcluj.student.business;

import ro.utcluj.student.dao.Model.Tournament;
import ro.utcluj.student.dao.TournamentDAO;

public class TournamentsService {
    public void delete(Tournament tournament){
        TournamentDAO.deleteTournament(tournament);
    }
    public void save(Tournament tournament){
        TournamentDAO.updateTournament(tournament);
    }
    public void create(Tournament tournament){
        TournamentDAO.createTournament(tournament);
    }
}
