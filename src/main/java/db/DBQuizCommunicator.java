package db;

import Models.QuestionParameters;
import Models.QuestionType;
import Models.*;
import Models.Quiz;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class DBQuizCommunicator {
    /**
     * names of column indexes
     */
    public static int COLUMNS_QUIZ_ID = 0;
    public static int COLUMNS_QUIZ_CREATOR_ID = 1;
    public static int COLUMNS_QUIZ_NAME = 2;
    public static int COLUMNS_QUIZ_DESCRIPTION = 3;
    public static int COLUMNS_QUIZ_CREATE_TIME = 4;

    public static int COLUMNS_QUIZ_MODE_RANDOM = 5;
    public static int COLUMNS_QUIZ_MODE_PAGES = 6;
    public static int COLUMNS_QUIZ_MODE_IMMEDIATE = 7;

    public static int COLUMNS_QUIZ_IT_SELF = 8;
    public static String[] columnUserNames = {"id", "creator_id", "name", "description", "create_time",
            "mode_random", "mode_pages", "mode_immediate",
            "quiz_data"};

    private static String quizTable = "quiz";

    private String createTableSQL = "CREATE TABLE IF NOT EXISTS " + quizTable + " (" +
            columnUserNames[COLUMNS_QUIZ_ID] + " INT AUTO_INCREMENT PRIMARY KEY, " +
            columnUserNames[COLUMNS_QUIZ_CREATOR_ID] + " INT NOT NULL , " +
            columnUserNames[COLUMNS_QUIZ_NAME] + " VARCHAR(255) NOT NULL UNIQUE, " +
            columnUserNames[COLUMNS_QUIZ_DESCRIPTION] + " TEXT, " +
            columnUserNames[COLUMNS_QUIZ_CREATE_TIME] + " LONG, " +
            columnUserNames[COLUMNS_QUIZ_MODE_RANDOM] + " BOOLEAN, " +
            columnUserNames[COLUMNS_QUIZ_MODE_PAGES] + " BOOLEAN, " +
            columnUserNames[COLUMNS_QUIZ_MODE_IMMEDIATE] + " BOOLEAN, " +
            columnUserNames[COLUMNS_QUIZ_IT_SELF] + " JSON )";

    private Connection con;
    private DBUserCommunicator userCommunicator;


    /**
     * gets connection and creates table if not exist
     *
     * @param con connection pointer
     * @throws SQLException
     */
    public DBQuizCommunicator(Connection con) throws SQLException {
        this.con = con;
        Statement stmt = con.createStatement();
        stmt.execute(createTableSQL);
        System.out.println("Table created successfully.");

        this.userCommunicator = new DBUserCommunicator(con);

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
            System.out.println("Quiz already exists");
            return false;
        }

        String sql = "INSERT INTO " + quizTable + "(" +
                columnUserNames[COLUMNS_QUIZ_CREATOR_ID] + ", " +
                columnUserNames[COLUMNS_QUIZ_NAME] + ", " +
                columnUserNames[COLUMNS_QUIZ_DESCRIPTION] + ", " +
                columnUserNames[COLUMNS_QUIZ_CREATE_TIME] + ", " +
                columnUserNames[COLUMNS_QUIZ_MODE_RANDOM] + ", " +
                columnUserNames[COLUMNS_QUIZ_MODE_PAGES] + ", " +
                columnUserNames[COLUMNS_QUIZ_MODE_IMMEDIATE] + ", " +
                columnUserNames[COLUMNS_QUIZ_IT_SELF] + ") " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, newQuiz.getUserId());
        ps.setString(2, newQuiz.getName());
        ps.setString(3, newQuiz.getDescription());
        ps.setDate(4, newQuiz.getCreate_time());
        ps.setBoolean(5, newQuiz.isRandomQuestion());
        ps.setBoolean(6, newQuiz.isMultiplePageQuiz());
        ps.setBoolean(7, newQuiz.isImmediateAnswer());

        ObjectMapper objectMapper = new ObjectMapper();
        String quizDataJson = objectMapper.writeValueAsString(newQuiz.getQuestions());
        ps.setString(8, quizDataJson);

        int affectedRows = ps.executeUpdate();
        if (affectedRows == 0) throw new SQLException("Creating quiz failed, no rows affected.");

        ResultSet generatedKeys = ps.getGeneratedKeys();
        if (!generatedKeys.next()) throw new SQLException("Creating quiz failed, no ID obtained.");

        return true;
    }

    /**
     * deletes quiz from DB
     * @param quizName name of quiz
     * @return true if quiz is deleted
     */
    public boolean deleteQuiz(String quizName) throws SQLException {
        if (!checkQuizExists(quizName))
            return false;
        String sql = "delete from " + quizTable + " where name = ?"; // delete from history!!!!!!!!!
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, quizName);
        return ps.executeUpdate() > 0;
    }


    /**
     * finds quiz with id
     * @param quizId quiz id
     * @return quiz object
     * @throws SQLException
     * @throws JsonProcessingException
     */
    public Quiz getQuizById(int quizId) throws SQLException, JsonProcessingException {
        String sql = "SELECT * FROM " + quizTable +" WHERE id = ?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, quizId);
        ResultSet rs = ps.executeQuery();

        return rs.next() ? writeDataToQuizObject(rs) : null;
    }

    public int getIDByQuizName(String quizName) throws SQLException {
        String sql = "SELECT id FROM " + quizTable +" WHERE name = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, quizName);
        ResultSet rs = ps.executeQuery();

        return rs.next() ? rs.getInt(1) : -1;
    }

    /**
     * user gets quiz ny its name
     * @param quizName quiz name
     * @return quiz object
     * @throws SQLException
     * @throws JsonProcessingException
     */
    public Quiz getQuizByName(String quizName) throws SQLException, JsonProcessingException {
        String sql = "SELECT * FROM "+ quizTable + " WHERE name = ?";

        PreparedStatement ps = con.prepareStatement(sql);

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
//        quiz.setId(rs.getInt("id"));
        quiz.setUserId(rs.getInt("creator_id"));
//        quiz.setUserName(userCommunicator.getUsername(quiz.getUserId()));
        quiz.setName(rs.getString("name"));
        quiz.setDescription(rs.getString("description"));
        quiz.setCreate_time(rs.getDate("create_time"));
        quiz.setRandomQuestion(rs.getBoolean("mode_random"));
        quiz.setMultiplePageQuiz(rs.getBoolean("mode_pages"));
        quiz.setImmediateAnswer(rs.getBoolean("mode_immediate"));

        String quizDataJson = rs.getString("quiz_data");
        if(deserializeQuizStructure(quizDataJson, quiz) == false) return null;

        return quiz;
    }

    private boolean deserializeQuizStructure(String quizDataJson, Quiz quiz){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, QuestionParameters> map = objectMapper.readValue(quizDataJson, new TypeReference<Map<String, QuestionParameters>>() {});
            HashMap<QuestionType, QuestionParameters> questionMap = new HashMap<>();

            for (Map.Entry<String, QuestionParameters> entry : map.entrySet()) {
                String key = entry.getKey();
                QuestionParameters value = entry.getValue();

                if (key.startsWith("PictureResponse")) {
                    PictureResponse pictureResponse = objectMapper.readValue(key.substring("PictureResponse".length()), PictureResponse.class);
                    questionMap.put(pictureResponse, value);
                } else if (key.startsWith("QuestionResponse")) {
                    QuestionResponse questionResponse = objectMapper.readValue(key.substring("QuestionResponse".length()), QuestionResponse.class);
                    questionMap.put(questionResponse, value);
                } else if (key.startsWith("FillInTheBlank")) {
                    FillInTheBlank fillInTheBlank = objectMapper.readValue(key.substring("FillInTheBlank".length()), FillInTheBlank.class);
                    questionMap.put(fillInTheBlank, value);
                } else if (key.startsWith("MultipleChoice")) {
                    MultipleChoice multipleChoice = objectMapper.readValue(key.substring("MultipleChoice".length()), MultipleChoice.class);
                    questionMap.put(multipleChoice, value);
                }
            }

            quiz.setQuestions(questionMap);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<QuizAppearParameters> getAllQuiz() throws SQLException {
        ArrayList<QuizAppearParameters> result = new ArrayList<>();
        String sql = "SELECT creator_id, name, description FROM " + quizTable;
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int creatorId = rs.getInt("creator_id");
            String name = rs.getString("name");
            String descString = rs.getString("description");
            String creatorName = userCommunicator.getUsername(creatorId);
            result.add(new QuizAppearParameters(name, descString, creatorName));
        }
        return result;
    }

    /**
     * search if quiz exist.
     *
     * @param quiz search quiz.
     * @return true if all good and quiz don't exist.
     */
    public boolean checkQuizExists(String quiz) throws SQLException {
        String sql = "SELECT COUNT(*) FROM " + quizTable + " WHERE name = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, quiz);

        ResultSet rs = ps.executeQuery();

        return rs.next() ? rs.getInt(1) > 0 : false;
    }
}
