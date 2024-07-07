package Models;


public class QuestionResponse implements QuestionType {
    private String question;
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
        return "QuestionResponse{" +
                "question='" + question + '\'' +
                ", response='" + response + '\'' +
                '}';
    }
}