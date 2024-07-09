import Models.Friend;
import Models.User;
import org.junit.Assert;
import org.junit.Test;

public class UserTests {

    @Test
    public void userTests(){
        User user = new User("user", "password", 0);

        Assert.assertEquals("user", user.getUsername());
        Assert.assertEquals("password", user.getPassword());
        Assert.assertEquals(0, user.getId());

        user.setUsername("user1");
        user.setPassword("password1");
        user.setId(1);

        Assert.assertEquals("user1", user.getUsername());
        Assert.assertEquals("password1", user.getPassword());
        Assert.assertEquals(1, user.getId());

        User user1 = new User("user1", "password1", 1);
        Assert.assertEquals(user1, user);
        Assert.assertEquals(user1.toString(), user.toString());

        User user2 = new User("user2", "password1", 1);
        User user3 = new User("user3", "password1", 1);

        user1.addSent(user2);
        user1.addSent(user3);
        Assert.assertTrue(user1.getSent().contains("user3"));
        user1.removeSent(user3);
        Assert.assertTrue(user1.getSent().contains("user2"));
        Assert.assertFalse(user1.getSent().contains("user3"));
        user1.removeAllSent(user1);
        Assert.assertEquals(0, user1.getSent().size());

        user1.addRequested(user2);
        user1.addRequested(user3);
        user1.removeRequested(user3);
        Assert.assertFalse(user1.getRequested().contains(new Friend(user3.getUsername(), user3.getId())));
        user1.removeAllRequested(user1);
        Assert.assertEquals(0, user1.getRequested().size());

        user1.addFriend(user2);
        user1.addFriend(user3);
        Assert.assertTrue(user1.getFriends().contains("user3"));
        user1.removeFriend(user3);
        Assert.assertTrue(user1.getFriends().contains("user2"));
        Assert.assertFalse(user1.getFriends().contains("user3"));
        user1.removeAllFriend(user1);
        Assert.assertEquals(0, user1.getFriends().size());
    }

    @Test
    public void friendTests(){
        Friend user1 = new Friend("user1", 1);
        Friend user2 = new Friend("user1", 1);

        Assert.assertEquals("user1", user1.getUsername());
        Assert.assertEquals(1, user2.getId());
        Assert.assertNotEquals(user1, null);
        Assert.assertEquals(user1, user2);
        Assert.assertEquals(user1.toString(), user2.toString());
    }
}
