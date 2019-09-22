package com.ecacho.challenge;

import com.ecacho.challenge.client.console.exception.ConsoleBowlingException;
import com.ecacho.challenge.client.console.gamemanager.IGameManager;
import com.ecacho.challenge.client.console.reader.impl.FileReaderBowlingPlayerRoll;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Slf4j
@SpringBootApplication
public class ChallengeApplication
		implements CommandLineRunner {

	IGameManager gameManager;

	public ChallengeApplication(IGameManager gameManager) {
		this.gameManager = gameManager;
	}

	public static void main(String[] args) {
		log.info("STARTING THE APPLICATION");
		SpringApplication.run(ChallengeApplication.class, args);
		log.info("APPLICATION FINISHED");
	}

	@Override
	public void run(String... args) throws ConsoleBowlingException {
		if (args.length < 1) {
			log.info("A parameter is required");
			log.info("program filename");
			return;
		}
		log.info("File = " + args[0]);

		String filename = args[0];

		FileReaderBowlingPlayerRoll reader = new FileReaderBowlingPlayerRoll(filename);
		gameManager.printAllPlayersScore(reader);
	}
}