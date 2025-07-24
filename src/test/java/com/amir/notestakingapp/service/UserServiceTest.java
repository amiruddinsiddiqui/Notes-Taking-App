package com.amir.notestakingapp.service;

import com.amir.notestakingapp.entity.User;
import com.amir.notestakingapp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Disabled
    @Test
    public void test(){
        assertNotNull(userService.findByUserName("amir"));
    }

    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {
            "amir",
            "samir",
            "sam"
    })
    public void tonsOfUserAtOnceCuzYNot(String userName){
        assertNotNull(userService.findByUserName(userName), "failed for: "+userName);
    }

    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {
            "amir",
            "samir",
            "kam"
    })
    public void isNotePresent(String userName){
        User user = userService.findByUserName(userName);
        assertTrue(!user.getNoteEntryList().isEmpty());
    }

    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void newUserTest(User user){
        assertTrue(userService.saveNewUser(user));
    }
}
