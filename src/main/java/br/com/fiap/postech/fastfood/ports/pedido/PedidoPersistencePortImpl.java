package br.com.fiap.postech.fastfood.ports.pedido;

import br.com.fiap.postech.fastfood.controller.dto.UpdatePedidoRequest;
import br.com.fiap.postech.fastfood.domain.enums.PedidoStatus;
import br.com.fiap.postech.fastfood.domain.pedido.Pedido;
import br.com.fiap.postech.fastfood.infrastructure.clients.dto.TechChallengeClientDTO;
import br.com.fiap.postech.fastfood.repository.entities.PedidoEntity;
import br.com.fiap.postech.fastfood.repository.item.ItemJpaRepository;
import br.com.fiap.postech.fastfood.repository.pedido.PedidoJpaRepository;
import br.com.fiap.postech.fastfood.services.ClienteService;
import br.com.fiap.postech.fastfood.services.PagamentoService;
import br.com.fiap.postech.fastfood.usecases.pedido.PedidoNumberGenerator;
import br.com.fiap.postech.fastfood.usecases.pedido.impl.PedidoNumberGeneratorImpl;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PedidoPersistencePortImpl implements PedidoPersistencePort {

    private final PedidoJpaRepository pedidoJpaRepository;
    private final ItemJpaRepository itemJpaRepository;
    private final ModelMapper modelMapper;
    private final ClienteService clienteService;
    //private final PagamentoService pagamentoService;


    @Override
    public Pedido createPedido(final String cpf) {
        PedidoNumberGenerator generator = new PedidoNumberGeneratorImpl();
        String numeroPedido = generator.generateNumber();

        Pedido pedido = Pedido.builder()
            .cpf(cpf)
            .numeroPedido(numeroPedido)
            //.statusPagamento(PagamentoStatus.PENDENTE)
            .statusPedido(PedidoStatus.CRIADO)
            .dataPedido(LocalDateTime.now())
            .dataAtualizacao(LocalDateTime.now())
            .build();

        TechChallengeClientDTO techChallengeClientDTO = clienteService.getClienteByCpf(cpf);
        if (techChallengeClientDTO != null) {

            PedidoEntity pedidoEntity = modelMapper.map(pedido, PedidoEntity.class);
            pedidoEntity.setCpf(techChallengeClientDTO.getCpf());

            pedidoEntity = pedidoJpaRepository.save(pedidoEntity);
            return modelMapper.map(pedidoEntity, Pedido.class);
        }
        return null;
    }

    @Override
    public List<Pedido> findAll() {
        return pedidoJpaRepository.findAll().stream().map(entity -> modelMapper.map(entity, Pedido.class)).toList();
    }

    @Override
    public Pedido findByNumeroPedido(String numeroPedido) {
        PedidoEntity pedidoEntity = pedidoJpaRepository.findByNumeroPedido(numeroPedido);
        if(pedidoEntity == null) {
            return null;
        }
        return modelMapper.map(pedidoEntity, Pedido.class);
    }

    @Override
    public Pedido updateStatusPedido(UpdatePedidoRequest request) {
        PedidoEntity pedidoEntity = pedidoJpaRepository.findByNumeroPedido(request.getNumeroPedido());

        if (pedidoEntity == null) {
            return null;
        }

        pedidoEntity.setPedidoStatus(request.getStatusPedido());
        pedidoEntity.setDataAtualizacao(LocalDateTime.now());
        // Verifique se o novo status do pedido é CANCELADO
        if (request.getStatusPedido() == PedidoStatus.CANCELADO) {
            // Recupere o Pagamento associado
            //PagamentoEntity pagamentoEntity = pagamentoJpaRepository.findByNumeroPedido(request.getNumeroPedido());

            // Atualize o status do Pagamento para ESTORNADO
            //pagamentoEntity.setStatus(PagamentoStatus.ESTORNADO);
            //pagamentoJpaRepository.save(pagamentoEntity);
            //pedidoEntity.setPagamentoStatus(PagamentoStatus.ESTORNADO);
        }


        pedidoEntity = pedidoJpaRepository.save(pedidoEntity);
        return modelMapper.map(pedidoEntity, Pedido.class);
    }

}
