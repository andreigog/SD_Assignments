package ro.utcluj.student.business.services;

import jdk.management.resource.internal.TotalResourceContext;
import ro.utcluj.student.business.entities.*;
import ro.utcluj.student.business.utils.Mapper;
import ro.utcluj.student.dao.FunctionalitiesDAO.*;
import ro.utcluj.student.dao.model.Enrollment;
import ro.utcluj.student.dao.model.Match;

import java.util.List;
import java.util.stream.Collectors;

public class AdminService {

    private UserDAO userDAO;

    private TournamentDAO tournamentDAO;

    private PlayerDAO playerDAO;

    private EnrollmentDAO enrollmentDAO;

    private ScoreDAO scoreDAO;

    private MatchDAO matchDAO;

    private Mapper mapper;

    boolean flagSemi1 = true, flagSemi2 = true, flagFinal = true, flagWinner = true;
    MatchEntity semi1 = null;
    MatchEntity semi2 = null;
    MatchEntity grandFinal = null;

    public AdminService(UserDAO userDAO, PlayerDAO playerDAO, EnrollmentDAO enrollmentDAO, TournamentDAO tournamentDAO, ScoreDAO scoreDAO, MatchDAO matchDAO, Mapper mapper) {
        this.userDAO = userDAO;
        this.playerDAO = playerDAO;
        this.enrollmentDAO = enrollmentDAO;
        this.tournamentDAO = tournamentDAO;
        this.scoreDAO = scoreDAO;
        this.matchDAO = matchDAO;
        this.mapper = mapper;
    }

    public List<TournamentEntity> getAllTournaments() {
        return tournamentDAO.getAllTournaments().stream().map(t -> mapper.mapTournamentToEntTournament(t)).collect(Collectors.toList());
    }

    public List<PlayerEntity> getAllPlayers() {
        return playerDAO.getAllPlayers().stream().map(t -> mapper.mapPlayerToEntPlayer(t)).collect(Collectors.toList());
    }

    public boolean checkIfRegistered(PlayerEntity player, TournamentEntity tournament) {
        if (enrollmentDAO.getEnrollment(mapper.mapEntPlayerToPlayer(player), mapper.mapEntTournamentToTournament(tournament)) == null)
            return false;
        else
            return true;
    }

    public void enroll(PlayerEntity player, TournamentEntity tournament) {
        Enrollment enrollment = new Enrollment(mapper.mapEntPlayerToPlayer(player), mapper.mapEntTournamentToTournament(tournament));
        tournament.setRegisteredPlayers(tournament.getRegisteredPlayers() + 1);
        tournamentDAO.updateTournament(mapper.mapEntTournamentToTournament(tournament));
        player.setBalance(player.getBalance() - tournament.getFee());
        playerDAO.updatePlayer(mapper.mapEntPlayerToPlayer(player));
        enrollmentDAO.createEnrollment(enrollment);
    }

    public void generateMatches(TournamentEntity tournamentEntity) {
        List<EnrollmentEntity> enrollments = enrollmentDAO.getAllTournamentEnrollMents(mapper.mapEntTournamentToTournament(tournamentEntity))
                .stream()
                .map(t -> mapper.mapEnrollmentToEntEnrollment(t))
                .collect(Collectors.toList());
        int j = 0;
        for (int i = 0; i < 4; i++) {
            ScoreEntity score = new ScoreEntity();
            score = mapper.mapScoreToEntScore(scoreDAO.createScore(mapper.mapEntScoreToScore(score)));
            MatchEntity match = new MatchEntity(enrollments.get(i).getPlayer(), enrollments.get(enrollments.size() - (i + 1)).getPlayer(), score, tournamentEntity);
            matchDAO.createMatch(mapper.mapEntMatchToMatch(match));
        }
    }

    public List<MatchEntity> getAllTournamentMatches(TournamentEntity tournamentEntity) {
        return matchDAO.getAllTournamentMatches(mapper.mapEntTournamentToTournament(tournamentEntity)).stream().map(t -> mapper.mapMatchToEntMatch(t)).collect(Collectors.toList());
    }

