package com.persistJPHM.sistemapersistencia.DAO;

import com.persistJPHM.sistemapersistencia.entity.Conta;

import java.util.List;
import java.util.Optional;

public interface ContaGeneric {

    public Conta findByNumeroTelefone(String numero);

    public List<Conta> consultarTodasContas();

    public List<String> consultarTodosTelefones();

        public void save(Conta conta);

        public void deleteById(String id);

        public Optional<Conta> findById(String id);

        public List<Conta> findAll();
}