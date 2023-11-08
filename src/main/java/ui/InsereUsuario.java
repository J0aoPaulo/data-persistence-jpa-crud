package ui;

import DAO.UsuarioDAO;
import entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@ComponentScan("com.persistJPHM.sistemapersistencia.DAO")
public class InsereUsuario implements CommandLineRunner {
    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public void run(String... args) throws Exception {
        Usuario usuFodse = new Usuario(null, "hermesonFodase", null);
        usuarioDAO.save(usuFodse);
    }
}
