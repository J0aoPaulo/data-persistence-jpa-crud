package com.persistJPHM.sistemapersistencia.DAO.mongo;

import com.persistJPHM.sistemapersistencia.entity.Transacao;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.persistJPHM.sistemapersistencia.DAO.TransacaoGeneric;

@Repository
public interface TransacaoMongoDao extends TransacaoGeneric,
        MongoRepository<Transacao, String> {
     
        @Query("{ 'idTran' : ?0 }")
        Transacao buscaPorId(String id);
        
        @Query("{ 'valorTransacao' : { $gte : ?0, $lte : ?1 } }")
        List<Transacao> buscarEntreValores(double minValue, double maxValue);
        
        @Query("{}")
        List<Transacao> listALL();
        
        @Query(value = "{}", count = true)
        int numTransacoes();
        
        @Query("{ 'dataTransacao' : { $gte : ?0, $lte : ?1 } }")
        List<Transacao> findByDateInterval(Date startDate, Date endDate);
        
        @Query("{}")
        Double calculateAverageValue();
        
        @Query("{}")
        Transacao findTopByOrderByValorTransacaoAsc();
        
        @Query("{}")
        Transacao findTopByOrderByValorTransacaoDesc();
}
