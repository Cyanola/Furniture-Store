package com.example.FurnitureStore;

import com.example.FurnitureStore.Configuration.WebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication

@ComponentScan(basePackages = "com.example.FurnitureStore")
public class FurnitureStoreApplication {
	@Bean
	public WebConfiguration corsConfig() {
		return new WebConfiguration();
	}
	public static void main(String[] args) {
		SpringApplication.run(FurnitureStoreApplication.class, args);
	}

}
