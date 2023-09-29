package com.example.mate.Eccomerce;

import com.example.mate.Eccomerce.models.*;
import com.example.mate.Eccomerce.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EccomerceApplication {
@Autowired
private PasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(EccomerceApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(PersonRepository personRepository, AdressRepository adressRepository, ProductRepository productRepository) {
		return (args) -> {
			Person admin = new Person("Bruno Marcos","Ferreira",
					"fbrunomarcos@gmail.com","123456", passwordEncoder.encode("pass123"), PersonType.ADMIN);
			Adress adress = new Adress("Calle Admin", 123, "Jardin America", "Casa", 2, true);
			Product product = new Product("Termo", "Termo de prueba", 100.000,50,CategoryProduct.CONTAINERS_MATE,ColorProduct.BLACK,2.0);
			admin.addAdress(adress);
			personRepository.save(admin);
			adressRepository.save(adress);
			productRepository.save(product);
		};
	}
}
