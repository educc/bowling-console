package com.ecacho.challenge;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Slf4j
@SpringBootApplication
public class ChallengeApplication
		implements CommandLineRunner {

	public static void main(String[] args) {
		log.info("STARTING THE APPLICATION");
		SpringApplication.run(ChallengeApplication.class, args);
		log.info("APPLICATION FINISHED");
	}

	@Override
	public void run(String... args) {

	}
}