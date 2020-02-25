package com.sebapd.zoomanager.dao;

import com.sebapd.zoomanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    Optional<User> findById(Long userId);

}
