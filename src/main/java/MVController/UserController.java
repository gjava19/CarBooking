package MVController;

import Models.User;
import db.DBConnector;
import db.DBFriendshipCommunicator;
import db.DBUserCommunicator;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class UserController {
    private DBUserCommunicator uCommunicator;
    private FriendshipController fCommunicator;

    /**
     * create user controller object
     * takes module which gives us info from table
     * @param connector object of connector
     */
    public UserController(DBConnector connector) {
        this.uCommunicator = new DBUserCommunicator(connector.getCon());
        this.fCommunicator = new FriendshipController(connector);
    }

    /**
     * get users name and password
     * checks if it valid and creates object with lists
     *
     * @param username given username
     * @param password given password
     * @return return user object if password is true else return null
     *
     * @throws SQLException
     */
    public User loginUser(String username, String password) throws SQLException {
        String encrypted = "";

        try {encrypted = getCryptedString(password);}
        catch (NoSuchAlgorithmException e) {throw new RuntimeException(e);}

        if(uCommunicator.checkPassword(username, encrypted)){
            int id = uCommunicator.getUserId(username);
            User newUser = new User(username, encrypted, id);

            return fCommunicator.fillUserRelations(newUser) ? newUser : null;
        }
        return null;
    }

    /**
     * creates user if not exist and returns user object
     *
     * @param username received username
     * @param password received password
     * @param secretWord received secretWord
     * @return object of new user if user allready exist old user parameters
     */
    public User createUser(String username, String password, String secretWord) throws SQLException {


        String crypted = "";
        try{crypted = getCryptedString(password);}
        catch (NoSuchAlgorithmException e) {throw new RuntimeException(e);}

        if(uCommunicator.createUser(username, crypted, secretWord)){
            int id = uCommunicator.getUserId(username);
            return new User(username, crypted, id);
        }
        return null;
    }

    /**
     * try to change password user gives secretWord and if it is simillar return true and cahnge db
     * @param username current username
     * @param password current password
     * @param secretWord current user secret word
     * @return true if password changed eslse false
     * @throws SQLException
     */
    public boolean changePasswordRequest(String username, String password, String secretWord) throws SQLException {
        if( ! uCommunicator.checkUserExists(username)) return false;
        if( ! uCommunicator.checkSecretWord(username, secretWord)) return false;

        String crypted = "";
        try{crypted = getCryptedString(password);}
        catch (NoSuchAlgorithmException e) {throw new RuntimeException(e);}

        return uCommunicator.changePassword(username, crypted);
    }

    /**
     * crypt password
     * @param password user password
     * @return crypted password
     */
    private String getCryptedString(String password) throws NoSuchAlgorithmException {
        MessageDigest md = null;
        try {md = MessageDigest.getInstance("SHA-256");}
        catch (NoSuchAlgorithmException e) {e.printStackTrace();}

        byte[] passwwordByte = password.getBytes();
        byte[] encrypted = md.digest(passwwordByte);

        StringBuffer result = new StringBuffer();

        for (int i : encrypted) {
            i &= 0xff;

            if (i < 16) result.append('0');
            result.append(Integer.toString(i, 16));
        }

        return result.toString();
    }



}
