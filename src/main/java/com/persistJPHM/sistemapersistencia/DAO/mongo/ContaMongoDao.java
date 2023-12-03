package com.persistJPHM.sistemapersistencia.DAO.mongo;

import com.persistJPHM.sistemapersistencia.DAO.ContaGeneric;
import com.persistJPHM.sistemapersistencia.entity.Conta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaMongoDao extends ContaGeneric, MongoRepository<Conta, String> {
    @Query(value = "{ '_id' : ?0 }", fields = "{ 'numeroTelefone' : 1, 'valorTotalConta' : 1}")
    Conta findByIdNativeQuery(String id);

    interface ContaProjection {
        String getNumeroTelefone();
        double getValorTotalConta();
    }

    @Query(value = "{}", fields = "{ 'numeroTelefone' : 1, 'valorTotalConta' : 1}")
    List<ContaProjection> findAllContaProjections();

    @Query("SELECT c FROM Conta c WHERE c.numeroTelefone = :numeroTelefone")
    Conta findByNumeroTelefoneJPQL(String numeroTelefone);

    @Query("{}")
    List<Conta> consultarTodasContas();

    @Query(value = "{ }", fields = "{ 'numeroTelefone' : 1 }")
    List<String> consultarTodosTelefones();
}