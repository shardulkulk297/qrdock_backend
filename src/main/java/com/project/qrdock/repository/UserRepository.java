package com.project.qrdock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.qrdock.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("Select u from User u WHERE u.username = ?1")
    User getByUsername(String username);
    
}
