package com.turing.backendChallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.turing.backendChallenge.Repo")
@SpringBootApplication
public class BackendChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendChallengeApplication.class, args);
	}

}
