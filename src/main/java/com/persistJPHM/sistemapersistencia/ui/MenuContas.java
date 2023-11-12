package com.persistJPHM.sistemapersistencia.ui;

import com.persistJPHM.sistemapersistencia.DAO.ContaDAO;
import com.persistJPHM.sistemapersistencia.DAO.UsuarioDAO;
import com.persistJPHM.sistemapersistencia.entity.Conta;
import com.persistJPHM.sistemapersistencia.entity.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.List;

@Slf4j
@Component
public class MenuContas {

    private enum OpcaoMenu {
        INSERIR,
        EXIBIR_POR_ID,
        EXIBIR_POR_NUMERO,
        EXIBIR_POR_DDD,
        EXIBIR_MAIOR_VALOR_X,
        EXIBIR_TOTAL_TRANSACOES,
        EXIBIR_TOTAL_CONTAS,
        EXIBIR_CONTAS_DESCONTO,
        EXIBIR_TODOS_NUMEROS,
        SAIR
    }

    @Autowired
    private ContaDAO baseConta;

    @Autowired
    private UsuarioDAO baseUsuario;

    public void obterConta(Conta con) {
        String numeroTele = JOptionPane.showInputDialog("Numero de telefone associado a conta: ", con.getNumeroTelefone());
        int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do usuario associado a conta"));

        Usuario usuario = baseUsuario.findByIdUsuario(id);
        if(usuario == null) {
            JOptionPane.showMessageDialog(null, "usuario não existente");
        } else {
            con.setNumeroTelefone(numeroTele);
            con.setUsuario(usuario);
        }
    }

    public void listarConta(Conta conta) {
        JOptionPane.showMessageDialog(null, conta == null ? "Nenhuma conta encontrada" : conta.toString());
    }

    public void listarContas(List<Conta> contas) {
        StringBuilder listagem = new StringBuilder();
        for(Conta conta : contas) {
            listagem.append(conta.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, listagem.isEmpty() ? "Nenhuma conta encontrada" : listagem);
    }

    public void menu() {
        OpcaoMenu opcao = null;
        do {
            try {
                opcao = obterOpcaoMenu();
                realizarAcao(opcao);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }
        } while(opcao != OpcaoMenu.SAIR);
    }

    public OpcaoMenu obterOpcaoMenu() {
        String menu = """
                Menu Contas
                1 - Inserir
                2 - Exibir por id
                3 - Exibir numero
                4 - Procurar telefone por ddd
                5 - Exibir todas as contas maior que um valor x
                6 - Exibir o total de transações ocorridas
                7 - Exibir o total de contas existentes
                8 - Exibir todas as contas com desconto recorrente
                9 - Exibir todos os numeros de telefone
                10 - Sair
                """;

        String opcaoStr = JOptionPane.showInputDialog(menu);
        int opcaoInt = Integer.parseInt(opcaoStr);

        // Validar a entrada do usuário
        if (opcaoInt < 1 || opcaoInt > OpcaoMenu.values().length) {
            JOptionPane.showMessageDialog(null, "Opção inválida");
            return obterOpcaoMenu();
        }

        return OpcaoMenu.values()[opcaoInt - 1];
    }

    private void realizarAcao(OpcaoMenu opcao) {
        Conta conta;
        List<Conta> contas;

        switch (opcao) {
            case INSERIR:
                conta = new Conta();
                obterConta(conta);
                baseConta.save(conta);
                break;
            case EXIBIR_POR_ID:
                int idConta = Integer.parseInt(JOptionPane.showInputDialog("Id da conta"));
                conta = baseConta.findByidUsuario(idConta);
                listarConta(conta);
                break;
            case EXIBIR_POR_NUMERO:
                String numeroTele = JOptionPane.showInputDialog("Numero de telefone");
                conta = baseConta.findByNumeroTelefone(numeroTele);
                listarConta(conta);
                break;
            case EXIBIR_POR_DDD:
                String ddd = JOptionPane.showInputDialog("Digite o ddd de sua região");
                contas = baseConta.consultaPorDdd(ddd);
                listarContas(contas);
            case EXIBIR_MAIOR_VALOR_X:
                double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor minimo"));
                contas = baseConta.valoresNaContaMaior(valor);
                listarContas(contas);
                break;
            case EXIBIR_TOTAL_TRANSACOES:
                conta = baseConta.findContaComMaiorValorTotal();
                listarConta(conta);
                break;
            case EXIBIR_TOTAL_CONTAS:
                contas = baseConta.consultarTodasContas();
                listarContas(contas);
                break;
            case EXIBIR_CONTAS_DESCONTO:
                contas = baseConta.consultarContasComDesconto();
                listarContas(contas);
                break;
            case EXIBIR_TODOS_NUMEROS:
                contas = baseConta.consultarTodosTelefones();
                listarContas(contas);
                break;
            case SAIR:
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida");
                break;
        }
    }
}