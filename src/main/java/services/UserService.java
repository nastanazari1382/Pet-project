package services;

import entities.User;
import exceptions.DatabaseException;
import exceptions.IllegalUserDataException;
import factories.UserFactory;
import org.postgresql.jdbc.UUIDArrayAssistant;
import persistence.ConnectionPool;
import persistence.UserMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class UserService {
    private UserFactory userFactory = new UserFactory();
    String user="postgres"  ;
    String password= "postgres";
    String url ="jdbc:postgresql://localhost:5432/keyspiracy2";
    String db = "keyspiracy2";
    //ConnectionPool.getInstance(user,password,url,db);
    ConnectionPool connectionPool;

    public UserMapper userMapper = new UserMapper(ConnectionPool.getInstance(user,password,url,db));
    public UserService(){


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




    public User createUser(String username, String password) throws IllegalUserDataException, DatabaseException, SQLException {
        try{
        if(username == null || username.isBlank() || password.isBlank() || validatePassword(password)==false){
              throw new IllegalUserDataException("Illegal data!!");
            }} catch (IllegalUserDataException e){
            System.out.println("fejl!!");
        }

            if(getUser(username) == null && userMapper.getUserByUserName(username)==null){

                String displayname = username;


                String id = String.valueOf(UUID.randomUUID());


                User user = new User(username,password,displayname,id);



                userMapper.createUser(user);

                userFactory.users.add(user);


                return user;
            }
        return null;
    }
    public boolean validatePassword(String password){
        if (password.length()  >= 8 && password.length() <= 16) {
            return true;
        }
        return false;
    }

}
