package org.example.controller;

import org.example.dao.ClienteDao;
import org.example.model.Cliente;
import org.example.utils.Utils;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteControllerTest {

    private ClienteController clienteController;
    private ClienteDao clienteDao;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        clienteDao = mock(ClienteDao.class);
        clienteController = new ClienteController();
        clienteController.clienteDAO = clienteDao;

        cliente = new Cliente("João", "123456789", "joao@email.com", "123.456.789-00", "senha123");
    }

    @Test
    void testAdicionarCliente() {
        when(clienteDao.buscarPorCPF(cliente.getCpf())).thenReturn(null);

        clienteController.adicionarCliente(cliente.getNome(), cliente.getTelefone(), cliente.getEmail(), cliente.getCpf(), cliente.getSenha());

        verify(clienteDao, times(1)).salvar(cliente);
    }

    @Test
    void testAdicionarClienteCPFInvalido() {
        when(clienteDao.buscarPorCPF(cliente.getCpf())).thenReturn(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            clienteController.adicionarCliente(cliente.getNome(), cliente.getTelefone(), cliente.getEmail(), "123.456.789-XX", cliente.getSenha());
        });

        assertEquals("CPF inválido!", exception.getMessage());
    }

    @Test
    void testAdicionarClienteJaCadastrado() {
        when(clienteDao.buscarPorCPF(cliente.getCpf())).thenReturn(cliente);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            clienteController.adicionarCliente(cliente.getNome(), cliente.getTelefone(), cliente.getEmail(), cliente.getCpf(), cliente.getSenha());
        });

        assertEquals("Cliente já cadastrado com o CPF: " + cliente.getCpf(), exception.getMessage());
    }

    @Test
    void testAtualizarCliente() {
        when(clienteDao.buscarPorCPF(cliente.getCpf())).thenReturn(cliente);

        clienteController.atualizarCliente(cliente.getCpf(), "Maria", "987654321", "maria@email.com", "novaSenha123");

        assertEquals("Maria", cliente.getNome());
        assertEquals("987654321", cliente.getTelefone());
        assertEquals("maria@email.com", cliente.getEmail());
        assertEquals("novaSenha123", cliente.getSenha());

        verify(clienteDao, times(1)).atualizar(cliente);
    }

    @Test
    void testAtualizarClienteNaoEncontrado() {
        when(clienteDao.buscarPorCPF(cliente.getCpf())).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            clienteController.atualizarCliente(cliente.getCpf(), "Maria", "987654321", "maria@email.com", "novaSenha123");
        });

        assertEquals("Cliente não encontrado com o CPF: " + cliente.getCpf(), exception.getMessage());
    }

    @Test
    void testExcluirCliente() {
        when(clienteDao.buscarPorCPF(cliente.getCpf())).thenReturn(cliente);

        clienteController.excluirCliente(cliente.getCpf());

        verify(clienteDao, times(1)).deletar(cliente);
    }

    @Test
    void testExcluirClienteNaoEncontrado() {
        when(clienteDao.buscarPorCPF(cliente.getCpf())).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            clienteController.excluirCliente(cliente.getCpf());
        });

        assertEquals("Cliente não encontrado com o CPF: " + cliente.getCpf(), exception.getMessage());
    }

    @Test
    void testListarClientes() {
        Cliente cliente2 = new Cliente("Ana", "987654321", "ana@email.com", "987.654.321-00", "senha456");
        when(clienteDao.listarTodos()).thenReturn(Arrays.asList(cliente, cliente2));

        List<Cliente> clientes = clienteController.listarClientes();

        assertNotNull(clientes);
        assertEquals(2, clientes.size());
        assertEquals("João", clientes.get(0).getNome());
        assertEquals("Ana", clientes.get(1).getNome());
    }
}
