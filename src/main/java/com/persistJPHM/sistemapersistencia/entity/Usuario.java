package com.persistJPHM.sistemapersistencia.entity;

import jakarta.persistence.*;
import lombok.*;

@NamedQueries({
        @NamedQuery(name = "usuarioEntreID",
        query = "SELECT u FROM Usuario u WHERE u.id BETWEEN :idInicial AND :idFinal"),
        @NamedQuery(name = "consultaPorNome",
        query = "SELECT u FROM Usuario u WHERE u.nome = :nome")})

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

    private String cpf;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Conta conta;

}