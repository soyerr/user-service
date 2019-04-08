package org.demo.application.userservice.config;

import org.demo.application.userservice.db.model.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataBaseConfiguration {

    @Bean
    public Map<Long, UserEntity> memoryStorage(){
        return new HashMap<>();
    }

}
