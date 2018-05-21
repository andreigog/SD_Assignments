package ro.utcluj.student.dao.FunctionalitiesDAO;

import ro.utcluj.student.dao.model.Tournament;

import java.util.List;

public interface TournamentDAO {

    Tournament createTournament(Tournament tournament);

    void updateTournament(Tournament tournament);

    Tournament getTournament(String name);

    List<Tournament> getAllTournaments();

    List<Tournament> getAllTournaments(String name, String category);

    void deleteTournament(Tournament tournament);

}
