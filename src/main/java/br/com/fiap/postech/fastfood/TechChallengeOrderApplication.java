package br.com.fiap.postech.fastfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class TechChallengeOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechChallengeOrderApplication.class, args);
	}

}
