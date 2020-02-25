package com.sebapd.zoomanager.model;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Set;

@Entity
public class Animal {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "animal_name")
    private String animalName;
    @Lob
    @Column(name = "description")
    private String animalDescription;
    @Lob
    @Column(name = "animal_image")
    private byte[] animalImage;
    @ManyToMany(mappedBy = "animals")
    private Set<Zoo> zoos;

    public Animal(String animalName, String animalDescription, byte[] animalImage, Set<Zoo> zoos) {
        this.animalName = animalName;
        this.animalDescription = animalDescription;
        this.animalImage = animalImage;
        this.zoos = zoos;
    }

    public Animal() {
    }

    public String getAnimalName() {
        return animalName;
    }

    public Set<Zoo> getZoos() {
        return zoos;
    }

    public void setZoos(Set<Zoo> zoos) {
        this.zoos = zoos;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnimalDescription() {
        return animalDescription;
    }

    public void setAnimalDescription(String animalDescription) {
        this.animalDescription = animalDescription;
    }

    public byte[] getAnimalImage() {
        return animalImage;
    }

    public void setAnimalImage(byte[] animalImage) {
        this.animalImage = animalImage;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", animalName='" + animalName + '\'' +
                ", description='" + animalDescription + '\'' +
                ", animalImage=" + Arrays.toString(animalImage) +
                ", zoos=" + zoos +
                '}';
    }
}

