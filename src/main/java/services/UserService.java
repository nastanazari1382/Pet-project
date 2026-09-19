package services;

import entities.User;
import factories.UserFactory;

import java.util.List;

public class UserService {
    private UserFactory userFactory;

    public UserService(UserFactory userFactory){
        this.userFactory = userFactory;

    }

    public void addUser(User user){

        userFactory.create10();
    }

    public void add10(){
        userFactory.create10();
    }


    public User getUser(String username){

        for(int i = 0; i < userFactory.getUsers().size() ; i++){
            if(userFactory.users.get(i).getUsername() != null && userFactory.users.get(i).getUsername().equals(username)){
                return userFactory.users.get(i);
            }
        }
        return null;
    }

    public User login(String username, String password){

        for(int i =0;i<userFactory.getUsers().size();i++){

            if(userFactory.users.get(i).getUsername().equals(username) && userFactory.users.get(i).getPassword().equals(password)){
                return userFactory.users.get(i);
            }

        }
        return null;
    }

    public User createUser(String username, String password){

            if(getUser(username) == null){
                userFactory.createUser(username,password);
                return getUser(username);
            }
        return null;
    }
    public boolean validatePassword(String username, String password){
        if (password.length()  >= 8 && password.length() <= 16) {
            return true;
        }
        return false;
    }

}
