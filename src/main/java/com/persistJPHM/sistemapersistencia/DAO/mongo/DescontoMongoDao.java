package com.persistJPHM.sistemapersistencia.DAO.mongo;

import com.persistJPHM.sistemapersistencia.entity.Conta;
import com.persistJPHM.sistemapersistencia.entity.DescontoRecorrente;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.persistJPHM.sistemapersistencia.DAO.DescontoGeneric;


public interface DescontoMongoDao extends DescontoGeneric,
        MongoRepository<DescontoRecorrente, String> {

    List<DescontoRecorrente> findAllByConta(Conta conta);

    Double findAverageByValorDescontoIsNotNull();

    List<DescontoRecorrente> findAllByDataDesconto(Date data);

    List<DescontoRecorrente> findAllByValorDescontoBetween(double minValor, double maxValor);

    DescontoRecorrente findTopByOrderByValorDescontoDesc();

    DescontoRecorrente findTopByOrderByValorDescontoAsc();
}