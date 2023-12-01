package com.persistJPHM.sistemapersistencia.repository;

import com.persistJPHM.sistemapersistencia.DAO.TransacaoDAO;
import com.persistJPHM.sistemapersistencia.entity.Transacao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransacaoMongoDao extends TransacaoDAO, MongoRepository<Transacao, String> {
}
