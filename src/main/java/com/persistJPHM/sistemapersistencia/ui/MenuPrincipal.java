package com.persistJPHM.sistemapersistencia.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.swing.*;

// @EnableJpaRepositories("com/persistJPHM/sistemapersistencia.DAO.jpa")
@EnableMongoRepositories("com.persistJPHM.sistemapersistencia.DAO.mongo")
@SpringBootApplication(scanBasePackages = { "com.persistJPHM.sistemapersistencia" })
@EntityScan("com.persistJPHM.sistemapersistencia.entity")
public class MenuPrincipal implements CommandLineRunner {
	@Autowired
	MenuUsuarios menuUsuarios;

	@Autowired
	MenuTransacoes menuTransacoes;

	@Autowired
	MenuContas menuContas;

	@Autowired
	MenuDesconto menuDesconto;

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(MenuPrincipal.class);
		builder.headless(false).run(args);
	}
	
	@Override
	public void run(String... args) {
		int op = 0;

		while (op < 5) {
			op = Integer.parseInt(JOptionPane.showInputDialog(null, "1 - Usuarios\n2 - Conta\n3 - Transacoes\n4 - Descontos"));

			switch (op) {
				case 1:
					menuUsuarios.menu();
					break;
				case 2:
					menuContas.menu();
					break;
				case 3:
					menuTransacoes.menu();
					break;
				case 4:
					menuDesconto.menu();
				default:
					break;
			}
		}
	}
}