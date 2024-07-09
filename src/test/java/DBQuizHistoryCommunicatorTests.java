import db.DBConnector;
import db.DBQuizCommunicator;
import db.DBQuizHistoryCommunicator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class DBQuizHistoryCommunicatorTests {

    private DBConnector conn;
    private DBQuizHistoryCommunicator quizHist;

    @Before
    public void before() throws SQLException {
        conn = new DBConnector();
        quizHist = new DBQuizHistoryCommunicator(conn.getCon(), "quizHistoryTest");
    }

    @Test
    public void quizHistoryTest() throws SQLException {
        Assert.assertTrue(quizHist.addData(1, 2, 1, 1));
        Assert.assertTrue(quizHist.addData(1, 3, 1, 1));
        Assert.assertTrue(quizHist.addData(4, 2, 1, 1));
        Assert.assertTrue(quizHist.addData(5, 2, 1, 1));

        Assert.assertTrue(quizHist.getQuizIDsByUserID(2).contains(1));
        Assert.assertFalse(quizHist.getQuizIDsByUserID(2).contains(3));
        Assert.assertTrue(quizHist.getUserIDsByQuizID(1).contains(2));

        Assert.assertTrue(quizHist.clearHistoryByUser(2));
        Assert.assertEquals(quizHist.getQuizIDsByUserID(1).size(), 0);

        Assert.assertTrue(quizHist.clearHistoryByQuiz(1));
        Assert.assertEquals(quizHist.getUserIDsByQuizID(1).size(), 0);
    }
}
