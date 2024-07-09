package Models;

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

    @Override
    public String toString(){
        return username + " " + id;
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof Friend){
            Friend obj = (Friend) o;
            return obj.getId() == this.getId() &&
                    obj.getUsername().equals(this.getUsername());
        }
        return false;
    }
}
