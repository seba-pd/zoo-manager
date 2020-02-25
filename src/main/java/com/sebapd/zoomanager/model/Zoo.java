package com.sebapd.zoomanager.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Zoo {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;
    @Column(name = "zoo_name")
    private String zooName;
    @Lob
    @Column(name = "description")
    private String zooDescription;
    @Lob
    @Column(name = "zoo_image")
    private byte[] zooImage;
    @ManyToMany
    private Set<Animal> animals;

    public Zoo() {
    }

    public Zoo(String zooName, String zooDescription, byte[] zooImage, Set<Animal> animals) {
        this.zooName = zooName;
        this.zooDescription = zooDescription;
        this.zooImage = zooImage;
        this.animals = animals;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(Set<Animal> animals) {
        this.animals = animals;
    }

    public String getZooName() {
        return zooName;
    }

    public void setZooName(String zooName) {
        this.zooName = zooName;
    }

    public byte[] getZooImage() {
        return zooImage;
    }

    public void setZooImage(byte[] zooImage) {
        this.zooImage = zooImage;
    }

    public String getZooDescription() {
        return zooDescription;
    }

    public void setZooDescription(String zooDescription) {
        this.zooDescription = zooDescription;
    }

}
