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
        EXIBIR_TOTAL_CONTAS,
        EXIBIR_TODOS_NUMEROS,
        SAIR
    }

    @Autowired
    private ContaDAO baseConta;

    @Autowired
    private UsuarioDAO baseUsuario;

    @Autowired
    private MenuTransacoes menuTransacoes;

    public void obterESalvarConta(Conta con) {
        String numeroTele = JOptionPane.showInputDialog("Numero de telefone associado a conta: ", con.getNumeroTelefone());
        int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do usuario que será associado a conta"));

        con.setNumeroTelefone(numeroTele);
        Usuario usuario = baseUsuario.findByIdUsuario(id);

        if(usuario == null) JOptionPane.showMessageDialog(null, "Digite um usuario existente");
        else con.setUsuario(usuario);

        baseConta.save(con);
        menuTransacoes.menu();
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

    public void listarTelefone(List<String> telefones) {
        StringBuilder listagemTele = new StringBuilder();
        for(String numero: telefones) {
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
        } while(opcao != OpcaoMenu.SAIR);
    }

    public OpcaoMenu obterOpcaoMenu() {
        String menu = """
                Menu Contas
                1 - Inserir
                2 - Exibir por id
                3 - Exibir numero
                4 - Procurar telefone por ddd
                5 - Listar todas as contas
                6 - Exibir todos os numeros de telefone
                7 - Sair
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
                obterESalvarConta(conta);
                break;
            case EXIBIR_POR_ID:
                int idConta = Integer.parseInt(JOptionPane.showInputDialog("Id da conta"));
                conta = baseConta.findByidConta(idConta);
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