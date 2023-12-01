package com.persistJPHM.sistemapersistencia.repository;

import com.persistJPHM.sistemapersistencia.entity.Transacao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransacaoMongoDao extends MongoRepository<Transacao, String> {
}
