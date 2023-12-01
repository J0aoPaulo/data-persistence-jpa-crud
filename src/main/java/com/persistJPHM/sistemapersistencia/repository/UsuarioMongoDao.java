package com.persistJPHM.sistemapersistencia.repository;

import com.persistJPHM.sistemapersistencia.DAO.UsuarioDAO;
import com.persistJPHM.sistemapersistencia.entity.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioMongoDao extends UsuarioDAO, MongoRepository<Usuario, String> {
}
