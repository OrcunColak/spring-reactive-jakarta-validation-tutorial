package com.colak.springreactivejakartavalidationtutorial;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "User management reactive application API", version = "1.0", description = "user management"))
public class SpringReactiveJakartaValidationTutorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringReactiveJakartaValidationTutorialApplication.class, args);
	}

}
