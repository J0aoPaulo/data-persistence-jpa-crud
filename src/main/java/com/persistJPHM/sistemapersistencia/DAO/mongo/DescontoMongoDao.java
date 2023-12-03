package com.persistJPHM.sistemapersistencia.DAO.mongo;

import com.persistJPHM.sistemapersistencia.entity.Conta;
import com.persistJPHM.sistemapersistencia.entity.DescontoRecorrente;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.persistJPHM.sistemapersistencia.DAO.DescontoGeneric;

@Repository
public interface DescontoMongoDao extends DescontoGeneric,
        MongoRepository<DescontoRecorrente, String> {

    @Query("{ 'descontoConta' : ?0 }")
    List<DescontoRecorrente> findAllByConta(Conta conta);

    @Aggregation("{ $group: { _id: null, avgValue: { $avg: '$valorDesconto' } } }")
    Double findAverageDiscountValue();

    Double findAverageByValorDescontoIsNotNull();

    List<DescontoRecorrente> findAllByDataDesconto(Date data);

    List<DescontoRecorrente> findAllByValorDescontoBetween(double minValor, double maxValor);

    DescontoRecorrente findTopByOrderByValorDescontoDesc();

    DescontoRecorrente findTopByOrderByValorDescontoAsc();
}