package persistence;
import entities.User;
import exceptions.DatabaseException;
import org.postgresql.jdbc.TimestampUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserMapper {

    ConnectionPool connectionPool;
    private static final Logger logger =
            LoggerFactory.getLogger(UserMapper.class);

    public UserMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }
    public User login (String userName, String password){

        return null;
    }

    public User getUserByUserName(String userName) throws DatabaseException, SQLException {
        User user = null;

        String query = "SELECT id, password , email " +
                "FROM public.\"User\"" +
                "WHERE username = ?";

        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement stm = connection.prepareStatement(query)){

            stm.setString(1, userName);



            try (ResultSet rs = stm.executeQuery(); ){
                if (rs.next()) {
                    String id = rs.getString("id");
                    String username = rs.getString("email");
                    String password = rs.getString("password");


                    user = new User(userName,password,username,id);

                }

            }

        }

        return user;
    }




    public User createUser(User user) throws DatabaseException, SQLException {

        String query = "INSERT INTO public.\"User\" (id, email, username, password, \"creationDate\" )" + " VALUES (?, ?, ?, ?, ?)";



        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement stm = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stm.setString(1, user.getUUID());
            stm.setString(2, user.getUsername());
            stm.setString(3, user.getUsername());


            stm.setString(4, user.getPassword());

            stm.setTimestamp(5, Timestamp.from(Instant.now()));

            stm.executeUpdate();


        }

return user;

    }
}