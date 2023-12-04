package com.persistJPHM.sistemapersistencia.DAO.jpa;

import com.persistJPHM.sistemapersistencia.entity.Transacao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.persistJPHM.sistemapersistencia.DAO.TransacaoGeneric;


@Repository
public interface TransacaoJPADAO extends TransacaoGeneric,
        JpaRepository<Transacao, String> {

  // ----------------------------- JPQL ----------------------------- //
  
  // busca uma lista de transacoes em um intervalor de valor
  @Query("SELECT t FROM Transacao t WHERE t.valorTransacao BETWEEN :minValue AND :maxValue") 
  public List<Transacao> buscarEntreValores(double minValue, double maxValue);


  // ------------------------- Native Query ------------------------- //
  
  // busca uma lista de todas as transacoes
  @Query(value = "SELECT * FROM Transacao", nativeQuery = true)
  public List<Transacao> listALL();

  // retorna o numero de transacoes cadastradas
  @Query(value = "SELECT COUNT(*) from Transacao t", nativeQuery=true)
	public int numTransacoes();


  // ------------------------- Named Query ------------------------- //
  // busca uma lista de transações por intervalo de data
  @Query(name = "findByDateInterval")
  public List<Transacao> findByDateInterval(Date startDate, Date endDate);

  // busca o valor médio das transacoes
  @Query(name = "calculateAverageValue")
  public Double calculateAverageValue();


  // ---------------------- por nome do método ---------------------- //

  // busca a transação com o valor mais baixo
  public Transacao findTopByOrderByValorTransacaoAsc();

  // busca a transação com o valor mais alto
  public Transacao findTopByOrderByValorTransacaoDesc();
}