package web.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    public String showAllUsers(
            @RequestParam(name = "count", defaultValue = "-1") int count,
            Model model) {

        List<User> userList = userDAO.showAllUsers();

        if ((count == -1) || (count >= userList.size())) {
            model.addAttribute("users", userList);
        } else {
            model.addAttribute("users", userList.subList(0, count));
        }
        return "showAllUsers";
    }

    @GetMapping("/users/{id}")
    public String showOneUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDAO.showOneUser(id));
        return "showOneUser";
    }

    @GetMapping("/users/new")
    public String createNewUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping("/users")
    public String createForm(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "new";

        userDAO.save(user);
        return "redirect:/users";
    }

    @GetMapping("/users/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDAO.showOneUser(id));
        return "edit";
    }

    @PatchMapping("/users/{id}")
    public String update(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if(bindingResult.hasErrors()){
            return "edit";
        }
        userDAO.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable("id") int id) {
        userDAO.delete(id);
        return "redirect:/users";
    }
}