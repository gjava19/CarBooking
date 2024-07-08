package Models;

public class QuizAppareParameters {
    private String quizName;
    private String quizDescription;
    private String creatorName;

    public QuizAppareParameters(String quizName, String quizDescription, String creatorName) {
        this.quizName = quizName;
        this.quizDescription = quizDescription;
        this.creatorName = creatorName;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public String getQuizDescription() {
        return quizDescription;
    }

    public void setQuizDescription(String quizDescription) {
        this.quizDescription = quizDescription;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    @Override
    public String toString() {
        return "quizAppareParameters{" +
                "quizName='" + quizName + '\'' +
                ", quizDescription='" + quizDescription + '\'' +
                ", creatorName='" + creatorName + '\'' +
                '}';
    }
}
