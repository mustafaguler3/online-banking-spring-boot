package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping("/profile")
    public String profile(Principal principal, Model model){
        User user = userService.findByUsername(principal.getName());

        model.addAttribute("user",user);

        return "profile";
    }

    @RequestMapping(value = "/profile",method = RequestMethod.POST)
    public String profilePost(@ModelAttribute("user") User newUser,Principal principal,Model model){
        User user = userService.findByUsername(newUser.getUsername());
        user.setUsername(newUser.getUsername());
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setEmail(newUser.getEmail());
        user.setPhone(newUser.getPhone());

        model.addAttribute("user",user);
        userService.save(user);

        return "profile";
    }
}





















