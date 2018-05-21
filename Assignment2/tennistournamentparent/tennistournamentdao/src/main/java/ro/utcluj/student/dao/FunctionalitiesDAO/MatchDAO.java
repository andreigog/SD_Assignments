package ro.utcluj.student.dao.FunctionalitiesDAO;

import ro.utcluj.student.dao.model.Match;
import ro.utcluj.student.dao.model.Tournament;

import java.util.List;

public interface MatchDAO {

    Match createMatch(Match match);

    List<Match> getAllTournamentMatches(Tournament tournament);

    void updateMatch(Match match);

}
