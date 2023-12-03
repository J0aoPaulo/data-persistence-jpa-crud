package com.persistJPHM.sistemapersistencia.ui;

import com.persistJPHM.sistemapersistencia.DAO.ContaGeneric;
import com.persistJPHM.sistemapersistencia.DAO.UsuarioGeneric;
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
        EXIBIR_TOTAL_CONTAS,
        EXIBIR_TODOS_NUMEROS,
        SAIR
    }

    @Autowired
    private ContaGeneric baseConta;

    @Autowired
    private UsuarioGeneric baseUsuario;

    @Autowired
    private MenuTransacoes menuTransacoes;

    public void obterESalvarConta() {
        String numeroTele = JOptionPane.showInputDialog(null, "Numero de telefone associado a conta: ");
        String id = String.valueOf(Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id do usuario que será associado a conta")));

        Usuario usuario = baseUsuario.findById(String.valueOf(Integer.valueOf(id))).orElse(null);

        if (usuario == null) {
            JOptionPane.showMessageDialog(null, "Digite um usuário existente");
        } else {
            Conta con = new Conta(id, usuario, numeroTele, null, null);
            baseConta.save(con);
        }
        menuTransacoes.menu();
    }

    public void listarConta(Conta conta) {
        JOptionPane.showMessageDialog(null, conta == null ? "Nenhuma conta encontrada" : conta.toString());
    }

    public void listarContas(List<Conta> contas) {
        StringBuilder listagem = new StringBuilder();
        for (Conta conta : contas) {
            listagem.append(conta.toStringTodasContas()).append("\n");
        }
        JOptionPane.showMessageDialog(null, listagem.isEmpty() ? "Nenhuma conta encontrada" : listagem);
    }

    public void listarTelefone(List<String> telefones) {
        StringBuilder listagemTele = new StringBuilder();
        for (String numero : telefones) {
            listagemTele.append(numero).append("\n");
        }
        JOptionPane.showMessageDialog(null,
                listagemTele.isEmpty() ? "Nenhum telefone encontrado" : listagemTele);
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
        } while (opcao != OpcaoMenu.SAIR);
    }

    public OpcaoMenu obterOpcaoMenu() {
        String menu = """
                Menu Contas
                1 - Inserir
                2 - Exibir por id
                3 - Exibir por numero de telefone // OK
                4 - Listar todas as contas // OK
                5 - Exibir todos os numeros de telefone // OK
                6 - Sair
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
                obterESalvarConta();
                break;
            case EXIBIR_POR_ID:
                String idConta = String.valueOf(Integer.parseInt(JOptionPane.showInputDialog("Id da conta")));
                conta = baseConta.findById(idConta).orElse(null);
                listarConta(conta);
                break;
            case EXIBIR_POR_NUMERO:
                String numeroTele = JOptionPane.showInputDialog("Numero de telefone");
                conta = baseConta.findByNumeroTelefone(numeroTele);
                listarConta(conta);
                break;
            case EXIBIR_TOTAL_CONTAS:
                contas = baseConta.consultarTodasContas();
                listarContas(contas);
                break;
            case EXIBIR_TODOS_NUMEROS:
                List<String> numeros = baseConta.consultarTodosTelefones();
                listarTelefone(numeros);
                break;
            case SAIR:
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida");
                break;
        }
    }
}