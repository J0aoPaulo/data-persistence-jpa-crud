package com.persistJPHM.sistemapersistencia.ui;

import com.persistJPHM.sistemapersistencia.DAO.ContaGeneric;
import com.persistJPHM.sistemapersistencia.DAO.TransacaoGeneric;
import com.persistJPHM.sistemapersistencia.entity.Conta;
import com.persistJPHM.sistemapersistencia.entity.Transacao;
import lombok.extern.slf4j.Slf4j;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

import java.awt.HeadlessException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class MenuTransacoes {

    private enum OpcaoMenu {
        INSERIR,
        EXIBIR_TODOS,
        EXIBIR_POR_INTERVALO_VALOR,
        NUMERO_TRANSACOES,
        EXIBIR_VALOR_MAIS_BAIXO,
        EXIBIR_VALOR_MAIS_ALTO,
        SAIR
    }

    @Autowired
    private TransacaoGeneric transacaoDAO;

    @Autowired
    private ContaGeneric contaDAO;

    public void obterTransacao() {
        List<Conta> contas = contaDAO.findAll(); // Supondo que você tenha um método findAll() em contaDAO
        Conta contaEscolhida = (Conta) JOptionPane.showInputDialog(
                null, "Selecione uma conta",
                "Contas", JOptionPane.PLAIN_MESSAGE, null, contas.toArray(), null);

        if (contaEscolhida == null) {
            JOptionPane.showMessageDialog(null, "Selecione uma conta válida");
            return;
        }

        Hibernate.initialize(contaEscolhida.getTransacoes());
        
        Date dataTransacao = new Date();
        double valorTransacao = Double.parseDouble(JOptionPane.showInputDialog("Valor da Transacao"));
        
        
        Transacao transacao = new Transacao(null, contaEscolhida, dataTransacao, valorTransacao);

        transacaoDAO.save(transacao);
    }

    public void listarTransacoes(List<Transacao> transacoes) {
        StringBuilder listagem = new StringBuilder();
        for (Transacao transacao : transacoes) {
            listagem.append(transacao.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, listagem.isEmpty() ? "Nenhuma transacao encontrada" : listagem);
    }

    public void exibirTransacao(Transacao transacao) {
        JOptionPane.showMessageDialog(null, transacao == null ? "Nenhuma transacao encontrada" : transacao.toString());
    }

    public void menu() {
        OpcaoMenu opcao = null;
        do {
            try {
                opcao = obterOpcaoMenu();
                realizarAcao(opcao);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                JOptionPane.showMessageDialog(null, "Erro " + e.getMessage());
            }
        } while (opcao != OpcaoMenu.SAIR);
    }

    private OpcaoMenu obterOpcaoMenu() {
        String menu = """
                Menu Transacoes
                1 - Inserir
                2 - Exibir todos
                3 - Exibir por intervalo de valor
                4 - Número de transacoes
                5 - Exibir valor mais baixo
                6 - Exibir valor mais alto
                7 - Sair
                """;

        String opcaoStr = JOptionPane.showInputDialog(menu);
        int opcaoInt = Integer.parseInt(opcaoStr);

        if (opcaoInt < 1 || opcaoInt > OpcaoMenu.values().length) {
            JOptionPane.showMessageDialog(null, "Opção inválida");
            return obterOpcaoMenu();
        }

        return OpcaoMenu.values()[opcaoInt - 1];
    }  
    
    private void realizarAcao(OpcaoMenu opcao) throws HeadlessException, ParseException {
        Transacao transacao;
        List<Transacao> transacoes;
        Integer id;
        switch (opcao) {
            case INSERIR:
                obterTransacao();
                break;
            case EXIBIR_TODOS:
                listarTransacoes(transacaoDAO.findAll());
                break;
            case EXIBIR_POR_INTERVALO_VALOR:
                double minValue = Double.parseDouble(JOptionPane.showInputDialog("Valor Mínimo"));
                double maxValue = Double.parseDouble(JOptionPane.showInputDialog("Valor Máximo"));
                transacoes = transacaoDAO.buscarEntreValores(minValue, maxValue);
                listarTransacoes(transacoes);
                break;
            case NUMERO_TRANSACOES:
                int numTransacoes = transacaoDAO.numTransacoes();
                JOptionPane.showMessageDialog(null, numTransacoes == 0 ? "Nenhuma transacao no sistema"
                        : "Número de transacoes no sistema: " + numTransacoes);
                break;
            case EXIBIR_VALOR_MAIS_BAIXO:
                transacao = transacaoDAO.findTopByOrderByValorTransacaoAsc();
                exibirTransacao(transacao);
                break;
            case EXIBIR_VALOR_MAIS_ALTO:
                transacao = transacaoDAO.findTopByOrderByValorTransacaoDesc();
                exibirTransacao(transacao);
                break;
            case SAIR:
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida");
                break;
        }
    }
}