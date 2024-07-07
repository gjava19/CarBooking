import Models.QuestionParameters;
import Models.QuestionType;
import Models.Quiz;
import com.fasterxml.jackson.core.JsonProcessingException;
import db.DBConnector;
import db.DBQuizCommunicator;
import db.DBUserCommunicator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
import java.util.HashMap;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

public class DBQuizCommunicatorTests {

    private DBConnector conn;
    private DBQuizCommunicator quizDB;

    @Before
    public void before() throws SQLException {
        conn = new DBConnector();
        quizDB = new DBQuizCommunicator(conn.getCon());
    }

    @Test
    public void checkQuizDB() throws SQLException, JsonProcessingException {
        Assert.assertFalse(quizDB.checkQuizExists(Utils.getRandomStr()));
        Assert.assertNull(quizDB.getQuizByName(Utils.getRandomStr()));

        Quiz quiz = new Quiz();

        int userId = 1;
        String name = Utils.getRandomStr();
        String description = Utils.getRandomStr();
        Date createDate = new Date(new java.util.Date().getTime());
        boolean randomQuestion = Utils.getRandomBool();
        boolean immediateAnswer = Utils.getRandomBool();
        boolean multiplePageQuiz = Utils.getRandomBool();

        quiz.setUserId(userId);
        quiz.setName(name);
        quiz.setDescription(description);
        quiz.setCreate_time(createDate);
        quiz.setRandomQuestion(randomQuestion);
        quiz.setImmediateAnswer(immediateAnswer);
        quiz.setMultiplePageQuiz(multiplePageQuiz);

        HashMap<QuestionType, QuestionParameters> questions = new HashMap<QuestionType, QuestionParameters>();
        quiz.setQuestions(questions);

        Assert.assertTrue(quizDB.createQuiz(quiz));

    }
}
