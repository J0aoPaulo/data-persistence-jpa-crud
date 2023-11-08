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
    private Integer idUsuario;

    @NonNull
    @Column(nullable = false)
    private String nome;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Conta conta;
}