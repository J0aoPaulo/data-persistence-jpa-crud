package com.persistJPHM.sistemapersistencia.ui;

import javax.swing.JOptionPane;

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

	@Autowired
	MenuContas menucontas;

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(MenuPrincipal.class);
		builder.headless(false).run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		// menuTransacoes.menu();
		// menucontas.menu();
		int op = 0;

		while(op < 4) {
			op = Integer.parseInt(JOptionPane.showInputDialog(null, "1 - Usuarios\n2 - Conta\n3 - Transacoes\n"));

			switch (op) {
				case 1:
					menuUsuarios.menu();
				break;
				case 2:
					menucontas.menu();
				break;
				case 3:
					menuTransacoes.menu();
				break;
				default:
					break;
			}
		}
	}
}