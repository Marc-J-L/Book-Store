package com.example.book_store_management_system.controller;

import com.example.book_store_management_system.repository.IUserRepository;
import com.example.book_store_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.book_store_management_system.model.MyUser;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class AuthController {

    UserService userService;
    @Autowired
    IUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("MyUser", new MyUser());
        return "register";
    }


    @PostMapping("register/user")
    public String addUser(@ModelAttribute MyUser myUser , RedirectAttributes redirectAttributes) {
        myUser.setPassword(passwordEncoder.encode(myUser.getPassword()));
        myUser.setRoles("USER");
        userRepository.save(myUser);
        redirectAttributes.addFlashAttribute("message", "User registered successfully");
        return "redirect:/login";

    }

}
