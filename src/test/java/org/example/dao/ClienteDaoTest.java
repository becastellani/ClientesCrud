package org.example.dao;

import org.example.model.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class ClienteDaoTest {

    private ClienteDao clienteDao;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        clienteDao = new ClienteDao();
        cliente = new Cliente("João", "123456789", "joao@email.com", "123.456.789-00", "senha123");
    }

    @Test
    void testSalvarCliente() {
        clienteDao.salvar(cliente);

        Cliente clienteSalvo = clienteDao.buscarPorCPF(cliente.getCpf());
        assertNotNull(clienteSalvo);
        assertEquals(cliente.getCpf(), clienteSalvo.getCpf());
        assertEquals(cliente.getNome(), clienteSalvo.getNome());
    }

    @Test
    void testBuscarClientePorCPF() {
        clienteDao.salvar(cliente);

        Cliente clienteBuscado = clienteDao.buscarPorCPF(cliente.getCpf());
        assertNotNull(clienteBuscado);
        assertEquals(cliente.getCpf(), clienteBuscado.getCpf());
    }

    @Test
    void testAtualizarCliente() {
        clienteDao.salvar(cliente);

        cliente.setNome("Maria");
        clienteDao.atualizar(cliente);

        Cliente clienteAtualizado = clienteDao.buscarPorCPF(cliente.getCpf());
        assertNotNull(clienteAtualizado);
        assertEquals("Maria", clienteAtualizado.getNome());
    }

    @Test
    void testDeletarCliente() {
        clienteDao.salvar(cliente);
        clienteDao.deletar(cliente);

        Cliente clienteDeletado = clienteDao.buscarPorCPF(cliente.getCpf());
        assertNull(clienteDeletado);
    }

    @Test
    void testListarTodosClientes() {
        Cliente cliente2 = new Cliente("Ana", "987654321", "ana@email.com", "987.654.321-00", "senha456");
        clienteDao.salvar(cliente);
        clienteDao.salvar(cliente2);

        List<Cliente> clientes = clienteDao.listarTodos();
        assertNotNull(clientes);
        assertTrue(clientes.size() > 0);
    }
}
