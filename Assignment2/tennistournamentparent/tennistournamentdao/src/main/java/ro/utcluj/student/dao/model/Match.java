package ro.utcluj.student.dao.model;


import javax.persistence.*;

@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MatchId")
    private int id;

    @OneToOne
    @JoinColumn(name = "Player1")
    private Player player1;

    @OneToOne
    @JoinColumn(name = "Player2")
    private Player player2;

    @OneToOne
    @JoinColumn(name = "Tournament")
    private Tournament tournament;

    @OneToOne
    @JoinColumn(name = "ScoreId")
    private Score score;

    @OneToOne
    @JoinColumn(name = "Winner")
    private Player winner;

    public Match() {
    }

    public Match(Player player1, Player player2, Tournament tournament, Score score) {
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

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) { this.score = score; }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
}
