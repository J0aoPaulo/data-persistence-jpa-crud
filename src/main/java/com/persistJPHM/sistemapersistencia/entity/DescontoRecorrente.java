package com.persistJPHM.sistemapersistencia.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@NamedQueries({
    @NamedQuery(name = "findAllByValorDescontoBetween", query = "SELECT dr FROM DescontoRecorrente dr WHERE dr.valorDesconto BETWEEN :minValor AND :maxValor")
})
@Document(collection = "descontos")
@Entity
@Table(name = "desconto_recorrente")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
public class DescontoRecorrente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idDesconto;

    @Column(name = "valor_desconto")
    private double valorDesconto;

    @ManyToOne
    Conta descontoConta;

    private Date dataDesconto;

    @Column(name = "restante_conta")
    private double restanteConta;
    public double debitarConta() {
        if(descontoConta.valorTotalConta() >= valorDesconto) {
            restanteConta = descontoConta.valorTotalConta() - valorDesconto;
        }
        return restanteConta;
    }

    @Override
    public String toString() {
        return "DescontoRecorrente{idDesconto=" + idDesconto + ", valorDesconto=" + valorDesconto +
                ", id da conta=" + (descontoConta != null ? descontoConta.getIdConta() : "null") +
                ", dataDesconto=" + dataDesconto + "}";
    }
}