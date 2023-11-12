package com.persistJPHM.sistemapersistencia.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com/persistJPHM/sistemapersistencia.entity")
@EnableJpaRepositories("com/persistJPHM/sistemapersistencia.DAO")
public class 	MenuPrincipal implements CommandLineRunner {
	@Autowired
	MenuUsuarios menuUsuarios;

	@Autowired
	MenuTransacoes menuTransacoes;
	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(MenuPrincipal.class);
		builder.headless(false).run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		//menuUsuarios.menu();
		menuTransacoes.menu();
	}
}