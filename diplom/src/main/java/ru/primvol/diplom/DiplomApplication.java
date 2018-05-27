package ru.primvol.diplom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ru.primvol.diplom.repo.UserRepository;

@SpringBootApplication
public class DiplomApplication {
	
	@Autowired
	UserRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(DiplomApplication.class, args);
	}
	
	
}
