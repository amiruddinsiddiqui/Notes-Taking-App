//package com.amir.notestakingapp.config;
//
//import com.amir.notestakingapp.service.UserDetailsServiceImp;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//@Profile("prod")
//public class SecurityConfigProd extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private UserDetailsServiceImp userDetailsServiceImp;
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();
//        http.csrf().disable();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsServiceImp).passwordEncoder(passwordEncoderMethod());
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoderMethod(){
//        return new BCryptPasswordEncoder();
//    }
//}
