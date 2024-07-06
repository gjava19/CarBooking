package Models;

import java.util.ArrayList;

public class Friend implements FriendInt{

    private String username;
    private int id;

    public Friend(String username, int id) {
        this.username = username;
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public int getId() {
        return id;
    }
}
