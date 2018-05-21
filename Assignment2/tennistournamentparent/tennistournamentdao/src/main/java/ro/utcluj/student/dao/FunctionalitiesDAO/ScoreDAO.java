package ro.utcluj.student.dao.FunctionalitiesDAO;

import ro.utcluj.student.dao.model.Match;
import ro.utcluj.student.dao.model.Score;

public interface ScoreDAO {

    Score createScore(Score score);

    void updateScore(Score score);

}
