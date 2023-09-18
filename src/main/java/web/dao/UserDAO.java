package web.dao;

import org.springframework.stereotype.Component;
import web.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {
    private static int USER_COUNT;
    private List<User> users;

    {
        users = new ArrayList<>();
        users.add(new User(++USER_COUNT, "Mamma mia", 29, "Nurse"));
        users.add(new User(++USER_COUNT, "Santa Maria", 39, "Actress"));
    }

    public List<User> users() {
        return users;
    }

    public User showOneUser(int id) {
        return users.stream().
                filter(user -> user.getId() == id).findAny().orElse(null);
    }

//    public - create(){
//
//    }
//
//    public - update(){
//
//    }
//
//    public - delete (){
//
//    }
}