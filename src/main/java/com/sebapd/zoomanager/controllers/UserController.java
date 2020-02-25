package com.sebapd.zoomanager.controllers;

import com.sebapd.zoomanager.validators.UserValidator;
import com.sebapd.zoomanager.model.User;
import com.sebapd.zoomanager.services.SecurityService;
import com.sebapd.zoomanager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {

    private UserValidator userValidator;
    private UserService userService;
    private SecurityService securityService;

    @Autowired
    public UserController(UserValidator userValidator, UserService userService, SecurityService securityService) {
        this.userValidator = userValidator;
        this.userService = userService;
        this.securityService = securityService;
    }

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("userForm", new User());
        return "registration";
    }
    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult){
        userValidator.validate(userForm,bindingResult);
        if(bindingResult.hasErrors())
            return "registration";

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(),userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout){
        if (error != null){
            model.addAttribute("error", "Your username and password are invalid");
        }
        if(logout != null)
            model.addAttribute("logout", "You have been logged out");

        return "login";
    }
}
