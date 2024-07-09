package Models;


import com.fasterxml.jackson.annotation.JsonProperty;

public class QuestionResponse implements QuestionType {
    @JsonProperty("question")
    private String question;
    @JsonProperty("response")
    private String response;

    public QuestionResponse(){}
    public QuestionResponse(String question, String response) {
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
        return "QuestionResponse{\"question\":\"" + question +
                "\", \"response\":\"" + response +
                "\"}";
    }

    public String getType() { return "QuestionResponse"; }

    @Override
    public boolean equals(Object o){
        if (o instanceof QuestionResponse){
            QuestionResponse obj = (QuestionResponse) o;
            return obj.getQuestion().equals(this.getQuestion()) &&
                    obj.getResponse().equals(this.getResponse());
        }
        return false;
    }
}