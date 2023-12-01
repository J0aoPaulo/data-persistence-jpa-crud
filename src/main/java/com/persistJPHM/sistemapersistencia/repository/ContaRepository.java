package com.persistJPHM.sistemapersistencia.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.persistJPHM.sistemapersistencia.entity.Conta;

@Repository
public interface ContaRepository extends MongoRepository<Conta, Integer> {
    
}