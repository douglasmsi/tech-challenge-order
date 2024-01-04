package br.com.fiap.postech.fastfood.infrastructure.clients;

import br.com.fiap.postech.fastfood.infrastructure.clients.dto.TechChallengeClientDTO;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "tech-challenge-client-alb")
public interface TechChallengeClient {

  @RequestMapping(method = RequestMethod.GET, value = "/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
  List<TechChallengeClientDTO> getAllClientes();

  @RequestMapping(method = RequestMethod.GET, value = "/clientes/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
  TechChallengeClientDTO getClienteByCpf(@PathVariable String cpf);

}
