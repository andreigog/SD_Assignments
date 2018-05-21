package ro.utcluj.student.business.utils;

import ro.utcluj.student.business.entities.*;
import ro.utcluj.student.dao.model.*;

public interface Mapper {
    TournamentEntity mapTournamentToEntTournament(Tournament tournament);
    PlayerEntity mapPlayerToEntPlayer(Player player);
    UserEntity mapUserToEntUser(User user);
    EnrollmentEntity mapEnrollmentToEntEnrollment(Enrollment enrollment);
    MatchEntity mapMatchToEntMatch(Match match);
    ScoreEntity mapScoreToEntScore(Score score);

    Tournament mapEntTournamentToTournament(TournamentEntity tournamentEntity);
    Player mapEntPlayerToPlayer(PlayerEntity playerEntity);
    User mapEntUserToUser(UserEntity userEntity);
    Enrollment mapEntEnrollmentToEnrollment(EnrollmentEntity enrollmentEntity);
    Match mapEntMatchToMatch(MatchEntity matchEntity);
    Score mapEntScoreToScore(ScoreEntity scoreEntity);
}
