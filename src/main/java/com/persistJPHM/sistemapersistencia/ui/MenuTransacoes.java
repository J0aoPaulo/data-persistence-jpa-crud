package com.persistJPHM.sistemapersistencia.ui;

import com.persistJPHM.sistemapersistencia.DAO.ContaGeneric;
import com.persistJPHM.sistemapersistencia.DAO.TransacaoGeneric;
import com.persistJPHM.sistemapersistencia.entity.Conta;
import com.persistJPHM.sistemapersistencia.entity.Transacao;
import lombok.extern.slf4j.Slf4j;
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
        ATUALIZAR_POR_ID,
        REMOVER_POR_ID,
        EXIBIR_POR_ID,
        EXIBIR_TODOS,
        EXIBIR_POR_INTERVALO_VALOR,
        NUMERO_TRANSACOES,
        EXIBIR_POR_INTERVALO_DATA,
        CALCULAR_MEDIA_VALOR,
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
        
        Date dataTransacao = new Date();
        double valorTransacao = Double.parseDouble(JOptionPane.showInputDialog("Valor da Transacao"));
        
        //Conta conta = contaDAO.findById(contaEscolhida).orElse(null);
        
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
                2 - Atualizar por ID
                3 - Remover por ID
                4 - Exibir por ID
                5 - Exibir todos
                6 - Exibir por intervalo de valor
                7 - Número de transacoes
                8 - Exibir por intervalo de data
                9 - Calcular média do valor
                10 - Exibir valor mais baixo
                11 - Exibir valor mais alto
                12 - Sair
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
            case ATUALIZAR_POR_ID:
                id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da Transacao a ser alterada"));
                transacao = transacaoDAO.findById(String.valueOf(id)).orElse(null);
                if (transacao != null) {
                    obterTransacao();
                    transacaoDAO.save(transacao);
                } else {
                    JOptionPane.showMessageDialog(null, "Não foi possível atualizar, pois a transacao não foi encontrada.");
                }
                break;
            case REMOVER_POR_ID:
                id = Integer.parseInt(JOptionPane.showInputDialog("ID da Transacao"));
                transacao = transacaoDAO.findById(String.valueOf(id)).orElse(null);
                if (transacao != null) {
                    transacaoDAO.deleteById(String.valueOf(Integer.valueOf(transacao.getId())));
                } else {
                    JOptionPane.showMessageDialog(null, "Não foi possível remover, pois a transacao não foi encontrada");
                }
                break;
            case EXIBIR_POR_ID:
                id = Integer.parseInt(JOptionPane.showInputDialog("ID da Transacao"));
                transacao = transacaoDAO.findById(String.valueOf(id)).orElse(null);
                exibirTransacao(transacao);
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
            case EXIBIR_POR_INTERVALO_DATA:
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date sd = dateFormat.parse(JOptionPane.showInputDialog("Data menor"));
                Date ed = dateFormat.parse(JOptionPane.showInputDialog("Data maior"));
                transacoes = transacaoDAO.findByDateInterval(sd, ed);
                listarTransacoes(transacoes);
                break;
            case CALCULAR_MEDIA_VALOR:
                Double mediaValor = transacaoDAO.calculateAverageValue();
                JOptionPane.showMessageDialog(null, mediaValor == null ? "Nenhuma transacao no sistema"
                        : "Média do valor das transacoes no sistema: " + mediaValor);
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