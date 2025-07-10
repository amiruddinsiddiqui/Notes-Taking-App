package com.amir.notestakingapp.controller;

import com.amir.notestakingapp.entity.User;
import com.amir.notestakingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        userService.save(user);
    }

    @GetMapping("{userName}")
    public User getByUser(@PathVariable String userName){
        return userService.findByUserName(userName);
    }

//    @DeleteMapping("{userName}")
//    public void deleteUser(@PathVariable String userName){
//        User user = userService.findByUserName(userName);
//        if (user != null){
//
//        }
//    }

    @PutMapping("{userName}")
    @Transactional
    public User updateUser(@RequestBody User user, @PathVariable String userName){
        try {
            User userInDB = userService.findByUserName(userName);
            if (userInDB != null){
                userInDB.setUserName(user.getUserName());
                userInDB.setPassword(user.getPassword());
            }
            userService.save(userInDB);
            return userInDB;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
