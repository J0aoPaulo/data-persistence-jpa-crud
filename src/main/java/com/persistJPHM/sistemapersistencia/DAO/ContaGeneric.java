package com.persistJPHM.sistemapersistencia.DAO;

import com.persistJPHM.sistemapersistencia.DAO.mongo.ContaMongoDao;
import com.persistJPHM.sistemapersistencia.entity.Conta;

import java.util.List;

public interface ContaGeneric {
    public Conta findByidConta(String idUsuario);

    public Conta findByNumeroTelefone(String numero);

    public List<Conta> consultarTodasContas();

    public List<String> consultarTodosTelefones();

    public Conta findByIdNativeQuery(String id);

    public List<ContaMongoDao.ContaProjection> findAllContaProjections();

    public Conta findByNumeroTelefoneJPQL(String numeroTelefone);

        public void deleteById(String id);

        public List<Conta> findAll();
}