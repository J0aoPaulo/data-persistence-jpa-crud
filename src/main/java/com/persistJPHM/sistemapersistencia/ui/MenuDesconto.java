package com.persistJPHM.sistemapersistencia.ui;

import com.persistJPHM.sistemapersistencia.DAO.ContaGeneric;
import com.persistJPHM.sistemapersistencia.DAO.DescontoGeneric;
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
    EXIBIR_TODOS,
    EXIBIR_POR_CONTA,
    CALCULAR_MEDIA_VALOR,
    EXIBIR_MAIS_ALTO,
    EXIBIR_MAIS_BAIXO,
    SAIR
  }

  @Autowired
  private DescontoGeneric baseDesconto;

  @Autowired
  private ContaGeneric baseConta;

  public void obterDescontoRecorrente(DescontoRecorrente descontoRecorrente) {
    List<Conta> contas = baseConta.findAll();

    if (contas.isEmpty()) {
      JOptionPane.showMessageDialog(null, "Não há contas disponíveis. Não é possível inserir Desconto Recorrente.");
      return;
    }

    Conta conta = (Conta) JOptionPane.showInputDialog(
        null, "Selecione uma conta",
        "Contas", JOptionPane.PLAIN_MESSAGE, null, contas.toArray(), null);

    if (conta == null) {
      JOptionPane.showMessageDialog(null, "Selecione uma conta válida. Desconto Recorrente não será inserido.");
      return;
    }

    double valorDesconto = Double.parseDouble(JOptionPane.showInputDialog("Valor do Desconto"));

    descontoRecorrente.setDescontoConta(conta);
    descontoRecorrente.setValorDesconto(valorDesconto);
    descontoRecorrente.setDataDesconto(new Date());

    baseDesconto.save(descontoRecorrente);
    JOptionPane.showMessageDialog(null, "Desconto Recorrente inserido com sucesso!");
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
        2 - Exibir todos
        3 - Exibir por conta
        4 - Calcular média do valor
        5 - Exibir valor mais alto
        6 - Exibir valor mais baixo
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
    DescontoRecorrente descontoRecorrente;
    List<DescontoRecorrente> descontosRecorrentes;
    String id;
    switch (opcao) {
      case INSERIR:
        descontoRecorrente = new DescontoRecorrente();
        obterDescontoRecorrente(descontoRecorrente);
        break;
      case EXIBIR_TODOS:
        listarDescontosRecorrentes(baseDesconto.findAll());
        break;
      case EXIBIR_POR_CONTA:
        id = String.valueOf(Integer.parseInt(JOptionPane.showInputDialog("ID da Conta")));
        Conta conta = baseConta.findById(id).orElse(null);
        if (conta != null) {
          descontosRecorrentes = baseDesconto.findAllByConta(conta);
          listarDescontosRecorrentes(descontosRecorrentes);
        } else {
          JOptionPane.showMessageDialog(null, "Conta não encontrada com o ID fornecido.");
        }
        break;
      case CALCULAR_MEDIA_VALOR:
        Double mediaValor = baseDesconto.findAverageDiscountValue();
        JOptionPane.showMessageDialog(null, mediaValor == null ? "Nenhum desconto recorrente no sistema"
            : "Média do valor dos descontos recorrentes no sistema: " + mediaValor);
        break;
      case EXIBIR_MAIS_ALTO:
        descontoRecorrente = baseDesconto.findTopByOrderByValorDescontoDesc();
        exibirDescontoRecorrente(descontoRecorrente);
        break;
      case EXIBIR_MAIS_BAIXO:
        descontoRecorrente = baseDesconto.findTopByOrderByValorDescontoAsc();
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