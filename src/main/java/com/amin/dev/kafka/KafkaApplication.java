package com.amin.dev.kafka;

import com.amin.dev.kafka.config.ConfigConstants;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import static com.amin.dev.kafka.config.ConfigConstants.*;
import static com.amin.dev.kafka.config.ConfigConstants.kafkaTopic;

@SpringBootApplication
public class KafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(KafkaTemplate<String, MovieRequest> template) {
		return args -> {
			for (int i = 0; i < 120; i++) {
				template.send(kafkaTopic,
						new MovieRequest("Movie Test", 2022));
			}

		};
	}

}
