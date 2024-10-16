package com.bankingapplication.controller;


import com.bankingapplication.model.User;
import com.bankingapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/showRegistrationForm")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "user-registration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        userService.registerUser(user);
        redirectAttributes.addFlashAttribute("message", "User registered successfully");
        redirectAttributes.addFlashAttribute("alertType", "success");
        return "redirect:/user/showLoginForm";
    }

    @GetMapping("/showLoginForm")
    public String showLoginForm(Model model)
    {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, Model model)
    {

       User userExists = userService.checkUserExists(user.getEmailId());
       if(userExists.getPassword().equals(user.getPassword()))
       {
           model.addAttribute("username", user.getEmailId());
           model.addAttribute("fullname",
                            userExists.getFirstName()+" "+userExists.getLastName());
           return "welcome";

       }else{
           return "login";
       }
    }

    @PostMapping("/showEditForm")
    public String showEditForm(@RequestParam String username, Model model)
    {
        User userExists = userService.checkUserExists(username);
        model.addAttribute("user", userExists);
        return "edit-profile";
    }
    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") User user, Model model)
    {
        System.out.println("Email"+user.getEmailId());
        userService.updateUser(user.getEmailId(), user);

        model.addAttribute("username", user.getEmailId());
        System.out.println("Email"+user.getEmailId());
        model.addAttribute("fullname",
                user.getFirstName()+" "+user.getLastName());
        System.out.println("Name : "+user.getFirstName()+" "+user.getLastName());
        return "welcome";
    }

    @PostMapping("/delete")
    public String deleteProfile(@RequestParam String username)
    {
        User userExists = userService.checkUserExists(username);
        if(userExists != null)
        {
            userService.deleteUser(userExists);
        }

        System.out.println("Profile Deleted");

        return "success";
    }

    @PostMapping("/welcome")
    public String showWelcomePage(@RequestParam String username, Model model)
    {
        User userExists = userService.checkUserExists(username);
        model.addAttribute("username", username);
        model.addAttribute("fullname", userExists.getFirstName()+" "+userExists.getLastName());
        return "welcome";
    }
}
