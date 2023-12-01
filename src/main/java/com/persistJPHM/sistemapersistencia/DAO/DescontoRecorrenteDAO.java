package com.persistJPHM.sistemapersistencia.DAO;

import com.persistJPHM.sistemapersistencia.entity.Conta;
import com.persistJPHM.sistemapersistencia.entity.DescontoRecorrente;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DescontoRecorrenteDAO extends JpaRepository<DescontoRecorrente, String> {
  // ----------------------------- JPQL ----------------------------- //

  // busca todos os descontos de uma conta específica
  @Query("SELECT dr FROM DescontoRecorrente dr WHERE dr.descontoConta = :conta")
  List<DescontoRecorrente> findAllByConta(@Param("conta") Conta conta);


  // ------------------------- Native Query ------------------------- //
  // busca o valor médio dos descontos recorrentes
  @Query(value = "SELECT AVG(valor_desconto) FROM desconto_recorrente", nativeQuery = true)
  Double findAverageDiscountValue();

  // busca uma lista de descontos que ocorrem em determinada data
  @Query(value = "SELECT * FROM desconto_recorrente WHERE data_desconto = :data", nativeQuery = true)
  List<DescontoRecorrente> findAllByDataDesconto(@Param("data") Date data);

  
  // ------------------------- Named Query ------------------------- //

  // busca uma lista de de descontos com base em um intervalo de valores específicos
  @Query(name = "findAllByValorDescontoBetween")
  List<DescontoRecorrente> findAllByValorDescontoBetween(@Param("minValor") double minValor, @Param("maxValor") double maxValor);

  // ---------------------- por nome do método ---------------------- //

  // Maior desconto já ocorrido
  DescontoRecorrente findTopByOrderByValorDescontoDesc();

  // Menor desconto já ocorrido
  DescontoRecorrente findTopByOrderByValorDescontoAsc();
}