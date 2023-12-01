package com.persistJPHM.sistemapersistencia.DAO;

import java.util.List;

import com.persistJPHM.sistemapersistencia.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, String> {
    //Buscar Usuario por cpf
    Usuario findFirstByCpf(String cpf);

    //Buscar por nome em ordem alfabética
    Usuario findByNomeOrderByNome(String nome);

    //Procurar cpf pelos 3 primeiros numeros fornecidos
    @Query("SELECT u FROM Usuario u WHERE SUBSTRING(u.cpf, 1, 3) = :tresPrimeirosNumeros")
    List<Usuario> procureCpfsComTresPrimeirosNumeros(@Param("tresPrimeirosNumeros") String tresPrimeirosNumeros);

    //Procurar usuarios entre dois 'ids’ diferentes
    @Query(name = "usuarioEntreID")
    public List<Usuario> consultarUsuarioEntreId(String idInicial, String idFinal);

    //Consultar Usuario por nome específico
    @Query(name = "consultaPorNome")
    public List<Usuario> consultaPorNomeEspecifico(String nome);

    //Consultar nomes de Usuarios por um caractere específico
    @Query(value = "Select * FROM Usuario WHERE nome LIKE :prefix%", nativeQuery = true)
    List<Usuario> consultaPorLetra(@Param("prefix") String prefix);

    //Conta quantos usuarios existem
    @Query(value = "select count(*) from usuario u", nativeQuery = true)
    public long contaUsuarios();

    @Query("SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario")
    Usuario findByIdUsuario(@Param("idUsuario") String idUsuario);
}