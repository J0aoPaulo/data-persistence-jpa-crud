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
        EXIBIR_10_CONTAS_MAIS_TRANSACOES, 
        SAIR
    }

    @Autowired
    private ContaGeneric baseConta;

    @Autowired
    private UsuarioGeneric baseUsuario;

    public void obterESalvarConta() {
        List<Usuario> usuarios = baseUsuario.findAll();
        Usuario usuario = (Usuario) JOptionPane.showInputDialog(
                null, "Selecione um usuário",
                "Usuários", JOptionPane.PLAIN_MESSAGE, null, usuarios.toArray(), null);
    
        if (usuario == null) {
            JOptionPane.showMessageDialog(null, "Selecione um usuário válido");
            return;
        }
    
        String numeroTelefone = JOptionPane.showInputDialog(null, "Número de telefone associado à conta: ");
    
        Conta conta = new Conta(null, usuario, numeroTelefone, null, null);
        
        baseConta.save(conta);
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
                3 - Exibir por numero de telefone
                4 - Listar todas as contas
                5 - Exibir todos os numeros de telefone
                6 - Exibir as 10 contas com mais transacoes
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
            case EXIBIR_10_CONTAS_MAIS_TRANSACOES:
                contas = baseConta.findTop10ContasByTransacoes();
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