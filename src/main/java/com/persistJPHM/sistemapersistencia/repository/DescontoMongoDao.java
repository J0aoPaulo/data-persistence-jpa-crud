package com.persistJPHM.sistemapersistencia.repository;

import com.persistJPHM.sistemapersistencia.DAO.DescontoRecorrenteDAO;
import com.persistJPHM.sistemapersistencia.entity.DescontoRecorrente;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DescontoMongoDao extends DescontoRecorrenteDAO,
        MongoRepository<DescontoRecorrente, String> {
}