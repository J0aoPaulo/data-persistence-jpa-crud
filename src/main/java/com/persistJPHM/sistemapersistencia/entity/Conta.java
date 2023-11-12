package com.persistJPHM.sistemapersistencia.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "consultarContasComDescontos",
        query = "SELECT c FROM Conta c LEFT JOIN FETCH c.dr " +
                "WHERE c.dr IS NOT EMPTY"),
        @NamedQuery(name = "listarTodosTelefones",
        query = "SELECT c.numeroTelefone FROM Conta c")
})
@Entity
@Table (name = "Conta")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Conta {
    @Id
    private Integer idUsuario;

    @OneToOne
    @JoinColumn(name = "idUsuario")
    @MapsId
    private Usuario usuario;

    private String numeroTelefone;

    private double totalNaConta;

    @ToString.Exclude
    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
    private List<Transacao> transacoes;

    @ToString.Exclude
    @OneToMany(mappedBy = "descontoConta")
    private List<DescontoRecorrente> dr;

    public double valorTotalConta() {
        for(Transacao tr : transacoes) {
            totalNaConta += tr.getValorTransacao();
        }
        return totalNaConta;
    }
}