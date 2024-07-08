package Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FillInTheBlank implements QuestionType {
    @JsonProperty("question")
    private String question;
    @JsonProperty("response")
    private String response;

    public FillInTheBlank(){}
    public FillInTheBlank(String question, String response) {
        this.question = question;
        this.response = response;
    }

    @Override
    public String getQuestion() {
        return question;
    }

    @Override
    public String getResponse() {
        return response;
    }

    @Override
    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "FillInTheBlank{\"question\":\"" + question +
                "\", \"response\":\"," + response +
                "\"}";
    }

    public String getType() { return "FillInTheBlank"; }
}
