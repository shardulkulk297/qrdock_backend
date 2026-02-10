package com.project.qrdock.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.qrdock.model.User;
import com.project.qrdock.repository.UserRepository;
import com.project.qrdock.service.UserService;
import com.project.qrdock.util.JwtUtil;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/user/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
    
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signup(user));
    }

    @GetMapping("/api/user/token")
    public ResponseEntity<?> getToken(Principal principal) {
        JwtUtil jwtUtil = new JwtUtil();

        try {
            String token = jwtUtil.createToken(principal.getName());
            Map<String, String> map = new HashMap();
            map.put(token, token);
            return ResponseEntity.status(HttpStatus.OK).body(token);
            
            
            
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

    
    }

    @GetMapping("/api/user/getLoggedInUserDetails")
    public Object getLoggedInUser(Principal principal){
        String username = principal.getName();
        return userService.getLoggedInUserDetails(username);
    }

    
    
    
    
}
