package com.persistJPHM.sistemapersistencia.DAO;

import com.persistJPHM.sistemapersistencia.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaDAO extends JpaRepository<Conta, Integer> {

}