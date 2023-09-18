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
        users.add(new User(++USER_COUNT, "Santa Maria", 34, "Actress"));
        users.add(new User(++USER_COUNT, "Bella Lucia", 47, "Driver"));
    }

    public List<User> showAllUsers() {
        return users;
    }

    public User showOneUser(int id) {
        return users.stream().
                filter(user -> user.getId() == id).findAny().orElse(null);
    }

    public void save(User user) {
        user.setId(++USER_COUNT);
        users.add(user);
    }

    public void update(int id, User updatedUser) {
        User userToNeedUpdated = showOneUser(id);

        userToNeedUpdated.setName(updatedUser.getName());
        userToNeedUpdated.setAge(updatedUser.getAge());
        userToNeedUpdated.setProfession(updatedUser.getProfession());
    }

    public void delete(int id) {
        users.removeIf(p -> p.getId() == id);
    }
}