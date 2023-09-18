package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.dao.UserDAO;
import web.model.User;

import java.util.List;

@Controller
//@RequestMapping("/users")
public class UserController {
    private UserDAO userDAO;

    @Autowired
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

   @GetMapping("/users")
    //@GetMapping
    public String users(
            @RequestParam(name = "count", defaultValue = "-1") int count,
            Model model) {

        List<User> userList = userDAO.users();

        if ((count == -1) || (count >= userList.size())) {
            model.addAttribute("users", userList);
        } else {
            model.addAttribute("users", userList.subList(0, count));
        }
        return "users";
    }

    @GetMapping("/users/{id}")
    public String showOneUser(@PathVariable("id") int id, Model model){
        model.addAttribute("user", userDAO.showOneUser(id));
        return "showOneUser";
    }
}