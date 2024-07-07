package db;

import Models.Quiz;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.interceptors.QueryInterceptor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class DBQuizCommunicator {
    /**
     * names of column indexes
     */
    public static int COLUMNS_QUIZ_ID = 0;
    public static int COLUMNS_QUIZ_CREATOR_ID = 1;
    public static int COLUMNS_QUIZ_NAME = 2;
    public static int COLUMNS_QUIZ_DESCRIPTION = 3;

    public static int COLUMNS_QUIZ_MODE_RANDOM = 4;
    public static int COLUMNS_QUIZ_MODE_PAGES = 5;
    public static int COLUMNS_QUIZ_MODE_IMMEDIATE = 6;

    public static int COLUMNS_QUIZ_IT_SELF = 7;
    public static String[] columnUserNames = {"id", "creator_id", "name", "description",
            "mode_random", "mode_pages", "mode_immediate",
            "quiz_data"};

    private static String quizTable = "quiz";

    private String createTableSQL = "CREATE TABLE IF NOT EXISTS " + quizTable + " (" +
            columnUserNames[COLUMNS_QUIZ_ID] + " INT AUTO_INCREMENT PRIMARY KEY, " +
            columnUserNames[COLUMNS_QUIZ_CREATOR_ID] + " INT NOT NULL , " +
            columnUserNames[COLUMNS_QUIZ_NAME] + " VARCHAR(255) NOT NULL UNIQUE, " +
            columnUserNames[COLUMNS_QUIZ_DESCRIPTION] + " TEXT, " +
            columnUserNames[COLUMNS_QUIZ_MODE_RANDOM] + " BOOLEAN, " +
            columnUserNames[COLUMNS_QUIZ_MODE_PAGES] + " BOOLEAN, " +
            columnUserNames[COLUMNS_QUIZ_MODE_IMMEDIATE] + " BOOLEAN, " +
            columnUserNames[COLUMNS_QUIZ_IT_SELF] + " JSON )";

    private DBConnector con;
    private DBUserCommunicator userCommunicator;


    /**
     * gets connection and creates table if not exist
     *
     * @param con connection pointer
     * @throws SQLException
     */
    public DBQuizCommunicator(DBConnector con) throws SQLException {
        this.con = con;
        Statement stmt = con.getCon().createStatement();
        stmt.execute(createTableSQL);
        System.out.println("Table created successfully.");

        this.userCommunicator = new DBUserCommunicator(con.getCon());

    }

    /**
     * create quiz in quiz table
     *
     * @param newQuiz quiz information object
     * @return true if create
     * @throws SQLException
     * @throws JsonProcessingException
     */
    public boolean createQuiz(Quiz newQuiz) throws SQLException, JsonProcessingException {
        if (checkQuizExists(newQuiz.getName())) {
            System.out.println("User already exists");
            return false;
        }

        String sql = "INSERT INTO " + quizTable +
                " (" +  columnUserNames[COLUMNS_QUIZ_ID] + ", " +
                        columnUserNames[COLUMNS_QUIZ_CREATOR_ID] + ", " +
                        columnUserNames[COLUMNS_QUIZ_NAME] + ", " +
                        columnUserNames[COLUMNS_QUIZ_DESCRIPTION] + ", " +
                        columnUserNames[COLUMNS_QUIZ_MODE_RANDOM] + ", " +
                        columnUserNames[COLUMNS_QUIZ_MODE_PAGES] + ", " +
                        columnUserNames[COLUMNS_QUIZ_MODE_IMMEDIATE] + ", " +
                        columnUserNames[COLUMNS_QUIZ_IT_SELF] + ") " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.getCon().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, newQuiz.getId());
        ps.setInt(2, newQuiz.getUserId());
        ps.setString(3, newQuiz.getName());
        ps.setString(4, newQuiz.getDescription());
        ps.setBoolean(5, newQuiz.isRandomQuestion());
        ps.setBoolean(6, newQuiz.isMultiplePageQuiz());
        ps.setBoolean(7, newQuiz.isImmediateAnswer());

        ObjectMapper objectMapper = new ObjectMapper();
        String quizDataJson = objectMapper.writeValueAsString(newQuiz.getQuestions());
        ps.setString(8, quizDataJson);

        int affectedRows = ps.executeUpdate();
        if (affectedRows == 0) throw new SQLException("Creating user failed, no rows affected.");

        ResultSet generatedKeys = ps.getGeneratedKeys();
        if (!generatedKeys.next()) throw new SQLException("Creating user failed, no ID obtained.");

        return true;
    }


    /**
     * finds quiz with id
     * @param quizId quiz id
     * @return quiz object
     * @throws SQLException
     * @throws JsonProcessingException
     */
    public Quiz getQuizById(int quizId) throws SQLException, JsonProcessingException {
        String sql = "SELECT id, creator_id, name, description, mode_random, mode_pages, mode_immediate, quiz_data " +
                "FROM " +quizTable+" WHERE id = ?";

        PreparedStatement ps = con.getCon().prepareStatement(sql);

        ps.setInt(1, quizId);
        ResultSet rs = ps.executeQuery();

        return rs.next() ? writeDataToQuizObject(rs) : null;
    }

    /**
     * user gets quiz ny its name
     * @param quizName quiz name
     * @return quiz object
     * @throws SQLException
     * @throws JsonProcessingException
     */
    public Quiz getQuizByName(String quizName) throws SQLException, JsonProcessingException {
        String sql = "SELECT id, creator_id, name, description, mode_random, mode_pages, mode_immediate, quiz_data " +
                "FROM "+quizTable+" WHERE name = ?";

        PreparedStatement ps = con.getCon().prepareStatement(sql);

        ps.setString(1, quizName);
        ResultSet rs = ps.executeQuery();
        return rs.next() ? writeDataToQuizObject(rs) : null;
    }

    /**
     * fill quiz with received database
     * @param rs received
     * @return quiz object
     * @throws SQLException
     * @throws JsonProcessingException
     */
    private Quiz writeDataToQuizObject(ResultSet rs) throws SQLException, JsonProcessingException {
        Quiz quiz = new Quiz();
        quiz.setId(rs.getInt("id"));
        quiz.setUserId(rs.getInt("creator_id"));
        quiz.setName(rs.getString("name"));
        quiz.setDescription(rs.getString("description"));
        quiz.setRandomQuestion(rs.getBoolean("mode_random"));
        quiz.setMultiplePageQuiz(rs.getBoolean("mode_pages"));
        quiz.setImmediateAnswer(rs.getBoolean("mode_immediate"));

        String quizDataJson = rs.getString("quiz_data");
        ObjectMapper objectMapper = new ObjectMapper();
        Object quizData = objectMapper.readValue(quizDataJson, Object.class);

        quiz.setQuestions((HashMap<String, String>) quizData);

        return quiz;
    }


        /**
         * search if quiz exist.
         *
         * @param quiz search quiz.
         * @return true if all good and quiz don't exist.
         */
    public boolean checkQuizExists(String quiz) throws SQLException {
        String sql = "SELECT COUNT(*) FROM " + quizTable + " WHERE name = ?";
        PreparedStatement ps = con.getCon().prepareStatement(sql);
        ps.setString(1, quiz);

        ResultSet rs = ps.executeQuery();

        return rs.next() ? rs.getInt(1) > 0 : false;
    }
}
