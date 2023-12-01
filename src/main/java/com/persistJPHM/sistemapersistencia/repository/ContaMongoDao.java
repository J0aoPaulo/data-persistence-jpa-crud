package com.persistJPHM.sistemapersistencia.repository;

import com.persistJPHM.sistemapersistencia.DAO.ContaDAO;
import com.persistJPHM.sistemapersistencia.entity.Conta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaMongoDao extends ContaDAO, MongoRepository<Conta, String> {
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
}