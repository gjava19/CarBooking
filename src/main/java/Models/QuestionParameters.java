package Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuestionParameters {
    @JsonProperty("timeSec")
    private int timeSec;
    @JsonProperty("score")
    private int score;
    @JsonProperty("index")
    private int index;
    @JsonProperty("id")
    private int id;

    public QuestionParameters(){}

    public QuestionParameters(int timeSeconds, int score, int index) {
        this.timeSec = timeSeconds;
        this.score = score;
        this.index = index;
    }

    public int getTimeSec() {
        return timeSec;
    }

    public void setTimeSec(int timeSec) {
        this.timeSec = timeSec;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "QuestionParameters{" +
                "\"timeSec\"=\"" + timeSec +
                "\", \"score\"=\"" + score +
                "\", \"index\"=\"" + index +
                "\", \"id\"=\"" + id +
                "\"}";
    }
}
