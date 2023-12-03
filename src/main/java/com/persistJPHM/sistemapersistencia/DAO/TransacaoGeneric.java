package com.persistJPHM.sistemapersistencia.DAO;

import com.persistJPHM.sistemapersistencia.entity.Transacao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TransacaoGeneric {

    public List<Transacao> buscarEntreValores(double minValue, double maxValue);

    public int numTransacoes();

    public List<Transacao> findByDateInterval(Date startDate, Date endDate);

    public Double calculateAverageValue();

    public Transacao findTopByOrderByValorTransacaoAsc();

    public Transacao findTopByOrderByValorTransacaoDesc();

        public void save(Transacao tran);

        public void deleteById(String id);

        public Optional<Transacao> findById(String id);

        public List<Transacao> findAll();
}
