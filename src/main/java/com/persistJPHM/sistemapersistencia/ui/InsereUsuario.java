package com.persistJPHM.sistemapersistencia.ui;

import com.persistJPHM.sistemapersistencia.DAO.UsuarioDAO;
import com.persistJPHM.sistemapersistencia.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com/persistJPHM/sistemapersistencia")
public class InsereUsuario implements CommandLineRunner {
    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public void run(String... args) throws Exception {
        Usuario usuFodse = new Usuario(null, "hermesonFodase", null);
        usuarioDAO.save(usuFodse);
    }
}