    public void updateTournamentMatches(TournamentEntity tournamentEntity) {
        List<MatchEntity> matches = matchDAO.getAllTournamentMatches(mapper.mapEntTournamentToTournament(tournamentEntity)).stream().map(t -> mapper.mapMatchToEntMatch(t)).collect(Collectors.toList());


        if (matches.get(0).getWinner() != null && matches.get(1).getWinner() != null && flagSemi1) {
            ScoreEntity score = new ScoreEntity();
            score = mapper.mapScoreToEntScore(scoreDAO.createScore(mapper.mapEntScoreToScore(score)));
            semi1 = new MatchEntity(matches.get(0).getWinner(), matches.get(1).getWinner(), score, tournamentEntity);
            matchDAO.createMatch(mapper.mapEntMatchToMatch(semi1));
            flagSemi1 = false;
        }
        if (matches.get(2).getWinner() != null && matches.get(3).getWinner() != null && flagSemi2) {
            ScoreEntity score = new ScoreEntity();
            score = mapper.mapScoreToEntScore(scoreDAO.createScore(mapper.mapEntScoreToScore(score)));
            semi2 = new MatchEntity(matches.get(2).getWinner(), matches.get(3).getWinner(), score, tournamentEntity);
            flagSemi2 = false;
            matchDAO.createMatch(mapper.mapEntMatchToMatch(semi2));
        }
        if (semi1 != null && semi2 != null) {
            if (matches.get(4).getWinner() != null && matches.get(5).getWinner() != null && flagFinal) {
                ScoreEntity score = new ScoreEntity();
                score = mapper.mapScoreToEntScore(scoreDAO.createScore(mapper.mapEntScoreToScore(score)));
                grandFinal = new MatchEntity(matches.get(4).getWinner(), matches.get(5).getWinner(), score, tournamentEntity);
                matchDAO.createMatch(mapper.mapEntMatchToMatch(grandFinal));
                flagFinal = false;
            }
        }
    }

    public PlayerEntity checkIfFinishedTournament(TournamentEntity tournamentEntity) {
        List<MatchEntity> matches = matchDAO.getAllTournamentMatches(mapper.mapEntTournamentToTournament(tournamentEntity)).stream().map(t -> mapper.mapMatchToEntMatch(t)).collect(Collectors.toList());
        if (matches.size() == 7)
            if (matches.get(6).getWinner() != null && flagWinner) {
                PlayerEntity playerEntity = matches.get(6).getWinner();
                playerEntity.setBalance(playerEntity.getBalance() + tournamentEntity.getFee() * tournamentEntity.getRegisteredPlayers());
                playerDAO.updatePlayer(mapper.mapEntPlayerToPlayer(playerEntity));
                flagWinner = false;
                return playerEntity;
            }
        return null;
    }

    public MatchEntity pointPlayer1(MatchEntity matchEntity) {
        if (checkIfFinished(matchEntity) != null) {
            matchEntity.setWinner(checkIfFinished(matchEntity));
        } else {
            matchEntity = pointP1(matchEntity);
        }
        if (checkIfFinished(matchEntity) != null) {
            matchEntity.setWinner(checkIfFinished(matchEntity));
        }
        return matchEntity;
    }

    public MatchEntity pointPlayer2(MatchEntity matchEntity) {
        if (checkIfFinished(matchEntity) != null) {
            matchEntity.setWinner(checkIfFinished(matchEntity));
        } else {
            matchEntity = pointP2(matchEntity);
        }
        if (checkIfFinished(matchEntity) != null) {
            matchEntity.setWinner(checkIfFinished(matchEntity));
        }
        return matchEntity;
    }

