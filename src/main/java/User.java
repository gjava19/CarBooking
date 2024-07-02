import db.DBCommunicator;
import db.FriendshipListener;

import java.sql.SQLException;

public class User {
    public static void main(String[] args) throws SQLException {
        DBCommunicator db = new DBCommunicator();
        String user1 = "rame 1";
        String user2 = "rame 2";

        if(db.createUser(user1, "rume", "rime")) System.out.println("User " + user1 + " created");
        else System.out.println("User " + user1 + " not created");

        if(db.createUser(user2, "rume", "rime")) System.out.println("User " + user2 + " created");
        else System.out.println("User " + user2 + " not created");

        if(db.checkUserexist("rame 1"))System.out.println("User rame 1 exists");
        if(db.checkUserexist("rame 2"))System.out.println("User rame 2 exists");
        int user1id = Integer.parseInt(db.getUserId(user1));
        int user2id = Integer.parseInt(db.getUserId(user2));

        System.out.println("user1 id = " + user1id);
        System.out.println("user2 id = " + user2id);

        db.createRequest(user1id, user2id);
        System.out.println("user 2 received " + db.getReceivedRequest(user2id));
        System.out.println("user 1 sent "     + db.getSentRequest(user1id));
        System.out.println("user 2 friends "  + db.getFriends(user2id));
        System.out.println("user 1 friends "  + db.getFriends(user1id));

        db.changeStatus(user1id, user2id, FriendshipListener.FriendshipStatus.FRIENDSHIP_STATUS_FRIENDS);
        System.out.println("user 2 received " + db.getReceivedRequest(user2id));
        System.out.println("user 1 sent "     + db.getSentRequest(user1id));
        System.out.println("user 2 friends "  + db.getFriends(user2id));
        System.out.println("user 1 friends "  + db.getFriends(user1id));


    }
}
