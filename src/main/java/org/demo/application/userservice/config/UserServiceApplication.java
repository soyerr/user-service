package org.demo.application.userservice.config;

import org.demo.application.userservice.db.model.UserEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
public class UserServiceApplication {

    @Bean
    public Map<Long, UserEntity> memoryStorage(){
        return new ConcurrentHashMap<>();
    }

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
