package com.persistJPHM.sistemapersistencia.DAO.mongo;

import com.persistJPHM.sistemapersistencia.entity.Transacao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.persistJPHM.sistemapersistencia.DAO.TransacaoGeneric;

@Repository
public interface TransacaoMongoDao extends TransacaoGeneric,
        MongoRepository<Transacao, String> {
     
}
