package com.example.Ilin_user_registration.controller;

import com.example.Ilin_user_registration.model.User;
import com.example.Ilin_user_registration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return "redirect:/register?error"; // Если email занят
        }
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
        return "redirect:/register?success";
    }
}
