package org.example.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void testConstrutorCliente() {
        Cliente cliente = new Cliente("João", "123456789", "joao@email.com", "123.456.789-00", "senha123");

        assertNotNull(cliente);
        assertEquals("João", cliente.getNome());
        assertEquals("123456789", cliente.getTelefone());
        assertEquals("joao@email.com", cliente.getEmail());
        assertEquals("123.456.789-00", cliente.getCpf());
        assertNotNull(cliente.getSenha());
    }

    @Test
    void testHashSenha() {
        Cliente cliente = new Cliente("João", "123456789", "joao@email.com", "123.456.789-00", "senha123");

        assertNotEquals("senha123", cliente.getSenha());
        assertTrue(cliente.validarSenha("senha123"));
    }

    @Test
    void testValidarSenha() {
        Cliente cliente = new Cliente("João", "123456789", "joao@email.com", "123.456.789-00", "senha123");

        assertTrue(cliente.validarSenha("senha123"));

        assertFalse(cliente.validarSenha("senhaErrada"));
    }

    @Test
    void testSettersAndGetters() {
        Cliente cliente = new Cliente("João", "123456789", "joao@email.com", "123.456.789-00", "senha123");

        cliente.setNome("Maria");
        cliente.setTelefone("987654321");
        cliente.setEmail("maria@email.com");
        cliente.setCpf("987.654.321-00");
        cliente.setSenha("novaSenha123");

        assertEquals("Maria", cliente.getNome());
        assertEquals("987654321", cliente.getTelefone());
        assertEquals("maria@email.com", cliente.getEmail());
        assertEquals("987.654.321-00", cliente.getCpf());
        assertTrue(cliente.validarSenha("novaSenha123"));
    }
}
