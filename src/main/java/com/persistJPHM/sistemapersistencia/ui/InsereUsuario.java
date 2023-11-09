package com.persistJPHM.sistemapersistencia.ui;

import com.persistJPHM.sistemapersistencia.DAO.ContaDAO;
import com.persistJPHM.sistemapersistencia.DAO.UsuarioDAO;
import com.persistJPHM.sistemapersistencia.entity.Conta;
import com.persistJPHM.sistemapersistencia.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com/persistJPHM/sistemapersistencia")
public class InsereUsuario implements CommandLineRunner {
    @Autowired
    private UsuarioDAO usuarioDAO;
    private ContaDAO contaDAO;

    @Override
    public void run(String... args) throws Exception {

    }
}
