package com.example.book_store_management_system.controller;

import com.example.book_store_management_system.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String userProfile(Model model) {
        // Retrieve the authentication object from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        String username = authentication.getName();

        model.addAttribute("username", username);
        return "user-profile";
    }

    @PostMapping("/deleteAccount/{username}")
    public String deleteAccount(@PathVariable String username, HttpServletRequest request) throws IOException, ServletException {

        userService.deleteUserByName(username);
        request.logout();
        return "redirect:/login?accountDeleted";
    }
}
