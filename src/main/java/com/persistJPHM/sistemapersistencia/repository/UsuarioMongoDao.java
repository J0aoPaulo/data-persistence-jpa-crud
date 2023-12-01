package com.persistJPHM.sistemapersistencia.repository;

import com.persistJPHM.sistemapersistencia.entity.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioMongoDao extends MongoRepository<Usuario, String> {
}
