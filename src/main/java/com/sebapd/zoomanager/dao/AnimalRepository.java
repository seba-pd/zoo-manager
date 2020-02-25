package com.sebapd.zoomanager.dao;

import com.sebapd.zoomanager.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    Animal findByAnimalName(String animalName);
    Animal deleteAnimalByAnimalName(String animalName);


}
