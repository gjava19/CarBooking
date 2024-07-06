package Models;


import java.util.HashSet;


public class User implements FriendInt{
    private String username;
    private String password;
    private int id;
    private HashSet<String> friends;
    private HashSet<String> sent;
    private HashSet<String> requested;

    public User(String username, String password, int id) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.friends    = new HashSet<String>();
        this.sent       = new HashSet<String>();
        this.requested  = new HashSet<String>();
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public int getId() {
        return id;
    }
    public HashSet<String> getFriends()   { return friends; };
    public HashSet<String> getSent()      { return sent; };
    public HashSet<String> getRequested() { return requested; };

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void addFriend(FriendInt user)      { friends.add(user.getUsername());}
    public void addSent(FriendInt user)        { sent.add(user.getUsername());}
    public void addRequested(FriendInt user)   { requested.add(user.getUsername());}
    public void removeFriend(FriendInt user)   { friends.remove(user.getUsername());}
    public void removeSent(FriendInt user)     { sent.remove(user.getUsername());}
    public void removeRequested(FriendInt user){ requested.remove(user.getUsername());}

    @Override
    public String toString() {

        String result = "";
        result = "  username  = " + username +"\r\n";
        result +="  password  = " + password +"\r\n";
        result +="  unique id = " + id +"\r\n";
        result +="  friends   = " + getFriends().toString() +"\r\n";
        result +="  request   = " + getSent().toString() +"\r\n";
        result +="  received  = " + getRequested().toString() +"\r\n";

        return result;
    }

    public boolean equals(Object obj) {
        if (obj == this)            return true;
        if (!(obj instanceof User)) return false;
        User input = (User)obj;

        return  input.id == id &&
                input.username == username &&
                input.password == password;

    }
}
