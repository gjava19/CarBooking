import db.DBConnector;
import db.DBFriendshipCommunicator;
import db.DBUserCommunicator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static db.DBFriendshipCommunicator.FriendshipStatus.*;

public class DBFriendshipCommunicatorTests {

    private DBUserCommunicator user;
    private DBFriendshipCommunicator friend;
    private DBConnector conn;

    private String user1;
    private String user2;
    private String user3;


    @Before
    public void prepareTables() throws SQLException {
        conn = new DBConnector();
        user = new DBUserCommunicator(conn.getCon());
        friend = new DBFriendshipCommunicator(conn.getCon());

        user1 = Utils.getRandomStr();
        user2 = Utils.getRandomStr();
        user3 = Utils.getRandomStr();

        user.createUser(user1, "", "");
        user.createUser(user2, "", "");
        user.createUser(user3, "", "");
    }

    @Test
    public void testFriends() throws SQLException {
        int id1 = user.getUserId(user1);
        int id2 = user.getUserId(user2);
        int id3 = user.getUserId(user3);

        Assert.assertEquals(FRIENDSHIP_STATUS_STRANGERS, friend.getFriendshipstatus(id1, id2));
        Assert.assertEquals(FRIENDSHIP_STATUS_STRANGERS, friend.getFriendshipstatus(id2, id1));
        Assert.assertEquals(FRIENDSHIP_STATUS_STRANGERS, friend.getFriendshipstatus(id2, id3));

        Assert.assertTrue(friend.createRequest(id1, id2));
        Assert.assertEquals(FRIENDSHIP_STATUS_REQUESTED, friend.getFriendshipstatus(id1, id2));
        Assert.assertEquals(FRIENDSHIP_STATUS_RECEIVED, friend.getFriendshipstatus(id2, id1));

        Assert.assertTrue(friend.createRequest(id2, id3));
        Assert.assertEquals(FRIENDSHIP_STATUS_REQUESTED, friend.getFriendshipstatus(id2, id3));
        Assert.assertEquals(FRIENDSHIP_STATUS_RECEIVED, friend.getFriendshipstatus(id3, id2));

        Assert.assertTrue(friend.changeStatus(id1, id2, FRIENDSHIP_STATUS_FRIENDS));
        Assert.assertEquals(FRIENDSHIP_STATUS_FRIENDS, friend.getFriendshipstatus(id1, id2));

        Assert.assertTrue(friend.changeStatus(id2, id3, FRIENDSHIP_STATUS_STRANGERS));
        Assert.assertEquals(FRIENDSHIP_STATUS_STRANGERS, friend.getFriendshipstatus(id2, id3));
    }

    @After
    public void deleteTestData() throws SQLException {
        user.deleteUser(user1);
        user.deleteUser(user2);
        user.deleteUser(user3);
    }
}
