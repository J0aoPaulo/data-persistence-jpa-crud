package com.persistJPHM.sistemapersistencia.DAO;

import com.persistJPHM.sistemapersistencia.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, Integer> {

}