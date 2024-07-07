package Models;

public interface QuestionType {

    /**
     * any type of quiz questions should have this functionalities
     */
    /**
     *
     * @return specific type question
     */
    String getQuestion();

    /**
     *
     * @return specific type response
     */
    String getResponse();

    /**
     *
     * @param question set specific type question
     */
    void setQuestion(String question);

    /**
     *
     * @param response set specific type respose
     */
    void setResponse(String response);

}
