package com.project.qrdock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.qrdock.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("Select c from Customer c WHERE c.user.username = ?1")
    Customer getByUsername(String username);
    
}
