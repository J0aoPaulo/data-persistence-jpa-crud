package com.persistJPHM.sistemapersistencia;

import DAO.UsuarioDAO;
import entity.Conta;
import entity.Usuario;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ui.InsereUsuario;

@SpringBootApplication
@EntityScan("com.persistJPHM.sistemapersistencia.entity")
@EnableJpaRepositories("com.persistJPHM.sistemapersistencia.DAO")
public class SistemaPersistenciaApplication {
	public static void main(String[] args) {
		SpringApplication.run(InsereUsuario.class, args);

	}
}