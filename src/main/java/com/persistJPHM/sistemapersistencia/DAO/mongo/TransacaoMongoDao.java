package com.persistJPHM.sistemapersistencia.DAO.mongo;

import com.persistJPHM.sistemapersistencia.entity.Transacao;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.persistJPHM.sistemapersistencia.DAO.TransacaoGeneric;


public interface TransacaoMongoDao extends TransacaoGeneric,
        MongoRepository<Transacao, String> {
}
