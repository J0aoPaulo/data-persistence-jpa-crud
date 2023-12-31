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
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne
    private Usuario usuario;

    private String numeroTelefone;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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
        sb.append("Conta: ")
                .append(usuario)
                .append(", numeroTelefone='").append(numeroTelefone).append('\'');
        return sb.toString();
    }
}