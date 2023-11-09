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
    Usuario findFirstByCpf(String cpf);

    Usuario findByNameOrderByName(String cpf);

    @Query(value = "Select nome FROM Usuario WHERE LOWER(nome) LIKE :prefix%", nativeQuery = true)
    List<String> consultaPorLetra(@Param("prefix") String prefix);

    @Query("SELECT u.cpf FROM Usuario WHERE SUBSTRING(u.cpf, 1, 3) = :tresPrimeirosNumeros")
    Set<String> procureCpfsComTresPrimeirosNumeros(@Param("tresPrimeirosNumeros") String tresPrimeirosNumeros);

    @Query(name = "usuarioEntreID")
    public Usuario consultarUsuarioEntreId(String cpfInicial, String cpfFinal);

    @Query("consultaPorNome")
    public Usuario consultaPorNomeEspecifico(String nome);

    @Query(value = "select count(*) from usuario u", nativeQuery = true)
    public long contaUsuarios();
}