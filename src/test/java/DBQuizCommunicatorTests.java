import Models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import db.DBConnector;
import db.DBQuizCommunicator;
import db.DBUserCommunicator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;

import java.sql.SQLException;
import java.sql.Date;
import java.util.HashMap;


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

        String username = Utils.getRandomStr();
        new DBUserCommunicator(conn.getCon()).createUser(username, "", "");
        int userId = new DBUserCommunicator(conn.getCon()).getUserId(username);
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

        QuestionResponse q1 = new QuestionResponse();
        q1.setQuestion(Utils.getRandomStr());
        q1.setResponse(Utils.getRandomStr());

        FillInTheBlank q2 = new FillInTheBlank();
        q2.setQuestion(Utils.getRandomStr());
        q2.setResponse(Utils.getRandomStr());

        MultipleChoice q3 = new MultipleChoice();
        q3.setQuestion(Utils.getRandomStr());
        String resp = Utils.getRandomStr();
        q3.setChoices(new String[]{resp, Utils.getRandomStr(), Utils.getRandomStr()});
        q3.setResponse(resp);

        PictureResponse q4 = new PictureResponse();
        q4.setQuestion(Utils.getRandomStr());
        q4.setResponse(Utils.getRandomStr());

        QuestionParameters qParams1 = new QuestionParameters(Utils.randInt(), Utils.randInt(), Utils.randInt());
        QuestionParameters qParams2 = new QuestionParameters(Utils.randInt(), Utils.randInt(), Utils.randInt());
        QuestionParameters qParams3 = new QuestionParameters(Utils.randInt(), Utils.randInt(), Utils.randInt());
        QuestionParameters qParams4 = new QuestionParameters(Utils.randInt(), Utils.randInt(), Utils.randInt());

        questions.put(q1, qParams1);
        questions.put(q2, qParams2);
        questions.put(q3, qParams3);
        questions.put(q4, qParams4);

        quiz.setQuestions(questions);

        Assert.assertTrue(quizDB.createQuiz(quiz));
        Assert.assertFalse(quizDB.createQuiz(quiz));
        Assert.assertTrue(quizDB.checkQuizExists(name));
        Quiz quiz1 = quizDB.getQuizByName(name);
        Quiz quiz2 = quizDB.getQuizById(quizDB.getIDByQuizName(name));
        Assert.assertEquals(quiz, quiz1);
        Assert.assertEquals(quiz, quiz2);

        QuizAppearParameters quizParams = new QuizAppearParameters(name, description, username);
        ArrayList<QuizAppearParameters> arr = quizDB.getAllQuiz();
        Assert.assertTrue(arr.contains(quizParams));

        Assert.assertFalse(quizDB.deleteQuiz(Utils.getRandomStr()));
        Assert.assertTrue(quizDB.deleteQuiz(name));
        Assert.assertFalse(quizDB.checkQuizExists(name));
    }
}
