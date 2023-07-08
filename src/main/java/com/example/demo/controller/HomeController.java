package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home(){
        return "redirect:/index";
    }
    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/signup")
    public String signup(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "signup";
    }

    @PostMapping("/signup")
    public String signupPost(@ModelAttribute("user") User user,Model model){
        if (userService.checkUserExists(user.getUsername(),user.getEmail())){
            if (userService.checkEmailExists(user.getEmail())){
                model.addAttribute("emailExists",true);
            }

            if (userService.checkUsernameExists(user.getUsername())){
                model.addAttribute("usernameExists",true);
            }
            return "signup";
        }else {
            userService.save(user);

            return "redirect:/";
        }



    }


}
