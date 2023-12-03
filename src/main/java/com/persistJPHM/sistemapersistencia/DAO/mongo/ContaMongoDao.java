package com.persistJPHM.sistemapersistencia.DAO.mongo;

import com.persistJPHM.sistemapersistencia.DAO.ContaGeneric;
import com.persistJPHM.sistemapersistencia.entity.Conta;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaMongoDao extends ContaGeneric, MongoRepository<Conta, String> {
    
    @Query("{ 'numeroTelefone' : ?0 }")
    Conta findByNumeroTelefone(String numeroTelefone); //OK

    @Query(value = "{}", fields = "{ 'numeroTelefone' : 1, 'valorTotalConta' : 1}")
    List<Conta> consultarTodasContas(); //OK

    @Query(value = "{ }", fields = "{ 'numeroTelefone' : 1 }")
    List<String> consultarTodosTelefones(); // OK
}