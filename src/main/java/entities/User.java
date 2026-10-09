package entities;


public class User {
    String username;
    String password;
    String displayName;
    String id;

    public User(String username, String password, String displayName, String id) {


        this.id = id;
        this.displayName=displayName;
        this.username = username;
        this.password = password;

    }

    public String toString() {
        return "User{" + "username=" + username + ", password=" + password + '}';
    }


    public String getUsername() {
        return username;
    }
    public String getUUID(){
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
