package db;

import java.sql.*;
import java.util.ArrayList;

public class DBQuizHistoryCommunicator {
    public static int COLUMN_QUIZ_HISTORY_ID = 0;
    public static int COLUMN_QUIZ_HISTORY_WRITER_ID = 1;
    public static int COLUMN_QUIZ_HISTORY_DATE = 2;
    public static int COLUMN_QUIZ_HISTORY_TIME = 3;
    public static String[] columnNames = {"id", "writer_id", "time","score"};
    private static String quizHistoryTable = "quizHistory";

    private Connection con;

    private String createTableSQL = "CREATE TABLE IF NOT EXISTS " + quizHistoryTable + " (" +
    columnNames[COLUMN_QUIZ_HISTORY_ID] + " INT NOT NULL, " +
    columnNames[COLUMN_QUIZ_HISTORY_WRITER_ID] + " INT NOT NULL , " +
    columnNames[COLUMN_QUIZ_HISTORY_DATE] + " INT NOT NULL, " +
    columnNames[COLUMN_QUIZ_HISTORY_TIME] + " INT NOT NULL)";

    public DBQuizHistoryCommunicator(Connection con) throws SQLException {
        this.con = con;
        Statement stmt = con.createStatement();
        stmt.execute(createTableSQL);
        System.out.println("Table created successfully.");
    }

    /**
     *
     * @param quizID
     * @param userID
     * @param time
     * @param score
     * @return add row into DB
     * @throws SQLException
     */
    public boolean addData(int quizID, int userID, int time, int score) throws SQLException {
        String sql = "insert into " + quizHistoryTable + " (id, writer_id, time, score) values (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, quizID);
        ps.setInt(2, userID);
        ps.setInt(3, time);
        ps.setInt(4, score);
        return ps.executeUpdate() > 0;
    }

    /**
     *
     * @param quizID
     * @return deletes rows by quizID
     * @throws SQLException
     */
    public boolean clearHistoryByQuiz(int quizID) throws SQLException {
        String sql = "delete from " + quizHistoryTable + " where id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, quizID);
        ps.executeUpdate();
        return true;
    }

    /**
     *
     * @param userID
     * @return delete rows by UserID
     * @throws SQLException
     */
    public boolean clearHistoryByUser(int userID) throws SQLException {
        String sql = "delete from " + quizHistoryTable + " where writer_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, userID);
        ps.executeUpdate();
        return true;
    }


    /**
     *
     * @param userId
     * @return list of quiz id
     * @throws SQLException
     */
    public ArrayList<Integer> getQuizIDsByUserID(int userId) throws SQLException {
        String sql = "SELECT id FROM " + quizHistoryTable + " WHERE writer_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, userId);

        ResultSet rs = ps.executeQuery();
        ArrayList<Integer> res = new ArrayList<>();

        while (rs.next()){
            res.add(rs.getInt(1));
        }

        return res;
    }

    /**
     *
     * @param quizId
     * @return list of user's id
     * @throws SQLException
     */
    public ArrayList<Integer> getUserIDsByQuizID(int quizId) throws SQLException {
        String sql = "SELECT writer_id FROM " + quizHistoryTable + " WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, quizId);

        ResultSet rs = ps.executeQuery();
        ArrayList<Integer> res = new ArrayList<>();

        while (rs.next()){
            res.add(rs.getInt(1));
        }

        return res;
    }

}
