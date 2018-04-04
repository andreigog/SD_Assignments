package ro.utcluj.student.dao.Model;


public class Match {

    private Player player1;
    private Player player2;

    private Score gameScore;
    private Score setScore;
    private Score[] overallScore;
    private int advantagePlayer1;
    private int advantagePlayer2;
    private int setsCountPlayer1;
    private int setsCountPlayer2;
    private int currentSet;
    private int gender;

    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.gender = 1;
        this.gameScore = new Score();
        this.setScore = new Score();
        this.setsCountPlayer1 = 0;
        this.setsCountPlayer2 = 0;
        this.advantagePlayer1 = 0;
        this.advantagePlayer2 = 0;
        this.currentSet = 0;
        if (this.gender == 1) {
            this.overallScore = new Score[]{new Score(), new Score(), new Score(), new Score(), new Score()};
        } else {
            this.overallScore = new Score[]{new Score(), new Score(), new Score()};
        }
    }

    public Score getGameScore() {
        return gameScore;
    }

    public Score getSetScore() {
        return setScore;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Score[] getOverallScore() {
        return overallScore;
    }

    public Player getPlayer1() {
        return this.player1;
    }

    public Player getPlayer2() {
        return this.player2;
    }

    public Player checkAdvantage() {
        if (this.advantagePlayer1 == 1)
            return this.player1;
        if (this.advantagePlayer2 == 1)
            return this.player2;
        return null;
    }

    public Player checkWinner() {
        if (this.gender == 1) {
            if (setsCountPlayer1 == 3) {
                return player1;
            }
            if (setsCountPlayer2 == 3) {
                return player2;
            }
        }
        if (this.gender == 0) {
            if (setsCountPlayer1 == 2)
                return player1;
            if (setsCountPlayer2 == 2)
                return player2;
        }
        return null;
    }

    public int getCurrentSet() {
        return currentSet;
    }

    public void point(Player player) {
        if (checkWinner() == null) {
            if (player == player1) {
                if (gameScore.getValue1() == 40 && (advantagePlayer1 == 1 || (gameScore.getValue2() != 40 && advantagePlayer2 == 0))) {
                    setScore.setValue1(getSetScore().getValue1() + 1);
                    overallScore[currentSet].setValue1(overallScore[currentSet].getValue1() + 1);
                    advantagePlayer1 = 0;
                    gameScore.setValue1(0);
                    gameScore.setValue2(0);
                } else if (gameScore.getValue1() == 40 && advantagePlayer2 == 1) {
                    advantagePlayer2 = 0;
                } else if (gameScore.getValue1() == 40 && gameScore.getValue2() == 40) {
                    advantagePlayer1 = 1;
                } else if (gameScore.getValue1() != 30) {
                    gameScore.setValue1(gameScore.getValue1() + 15);
                } else {
                    gameScore.setValue1(gameScore.getValue1() + 10);
                }
                if (setScore.getValue1() >= 6 && (setScore.getValue1() - setScore.getValue2()) >= 2) {
                    setScore.setValue1(0);
                    setScore.setValue2(0);
                    setsCountPlayer1++;
                    currentSet++;
                }
            }
            if (player == player2) {
                if (gameScore.getValue2() == 40 && (advantagePlayer2 == 1 || (gameScore.getValue1() != 40 && advantagePlayer1 == 0))) {
                    setScore.setValue2(getSetScore().getValue2() + 1);
                    overallScore[currentSet].setValue2(overallScore[currentSet].getValue2() + 1);
                    advantagePlayer2 = 0;
                    gameScore.setValue1(0);
                    gameScore.setValue2(0);
                } else if (gameScore.getValue2() == 40 && advantagePlayer1 == 1) {
                    advantagePlayer1 = 0;
                } else if (gameScore.getValue2() == 40 && gameScore.getValue1() == 40) {
                    advantagePlayer2 = 1;
                } else if (gameScore.getValue2() != 30) {
                    gameScore.setValue2(gameScore.getValue2() + 15);
                } else {
                    gameScore.setValue2(gameScore.getValue2() + 10);
                }
                if (setScore.getValue2() >= 6 && (setScore.getValue2() - setScore.getValue1()) >= 2) {
                    setScore.setValue1(0);
                    setScore.setValue2(0);
                    setsCountPlayer2++;
                    currentSet++;
                }
            }
        }
    }
}
