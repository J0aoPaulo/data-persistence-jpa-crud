package entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table (name = "Conta")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Conta {
    @Id
    @PrimaryKeyJoinColumn(name = "idUsuario")
    private Integer idConta;

    @Column(nullable = false)
    private String nome;

    @OneToOne(mappedBy = "conta")
    private Usuario usuario;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
    private List<Transacao> transacoes;

    @OneToMany(mappedBy = "decontoConta")
    private DescontoRecorrente dr;

    public double valorTotalConta() {
        double totalNaConta = 0;
        for(Transacao tr : transacoes) {
            totalNaConta += tr.getValorTransacao();
        }
        return totalNaConta;
    }
}