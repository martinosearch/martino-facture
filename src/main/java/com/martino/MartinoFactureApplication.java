package com.martino;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.martino")
public class MartinoFactureApplication {
	public static void main(String[] args) {
		SpringApplication.run(MartinoFactureApplication.class, args);
	}

}
