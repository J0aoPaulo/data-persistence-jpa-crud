package com.persistJPHM.sistemapersistencia.DAO.jpa;

import java.util.List;

import com.persistJPHM.sistemapersistencia.DAO.UsuarioGeneric;
import com.persistJPHM.sistemapersistencia.entity.Usuario;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioJPADAO extends UsuarioGeneric, JpaRepository<Usuario, String> {
    //Buscar Usuario por cpf
    Usuario findFirstByCpf(String cpf);

    //Buscar por nome em ordem alfabética
    Usuario findByNomeOrderByNome(String nome);

    //Procurar cpf pelos 3 primeiros numeros fornecidos
    @Query("SELECT u FROM Usuario u WHERE SUBSTRING(u.cpf, 1, 3) = :tresPrimeirosNumeros")
    List<Usuario> procureCpfsComTresPrimeirosNumeros(String tresPrimeirosNumeros);

    //Consultar Usuario por nome específico
    @Query(name = "consultaPorNome")
    public List<Usuario> consultaPorNomeEspecifico(String nome);

    //Consultar nomes de Usuarios por um caractere específico
    @Query(value = "Select * FROM Usuario WHERE nome LIKE :prefix%", nativeQuery = true)
    List<Usuario> consultaPorLetra(String prefix);

    //Conta quantos usuarios existem
    @Query(value = "select count(*) from usuario u", nativeQuery = true)
    public int contaUsuarios();
}