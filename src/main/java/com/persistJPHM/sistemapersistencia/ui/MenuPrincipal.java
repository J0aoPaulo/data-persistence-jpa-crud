package com.persistJPHM.sistemapersistencia.ui;

import com.persistJPHM.sistemapersistencia.model.MongoConta;
import com.persistJPHM.sistemapersistencia.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
//@EntityScan("com/persistJPHM/sistemapersistencia.entity")
//@EnableJpaRepositories("com/persistJPHM/sistemapersistencia.DAO")
//@ComponentScan("com.persistJPHM.sistemapersistencia")
@EnableMongoRepositories("com.persistJPHM.sistemapersistencia.repository")
public class MenuPrincipal implements CommandLineRunner {
	//@Autowired
	//MenuUsuarios menuUsuarios;

	//@Autowired
	//MenuTransacoes menuTransacoes;

	//@Autowired
	//MenuContas menuContas;

	//@Autowired
	//MenuDesconto menuDesconto

	@Autowired
	ContaRepository contaRepository;

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(MenuPrincipal.class);
		builder.headless(false).run(args);
	}

	@Override
	public void run(String... args) {
		contaRepository.save(new MongoConta("Hermeson Borgas"));


		/*int op = 0;

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
		}*/
	}
}