package persistence;
import entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    ConnectionPool connectionPool;
    private static final Logger logger =
            LoggerFactory.getLogger(UserMapper.class);

    public UserMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }
    public User login (String userName, String password){

    }

    public User getUserByUserName(String userName) throws SQLException {
        User user = null;

        String query = "SELECT id, password , email " +
                "FROM public.\"User\"" +
                "WHERE username = ?";

     try(Connection connection = connectionPool.getConnection();
        PreparedStatement stm = connection.prepareStatement(query)){
         stm.setString(1,userName);
         try (ResultSet rs = stm.executeQuery(); ){
             if (rs.next()) {
                 String id = rs.getString("id");
                 String email = rs.getString("email");
                 String password = rs.getString("password");

                 user = new User(userName,password);

             }

         }

     }





    }
}