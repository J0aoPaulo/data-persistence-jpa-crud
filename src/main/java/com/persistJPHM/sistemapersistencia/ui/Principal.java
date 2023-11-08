package com.persistJPHM.sistemapersistencia.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com/persistJPHM/sistemapersistencia.entity")
@EnableJpaRepositories("com/persistJPHM/sistemapersistencia.DAO")
public class Principal {
	public static void main(String[] args) {
		SpringApplication.run(InsereUsuario.class, args);

	}
}