import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class dadosDAOTest {

    private final PrintStream originalOut = System.out;
    private final java.io.InputStream originalIn = System.in;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    void testBuscar() {


        String simulatedInput = "3\n12345678900\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));


        dadosDAO dao = new dadosDAO();
        dao.Buscar();


        String output = outContent.toString();

        assertTrue(output.contains("Digite qual será a sua busca:"), "Deve solicitar a escolha da busca.");
        assertTrue(output.contains("Cliente não encontrado.") || output.contains("Cliente encontrado:"),
                "Deve indicar se o cliente foi encontrado ou não.");
    }

    @Test
    void testAdicionar() {

        String simulatedInput = ""
                + "12345678901234\n"
                + "Test Name\n"
                + "Rua X\n"
                + "City Y\n"
                + "EstadoInválido\n"
                + "PE\n"
                + "81912345678\n"
                + "abc\n"
                + "100\n";

        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        dadosDAO dao = new dadosDAO();
        dao.Adicionar();

        String output = outContent.toString();

        assertTrue(output.contains("Cliente inserido da maneira correta"),
                "Deve confirmar que o cliente foi inserido.");
    }

    @Test
    void testListarTodos() {

        dadosDAO dao = new dadosDAO();
        dao.ListarTodos();

        String output = outContent.toString();
        assertTrue(output.contains("Nenhum cliente cadastrado.") || output.contains("--- Lista de Clientes ---"),
                "Deve listar clientes ou indicar que nenhum foi cadastrado.");
    }

    @Test
    void testAtualizarClienteNaoEncontrado() {

        String simulatedInput = "000\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        dadosDAO dao = new dadosDAO();
        dao.Atualizar();

        String output = outContent.toString();
        assertTrue(output.contains("Cliente não encontrado."),
                "Deve informar que o cliente não foi encontrado.");
    }

    @Test
    void testDeletarClienteNaoEncontrado() {

        String simulatedInput = "000\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        dadosDAO dao = new dadosDAO();
        dao.Deletar();

        String output = outContent.toString();
        assertTrue(output.contains("Cliente não encontrado."),
                "Deve informar que o cliente não foi encontrado para deleção.");
    }
}
