package com.sebapd.zoomanager.services;

import com.sebapd.zoomanager.model.Animal;
import com.sebapd.zoomanager.model.Zoo;

import java.util.List;

public interface ZooService {

    void save(Zoo zoo);
    Zoo findByZooName(String zooName);
    List<Zoo> findall();
    void setAnimal(Long animal, Long zoo);
    Zoo deleteByZooName(String zooName);

}
