package com.navi.loan;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@OpenAPIDefinition(
		info = @Info(
				title = "Loan microservice REST API Documentation",
				description = "NaviBank loan microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Naveen",
						email = "naveen@gmail.com"
				),
				license = @License(
						name = "Apache 2.0")
		)
)
@SpringBootApplication
@EnableJpaAuditing
public class LoanApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanApplication.class, args);
	}

}
