import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

    public class ConexaoTest {

        @Test
        void testConexaoNaoEhNula() {
            Connection conn = Conexao.getConnection();
            assertNotNull(conn, "A conexão deve ser estabelecida com sucesso");
        }

        @Test
        void testConexaoEstaAtiva() throws SQLException {
            Connection conn = Conexao.getConnection();
            assertTrue(conn != null && !conn.isClosed(), "A conexão deve estar aberta");
        }
    }


