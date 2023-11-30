package com.persistJPHM.sistemapersistencia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MongoConta {

    public MongoConta(String nome) {
        this.nome = nome;
    }

    @Id
    private Integer idConta;

    private String nome;

}