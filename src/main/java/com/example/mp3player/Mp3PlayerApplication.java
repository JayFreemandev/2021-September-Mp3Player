package com.example.mp3player;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Mp3PlayerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Mp3PlayerApplication.class, args);
	}

}