package com.persistJPHM.sistemapersistencia.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@NamedQueries({
    @NamedQuery(name = "findAllOrderByDataDescontoAsc", query = "SELECT dr FROM DescontoRecorrente dr ORDER BY dr.dataDesconto ASC"),
    @NamedQuery(name = "findAllByValorDescontoBetween", query = "SELECT dr FROM DescontoRecorrente dr WHERE dr.valorDesconto BETWEEN :minValor AND :maxValor")
})

@Entity
@Table(name = "desconto_recorrente")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class DescontoRecorrente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDesconto;

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
}