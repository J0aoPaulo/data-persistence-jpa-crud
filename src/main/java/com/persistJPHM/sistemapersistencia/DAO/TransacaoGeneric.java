package com.persistJPHM.sistemapersistencia.DAO;

import com.persistJPHM.sistemapersistencia.entity.Transacao;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TransacaoGeneric {
    public Transacao buscaPorId(String id);

    public List<Transacao> buscaPorContaId(double minValue, double maxValue);

    public List<Transacao> listALL();

    public int numTransacoes();

    public List<Transacao> findByDateInterval(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    public Double calculateAverageValue();

    public Transacao findTopByOrderByValorTransacaoAsc();

    public Transacao findTopByOrderByValorTransacaoDesc();
}
