package com.persistJPHM.sistemapersistencia.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@NamedQueries({
        @NamedQuery(name = "listarTodosTelefones",
        query = "SELECT c.numeroTelefone FROM Conta c")
})
@Document(collection = "contas")
@Entity
@Table (name = "Conta")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Conta {
    @Id
    private String idConta;

    @OneToOne
    private Usuario usuario;

    private String numeroTelefone;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
    private List<Transacao> transacoes;

    @ToString.Exclude
    @OneToMany(mappedBy = "descontoConta")
    private List<DescontoRecorrente> dr;

    public double valorTotalConta() {
        double totalNaConta = 0;
        for(Transacao tr : transacoes) {
            totalNaConta += tr.getValorTransacao();
        }
        return totalNaConta;
    }

    public String toStringTodasContas() {
        StringBuilder sb = new StringBuilder();
        sb.append("Conta{")
                .append("idConta=").append(idConta)
                .append(", usuario=").append(usuario)
                .append(", numeroTelefone='").append(numeroTelefone).append('\'')
                .append(", transacoes=[");

        if (transacoes != null) {
            for (Transacao tr : transacoes) {
                sb.append("{valorTransacao=").append(tr.getValorTransacao()).append("}, ");
            }
        }
        sb.append("]}");
        return sb.toString();
    }
}