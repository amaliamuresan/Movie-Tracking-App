package com.client.webClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class WebClientApplication {

	public static void main(String[] args) {
		SpringApplication application=new SpringApplication(WebClientApplication.class);
		application.run(args);
	}

}
