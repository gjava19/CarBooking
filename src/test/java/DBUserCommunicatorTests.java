import db.DBConnector;
import db.DBUserCommunicator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

public class DBUserCommunicatorTests {

    private DBUserCommunicator user;
    private DBConnector conn;

    private int userCountOld;

    @Before
    public void prepareTables() throws SQLException {
        conn = new DBConnector();
        user = new DBUserCommunicator(conn.getCon());

        userCountOld = 0;
        String sql = "select max(id) from user";
        Statement stmt = conn.getCon().createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next())
            userCountOld = rs.getInt(1);
    }

    @Test
    public void testUser() throws SQLException {
        String username = Utils.getRandomStr();
        String password = Utils.getRandomStr();
        String secret = Utils.getRandomStr();

        // user exists
        Assert.assertTrue(user.createUser(username, password, secret));
        int userID = user.getUserId(username);
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
        String newPassword = Utils.getRandomStr();
        Assert.assertTrue(user.changePassword(username, newPassword));
        Assert.assertFalse(user.checkPassword(username, password));
        Assert.assertTrue(user.checkPassword(username, newPassword));
        Assert.assertFalse(user.changePassword(username.substring(1), newPassword));

        // check delete user
        Assert.assertFalse(user.deleteUser(username.substring(1)));
        Assert.assertTrue(user.deleteUser(username));
        Assert.assertFalse(user.checkUserExists(username));
    }

    @After
    public void deleteTestData() throws SQLException {
        String sql = "delete from user where id > ?";
        PreparedStatement stmt = conn.getCon().prepareStatement(sql);
        stmt.setInt(1, userCountOld);
        stmt.executeUpdate();
    }
}