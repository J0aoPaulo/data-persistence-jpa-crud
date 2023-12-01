package com.persistJPHM.sistemapersistencia.ui;

import com.persistJPHM.sistemapersistencia.DAO.UsuarioDAO;
import com.persistJPHM.sistemapersistencia.entity.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.List;

@Slf4j
@Component
public class MenuUsuarios {

    // Enums para representar as opções do menu
    private enum OpcaoMenu {
        INSERIR,
        ATUALIZAR_POR_CPF,
        REMOVER_POR_CPF,
        EXIBIR_POR_CPF,
        EXIBIR_POR_ID,
        EXIBIR_TODOS,
        EXIBIR_POR_NOME,
        EXIBIR_USUARIOS_ENTRE_IDS,
        EXIBIR_POR_LETRA,
        BUSCAR_POR_TRES_PRIMEIROS_DIGITOS,
        LISTAR_QUANTIDADE_CONTAS,
        SAIR
    }

    @Autowired
    private UsuarioDAO baseUsuario;

    public void obterUsuario(Usuario usu) {
        String nome = JOptionPane.showInputDialog("Nome", usu.getNome());
        String cpf = JOptionPane.showInputDialog("Cpf", usu.getCpf());
        usu.setNome(nome);
        usu.setCpf(cpf);
    }

    public void listaUsuarios(List<Usuario> usuarios) {
        StringBuilder listagem = new StringBuilder();
        for (Usuario usuario : usuarios) {
            listagem.append(usuario.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, listagem.isEmpty() ? "Nenhum usuario encontrado" : listagem);
    }

    public void listaUsuario(Usuario usu) {
        JOptionPane.showMessageDialog(null, usu == null ? "Nenhum usuario encontrado" : usu.toString());
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
                Menu Usuarios
                1 - Inserir
                2 - Atualizar por CPF
                3 - Remover por CPF
                4 - Exibir por CPF
                5 - Exibir por id
                6 - Exibir todos
                7 - Exibir todos que contém determinado nome
                8 - Exibir usuarios entre dois id diferentes
                9 - Exibir usuarios por uma letra específica
                10 - Buscar usuarios pelos 3 primeiros numeros do cpf
                11 - Listar a quantidade de contas existentes
                12 - Sair
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
        Usuario usu;
        String cpf;
        List<Usuario> usuarios;

        switch (opcao) {
            case INSERIR:
                usu = new Usuario();
                obterUsuario(usu);
                baseUsuario.save(usu);
                break;
            case ATUALIZAR_POR_CPF:
                cpf = JOptionPane.showInputDialog("Digite o cpf do Usuario a ser alterado");
                usu = baseUsuario.findFirstByCpf(cpf);
                if (usu != null) {
                    obterUsuario(usu);
                    baseUsuario.save(usu);
                } else {
                    JOptionPane.showMessageDialog(null, "Não foi possível atualizar, pois o usuario não foi encontrado.");
                }
                break;
            case REMOVER_POR_CPF:
                cpf = JOptionPane.showInputDialog("CPF");
                usu = baseUsuario.findFirstByCpf(cpf);
                if (usu != null) {
                    baseUsuario.deleteById(String.valueOf(Integer.valueOf(usu.getIdUsuario())));
                } else {
                    JOptionPane.showMessageDialog(null, "Não foi possível remover, pois o usuario não foi encontrado");
                }
                break;
            case EXIBIR_POR_CPF:
                cpf = JOptionPane.showInputDialog("CPF");
                usu = baseUsuario.findFirstByCpf(cpf);
                listaUsuario(usu);
                break;
            case EXIBIR_POR_ID:
                int id = Integer.parseInt(JOptionPane.showInputDialog("Id"));
                usu = baseUsuario.findById(String.valueOf(id)).orElse(null);
                listaUsuario(usu);
                break;
            case EXIBIR_TODOS:
                listaUsuarios(baseUsuario.findAll());
                break;
            case EXIBIR_POR_NOME:
                String nome = JOptionPane.showInputDialog("Nome");
                usuarios = baseUsuario.consultaPorNomeEspecifico(nome);
                listaUsuarios(usuarios);
                break;
            case EXIBIR_USUARIOS_ENTRE_IDS:
                String idInicial = JOptionPane.showInputDialog("Digite o id 1");
                String idFinal = JOptionPane.showInputDialog("Digite o id 2");
                if (idInicial.equals(idFinal)) {
                    JOptionPane.showMessageDialog(null, "Por favor digite dois 'ids' diferentes");
                    break;
                }
                usuarios = baseUsuario.consultarUsuarioEntreId(idInicial, idFinal);
                listaUsuarios(usuarios);
                break;
            case EXIBIR_POR_LETRA:
                String letra = JOptionPane.showInputDialog("Letra");
                usuarios = baseUsuario.consultaPorLetra(letra);
                listaUsuarios(usuarios);
                break;
            case BUSCAR_POR_TRES_PRIMEIROS_DIGITOS:
                String numeros = JOptionPane.showInputDialog("Digite os 3 primeiros numeros do cpf");
                usuarios = baseUsuario.procureCpfsComTresPrimeirosNumeros(numeros);
                listaUsuarios(usuarios);
                break;
            case LISTAR_QUANTIDADE_CONTAS:
                long quantidadeUsuarios = baseUsuario.contaUsuarios();
                JOptionPane.showMessageDialog(null, quantidadeUsuarios == 0 ? "Nenhum usuario no sistema"
                        : "Quantidade de usuarios no sistema: " + quantidadeUsuarios);
                break;
            case SAIR:
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida");
                break;
        }
    }
}