package application.controller;

import application.model.User;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public String createUser(@RequestParam("name") String name, @RequestParam("surname") String surname,
                             @RequestParam("city") String city, @RequestParam("age") int age) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setCity(city);
        user.setAge(age);
        userService.addUser(user);
        return ("redirect:/users");
    }

    @GetMapping
    public String getListUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "listUsers";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.readUser(id));
        return "show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.readUser(id));
        return "edit";

    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @RequestParam("id") int id) {
        userService.updateUser(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

}
