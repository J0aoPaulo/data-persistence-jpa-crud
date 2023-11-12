package com.persistJPHM.sistemapersistencia.DAO;

import com.persistJPHM.sistemapersistencia.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaDAO extends JpaRepository<Conta, Integer> {
    //Pesquisar um id de conta especifico -
    Conta findByidConta(Integer idUsuario);

    //Pesquisar numero de telefone específico -
    Conta findByNumeroTelefone(String numero);

    //Consultar telefones com ddd específico -
    @Query("SELECT c FROM Conta c WHERE SUBSTRING(c.numeroTelefone, 1, 2) = :ddd")
    public List<Conta> consultaPorDdd(@Param("ddd") String ddd);

    //Listar todas as contas existentes -
    @Query(value = "SELECT * FROM Conta", nativeQuery = true)
    public List<Conta> consultarTodasContas();

    //Consultar todas as contas com um desconto recorrente nelas -
    @Query(name = "consultarContasComDescontos")
    public List<Conta> consultarContasComDesconto();

    //Listar todos os numeros de telefone -
    @Query(name = "listarTodosTelefones")
    public List<Conta> consultarTodosTelefones();
}