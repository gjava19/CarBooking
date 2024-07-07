package Models;

import java.util.Arrays;

public class MultipleChoice implements QuestionType {
    private String question;
    private String[] choices;
    private String correctAnswer;

    public MultipleChoice(){}
    public MultipleChoice(String question, String[] choices, String correctAnswer) {
        this.question = question;
        this.choices = choices;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String getQuestion() {
        return question;
    }

    public String[] getChoices() {
        return choices;
    }

    @Override
    public String getResponse() {
        return correctAnswer;
    }

    @Override
    public void setQuestion(String question) {
        this.question = question;
    }

    public void setChoices(String[] choices) {
        this.choices = choices;
    }

    @Override
    public void setResponse(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String toString() {
        return "MultipleChoice{" +
                "question='" + question + '\'' +
                ", choices=" + Arrays.toString(choices) +
                ", correctAnswer='" + correctAnswer + '\'' +
                '}';
    }
}
