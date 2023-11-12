package com.persistJPHM.sistemapersistencia.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTran;

    @ManyToOne
    @ToString.Exclude
    private Conta conta;

    @NonNull
    @Column(name = "data_transacao", nullable = false)
    Date dataTransacao;

    @Column(name = "valor_transacao")
    private double valorTransacao;

    public String toString() {
        String s = "Transacao(idTran=" + this.idTran + ", idConta=" + this.conta.getIdConta() + ", dataTransacao=" + this.dataTransacao + ", valorTransacao=" + this.valorTransacao + ")";
        return s;
    }
}