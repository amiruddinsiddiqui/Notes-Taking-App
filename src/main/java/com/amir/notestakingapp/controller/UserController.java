package com.amir.notestakingapp.controller;

import com.amir.notestakingapp.entity.NoteEntry;
import com.amir.notestakingapp.entity.User;
import com.amir.notestakingapp.repository.UserRepository;
import com.amir.notestakingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;



    @DeleteMapping
    public ResponseEntity<?> deleteUser(){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            userRepository.deleteByUserName(userName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            User userInDB = userService.findByUserName(userName);
            if (userInDB != null){
                userInDB.setUserName(!(user.getUserName() == null && user.getUserName().isEmpty()) ? user.getUserName() : userInDB.getUserName());
                userInDB.setPassword(!(user.getPassword() == null && user.getPassword().isEmpty()) ? user.getPassword() : userInDB.getPassword());
                userService.saveNewUser(userInDB);
                return new ResponseEntity<>(userInDB, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
