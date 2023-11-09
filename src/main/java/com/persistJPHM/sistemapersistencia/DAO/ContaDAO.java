package com.persistJPHM.sistemapersistencia.DAO;

import com.persistJPHM.sistemapersistencia.entity.Conta;
import com.persistJPHM.sistemapersistencia.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaDAO extends JpaRepository<Conta, Integer> {
    //Pesquisar um id de conta especifico
    Conta findByContaId(Integer contaId);

    //Pesquisar numero de telefone específico
    Conta findByNumeroTelefone(String numero);

    //Listar todas as contas existentes
    @Query("Select c FROM Conta c")
    public Conta consultarTodasContas();

    // Consultar todas as contas com um desconto recorrente nelas
    @Query(name = "consultarContasComDescontos")
    public List<Conta> consultarContasComDesconto();

    // Mostra os valores nas contas que são maiores que um valor x
    @Query("SELECT c FROM Conta c WHERE c.valorTotalConta() > :valorLimite")
    public Conta valoresNaContaMaior(@Param("valorLimite") double valorLimite);

    // Consultar a conta com o maior valor total
    @Query(value = "SELECT c.* FROM Conta c" +
            "ORDER BY c.valorTotalConta DESC LIMIT 1",
            nativeQuery = true)
    public Conta findContaComMaiorValorTotal();

    // Consultar telefones com ddd específico
    @Query("SELECT c FROM Conta c WHERE SUBSTRING(c.numeroTelefone, 1, 2) = :ddd")
    public Conta consultaPorDdd(@Param("ddd") String ddd);

    //Listar todos os numeros de telefone
    @Query(name = "listarTodosTelefones")
    public List<String> consultarTodosTelefones();
}