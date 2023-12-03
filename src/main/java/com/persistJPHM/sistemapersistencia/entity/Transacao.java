package com.persistJPHM.sistemapersistencia.entity;

import jakarta.persistence.*;
import lombok.*;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@NamedQueries({
        @NamedQuery(name = "findByDateInterval", query = "SELECT t FROM Transacao t WHERE t.dataTransacao BETWEEN :startDate AND :endDate"),
        @NamedQuery(name = "calculateAverageValue", query = "SELECT AVG(t.valorTransacao) FROM Transacao t")
})

@Document(collection = "transacoes")
@Entity
@Table(name = "Transacao")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @ToString.Exclude
    private Conta conta;

    @NonNull
    @Column(name = "data_transacao", nullable = false)
    Date dataTransacao;

    @Column(name = "valor_transacao")
    private double valorTransacao;

    public String toString() {
        String s = "Transacao(idTran=" + this.id + ", idConta=" + this.conta.getUsuario().getId() + ", dataTransacao=" + this.dataTransacao + ", valorTransacao=" + this.valorTransacao + ")";
        return s;
    }
}