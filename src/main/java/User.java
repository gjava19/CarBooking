import db.DBConnector;
import db.DBUserCommunicator;
import db.DBFriendshipCommunicator;

import java.sql.SQLException;

public class User {
    public static void main(String[] args) throws SQLException {

        DBConnector dbCon = new DBConnector();
        DBUserCommunicator user = new DBUserCommunicator(dbCon.getCon());
        DBFriendshipCommunicator friends = new DBFriendshipCommunicator(dbCon.getCon());

        String user1 = "rame 1";
        String user2 = "rame 2";

        if(user.createUser(user1, "rume", "rime")) System.out.println("User " + user1 + " created");
        else System.out.println("User " + user1 + " not created");

        if(user.createUser(user2, "rume", "rime")) System.out.println("User " + user2 + " created");
        else System.out.println("User " + user2 + " not created");

        if(user.checkUserExists("rame 1"))System.out.println("User rame 1 exists");
        if(user.checkUserExists("rame 2"))System.out.println("User rame 2 exists");
        int user1id = user.getUserId(user1);
        int user2id = user.getUserId(user2);

        System.out.println("user1 id = " + user1id);
        System.out.println("user2 id = " + user2id);

        friends.createRequest(user1id, user2id);
        System.out.println("user 2 received " + friends.getReceivedRequest(user2id));
        System.out.println("user 1 sent "     + friends.getSentRequest(user1id));
        System.out.println("user 2 friends "  + friends.getFriends(user2id));
        System.out.println("user 1 friends "  + friends.getFriends(user1id));

        friends.changeStatus(user1id, user2id, DBFriendshipCommunicator.FriendshipStatus.FRIENDSHIP_STATUS_FRIENDS);
        System.out.println("user 2 received " + friends.getReceivedRequest(user2id));
        System.out.println("user 1 sent "     + friends.getSentRequest(user1id));
        System.out.println("user 2 friends "  + friends.getFriends(user2id));
        System.out.println("user 1 friends "  + friends.getFriends(user1id));


    }
}
