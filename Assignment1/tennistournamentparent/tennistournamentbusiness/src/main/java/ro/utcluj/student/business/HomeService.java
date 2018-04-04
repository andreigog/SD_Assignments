package ro.utcluj.student.business;

import ro.utcluj.student.dao.*;
import ro.utcluj.student.dao.Model.Match;
import ro.utcluj.student.dao.Model.Player;
import ro.utcluj.student.dao.Model.Tournament;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class HomeService {

    public ArrayList<Tournament> getTournaments() {
        ArrayList<Tournament> tournaments = new ArrayList<>();
        tournaments = TournamentDAO.viewAll();
        return tournaments;
    }

    public ArrayList<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        players = PlayerDAO.viewAll();
        return players;
    }

    public Tournament generateGames(Tournament tournament) {
        ArrayList<Player> players = tournament.getPlayers();
        ArrayList<Match> games = new ArrayList<>();
        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return o1.getRanking() > o2.getRanking() ? -1 : (o1.getRanking() < o2.getRanking()) ? 1 : 0;
            }
        });
        int j = 0;
        for (int i = 0; i < 4; i++) {
            Match match = new Match(players.get(i), players.get(players.size() - (i + 1)));
            games.add(match);
        }
        games.add(new Match(null, null));
        games.add(new Match(null, null));
        games.add(new Match(null, null));
        tournament.setMatches(games);
        return tournament;
    }

    public Tournament updateGames(Tournament tournament) {
        ArrayList<Match> input = tournament.getMatches();
        Iterator iterator = input.iterator();
        Match quart1 = (Match) iterator.next();
        Match quart2 = (Match) iterator.next();
        Match quart3 = (Match) iterator.next();
        Match quart4 = (Match) iterator.next();
        input.get(4).setPlayer1(quart1.checkWinner());
        input.get(4).setPlayer2(quart2.checkWinner());
        input.get(5).setPlayer1(quart3.checkWinner());
        input.get(5).setPlayer2(quart4.checkWinner());
        input.get(6).setPlayer1(input.get(4).checkWinner());
        input.get(6).setPlayer2(input.get(5).checkWinner());

        tournament.setMatches(input);
        return tournament;
    }

    public void saveTournament(Tournament tournament){
        TournamentDAO.setWinner(tournament);
    }
}
