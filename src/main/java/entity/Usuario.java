package entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Usuario")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    private double valorNaConta;
    private double transacao;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Conta conta;
}