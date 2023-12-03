package com.persistJPHM.sistemapersistencia.DAO.mongo;

import com.persistJPHM.sistemapersistencia.entity.DescontoRecorrente;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.persistJPHM.sistemapersistencia.DAO.DescontoGeneric;


public interface DescontoMongoDao extends DescontoGeneric,
        MongoRepository<DescontoRecorrente, String> {
}