    public PlayerEntity checkIfFinished(MatchEntity matchEntity) {
        ScoreEntity scoreEntity = matchEntity.getScore();
        int setCountPlayer1 = 0;
        int setCountPlayer2 = 0;

        if (scoreEntity.getSet1Player1() >= 2 && scoreEntity.getSet1Player1() - scoreEntity.getSet1Player2() >= 2)
            setCountPlayer1++;
        if (scoreEntity.getSet2Player1() >= 2 && scoreEntity.getSet2Player1() - scoreEntity.getSet2Player2() >= 2)
            setCountPlayer1++;
        if (scoreEntity.getSet3Player1() >= 2 && scoreEntity.getSet3Player1() - scoreEntity.getSet3Player2() >= 2)
            setCountPlayer1++;


        if (scoreEntity.getSet1Player2() >= 2 && scoreEntity.getSet1Player2() - scoreEntity.getSet1Player1() >= 2)
            setCountPlayer2++;
        if (scoreEntity.getSet2Player2() >= 2 && scoreEntity.getSet2Player2() - scoreEntity.getSet2Player1() >= 2)
            setCountPlayer2++;
        if (scoreEntity.getSet3Player2() >= 2 && scoreEntity.getSet3Player2() - scoreEntity.getSet3Player1() >= 2)
            setCountPlayer2++;

        if (setCountPlayer1 == 2) {
            matchEntity.setWinner(matchEntity.getPlayer1());
            matchDAO.updateMatch(mapper.mapEntMatchToMatch(matchEntity));
            scoreDAO.updateScore(mapper.mapEntScoreToScore(matchEntity.getScore()));
            return matchEntity.getPlayer1();
        } else if (setCountPlayer2 == 2) {
            matchEntity.setWinner(matchEntity.getPlayer2());
            matchDAO.updateMatch(mapper.mapEntMatchToMatch(matchEntity));
            scoreDAO.updateScore(mapper.mapEntScoreToScore(matchEntity.getScore()));
            return matchEntity.getPlayer2();
        } else
            return null;

    }

    public MatchEntity pointP1(MatchEntity matchEntity) {
        ScoreEntity scoreEntity = matchEntity.getScore();
        if (scoreEntity.getAdvantage() == 2)
            scoreEntity.setAdvantage(0);
        else if (scoreEntity.getAdvantage() == 1) {
            scoreEntity.setAdvantage(0);
            scoreEntity.setGamePlayer1(0);
            scoreEntity.setGamePlayer2(0);
            if (!(scoreEntity.getSet1Player1() >= 2 && scoreEntity.getSet1Player1() - scoreEntity.getSet1Player2() >= 2)
                    && !(scoreEntity.getSet1Player2() >= 2 && scoreEntity.getSet1Player2() - scoreEntity.getSet1Player1() >= 2)) {
                scoreEntity.setSet1Player1(scoreEntity.getSet1Player1() + 1);
            } else if (!(scoreEntity.getSet2Player1() >= 2 && scoreEntity.getSet2Player1() - scoreEntity.getSet2Player2() >= 2)
                    && !(scoreEntity.getSet2Player2() >= 2 && scoreEntity.getSet2Player2() - scoreEntity.getSet2Player1() >= 2)) {
                scoreEntity.setSet2Player1(scoreEntity.getSet2Player1() + 1);
            } else if (!(scoreEntity.getSet3Player1() >= 2 && scoreEntity.getSet3Player1() - scoreEntity.getSet3Player2() >= 2)
                    && !(scoreEntity.getSet3Player2() >= 2 && scoreEntity.getSet3Player2() - scoreEntity.getSet3Player1() >= 2)) {
                scoreEntity.setSet3Player1(scoreEntity.getSet3Player1() + 1);
            }
        } else if (scoreEntity.getGamePlayer1() == 40 && scoreEntity.getGamePlayer2() == 40)
            scoreEntity.setAdvantage(1);
        else if (scoreEntity.getGamePlayer1() == 40) {
            scoreEntity.setAdvantage(0);
            scoreEntity.setGamePlayer1(0);
            scoreEntity.setGamePlayer2(0);
            if (!(scoreEntity.getSet1Player1() >= 2 && scoreEntity.getSet1Player1() - scoreEntity.getSet1Player2() >= 2)
                    && !(scoreEntity.getSet1Player2() >= 2 && scoreEntity.getSet1Player2() - scoreEntity.getSet1Player1() >= 2)) {
                scoreEntity.setSet1Player1(scoreEntity.getSet1Player1() + 1);
            } else if (!(scoreEntity.getSet2Player1() >= 2 && scoreEntity.getSet2Player1() - scoreEntity.getSet2Player2() >= 2)
                    && !(scoreEntity.getSet2Player2() >= 2 && scoreEntity.getSet2Player2() - scoreEntity.getSet2Player1() >= 2)) {
                scoreEntity.setSet2Player1(scoreEntity.getSet2Player1() + 1);
            } else if (!(scoreEntity.getSet3Player1() >= 2 && scoreEntity.getSet3Player1() - scoreEntity.getSet3Player2() >= 2)
                    && !(scoreEntity.getSet3Player2() >= 2 && scoreEntity.getSet3Player2() - scoreEntity.getSet3Player1() >= 2)) {
                scoreEntity.setSet3Player1(scoreEntity.getSet3Player1() + 1);
            }
        } else if (scoreEntity.getGamePlayer1() == 30) {
            scoreEntity.setGamePlayer1(40);
        } else {
            scoreEntity.setGamePlayer1(scoreEntity.getGamePlayer1() + 15);
        }
        matchEntity.setScore(scoreEntity);
        return matchEntity;
    }

