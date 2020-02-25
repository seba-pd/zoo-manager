package com.sebapd.zoomanager.services;

import com.sebapd.zoomanager.model.Animal;

import java.util.List;

public interface AnimalService {

    void save(Animal animal);
    Animal findByAnimalName(String animalName);
    List<Animal> findAll();
    void setZooToAnimal (Long animal, Long zoo);
    Animal deleteAnimalByName(String animalName);

}
