package com.example.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "KLTN API", version = "1.0", description = "api demo for fe"))
public class KltnApplication {

    public static void main(String[] args) {
        SpringApplication.run(KltnApplication.class, args);
    }

}
