package Models;

public class FillInTheBlank implements QuestionType {
    private String question;
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
        return "FillInTheBlank{" +
                "question='" + question + '\'' +
                ", response='" + response + '\'' +
                '}';
    }
}
