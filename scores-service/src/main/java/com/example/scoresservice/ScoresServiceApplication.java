package com.example.scoresservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ScoresServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ScoresServiceApplication.class, args);
	}
}
