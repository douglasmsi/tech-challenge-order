package br.com.fiap.postech.fastfood.repository.entities;

import br.com.fiap.postech.fastfood.domain.enums.PedidoStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pedidos", uniqueConstraints = {@UniqueConstraint(columnNames = "numeroPedido")})
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String numeroPedido;

    @Column
    private String cpf;

    @Enumerated(EnumType.STRING)
    @Column
    private PedidoStatus pedidoStatus;


    @Column
    private BigDecimal valorTotal;

    @Column
    private LocalDateTime dataPedido;

    @Column
    private LocalDateTime dataAtualizacao;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedidoEntity> items;

    @Override
    public String toString() {
        return "PedidoEntity{" +
            "id=" + id +
            ", numeroPedido='" + numeroPedido + '\'' +
            ", cpf='" + cpf + '\'' +
            ", pedidoStatus=" + pedidoStatus +
            ", valorTotal=" + valorTotal +
            ", dataPedido=" + dataPedido +
            ", dataAtualizacao=" + dataAtualizacao +
            ", items=" + items +
            '}';
    }

}
