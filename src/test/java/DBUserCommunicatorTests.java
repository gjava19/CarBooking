import db.DBConnector;
import db.DBUserCommunicator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.Random;

public class DBUserCommunicatorTests {

    private DBUserCommunicator user;
    private DBConnector conn;

    @Before
    public void prepareTables() {
        conn = new DBConnector();
        user = new DBUserCommunicator(conn.getCon());
    }

    @Test
    public void testUser() throws SQLException {
        int userCountOld = 0;
        String sql = "select COUNT(*) from user";
        Statement stmt = conn.getCon().createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next())
            userCountOld = rs.getInt(1);

        int userID = userCountOld + 1;
        String username = getRandomStr();
        String password = getRandomStr();
        String secret = getRandomStr();

        // user exists
        Assert.assertTrue(user.createUser(username, password, secret));
        Assert.assertEquals(userID, user.getUserId(username));
        Assert.assertTrue(user.checkUserExists(username));
        Assert.assertEquals(username, user.getUsername(userID));
        Assert.assertFalse(user.createUser(username, "", ""));
        Assert.assertEquals(-1, user.getUserId(username.substring(1)));
        Assert.assertEquals("", user.getUsername(userID + 1));

        // password
        Assert.assertTrue(user.checkPassword(username, password));
        Assert.assertFalse(user.checkPassword(username, password.substring(1)));
        Assert.assertFalse(user.checkPassword(username.substring(1), password));

        // secret word
        Assert.assertTrue(user.checkSecretWord(username, secret));
        Assert.assertFalse(user.checkSecretWord(username, secret.substring(1)));
        Assert.assertFalse(user.checkSecretWord(username.substring(1), secret));

        // check updated password
        String newPassword = getRandomStr();
        Assert.assertTrue(user.changePassword(username, newPassword));
        Assert.assertFalse(user.checkPassword(username, password));
        Assert.assertTrue(user.checkPassword(username, newPassword));

    }

    private String getRandomStr(){
        String res = "";
        for (int i = 0; i < 15; i++){
            res += (char)(new Random().nextInt(125 - 65) + 65);
        }
        return res;
    }
}
