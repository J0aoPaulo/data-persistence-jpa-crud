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

    @NonNull private String nome;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Conta conta;
}