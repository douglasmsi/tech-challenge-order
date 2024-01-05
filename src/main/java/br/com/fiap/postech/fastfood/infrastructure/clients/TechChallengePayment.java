package br.com.fiap.postech.fastfood.infrastructure.clients;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "tech-challenge-payment")
public interface TechChallengePayment {


}
