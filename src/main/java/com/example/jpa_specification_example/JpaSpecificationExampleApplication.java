package com.example.jpa_specification_example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JpaSpecificationExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaSpecificationExampleApplication.class, args);
	}

}
