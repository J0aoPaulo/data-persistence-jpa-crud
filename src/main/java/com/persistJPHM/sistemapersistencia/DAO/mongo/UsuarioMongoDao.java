package com.persistJPHM.sistemapersistencia.DAO.mongo;

import com.persistJPHM.sistemapersistencia.entity.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.persistJPHM.sistemapersistencia.DAO.UsuarioGeneric;


public interface UsuarioMongoDao extends UsuarioGeneric,
        MongoRepository<Usuario, String> {


}
