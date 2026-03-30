package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.UserRegistrationDTO;
import com.example.demo.service.UserService;



@Controller
public class AuthController {

	@Autowired
    private UserService userService;
	
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(ModelMap model) {
        model.addAttribute("user", new UserRegistrationDTO());
        return "register";
    }
    
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserRegistrationDTO registrationDto, ModelMap model) {
        try {
            userService.registerNewUser(registrationDto);
            return "redirect:/login?registered";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }
}