import MVController.FriendshipController;
import MVController.UserController;
import db.DBConnector;
import db.DBUserCommunicator;
import db.DBFriendshipCommunicator;

import Models.FriendInt;
import Models.User;


import java.sql.SQLException;

public class Usera {
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

        User usr = new User("saxeli", "gvari", 1);
        System.out.println(usr.toString());

        FriendInt frnd = new User("megobari", "gvara", 2);
        System.out.println("username " + frnd.getUsername());
        System.out.println("ID " + frnd.getId());


        // use mvc controller and models
        System.out.println("\r\n\n\n<<<<<<<<<<<<< use of mvc and models >>>>>>>>>>>>>\r\n\n\n");
        UserController uController = new UserController(dbCon);
        FriendshipController fcontroller = new FriendshipController(dbCon);

        User newUser1 = uController.createUser("user1", "password1", "secret1");
        User newUser2 = uController.createUser("user2", "password2", "secret2");
        User newUser3 = uController.createUser("user3", "password3", "secret3");

        if(newUser1 != null) System.out.println("user 1 created");
        else newUser1 = uController.loginUser("user1", "password1");

        if(newUser2 != null) System.out.println("user 2 created");
        else newUser2 = uController.loginUser("user2", "password2");

        if(newUser3 != null) System.out.println("user 3 created");
        else newUser3 = uController.loginUser("user3", "password3");

        if(newUser1 == null || newUser2 == null || newUser3 == null) {
            System.out.println("Error while login user");
            return;
        }


//        if(uController.changePasswordRequest("user1", "pass1", "secret1")) System.out.println("user 1 changed password");
//        if(uController.changePasswordRequest("user2", "pass2", "secret2")) System.out.println("user 2 changed password");
//        if(uController.changePasswordRequest("user3", "pass3", "secret3")) System.out.println("user 2 changed password");



        if(fcontroller.sendFriendship(newUser1, newUser2)){
            System.out.println("user 1 sent friendship");
        }
        if(fcontroller.approveFriendship(newUser2, newUser1)){
            System.out.println("user 2 approved friendship");
        }

        if(fcontroller.sendFriendship(newUser3, newUser2)){
            System.out.println("user 3 sent friendship");
        }
        if(fcontroller.rejectFriendship(newUser2, newUser3)){
            System.out.println("user 2 rejected friendship");
        }

        System.out.println(newUser1);
        System.out.println(newUser2);
        System.out.println(newUser3);

//        fcontroller.fillUserRelations(newUser1);
//        fcontroller.fillUserRelations(newUser2);
//        fcontroller.fillUserRelations(newUser3);
//
//        System.out.println(newUser1);
//        System.out.println(newUser2);
//        System.out.println(newUser3);

    }
}
