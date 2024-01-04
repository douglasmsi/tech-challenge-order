package br.com.fiap.postech.fastfood.usecases.pedido;

import br.com.fiap.postech.fastfood.domain.pedido.Pedido;

public interface CriarPedidoUseCase {
  Pedido createPedido(String cpf);

}
