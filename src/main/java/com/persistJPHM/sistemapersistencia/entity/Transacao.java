package com.persistJPHM.sistemapersistencia.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@NamedQueries({
        @NamedQuery(name = "findByDateInterval", query = "SELECT t FROM Transacao t WHERE t.dataTransacao BETWEEN :startDate AND :endDate"),
        @NamedQuery(name = "calculateAverageValue", query = "SELECT AVG(t.valorTransacao) FROM Transacao t")
})

@Entity
@Table(name = "Transacao")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTran;

    @ManyToOne
    private Conta conta;

    @NonNull
    @Column(name = "data_transacao", nullable = false)
    Date dataTransacao;

    @Column(name = "valor_transacao")
    private double valorTransacao;
}