    public MatchEntity pointP2(MatchEntity matchEntity) {
        ScoreEntity scoreEntity = matchEntity.getScore();
        if (scoreEntity.getAdvantage() == 1)
            scoreEntity.setAdvantage(0);
        else if (scoreEntity.getAdvantage() == 2) {
            scoreEntity.setAdvantage(0);
            scoreEntity.setGamePlayer1(0);
            scoreEntity.setGamePlayer2(0);
            if (!(scoreEntity.getSet1Player1() >= 2 && scoreEntity.getSet1Player1() - scoreEntity.getSet1Player2() >= 2)
                    && !(scoreEntity.getSet1Player2() >= 2 && scoreEntity.getSet1Player2() - scoreEntity.getSet1Player1() >= 2)) {
                scoreEntity.setSet1Player2(scoreEntity.getSet1Player2() + 1);
            } else if (!(scoreEntity.getSet2Player1() >= 2 && scoreEntity.getSet2Player1() - scoreEntity.getSet2Player2() >= 2)
                    && !(scoreEntity.getSet2Player2() >= 2 && scoreEntity.getSet2Player2() - scoreEntity.getSet2Player1() >= 2)) {
                scoreEntity.setSet2Player2(scoreEntity.getSet2Player2() + 1);
            } else if (!(scoreEntity.getSet3Player1() >= 2 && scoreEntity.getSet3Player1() - scoreEntity.getSet3Player2() >= 2)
                    && !(scoreEntity.getSet3Player2() >= 2 && scoreEntity.getSet3Player2() - scoreEntity.getSet3Player1() >= 2)) {
                scoreEntity.setSet3Player2(scoreEntity.getSet3Player2() + 1);
            }
        } else if (scoreEntity.getGamePlayer2() == 40 && scoreEntity.getGamePlayer1() == 40)
            scoreEntity.setAdvantage(2);
        else if (scoreEntity.getGamePlayer2() == 40) {
            scoreEntity.setAdvantage(0);
            scoreEntity.setGamePlayer1(0);
            scoreEntity.setGamePlayer2(0);
            if (!(scoreEntity.getSet1Player1() >= 2 && scoreEntity.getSet1Player1() - scoreEntity.getSet1Player2() >= 2)
                    && !(scoreEntity.getSet1Player2() >= 2 && scoreEntity.getSet1Player2() - scoreEntity.getSet1Player1() >= 2)) {
                scoreEntity.setSet1Player2(scoreEntity.getSet1Player2() + 1);
            } else if (!(scoreEntity.getSet2Player1() >= 2 && scoreEntity.getSet2Player1() - scoreEntity.getSet2Player2() >= 2)
                    && !(scoreEntity.getSet2Player2() >= 2 && scoreEntity.getSet2Player2() - scoreEntity.getSet2Player1() >= 2)) {
                scoreEntity.setSet2Player2(scoreEntity.getSet2Player2() + 1);
            } else if (!(scoreEntity.getSet3Player1() >= 2 && scoreEntity.getSet3Player1() - scoreEntity.getSet3Player2() >= 2)
                    && !(scoreEntity.getSet3Player2() >= 2 && scoreEntity.getSet3Player2() - scoreEntity.getSet3Player1() >= 2)) {
                scoreEntity.setSet3Player2(scoreEntity.getSet3Player2() + 1);
            }
        } else if (scoreEntity.getGamePlayer2() == 30) {
            scoreEntity.setGamePlayer2(40);
        } else {
            scoreEntity.setGamePlayer2(scoreEntity.getGamePlayer2() + 15);
        }
        matchEntity.setScore(scoreEntity);
        return matchEntity;
    }
}
