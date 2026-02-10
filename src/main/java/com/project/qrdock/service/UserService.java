package com.project.qrdock.service;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.qrdock.model.Customer;
import com.project.qrdock.model.User;
import com.project.qrdock.repository.CustomerRepository;
import com.project.qrdock.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public User signup(User user) {
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    public Object getLoggedInUserDetails(String username){
        User user = userRepository.getByUsername(username);
        if(user == null){
           return null;
        }
        switch(user.getRole()){
            case "ADMIN" ->
            {

                return null;
            }
            case "CUSTOMER"->{
                Customer customer = customerRepository.getByUsername(username);
                return customer;
            }
            default ->{
                return null;
            }
        }
    }


    
}
