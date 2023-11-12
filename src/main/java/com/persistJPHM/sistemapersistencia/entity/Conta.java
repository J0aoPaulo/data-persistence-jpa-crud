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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idConta;

    @OneToOne
    @PrimaryKeyJoinColumn
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
}