package com.sebapd.zoomanager.controllers;


import com.sebapd.zoomanager.model.Animal;
import com.sebapd.zoomanager.model.Zoo;
import com.sebapd.zoomanager.services.AnimalService;
import com.sebapd.zoomanager.services.ZooService;
import com.sebapd.zoomanager.validators.AnimalValidator;
import com.sebapd.zoomanager.validators.ZooValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private ZooService zooService;
    private AnimalService animalService;
    private ZooValidator zooValidator;
    private AnimalValidator animalValidator;

    @Autowired
    public AdminController(ZooService zooService, AnimalService animalService, ZooValidator zooValidator, AnimalValidator animalValidator) {
        this.zooService = zooService;
        this.animalService = animalService;
        this.zooValidator = zooValidator;
        this.animalValidator = animalValidator;
    }

    @GetMapping
    public String adminPage(){
        return "admin_page";
    }

    @GetMapping("/add-zoo")
    public String addZoo(Model model){
        model.addAttribute("zooForm", new Zoo());
        return "add-zoo";
    }

    @PostMapping("/add-zoo")
    public String addZoo(@Valid @ModelAttribute("zooForm") Zoo zooForm, @RequestParam("file") MultipartFile file, BindingResult bindingResult) throws IOException {
        zooValidator.validate(zooForm,bindingResult);
        if(bindingResult.hasErrors())
            return "add-zoo";

        zooForm.setZooImage(file.getBytes());
        zooService.save(zooForm);
        return "redirect:../admin";
    }

    @GetMapping("/add-animal")
    public String addAnimal(Model model){
        model.addAttribute("animalForm", new Animal());
        return "add-animal";
    }

    @PostMapping("/add-animal")
    public String addAnimal(@Valid @ModelAttribute("animalForm") Animal animalForm, @RequestParam("file") MultipartFile file, BindingResult bindingResult) throws IOException {
        animalValidator.validate(animalForm,bindingResult);
        if(bindingResult.hasErrors())
            return "add-animal";

        animalForm.setAnimalImage(file.getBytes());
        animalService.save(animalForm);
        return "redirect:../admin";
    }

    @GetMapping("/set-animal-to-zoo")
    public String setAnimalToZoo(Model model){
        model.addAttribute("animals", animalService.findAll());
        model.addAttribute("zoos", zooService.findall());
        return "set-animal-to-zoo";
    }

    @PostMapping("/set-animal-to-zoo")
    public String setAnimalToZoo(@ModelAttribute("zoo") String zoo, @ModelAttribute("animal") String animal){
        animalService.setZooToAnimal(Long.parseLong(animal),Long.parseLong(zoo));
        zooService.setAnimal(Long.parseLong(animal),Long.parseLong(zoo));
        return "redirect:../admin";
    }

    @DeleteMapping("/delete-zoo/{zoo-name}")
    @ResponseBody
    public ResponseEntity<String> deleteZoo(@PathVariable("zoo-name") String zooName){

        Zoo zoo = zooService.deleteByZooName(zooName);

        if (zoo==null)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Wrong zoo name!");

        return ResponseEntity.status(HttpStatus.OK).body("Delete Success!");
    }

    @DeleteMapping("/delete-animal/{animal-name}")
    @ResponseBody
    public ResponseEntity<String> deleteAnimal(@PathVariable("animal-name") String animalName){
        Animal animal = animalService.deleteAnimalByName(animalName);

        if(animal == null)
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Wrong animal name!");

        return ResponseEntity.status(HttpStatus.OK).body("Delete Success!");
    }
}
