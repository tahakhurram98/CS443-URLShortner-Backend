package com.urlShortner.Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
//
//		SpringApplication app = new SpringApplication(Application.class);
//		app.setDefaultProperties(Collections
//				.singletonMap("server.port", "9042"));
//		app.run(args);
//	}
		SpringApplication.run(Application.class, args);
	}

}
