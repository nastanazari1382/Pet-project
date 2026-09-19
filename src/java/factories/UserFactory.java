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

    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> pass = new ArrayList<>();


    public void create10(){
names.add("Saige");
names.add("Bowen");
names.add("Lukas");
names.add("Peter");
names.add("Hest");
names.add("Kost");
names.add("Bo");
names.add("Tim");
names.add("Fede");
names.add("Jens");

pass.add("1234");
pass.add("4321");
pass.add("3333");
pass.add("4444");
pass.add("5555");
pass.add("6666");
pass.add("7777");
pass.add("8888");
pass.add("9999");
pass.add("9990");


for(int i=0;i<names.size();i++){
    createUser(names.get(i),pass.get(i));
}
    }
    public List<User> getUsers() {
        return users;
    }



}
