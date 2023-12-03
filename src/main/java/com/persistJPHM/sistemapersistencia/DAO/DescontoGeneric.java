package com.persistJPHM.sistemapersistencia.DAO;

import com.persistJPHM.sistemapersistencia.entity.Conta;
import com.persistJPHM.sistemapersistencia.entity.DescontoRecorrente;

import java.util.Date;
import java.util.List;

public interface DescontoGeneric {
    public List<DescontoRecorrente> findAllByConta(Conta conta);

    public Double findAverageDiscountValue();

    public List<DescontoRecorrente> findAllByDataDesconto(Date data);

    public List<DescontoRecorrente> findAllByValorDescontoBetween(double minValor, double maxValor);

    DescontoRecorrente findTopByOrderByValorDescontoDesc();

    DescontoRecorrente findTopByOrderByValorDescontoAsc();

        public void save(DescontoRecorrente desc);

        public void deleteById(String id);

        public DescontoRecorrente findById(String id);

        public List<DescontoRecorrente> findAll();

}