package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table (name = "Conta")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idConta;

    @OneToMany(mappedBy = "conta")
    List<Transacao> transacoes;

    public double valorTotalTransacoes() {
        double totalTran = 0;
        for(Transacao tr : transacoes) {
            totalTran += tr.getValorTransacao();
        }
        return totalTran;
    }
}