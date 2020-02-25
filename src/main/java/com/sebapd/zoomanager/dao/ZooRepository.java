package com.sebapd.zoomanager.dao;

import com.sebapd.zoomanager.model.Zoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZooRepository extends JpaRepository<Zoo,Long> {

    Zoo findByZooName(String zooName);
    Zoo deleteByZooName(String zooName);

}
