package ro.utcluj.student.dao.model;

import javax.persistence.*;

@Entity
@Table(name = "scores")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ScoreId")
    private int id;

    @Column(name = "Set1Player1")
    private int set1Player1;

    @Column(name = "Set1Player2")
    private int set1Player2;

    @Column(name = "Set2Player1")
    private int set2Player1;

    @Column(name = "Set2Player2")
    private int set2Player2;

    @Column(name = "Set3Player1")
    private int set3Player1;

    @Column(name = "Set3Player2")
    private int set3Player2;

    @Column(name = "GamePlayer1")
    private int gamePlayer1;

    @Column(name = "GamePlayer2")
    private int gamePlayer2;

    @Column(name = "Advantage")
    private int advantage;


    public Score() {
        this.set1Player1 = 0;
        this.set1Player2 = 0;
        this.set2Player1 = 0;
        this.set2Player2 = 0;
        this.set3Player1 = 0;
        this.set3Player2 = 0;
        this.gamePlayer1 = 0;
        this.gamePlayer2 = 0;
        this.advantage = 0;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSet1Player1() {
        return set1Player1;
    }

    public void setSet1Player1(int set1Player1) {
        this.set1Player1 = set1Player1;
    }

    public int getSet1Player2() {
        return set1Player2;
    }

    public void setSet1Player2(int set1Player2) {
        this.set1Player2 = set1Player2;
    }

    public int getSet2Player1() {
        return set2Player1;
    }

    public void setSet2Player1(int set2Player1) {
        this.set2Player1 = set2Player1;
    }

    public int getSet2Player2() {
        return set2Player2;
    }

    public void setSet2Player2(int set2Player2) {
        this.set2Player2 = set2Player2;
    }

    public int getSet3Player1() {
        return set3Player1;
    }

    public void setSet3Player1(int set3Player1) {
        this.set3Player1 = set3Player1;
    }

    public int getSet3Player2() {
        return set3Player2;
    }

    public void setSet3Player2(int set3Player2) {
        this.set3Player2 = set3Player2;
    }

    public int getGamePlayer1() {
        return gamePlayer1;
    }

    public void setGamePlayer1(int gamePlayer1) {
        this.gamePlayer1 = gamePlayer1;
    }

    public int getGamePlayer2() {
        return gamePlayer2;
    }

    public void setGamePlayer2(int gamePlayer2) {
        this.gamePlayer2 = gamePlayer2;
    }

    public int getAdvantage() {
        return advantage;
    }

    public void setAdvantage(int advantage) {
        this.advantage = advantage;
    }
}
