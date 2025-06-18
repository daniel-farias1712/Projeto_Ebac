import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



public class ClienteDAOTest {
    ClienteDAOimpl dao = new ClienteDAOimpl();

    @Test
    void testSalvarCliente() {
        Cliente cliente = new Cliente("123", "Daniel", "Recife", "Rua A", "PE", "81999999999", 45);
        ClienteDAOimpl dao = new ClienteDAOimpl();

        dao.salvar(cliente);

        Cliente clienteBuscado = dao.buscarPorCpf("*", "123");
        assertNotNull(clienteBuscado);
        assertEquals("Daniel", clienteBuscado.getNome());
    }
    @Test
    void testAtualizarCliente() {
        Cliente cliente = new Cliente("321", "Maria", "Olinda", "Rua B", "PE", "81999911111", 12);
        dao.salvar(cliente);

        cliente.setNome("Maria Clara");
        dao.atualizar(cliente);

        Cliente atualizado = dao.buscarPorCpf("nome,cpf", "321");
        assertEquals("Maria Clara", atualizado.getNome());
    }

    @Test
    void testBuscarPorCpfInexistente() {
        Cliente resultado = dao.buscarPorCpf("*", "00000000000");
        assertNull(resultado);
    }

    @Test
    void testListarTodos() {
        dao.salvar(new Cliente("111", "Cliente 1", "C1", "Rua", "UF", "123", 10));
        dao.salvar(new Cliente("222", "Cliente 2", "C2", "Rua", "UF", "456", 20));

        List<Cliente> lista = dao.listarTodos();
        assertTrue(lista.size() >= 2);
    }

    @Test
    void testDeletarCliente() {
        Cliente cliente = new Cliente("999", "Delete Me", "Cidade", "Rua", "UF", "000", 0);
        dao.salvar(cliente);

        dao.deletar("999");

        Cliente apagado = dao.buscarPorCpf("*", "999");
        assertNull(apagado);
    }



}