package com.persistJPHM.sistemapersistencia.ui;


import com.persistJPHM.sistemapersistencia.entity.Conta;
import com.persistJPHM.sistemapersistencia.entity.Transacao;
import com.persistJPHM.sistemapersistencia.entity.Usuario;
import com.persistJPHM.sistemapersistencia.DAO.mongo.ContaMongoDao;
import com.persistJPHM.sistemapersistencia.DAO.mongo.DescontoMongoDao;
import com.persistJPHM.sistemapersistencia.DAO.mongo.TransacaoMongoDao;
import com.persistJPHM.sistemapersistencia.DAO.mongo.UsuarioMongoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

@SpringBootApplication
@EntityScan("com/persistJPHM/sistemapersistencia.entity")
@EnableJpaRepositories("com/persistJPHM/sistemapersistencia.DAO")
@ComponentScan("com.persistJPHM.sistemapersistencia")
@EnableMongoRepositories("com.persistJPHM.sistemapersistencia.repository")
public class MenuPrincipal implements CommandLineRunner {
	@Autowired
	MenuUsuarios menuUsuarios;

	@Autowired
	MenuTransacoes menuTransacoes;

	@Autowired
	MenuContas menuContas;

	@Autowired
	MenuDesconto menuDesconto;

	@Autowired
	ContaMongoDao contaMongoDao;

	@Autowired
	DescontoMongoDao descontoMongoDao;

	@Autowired
	TransacaoMongoDao transacaoMongoDao;

	@Autowired
	UsuarioMongoDao usuarioMongoDao;

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(MenuPrincipal.class);
		builder.headless(false).run(args);
	}
	
	@Override
	public void run(String... args) {
		int op = 0;

		while (op < 5) {
			//contaMongoDao.save(new Conta());
			//contaMongoDao.findByIdNativeQuery("0");
			//contaMongoDao.findByNumeroTelefoneJPQL("9999999");
			//descontoMongoDao.save(new DescontoRecorrente());
			//transacaoMongoDao.save(new Transacao());
			//usuarioMongoDao.save(new Usuario());
			op = Integer.parseInt(JOptionPane.showInputDialog(null, "1 - Usuarios\n2 - Conta\n3 - Transacoes\n4 - Descontos"));

			switch (op) {
				case 1:
					// menuUsuarios.menu();
					Conta conta = new Conta("herso", new Usuario(), "99999999", new ArrayList<>(), new ArrayList<>());
					contaMongoDao.save(conta);
					break;
				case 2:
					menuContas.menu();
					break;
				case 3:
					Transacao transacao = new Transacao(null, new Conta(), new Date(), 100.0);
					transacaoMongoDao.save(transacao);
					// menuTransacoes.menu();
					break;
				case 4:
					menuDesconto.menu();
				default:
					break;
			}
		}
	}
}