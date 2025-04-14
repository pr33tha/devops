package com.spacepark.park.Controller;

import com.spacepark.park.domain.User;
import com.spacepark.park.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }
    @GetMapping("/home")
    public String parkPage() {
        return "home";
    }
    @GetMapping("/park")
    public String parkDetails() {
        return "park";
    }
    @PostMapping("/signup")
    public String signup(@RequestParam String username,
                         @RequestParam String password,
                         Model model) {
        try {
            userService.registerUser(new User(username, password));
            return "index";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "signup";
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        try {
            userService.loginUser(username, password);
            return "home";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "index";
        }
    }
    @PostMapping("/home")
public String handleParkPost(Model model) {
    return "home";
}
@PostMapping("/park")
    public String showParkingDetails(@RequestParam(value = "parkingName", required = false) String parkingName, Model model) {
        model.addAttribute("parkingName", parkingName);
        return "park";
    }

}
