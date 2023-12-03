package com.persistJPHM.sistemapersistencia.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@NamedQueries({
        @NamedQuery(name = "usuarioEntreID",
        query = "SELECT u FROM Usuario u WHERE u.id BETWEEN :idInicial AND :idFinal"),
        @NamedQuery(name = "consultaPorNome",
        query = "SELECT u FROM Usuario u WHERE u.nome = :nome")})

@Document(collection = "usuarios")
@Entity
@Table(name = "Usuario")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idUsuario;

    @NonNull
    @Column(nullable = false)
    private String nome;

    @NonNull
    @Column(nullable = false)
    private String cpf;

    @ToString.Exclude
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Conta conta;

    @Override
    public String toString() {
        return "Nome: " + getNome() + ", CPF: " + getCpf();
    }
}
