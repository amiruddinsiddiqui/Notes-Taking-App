package com.amir.notestakingapp.controller;

import com.amir.notestakingapp.config.SecurityConfig;
import com.amir.notestakingapp.entity.User;
import com.amir.notestakingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/all-user")
    public ResponseEntity<List<User>> getAllUserBczIamADMINHAHAHA(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);

        if(user != null){
            List<User> allUser = userService.getAllUser();
            if (!allUser.isEmpty()){
                return new ResponseEntity<>(allUser, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/new-admin")
    public ResponseEntity<?> makeNewAdmin(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (user != null && user.equals("")){
            userService.saveNewUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
