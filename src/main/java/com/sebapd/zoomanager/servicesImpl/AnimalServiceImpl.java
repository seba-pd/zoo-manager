package com.sebapd.zoomanager.servicesImpl;

import com.sebapd.zoomanager.dao.AnimalRepository;
import com.sebapd.zoomanager.dao.ZooRepository;
import com.sebapd.zoomanager.exceptions.AnimalNotFoundException;
import com.sebapd.zoomanager.exceptions.ZooNotFoundException;
import com.sebapd.zoomanager.model.Animal;
import com.sebapd.zoomanager.services.AnimalService;
import javassist.NotFoundException;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {

    private AnimalRepository animalRepository;
    private ZooRepository zooRepository;

    @Autowired
    public AnimalServiceImpl(AnimalRepository animalRepository, ZooRepository zooRepository) {
        this.animalRepository = animalRepository;
        this.zooRepository = zooRepository;
    }

    @Override
    public void save(Animal animal) {
        animalRepository.save(animal);
    }

    @Override
    public Animal findByAnimalName(String animalName) {
        return animalRepository.findByAnimalName(animalName);
    }

    @Override
    public List<Animal> findAll() {
        return animalRepository.findAll();
    }

    @Override
    public void setZooToAnimal(Long animalId,Long zooId) {
            Animal updatingAnimal = animalRepository.findById(animalId).orElseThrow(() -> new AnimalNotFoundException(String.format("Animal with id: %s not found",animalId)));
            updatingAnimal.getZoos().add(zooRepository.findById(zooId).orElseThrow(() -> new ZooNotFoundException(String.format("Zoo with id: %s not found.", zooId))));
            animalRepository.save(updatingAnimal);
        }

    @Override
    public Animal deleteAnimalByName(String animalName) {
        return animalRepository.deleteAnimalByAnimalName(animalName);
    }
}
