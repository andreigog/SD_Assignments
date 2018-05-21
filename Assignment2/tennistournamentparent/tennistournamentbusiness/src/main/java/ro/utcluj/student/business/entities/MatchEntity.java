package ro.utcluj.student.business.entities;


import ro.utcluj.student.dao.model.Tournament;

public class MatchEntity {

    private int id;
    private PlayerEntity player1;
    private PlayerEntity player2;
    private ScoreEntity score;
    private TournamentEntity tournament;
    private PlayerEntity winner;

    public MatchEntity() {
    }

    public MatchEntity(PlayerEntity player1, PlayerEntity player2, ScoreEntity score, TournamentEntity tournament) {
        this.player1 = player1;
        this.player2 = player2;
        this.score = score;
        this.tournament = tournament;
        this.winner = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PlayerEntity getPlayer1() {
        return player1;
    }

    public void setPlayer1(PlayerEntity player1) {
        this.player1 = player1;
    }

    public PlayerEntity getPlayer2() {
        return player2;
    }

    public void setPlayer2(PlayerEntity player2) {
        this.player2 = player2;
    }

    public ScoreEntity getScore() {
        return score;
    }

    public void setScore(ScoreEntity score) {
        this.score = score;
    }

    public TournamentEntity getTournament() {
        return tournament;
    }

    public void setTournament(TournamentEntity tournament) {
        this.tournament = tournament;
    }

    public PlayerEntity getWinner() {
        return winner;
    }

    public void setWinner(PlayerEntity winner) {
        this.winner = winner;
    }
}
