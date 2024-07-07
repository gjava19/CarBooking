package Models;

import java.util.HashMap;

public class Quiz {
    private boolean randomQuestion;
    private boolean immediateAnswer;
    private boolean multiplePageQuiz;

    private int id;
    private int userId;

    private String name;
    private String description;

    private HashMap <String, String> questions;

    public Quiz(){}

    public Quiz(boolean randomQuestion, boolean immediateAnswer, boolean multiplePageQuiz,
                int id, int userId,
                String name, String description,
                HashMap<String, String> questions) {
        this.randomQuestion = randomQuestion;
        this.immediateAnswer = immediateAnswer;
        this.multiplePageQuiz = multiplePageQuiz;
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.questions = questions;
    }


    public boolean isRandomQuestion() {
        return randomQuestion;
    }

    public boolean isImmediateAnswer() {
        return immediateAnswer;
    }

    public boolean isMultiplePageQuiz() {
        return multiplePageQuiz;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public HashMap<String, String> getQuestions() {
        return questions;
    }

    public void setRandomQuestion(boolean randomQuestion) {
        this.randomQuestion = randomQuestion;
    }

    public void setImmediateAnswer(boolean immediateAnswer) {
        this.immediateAnswer = immediateAnswer;
    }

    public void setMultiplePageQuiz(boolean multiplePageQuiz) {
        this.multiplePageQuiz = multiplePageQuiz;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuestions(HashMap<String, String> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        String result = "";
        result += "randomQuestion = " + randomQuestion + "\r\n";
        result += "immediateAnswer = " + immediateAnswer + "\r\n";
        result += "multiplePageQuiz = " + multiplePageQuiz + "\r\n";
        result += "id = " + id + "\r\n";
        result += "userId = " + userId + "\r\n";
        result += "name = " + name + "\r\n";
        result += "description = " + description + "\r\n";
        result += "questions = " + questions.toString() + "\r\n";

        return result;
    }
}
