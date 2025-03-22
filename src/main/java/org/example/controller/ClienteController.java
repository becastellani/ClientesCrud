package org.example.controller;

import org.example.dao.ClienteDao;
import org.example.model.Cliente;
import org.example.utils.Utils;

import java.util.List;

public class ClienteController {

    ClienteDao clienteDAO;

    public ClienteController() {
        this.clienteDAO = new ClienteDao();
    }

    public void adicionarCliente(String nome, String telefone, String email, String cpf, String senha) {
        if (nome == null || nome.isEmpty() || cpf == null || cpf.isEmpty() || senha == null || senha.isEmpty()) {
            throw new IllegalArgumentException("Nome, CPF e senha são obrigatórios!");
        }

        String cpfLimpo = cpf.replaceAll("[.-]", "");

        if (!Utils.validarCPF(cpfLimpo)) {
            throw new IllegalArgumentException("CPF inválido!");
        }

        String cpfFormatado = Utils.formatarCPF(cpfLimpo);


        Cliente clienteExistente = clienteDAO.buscarPorCPF(cpfFormatado);
        if (clienteExistente != null) {
            throw new RuntimeException("Cliente já cadastrado com o CPF: " + cpfFormatado);
        }


        Cliente cliente = new Cliente(nome, telefone, email, cpfFormatado, senha);
        clienteDAO.salvar(cliente);
    }

    public void atualizarCliente(String cpf, String nome, String telefone, String email, String senha) {
        String cpfFormatado = Utils.formatarCPF(cpf.replaceAll("[.-]", ""));
        Cliente cliente = clienteDAO.buscarPorCPF(cpfFormatado);
        if (cliente != null) {

            if(nome != null && !nome.isEmpty()){
                cliente.setNome(nome);
            }

            if(telefone != null && !telefone.isEmpty()){
                cliente.setTelefone(telefone);
            }

            if(email != null && !email.isEmpty()){
                cliente.setEmail(email);
            }

            if (senha != null && !senha.isEmpty()) {
                cliente.setSenha(senha);
            }
            clienteDAO.atualizar(cliente);
        } else {
            throw new RuntimeException("Cliente não encontrado com o CPF: " + cpfFormatado);
        }
    }

    public void excluirCliente(String cpf) {
        String cpfFormatado = Utils.formatarCPF(cpf.replaceAll("[.-]", ""));
        Cliente cliente = clienteDAO.buscarPorCPF(cpfFormatado);
        if (cliente != null) {
            clienteDAO.deletar(cliente);
        } else {
            throw new RuntimeException("Cliente não encontrado com o CPF: " + cpfFormatado);
        }
    }
    
    public List<Cliente> listarClientes() {
        return clienteDAO.listarTodos();
    }

    public Cliente buscarClientePorCPF(String cpfAtualizar) {
        String cpfFormatado = Utils.formatarCPF(cpfAtualizar.replaceAll("[.-]", ""));
        return clienteDAO.buscarPorCPF(cpfFormatado);
    }
}
