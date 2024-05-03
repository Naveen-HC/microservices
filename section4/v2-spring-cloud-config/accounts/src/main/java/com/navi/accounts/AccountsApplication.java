package com.navi.accounts;

import com.navi.accounts.dto.AccountsProperties;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication

/**
if components, entity and repository are outside the basePackage
@ComponentScan("com.navi.accounts") or @ComponentScans({ComponentScan("com.navi.accounts")})
@EntityScan("com.navi.accounts")
@EnableJpaRepositories("com.navi.accounts.repository")
*/

@EnableJpaAuditing(auditorAwareRef =  "auditorAwareImpl")
@EnableConfigurationProperties(value = {AccountsProperties.class})
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts microservice REST API Documentation",
				description = "NaviBank Accounts microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Naveen HC",
						email = "naveen@gmail.com"
				),
				license = @License(
						name = "Apache 2.0"
				)
		),
		externalDocs = 	@ExternalDocumentation(
				description = "NaviBank Accounts microservice REST API Documentation",
				url = "http//:localhost:8080/swagger-ui/index.html"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
