package com.persistJPHM.sistemapersistencia.entity;

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
    private Integer idUsuario;

    @Column(nullable = false)
    private String nome;

    @OneToOne
    @JoinColumn(name = "idUsuario")
    @MapsId
    private Usuario usuario;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
    private List<Transacao> transacoes;

    @OneToMany(mappedBy = "descontoConta")
    private List<DescontoRecorrente> dr;

    public double valorTotalConta() {
        double totalNaConta = 0;
        for(Transacao tr : transacoes) {
            totalNaConta += tr.getValorTransacao();
        }
        return totalNaConta;
    }
}