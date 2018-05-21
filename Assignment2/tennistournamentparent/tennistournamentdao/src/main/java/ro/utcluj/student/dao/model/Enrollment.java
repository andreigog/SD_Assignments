package ro.utcluj.student.dao.model;

import javax.persistence.*;

@Entity
@Table(name = "enrollments")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EnrollmentId")
    private int id;

    @OneToOne
    @JoinColumn(name = "PlayerId")
    private Player player;


    @OneToOne
    @JoinColumn(name = "TournamentId")
    private Tournament tournament;

    public Enrollment() {

    }

    public Enrollment(Player player, Tournament tournament){
        this.player = player;
        this.tournament = tournament;
    }

    public Player getPlayer(){
        return player;
    }

    public void setPlayer(Player player){
        this.player=player;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}