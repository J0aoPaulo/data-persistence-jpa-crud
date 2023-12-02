package com.persistJPHM.sistemapersistencia.repository;

import com.persistJPHM.sistemapersistencia.entity.DescontoRecorrente;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DescontoMongoDao extends MongoRepository<DescontoRecorrente, String> {
}