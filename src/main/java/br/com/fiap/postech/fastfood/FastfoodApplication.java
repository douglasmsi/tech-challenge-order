package br.com.fiap.postech.fastfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FastfoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastfoodApplication.class, args);
	}

}
