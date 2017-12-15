package com.ats;

import com.ats.repo.ApplicantRepository;
import com.mongodb.MongoClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@ComponentScan("com.ats")
public class ATSApplication {
    public static void main(String[] args) {
        SpringApplication.run(ATSApplication.class, args);
    }
}
