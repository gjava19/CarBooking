import db.DBConnector;
import db.DBQuizCommunicator;
import db.DBUserCommunicator;
import org.junit.Before;

import java.sql.SQLException;

public class DBQuizCommunicatorTests {

    private DBUserCommunicator user;
    private DBConnector conn;
    private DBQuizCommunicator quizDB;

    @Before
    public void before() throws SQLException {
        conn = new DBConnector();
        quizDB = new DBQuizCommunicator((DBConnector) conn.getCon());
    }
}
