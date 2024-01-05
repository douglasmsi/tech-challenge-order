package br.com.fiap.postech.fastfood.services;

import br.com.fiap.postech.fastfood.infrastructure.clients.TechChallengePayment;
import br.com.fiap.postech.fastfood.infrastructure.clients.dto.TechChallengePaymentStatus;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

  private final TechChallengePayment techChallengePayment;

  public PagamentoService(TechChallengePayment techChallengePayment) {
    this.techChallengePayment = techChallengePayment;
  }

  public TechChallengePaymentStatus getStatusPagamentoByNumeroPedido(String numeroPedido) {
    return techChallengePayment.getStatusPagamento(numeroPedido);
  }

}
