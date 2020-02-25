package com.sebapd.zoomanager.controllers;

import com.sebapd.zoomanager.services.ZooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "zoo")
public class ZooController {

    @Autowired
    private ZooService zooService;

    @GetMapping("/{zooName}")
    public String showZoo(@PathVariable("zooName") String zooName, Model model){
        model.addAttribute("zoo", zooService.findByZooName(zooName));
        return "zoo";
    }
}
