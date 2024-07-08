package Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PictureResponse implements QuestionType {
    @JsonProperty("imagePath")
    private String imagePath;
    @JsonProperty("response")
    private String response;

    public PictureResponse(){}
    public PictureResponse(String imagePath, String response) {
        this.imagePath = imagePath;
        this.response = response;
    }

    @Override
    public String getQuestion() {
        return imagePath;
    }

    @Override
    public String getResponse() {
        return response;
    }

    @Override
    public void setQuestion(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "PictureResponse{" +
                "\"imagePath\":\"" + imagePath + "\"" +
                ",\"response\":\"" + response +
                "\"}";
    }

    public String getType() {
        return "PictureResponse";
    }
}