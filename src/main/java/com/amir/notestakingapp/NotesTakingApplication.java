package com.amir.notestakingapp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class NotesTakingApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotesTakingApplication.class, args);
    }

    @Bean
    public PlatformTransactionManager toCommitOrRollback(MongoDatabaseFactory databaseFactory){
        return new MongoTransactionManager(databaseFactory);
    }
}