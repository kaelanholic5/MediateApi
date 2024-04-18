package com.mediate.mediateDBConnector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@Configuration
@ComponentScan({ "com.mediate.mediateDBConnector" })
@SpringBootApplication
public class MediateDbConnectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediateDbConnectorApplication.class, args);
	}
}
