package br.com.fiap.postech.fastfood.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class CriarPedidoRequest {

  private String clienteCpf;

}
