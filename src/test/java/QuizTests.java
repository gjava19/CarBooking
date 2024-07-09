import Models.*;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.util.HashMap;

public class QuizTests {

    @Test
    public void quizTest(){

        int userId = Utils.randInt();
        String name = Utils.getRandomStr();
        String description = Utils.getRandomStr();
        Date createDate = new Date(new java.util.Date().getTime());
        boolean randomQuestion = Utils.getRandomBool();
        boolean immediateAnswer = Utils.getRandomBool();
        boolean multiplePageQuiz = Utils.getRandomBool();

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

        int sec = Utils.randInt();
        int score = Utils.randInt();
        int id = Utils.randInt();
        QuestionParameters qParams1 = new QuestionParameters(sec, score, id);
        QuestionParameters qParams2 = new QuestionParameters(sec, score, id);
        QuestionParameters qParams3 = new QuestionParameters(Utils.randInt(), Utils.randInt(), Utils.randInt());
        QuestionParameters qParams4 = new QuestionParameters(Utils.randInt(), Utils.randInt(), Utils.randInt());

        Assert.assertEquals(qParams1, qParams2);
        Assert.assertNotEquals(qParams3, qParams4);
        Assert.assertNotEquals(null, qParams4);

        questions.put(q1, qParams1);
        questions.put(q2, qParams2);
        questions.put(q3, qParams3);
        questions.put(q4, qParams4);


        Quiz quiz = new Quiz(userId, name, description, createDate, randomQuestion, immediateAnswer, multiplePageQuiz, questions);

        Assert.assertEquals(userId, quiz.getUserId());
        Assert.assertEquals(name, quiz.getName());
        Assert.assertEquals(description, quiz.getDescription());
        Assert.assertEquals(createDate, quiz.getCreate_time());
        Assert.assertEquals(randomQuestion, quiz.isRandomQuestion());
        Assert.assertEquals(immediateAnswer, quiz.isImmediateAnswer());
        Assert.assertEquals(multiplePageQuiz, quiz.isMultiplePageQuiz());
        Assert.assertEquals(questions, quiz.getQuestions());

        Quiz quiz2 = new Quiz();
        quiz2.setUserId(userId);
        quiz2.setName(name.substring(1));
        quiz2.setDescription(description);
        quiz2.setCreate_time(createDate);
        quiz2.setRandomQuestion(randomQuestion);
        quiz2.setImmediateAnswer(immediateAnswer);
        quiz2.setMultiplePageQuiz(multiplePageQuiz);
        quiz2.setQuestions(questions);

        Assert.assertNotEquals(null, quiz);
        Assert.assertNotEquals(quiz, quiz2);

        quiz2.setName(name);
        Assert.assertEquals(quiz, quiz2);
        Assert.assertEquals(quiz.toString(), quiz2.toString());
    }

    @Test
    public void quizAppearParametersTest(){
        String name = Utils.getRandomStr();
        String description = Utils.getRandomStr();
        String creatorName = Utils.getRandomStr();

        QuizAppearParameters param = new QuizAppearParameters(name, description, creatorName);

        Assert.assertEquals(name, param.getQuizName());
        Assert.assertEquals(description, param.getQuizDescription());
        Assert.assertEquals(creatorName, param.getCreatorName());

        QuizAppearParameters param2 = new QuizAppearParameters();
        param2.setQuizName(name);
        param2.setQuizDescription(description);
        param2.setCreatorName(creatorName);

        Assert.assertEquals(param, param2);
        Assert.assertEquals(param.toString(), param2.toString());
        Assert.assertNotEquals(null, param);
    }

}
