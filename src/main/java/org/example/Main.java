package org.example;

import org.example.controller.ClienteController;
import org.example.model.Cliente;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClienteController clienteController = new ClienteController();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Sistema de Cadastro de Clientes ---");
            System.out.println("1 - Adicionar Cliente");
            System.out.println("2 - Atualizar Cliente");
            System.out.println("3 - Excluir Cliente");
            System.out.println("4 - Listar Clientes");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Senha: ");
                    String senha = scanner.nextLine();
                    try {
                        clienteController.adicionarCliente(nome, telefone, email, cpf, senha);
                        System.out.println("Cliente cadastrado com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Digite o CPF do cliente a atualizar: ");
                    String cpfAtualizar = scanner.nextLine();
                    try {
                        Cliente cliente = clienteController.buscarClientePorCPF(cpfAtualizar);
                        if (cliente == null) {
                            System.out.println("Cliente não encontrado com o CPF: " + cpfAtualizar);
                            break;
                        }else{
                            System.out.println("Cliente encontrado com sucesso!");
                            System.out.println("Nome: " + cliente.getNome() + " | CPF: " + cliente.getCpf() + " | Email: " + cliente.getEmail());
                        }
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                        break;
                    }


                    System.out.print("Novo Nome: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Novo Telefone: ");
                    String novoTelefone = scanner.nextLine();
                    System.out.print("Novo Email: ");
                    String novoEmail = scanner.nextLine();
                    System.out.print("Nova Senha (ou deixe em branco para manter a atual): ");
                    String novaSenha = scanner.nextLine();
                    try {
                        clienteController.atualizarCliente(cpfAtualizar, novoNome, novoTelefone, novoEmail, novaSenha);
                        System.out.println("Cliente atualizado com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.print("Digite o CPF do cliente a excluir: ");
                    String cpfExcluir = scanner.nextLine();
                    try {
                        clienteController.excluirCliente(cpfExcluir);
                        System.out.println("Cliente excluído com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;
                case 4:
                    List<Cliente> clientes = clienteController.listarClientes();
                    if (clientes.isEmpty()) {
                        System.out.println("Nenhum cliente cadastrado.");
                    } else {
                        System.out.println("\n--- Lista de Clientes ---");
                        for (Cliente c : clientes) {
                            System.out.println("Nome: " + c.getNome() + " | CPF: " + c.getCpf() + " | Email: " + c.getEmail());
                        }
                    }
                    break;
                case 5:
                    System.out.println("Saindo do sistema...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}



