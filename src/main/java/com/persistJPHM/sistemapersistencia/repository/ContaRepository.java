package com.persistJPHM.sistemapersistencia.repository;

import com.persistJPHM.sistemapersistencia.model.MongoConta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends MongoRepository<MongoConta, Integer> {

    @Query
    MongoConta findContaById(Integer id);
}