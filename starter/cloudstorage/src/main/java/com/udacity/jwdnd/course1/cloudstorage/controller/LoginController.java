package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping()
    public ModelAndView loginPage(Authentication authentication){
        if(authentication != null && authentication.isAuthenticated())
            return new ModelAndView("redirect:/home");
        return new ModelAndView("login");
    }
}
