package br.com.fiap.postech.fastfood.infrastructure.clients.dto;


public enum TechChallengePaymentStatus {

  PENDENTE("PENDENTE"),
  APROVADO("APROVADO"),
  NEGADO("NEGADO"),
  ESTORNADO("ESTORNADO");

  private final String value;

  TechChallengePaymentStatus(final String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

}
