package Models;

public class QuestionParameters {
    private int timeSec;
    private int score;
    private int id;

    public QuestionParameters(){}

    public QuestionParameters(int timeSeconds, int score, int id) {
        this.timeSec = timeSeconds;
        this.score = score;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "QuestionParameters{" +
                "timeSec=" + timeSec +
                ", score=" + score +
                ", id=" + id +
                '}';
    }
}
