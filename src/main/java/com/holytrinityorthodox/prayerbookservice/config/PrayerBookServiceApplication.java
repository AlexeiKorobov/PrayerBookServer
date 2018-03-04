package com.holytrinityorthodox.prayerbookservice.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ComponentScan("com.holytrinityorthodox.prayerbookservice.*")
public class PrayerBookServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrayerBookServiceApplication.class, args);
	}
}
