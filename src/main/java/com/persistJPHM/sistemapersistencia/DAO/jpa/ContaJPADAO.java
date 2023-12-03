package com.persistJPHM.sistemapersistencia.DAO.jpa;

import com.persistJPHM.sistemapersistencia.DAO.ContaGeneric;
import com.persistJPHM.sistemapersistencia.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaJPADAO extends ContaGeneric, JpaRepository<Conta, String> {
    //Pesquisar um id de conta especifico -
    Conta findByidConta(String idUsuario);

    //Pesquisar numero de telefone específico -
    Conta findByNumeroTelefone(String numero);

    //Listar todas as contas existentes -
    @Query(value = "SELECT * FROM Conta", nativeQuery = true)
    public List<Conta> consultarTodasContas();

    //Listar todos os numeros de telefone -
    @Query(name = "listarTodosTelefones")
    public List<String> consultarTodosTelefones();
}