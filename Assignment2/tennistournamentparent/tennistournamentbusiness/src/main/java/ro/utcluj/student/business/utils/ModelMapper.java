package ro.utcluj.student.business.utils;

import ro.utcluj.student.business.entities.*;
import ro.utcluj.student.dao.model.*;

public class ModelMapper implements Mapper {

    @Override
    public TournamentEntity mapTournamentToEntTournament(Tournament tournament) {
        TournamentEntity tournamentEntity = new TournamentEntity();
        tournamentEntity.setId(tournament.getId());
        tournamentEntity.setName(tournament.getName());
        tournamentEntity.setLocation(tournament.getLocation());
        tournamentEntity.setDate(tournament.getDate());
        tournamentEntity.setRegisteredPlayers(tournament.getRegisteredPlayers());
        tournamentEntity.setFee(tournament.getFee());
        return tournamentEntity;
    }

    @Override
    public PlayerEntity mapPlayerToEntPlayer(Player player) {
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setId(player.getId());
        playerEntity.setName(player.getName());
        playerEntity.setAge(player.getAge());
        playerEntity.setCountry(player.getCountry());
        playerEntity.setBalance(player.getBalance());
        playerEntity.setUser(mapUserToEntUser(player.getUser()));
        return playerEntity;
    }

    @Override
    public UserEntity mapUserToEntUser(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        userEntity.setAccessRight(UserEntity.ACCESS_RIGHT.values()[user.getAccessRight().ordinal()]);
        return userEntity;
    }

    @Override
    public EnrollmentEntity mapEnrollmentToEntEnrollment(Enrollment enrollment) {
        EnrollmentEntity enrollmentEntity = new EnrollmentEntity();
        enrollmentEntity.setId(enrollment.getId());
        enrollmentEntity.setPlayer(mapPlayerToEntPlayer(enrollment.getPlayer()));
        enrollmentEntity.setTournament(mapTournamentToEntTournament(enrollment.getTournament()));
        return enrollmentEntity;
    }

    @Override
    public MatchEntity mapMatchToEntMatch(Match match) {
        MatchEntity matchEntity = new MatchEntity();
        matchEntity.setId(match.getId());
        matchEntity.setPlayer1(mapPlayerToEntPlayer(match.getPlayer1()));
        matchEntity.setPlayer2(mapPlayerToEntPlayer(match.getPlayer2()));
        matchEntity.setTournament(mapTournamentToEntTournament(match.getTournament()));
        matchEntity.setScore(mapScoreToEntScore(match.getScore()));
        if (match.getWinner()!=null)
            matchEntity.setWinner(mapPlayerToEntPlayer(match.getWinner()));
        return matchEntity;
    }

    @Override
    public ScoreEntity mapScoreToEntScore(Score score) {
        ScoreEntity scoreEntity = new ScoreEntity();
        scoreEntity.setId(score.getId());
        scoreEntity.setSet1Player1(score.getSet1Player1());
        scoreEntity.setSet1Player2(score.getSet1Player2());
        scoreEntity.setSet2Player1(score.getSet2Player1());
        scoreEntity.setSet2Player2(score.getSet2Player2());
        scoreEntity.setSet3Player1(score.getSet3Player1());
        scoreEntity.setSet3Player2(score.getSet3Player2());
        scoreEntity.setGamePlayer1(score.getGamePlayer1());
        scoreEntity.setGamePlayer2(score.getGamePlayer2());
        scoreEntity.setAdvantage(score.getAdvantage());
        return scoreEntity;
    }



    @Override
    public Tournament mapEntTournamentToTournament(TournamentEntity tournamentEntity) {
        Tournament tournament = new Tournament();
        tournament.setId(tournamentEntity.getId());
        tournament.setName(tournamentEntity.getName());
        tournament.setLocation(tournamentEntity.getLocation());
        tournament.setDate(tournamentEntity.getDate());
        tournament.setRegisteredPlayers(tournamentEntity.getRegisteredPlayers());
        tournament.setFee(tournamentEntity.getFee());
        return tournament;
    }

    @Override
    public Player mapEntPlayerToPlayer(PlayerEntity playerEntity) {
        Player player = new Player();
        player.setId(playerEntity.getId());
        player.setName(playerEntity.getName());
        player.setAge(playerEntity.getAge());
        player.setCountry(playerEntity.getCountry());
        player.setBalance(playerEntity.getBalance());
        player.setUser(mapEntUserToUser(playerEntity.getUser()));
        return player;
    }

    @Override
    public User mapEntUserToUser(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getId());
        user.setUsername(userEntity.getUsername());
        user.setPassword(userEntity.getPassword());
        user.setAccessRight(User.ACCESS_RIGHT.values()[userEntity.getAccessRight().ordinal()]);
        return user;
    }

    @Override
    public Enrollment mapEntEnrollmentToEnrollment(EnrollmentEntity enrollmentEntity) {
        Enrollment enrollment = new Enrollment();
        enrollment.setId(enrollmentEntity.getId());
        enrollment.setPlayer(mapEntPlayerToPlayer(enrollmentEntity.getPlayer()));
        enrollment.setTournament(mapEntTournamentToTournament(enrollmentEntity.getTournament()));
        return enrollment;
    }

    @Override
    public Match mapEntMatchToMatch(MatchEntity matchEntity) {
        Match match = new Match();
        match.setId(matchEntity.getId());
        match.setPlayer1(mapEntPlayerToPlayer(matchEntity.getPlayer1()));
        match.setPlayer2(mapEntPlayerToPlayer(matchEntity.getPlayer2()));
        match.setTournament(mapEntTournamentToTournament(matchEntity.getTournament()));
        match.setScore(mapEntScoreToScore(matchEntity.getScore()));
        if (matchEntity.getWinner()!=null)
            match.setWinner(mapEntPlayerToPlayer(matchEntity.getWinner()));
        return match;
    }

    @Override
    public Score mapEntScoreToScore(ScoreEntity scoreEntity) {
        Score score = new Score();
        score.setId(scoreEntity.getId());
        score.setSet1Player1(scoreEntity.getSet1Player1());
        score.setSet1Player2(scoreEntity.getSet1Player2());
        score.setSet2Player1(scoreEntity.getSet2Player1());
        score.setSet2Player2(scoreEntity.getSet2Player2());
        score.setSet3Player1(scoreEntity.getSet3Player1());
        score.setSet3Player2(scoreEntity.getSet3Player2());
        score.setGamePlayer1(scoreEntity.getGamePlayer1());
        score.setGamePlayer2(scoreEntity.getGamePlayer2());
        score.setAdvantage(scoreEntity.getAdvantage());
        return score;
    }
}
