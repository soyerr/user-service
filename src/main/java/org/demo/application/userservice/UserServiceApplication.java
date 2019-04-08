package org.demo.application.userservice;

import org.demo.application.userservice.config.DataBaseConfiguration;
import org.demo.application.userservice.config.SecurityConfiguration;
import org.demo.application.userservice.config.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({DataBaseConfiguration.class, SwaggerConfiguration.class, SecurityConfiguration.class})
@SpringBootApplication(scanBasePackages = "org.demo.application.userservice")
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
