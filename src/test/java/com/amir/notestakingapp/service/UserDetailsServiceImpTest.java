package com.amir.notestakingapp.service;

import com.amir.notestakingapp.entity.User;
import com.mongodb.assertions.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

public class UserDetailsServiceImpTest {

    @InjectMocks
    private UserDetailsServiceImp userDetailsService;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testing(){
        when(userService.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("sam").password("asdgs").roles(new ArrayList<>()).build());
        UserDetails user = userDetailsService.loadUserByUsername("sam");
        Assertions.assertNotNull(user);
    }
}
