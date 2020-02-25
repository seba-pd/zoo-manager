package com.sebapd.zoomanager.controllers;

import com.sebapd.zoomanager.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/animal")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @GetMapping("/{animalName}")
    public String showAnimal(@PathVariable("animalName") String animalName, Model model){
        model.addAttribute("animal", animalService.findByAnimalName(animalName));
        return "animal";
    }
}
