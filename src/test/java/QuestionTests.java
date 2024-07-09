import Models.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class QuestionTests {

    @Test
    public void testFillThiBlank(){
        String question = Utils.getRandomStr();
        String response = Utils.getRandomStr();
        FillInTheBlank q = new FillInTheBlank(question, response);

        Assert.assertEquals("FillInTheBlank", q.getType());
        Assert.assertNotEquals(null, q);

        FillInTheBlank q1 = new FillInTheBlank();
        q1.setQuestion(question);
        q1.setResponse(response);
        Assert.assertEquals(q, q1);

        Assert.assertEquals(question, q.getQuestion());
        Assert.assertEquals(response, q1.getResponse());
    }

    @Test
    public void testMultipleChoices(){
        String question = Utils.getRandomStr();
        String response = Utils.getRandomStr();
        String[] choices = {response, Utils.getRandomStr(), Utils.getRandomStr()};
        MultipleChoice q = new MultipleChoice(question, choices, response);

        Assert.assertEquals("MultipleChoice", q.getType());
        Assert.assertNotEquals(null, q);

        MultipleChoice q1 = new MultipleChoice();
        q1.setQuestion(question);
        q1.setChoices(choices);
        q1.setResponse(response);
        Assert.assertEquals(q, q1);

        Assert.assertEquals(question, q.getQuestion());
        Assert.assertEquals(response, q1.getResponse());
        Assert.assertArrayEquals(choices, q.getChoices());
    }

    @Test
    public void testPictureResponse(){
        String question = Utils.getRandomStr();
        String response = Utils.getRandomStr();
        PictureResponse q = new PictureResponse(question, response);

        Assert.assertEquals("PictureResponse", q.getType());
        Assert.assertNotEquals(null, q);

        PictureResponse q1 = new PictureResponse();
        q1.setQuestion(question);
        q1.setResponse(response);
        Assert.assertEquals(q, q1);

        Assert.assertEquals(question, q.getQuestion());
        Assert.assertEquals(response, q1.getResponse());
    }

    @Test
    public void testQuestionResponse(){
        String question = Utils.getRandomStr();
        String response = Utils.getRandomStr();
        QuestionResponse q = new QuestionResponse(question, response);

        Assert.assertEquals("QuestionResponse", q.getType());
        Assert.assertNotEquals(null, q);

        QuestionResponse q1 = new QuestionResponse();
        q1.setQuestion(question);
        q1.setResponse(response);
        Assert.assertEquals(q, q1);

        Assert.assertEquals(question, q.getQuestion());
        Assert.assertEquals(response, q1.getResponse());
    }

    @Test
    public void checkEqualBetweenDifferentTypes(){
        QuestionType q1 = new FillInTheBlank();
        QuestionType q2 = new MultipleChoice();
        QuestionType q3 = new PictureResponse();
        QuestionType q4 = new QuestionResponse();

        Assert.assertNotEquals(q1, q2);
        Assert.assertNotEquals(q2, q3);
        Assert.assertNotEquals(q3, q4);
        Assert.assertNotEquals(q4, q2);
    }
}
