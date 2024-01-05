package br.com.fiap.postech.fastfood.infrastructure.clients;


import br.com.fiap.postech.fastfood.infrastructure.clients.dto.TechChallengePaymentStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "tech-challenge-payment")
public interface TechChallengePayment {

  @RequestMapping(method = RequestMethod.GET, value = "/pagamentos/status/{numeroPedido}", produces = MediaType.APPLICATION_JSON_VALUE)
  TechChallengePaymentStatus getStatusPagamento(@PathVariable String numeroPedido);


}
