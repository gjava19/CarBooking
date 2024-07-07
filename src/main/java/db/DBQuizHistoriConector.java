package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBQuizHistoriConector {
    public static int COLUMN_QUIZ_HISTORY_ID = 0;
    public static int COLUMN_QUIZ_HISTORY_WRITER_ID = 1;
    public static int COLUMN_QUIZ_HISTORY_DATE = 2;
    public static int COLUMN_QUIZ_HISTORY_TIME = 3;
    public static String[] columnNames = {"id", "writer_id",  "time","score"};
    private static String quizHistoryTalbe = "quizHistory";

    private DBConnector dbConnector;

    private String createTableSQL = "CREATE TABLE IF NOT EXISTS " + quizHistoryTalbe + " (" +
    columnNames[COLUMN_QUIZ_HISTORY_ID] + " INT NOT NULL, " +
    columnNames[COLUMN_QUIZ_HISTORY_WRITER_ID] + " INT NOT NULL , " +
    columnNames[COLUMN_QUIZ_HISTORY_DATE] + " INT NOT NULL, " +
    columnNames[COLUMN_QUIZ_HISTORY_TIME] + " INT NOT NULL)";

    public DBQuizHistoriConector(DBConnector dbConnector) throws SQLException {
        this.dbConnector = dbConnector;
        Statement stmt = dbConnector.getCon().createStatement();
        stmt.execute(createTableSQL);
        System.out.println("Table created successfully.");

        this.dbConnector= new DBConnector();
    }
    //am tips ra qvizebi aqvs naweri
    // es qvizi romel iuzers aqvs naweri
    //damateba qvizis, washla, modulshi frient=d obieqtis msgavsad

    public boolean checkQuizUser(int id) throws SQLException {
        String sql = "SELECT writer_id FROM " + quizHistoryTalbe + " WHERE id = ?";
        PreparedStatement ps = dbConnector.getCon().prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        ArrayList<Integer> res;


        return rs.next() ? rs.getInt(1) > 0 : false;
    }

}
