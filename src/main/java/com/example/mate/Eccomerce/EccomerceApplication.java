package com.example.mate.Eccomerce;

import com.example.mate.Eccomerce.models.Person;
import com.example.mate.Eccomerce.models.PersonType;
import com.example.mate.Eccomerce.repositories.PersonRepositories;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EccomerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EccomerceApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(PersonRepositories personRepositories) {
		return (args) -> {
			Person admin = new Person("Bruno Marcos","Ferreira","fbrunomarcos@gmail.com","123456","pass123", PersonType.ADMIN);
			personRepositories.save(admin);
		};

	}

}
