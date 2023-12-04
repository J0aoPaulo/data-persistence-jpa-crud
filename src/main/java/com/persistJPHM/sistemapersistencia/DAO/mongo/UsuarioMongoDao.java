package com.persistJPHM.sistemapersistencia.DAO.mongo;

import com.persistJPHM.sistemapersistencia.DAO.UsuarioGeneric;
import com.persistJPHM.sistemapersistencia.entity.Usuario;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioMongoDao extends UsuarioGeneric, MongoRepository<Usuario, String> {
    // Buscar Usuario por cpf
    Usuario findFirstByCpf(String cpf);

    // Buscar por nome em ordem alfabética
    Usuario findByNomeOrderByNome(String nome);

    // Procurar cpf pelos 3 primeiros numeros fornecidos
    @Query("{'cpf': {$regex : '^?0.*', $options: 'i'}}")
    List<Usuario> procureCpfsComTresPrimeirosNumeros(String tresPrimeirosNumeros);

    // Consultar Usuario por nome específico
    List<Usuario> findByNome(String nome);

    // Consultar nomes de Usuarios por um caractere específico
    @Query("{'nome': {$regex : '^?0.*', $options: 'i'}}")
    List<Usuario> consultaPorLetra(String prefix);

    @Query("{ 'nome' : ?0 }")
    List<Usuario> consultaPorNomeEspecifico(String nome);

    // Conta quantos usuarios existem
    @Query(value = "{}", count = true)
    public int contaUsuarios();
}
