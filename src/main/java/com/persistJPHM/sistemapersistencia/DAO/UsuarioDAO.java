package com.persistJPHM.sistemapersistencia.DAO;

import java.util.List;
import java.util.Set;

import com.persistJPHM.sistemapersistencia.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, Integer> {
    //Buscar Usuario por cpf
    Usuario findFirstByCpf(String cpf);

    //Buscar por nome em ordem alfabética
    Usuario findByNomeOrderByNome(String nome);

    //Procurar cpf pelos 3 primeiros numeros fornecidos
   @Query("SELECT u FROM Usuario u WHERE SUBSTRING(u.cpf, 1, 3) = :tresPrimeirosNumeros")
    Set<Usuario> procureCpfsComTresPrimeirosNumeros(@Param("tresPrimeirosNumeros") String tresPrimeirosNumeros);

    //Procurar usuarios entre doi‘ids’ diferentes
    @Query(name = "usuarioEntreID")
    public Usuario consultarUsuarioEntreId(String cpfInicial, String cpfFinal);

    //Consultar Usuario por nome específico
    @Query(name = "consultaPorNome")
    public Usuario consultaPorNomeEspecifico(String nome);

    //Consultar nomes de Usuarios por um caractere específico
    @Query(value = "Select nome FROM Usuario WHERE LOWER(nome) LIKE :prefix%", nativeQuery = true)
    List<String> consultaPorLetra(@Param("prefix") String prefix);

    //Conta quantos usuarios existem
    @Query(value = "select count(*) from usuario u", nativeQuery = true)
    public long contaUsuarios();
}