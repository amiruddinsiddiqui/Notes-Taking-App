package com.amir.notestakingapp.controller;

import com.amir.notestakingapp.entity.User;
import com.amir.notestakingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;


    @PostMapping("create-user")
    public ResponseEntity<?> createNewUser(@RequestBody User user){
        if (user != null){
            boolean isNewUser = userService.saveNewUser(user);
            if(isNewUser){
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}
