package Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class MultipleChoice implements QuestionType {
    @JsonProperty("question")
    private String question;
    @JsonProperty("choices")
    private String[] choices;
    @JsonProperty("correctAnswer")
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
        String result = "MultipleChoice{\"question\":\"" + question;

        result = result + "\",\"choices\": [";
        for(int index = 0; index < choices.length; index++) {
            result = result + "\"" +choices[index] + "\"";
            if(index + 1 < choices.length) result = result + ",";
        }
        result = result + "],";

        result = result + "\"correctAnswer\":\"" + correctAnswer + "\"}";
        return result;
    }

    public static String getType() { return "MultipleChoice"; }
}
