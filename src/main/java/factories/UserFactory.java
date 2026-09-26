package factories;

import entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserFactory {

    public List<User> users = new ArrayList<>();


    public User createUser(String username, String password) {
        User user = new User(username,password);
        users.add(user);
        return user;
    }


    public List<User> getUsers() {
        return users;
    }



}
