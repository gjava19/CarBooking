package db;

import java.sql.SQLException;

public interface UserLoginListener {

    /**
     * save new user information in database.
     *
     * @param username   client username (unique).
     * @param password   client password (save encrypted).
     * @param secretWord client answer to question (for restore password).
     * @return           true if user created
     */
    boolean createUser(String username, String password, String secretWord)throws SQLException;


    //TODO add user delete functionality


    /**
     * change user password when restore it.
     *
     * @param username  username.
     * @param password  new password.
     * @return          true if password changed
     */
    boolean changePassword(String username, String password)throws SQLException;

    /**
     * search if user exist.
     *
     * @param username search name.
     * @return error code, 0 if all good and user dont exist. userExisit if user exist.
     */
    boolean checkUserexist(String username)throws SQLException;

    /**
     * check if username and password match (use while login)
     * @param username  current client username
     * @param password  current client password
     * @return          true if they match
     */
    boolean checkPassword(String username, String password) throws SQLException;

    /**
     * check if username and secretWord match (use while restroe password)
     * @param username      current client username
     * @param secretWord    current client secret word
     * @return              true if they match
     */
    boolean checkSecretWord(String username, String secretWord) throws SQLException;

    /**
     * by user id returns username (helps to connect two data table to each-other)
     * @param id    current username id
     * @return      username or empty string
     */
    String getUsername(int id)throws SQLException;

    /**
     * by user username returns id (helps to connect two data table to each-other)
     * @param username  current username
     * @return          id or empty string
     */
    String getUserId(String username)throws SQLException;

}
