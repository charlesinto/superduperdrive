package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.apiModel.request.SignupRequest;
import com.udacity.jwdnd.course1.cloudstorage.apiModel.response.SignupResponse;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    private final UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public String signUpPage(){
        return "signup";
    }

    @PostMapping()
    public ModelAndView signupUser(@ModelAttribute SignupRequest data, Model model){
        SignupResponse signupResponse = userService.signupUser(data);

        if(signupResponse.getMessage() == "Login successful")
            return new ModelAndView("redirect:/home");

        model.addAttribute("signupError", signupResponse.getMessage());

        return new ModelAndView("signup");
    }
}
