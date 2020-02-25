package com.sebapd.zoomanager.servicesImpl;

import com.sebapd.zoomanager.dao.AnimalRepository;
import com.sebapd.zoomanager.dao.ZooRepository;
import com.sebapd.zoomanager.exceptions.AnimalNotFoundException;
import com.sebapd.zoomanager.exceptions.ZooNotFoundException;
import com.sebapd.zoomanager.model.Zoo;
import com.sebapd.zoomanager.services.ZooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZooServiceImpl implements ZooService {

    private ZooRepository zooRepository;
    private AnimalRepository animalRepository;

    @Autowired
    public ZooServiceImpl(ZooRepository zooRepository, AnimalRepository animalRepository) {
        this.zooRepository = zooRepository;
        this.animalRepository = animalRepository;
    }

    @Override
    public void save(Zoo zoo) { zooRepository.save(zoo);
    }

    @Override
    public Zoo findByZooName(String zooName) {
        return zooRepository.findByZooName(zooName);
    }

    @Override
    public List<Zoo> findall() {
        return zooRepository.findAll();
    }

    @Override
    public void setAnimal(Long animalId, Long zooid) {
        Zoo updatingZoo = zooRepository.findById(zooid).orElseThrow(() -> new ZooNotFoundException(String.format("Zoo with id: %s not found.",zooid)));
        updatingZoo.getAnimals().add(animalRepository.findById(animalId).orElseThrow(() -> new AnimalNotFoundException(String.format("Animal with id: %s not found", animalId))));
        zooRepository.save(updatingZoo);
    }

    @Override
    public Zoo deleteByZooName(String zooName) {
        return zooRepository.deleteByZooName(zooName);
    }
}
