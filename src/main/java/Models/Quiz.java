package Models;

import java.sql.Date;
import java.util.HashMap;
import java.util.HashSet;
import Models.QuestionParameters;
import db.DBUserCommunicator;

public class Quiz {
    private boolean randomQuestion;
    private boolean immediateAnswer;
    private boolean multiplePageQuiz;


    private int id;
    private String userName;
    private int userId;

    private String name;
    private String description;
    private Date create_time;

    private HashMap<QuestionType, QuestionParameters> questions;

    public Quiz(){}

    public Quiz(int userId, String name, String description, Date create_time,
                boolean randomQuestion, boolean immediateAnswer, boolean multiplePageQuiz,
                HashMap<QuestionType, QuestionParameters> questions) {
        this.randomQuestion = randomQuestion;
        this.immediateAnswer = immediateAnswer;
        this.multiplePageQuiz = multiplePageQuiz;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.create_time = create_time;
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

    public int getUserId() { return userId;   }
    public String getUserName() { return userName;   }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    public Date getCreate_time() {
        return create_time;
    }

    public HashMap<QuestionType, QuestionParameters> getQuestions() {
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
    
    public void setUserName(String name) {
        this.userName = name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void setCreate_time(Date create_time){
        this.create_time = create_time;
    }

    public void setQuestions(HashMap<QuestionType, QuestionParameters> questions) {
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
        result += "userName = " + userName + "\r\n";
        result += "name = " + name + "\r\n";
        result += "description = " + description + "\r\n";
        result += "create tile = " + create_time + "\r\n";
        if(questions != null)result += "questions = " + questions + "\r\n";

        return result;;
    }
    
    @Override
    public boolean equals(Object o){
        if (o instanceof Quiz){
            Quiz q = (Quiz) o;
            return q.getName().equals(this.getName());
        }
        return false;
    }
}
