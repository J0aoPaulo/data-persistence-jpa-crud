package com.persistJPHM.sistemapersistencia.ui;

import com.persistJPHM.sistemapersistencia.DAO.ContaDAO;
import com.persistJPHM.sistemapersistencia.DAO.DescontoRecorrenteDAO;
import com.persistJPHM.sistemapersistencia.entity.Conta;
import com.persistJPHM.sistemapersistencia.entity.DescontoRecorrente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.HeadlessException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class MenuDesconto {

  private enum OpcaoMenu {
    INSERIR,
    ATUALIZAR_POR_ID,
    REMOVER_POR_ID,
    EXIBIR_POR_ID,
    EXIBIR_TODOS,
    EXIBIR_POR_CONTA,
    CALCULAR_MEDIA_VALOR,
    EXIBIR_MAIS_ALTO,
    EXIBIR_MAIS_BAIXO,
    SAIR
  }

  @Autowired
  private DescontoRecorrenteDAO descontoRecorrenteDAO;

  @Autowired
  private ContaDAO contaDAO;

  public void obterDescontoRecorrente(DescontoRecorrente descontoRecorrente) {
    String idConta = String.valueOf(Integer.parseInt(JOptionPane.showInputDialog("ID da Conta")));
    double valorDesconto = Double.parseDouble(JOptionPane.showInputDialog("Valor do Desconto"));

    Conta conta = contaDAO.findById(idConta).orElse(null);

    if (conta == null) {
      JOptionPane.showMessageDialog(null,
          "Conta não encontrada com o ID fornecido. Desconto Recorrente não será inserido.");
    } else {
      descontoRecorrente.setDescontoConta(conta);
      descontoRecorrente.setValorDesconto(valorDesconto);
      descontoRecorrente.setDataDesconto(new Date());

      descontoRecorrenteDAO.save(descontoRecorrente);
      JOptionPane.showMessageDialog(null, "Desconto Recorrente inserido com sucesso!");
    }
  }

  public void listarDescontosRecorrentes(List<DescontoRecorrente> descontosRecorrentes) {
    StringBuilder listagem = new StringBuilder();
    for (DescontoRecorrente descontoRecorrente : descontosRecorrentes) {
      listagem.append(descontoRecorrente.toString()).append("\n");
    }
    JOptionPane.showMessageDialog(null, listagem.isEmpty() ? "Nenhum desconto recorrente encontrado" : listagem);
  }

  public void exibirDescontoRecorrente(DescontoRecorrente descontoRecorrente) {
    JOptionPane.showMessageDialog(null,
        descontoRecorrente == null ? "Nenhum desconto recorrente encontrado" : descontoRecorrente.toString());
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
        Menu Desconto Recorrente
        1 - Inserir
        2 - Atualizar por ID
        3 - Remover por ID
        4 - Exibir por ID
        5 - Exibir todos
        6 - Exibir por conta
        7 - Calcular média do valor
        8 - Exibir valor mais alto
        9 - Exibir valor mais baixo
        10 - Sair
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
    DescontoRecorrente descontoRecorrente;
    List<DescontoRecorrente> descontosRecorrentes;
    String id;
    switch (opcao) {
      case INSERIR:
        descontoRecorrente = new DescontoRecorrente();
        obterDescontoRecorrente(descontoRecorrente);
        break;
      case ATUALIZAR_POR_ID:
        id = String.valueOf(Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do Desconto Recorrente a ser alterado")));
        descontoRecorrente = descontoRecorrenteDAO.findById(String.valueOf(Integer.valueOf(id))).orElse(null);
        if (descontoRecorrente != null) {
          obterDescontoRecorrente(descontoRecorrente);
          descontoRecorrenteDAO.save(descontoRecorrente);
        } else {
          JOptionPane.showMessageDialog(null,
              "Não foi possível atualizar, pois o desconto recorrente não foi encontrado.");
        }
        break;
      case REMOVER_POR_ID:
        id = String.valueOf(Integer.parseInt(JOptionPane.showInputDialog("ID do Desconto Recorrente")));
        descontoRecorrente = descontoRecorrenteDAO.findById(String.valueOf(Integer.valueOf(id))).orElse(null);
        if (descontoRecorrente != null) {
          descontoRecorrenteDAO.deleteById(descontoRecorrente.getIdDesconto());
        } else {
          JOptionPane.showMessageDialog(null,
              "Não foi possível remover, pois o desconto recorrente não foi encontrado");
        }
        break;
      case EXIBIR_POR_ID:
        id = String.valueOf(Integer.parseInt(JOptionPane.showInputDialog("ID do Desconto Recorrente")));
        descontoRecorrente = descontoRecorrenteDAO.findById(String.valueOf(Integer.valueOf(id))).orElse(null);
        exibirDescontoRecorrente(descontoRecorrente);
        break;
      case EXIBIR_TODOS:
        listarDescontosRecorrentes(descontoRecorrenteDAO.findAll());
        break;
      case EXIBIR_POR_CONTA:
        id = String.valueOf(Integer.parseInt(JOptionPane.showInputDialog("ID da Conta")));
        Conta conta = contaDAO.findById(id).orElse(null);
        if (conta != null) {
          descontosRecorrentes = descontoRecorrenteDAO.findAllByConta(conta);
          listarDescontosRecorrentes(descontosRecorrentes);
        } else {
          JOptionPane.showMessageDialog(null, "Conta não encontrada com o ID fornecido.");
        }
        break;
      case CALCULAR_MEDIA_VALOR:
        Double mediaValor = descontoRecorrenteDAO.findAverageDiscountValue();
        JOptionPane.showMessageDialog(null, mediaValor == null ? "Nenhum desconto recorrente no sistema"
            : "Média do valor dos descontos recorrentes no sistema: " + mediaValor);
        break;
      case EXIBIR_MAIS_ALTO:
        descontoRecorrente = descontoRecorrenteDAO.findTopByOrderByValorDescontoDesc();
        exibirDescontoRecorrente(descontoRecorrente);
        break;
      case EXIBIR_MAIS_BAIXO:
        descontoRecorrente = descontoRecorrenteDAO.findTopByOrderByValorDescontoAsc();
        exibirDescontoRecorrente(descontoRecorrente);
        break;
      case SAIR:
        break;
      default:
        JOptionPane.showMessageDialog(null, "Opção inválida");
        break;
    }
  }
}