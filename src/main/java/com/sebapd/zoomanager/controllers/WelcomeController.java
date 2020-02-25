package com.sebapd.zoomanager.controllers;

import com.sebapd.zoomanager.services.ZooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WelcomeController {

    @Autowired
    private ZooService zooService;

    @GetMapping(value = {"/welcome", "/"})
    public String welcome(Model model) {
        model.addAttribute("zoos", zooService.findall());
        return "welcome";
    }
}
