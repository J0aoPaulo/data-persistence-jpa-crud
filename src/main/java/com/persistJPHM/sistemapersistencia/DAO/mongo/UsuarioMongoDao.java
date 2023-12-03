package com.persistJPHM.sistemapersistencia.DAO.mongo;

import com.persistJPHM.sistemapersistencia.entity.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

public interface UsuarioMongoDao extends MongoRepository<Usuario, String> {
    // Buscar Usuario por cpf
    Usuario findFirstByCpf(String cpf);

    // Buscar por nome em ordem alfabética
    Usuario findByNomeOrderByNome(String nome);

    // Procurar cpf pelos 3 primeiros numeros fornecidos
    @Query("{'cpf': {$regex : '^?0.*', $options: 'i'}}")
    List<Usuario> procureCpfsComTresPrimeirosNumeros(String tresPrimeirosNumeros);

    // Procurar usuarios entre dois 'ids’ diferentes
    List<Usuario> findByIdUsuarioBetween(String idInicial, String idFinal);

    // Consultar Usuario por nome específico
    List<Usuario> findByNome(String nome);

    // Consultar nomes de Usuarios por um caractere específico
    @Query("{'nome': {$regex : '^?0.*', $options: 'i'}}")
    List<Usuario> consultaPorLetra(String prefix);

    // Conta quantos usuarios existem
    long count();

    // Consulta por ID de Usuario
    Usuario findByIdUsuario(String idUsuario);
}
