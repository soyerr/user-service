package org.demo.application.userservice.config;

import com.google.common.base.Predicates;
import com.google.common.collect.Lists;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .securitySchemes(Lists.newArrayList(basicAuth()))
                .securityContexts(Lists.newArrayList(securityContext()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "User Service API",
                "Description how to use api to manage users",
                "0.0.1",
                "",
                new Contact("Piotr Mackowiak", "", "we-wont-help-you@gmail.com"),
                "", "", Collections.emptyList());
    }

    private BasicAuth basicAuth() {
        BasicAuth basic = new BasicAuth("basic");
        return basic;
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(regex("/user*/*"))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(new SecurityReference("basic", authorizationScopes));
    }

}
