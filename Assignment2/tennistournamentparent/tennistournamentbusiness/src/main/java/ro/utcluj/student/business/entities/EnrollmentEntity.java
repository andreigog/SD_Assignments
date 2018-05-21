package ro.utcluj.student.business.entities;

public class EnrollmentEntity {

    private int id;
    private PlayerEntity player;
    private TournamentEntity tournament;

    public EnrollmentEntity() {

    }

    public EnrollmentEntity(PlayerEntity player, TournamentEntity tournament){
        this.player = player;
        this.tournament = tournament;
    }

    public PlayerEntity getPlayer(){
        return player;
    }

    public void setPlayer(PlayerEntity player){
        this.player=player;
    }

    public TournamentEntity getTournament() {
        return tournament;
    }

    public void setTournament(TournamentEntity tournament) {
        this.tournament = tournament;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}