import com.sun.source.tree.TryTree;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ClienteDAOimpl implements IClienteDAO {

    @Override
    public void salvar(Cliente cliente) {
        String sql = "INSERT INTO Cliente (cpf, nome,endereco,cidade,estado,nt,nc) VALUES (?, ?,?,?, ?,?,?)";

        try {
            Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, cliente.getCpf());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getendereco());
            stmt.setString(4, cliente.getCidade());
            stmt.setString(5, cliente.getEstado());
            stmt.setString(6, cliente.getNumero_tel());
            stmt.setInt(7, cliente.getNumero_casa());

            int linhasAfetadas = stmt.executeUpdate();
            System.out.println("Cliente salvo. Dados do cliente:     " + linhasAfetadas);

            stmt.close();
        } catch (Exception e) {
            System.out.println(" Erro ao salvar cliente: " );
            e.printStackTrace();
        }
    }


    @Override
    public void atualizar(Cliente cliente) {
        String sql = "UPDATE cliente SET nome = ?, endereco = ?, cidade = ?, estado = ?, nt = ?, nc = ? WHERE cpf = ?";

        Connection connUpdate = Conexao.getConnection();

        try (PreparedStatement stmt = connUpdate.prepareStatement(sql)) {
            // Define os valores dos parâmetros na ordem dos "?" do SQL
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getendereco());
            stmt.setString(3, cliente.getCidade());
            stmt.setString(4, cliente.getEstado());
            stmt.setString(5, cliente.getNumero_tel());
            stmt.setInt(6, cliente.getNumero_casa());
            stmt.setString(7, cliente.getCpf());


            int linhasAfetadas = stmt.executeUpdate();

            System.out.println(" Cliente atualizado com sucesso. dados atualizados " + linhasAfetadas);
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar cliente: " );

        }


    }

    @Override
    public Cliente buscarPorCpf(String colunas, String cpf) {
        String sql = "SELECT " + colunas + " FROM cliente WHERE CPF = ?";

        Cliente cliente = new Cliente();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);

            var rs = stmt.executeQuery();

            if (rs.next()) {
                if (colunas.contains("cpf")) cliente.setCpf(rs.getString("cpf"));
                if (colunas.contains("nome")) cliente.setNome(rs.getString("nome"));
                if (colunas.contains("endereco")) cliente.setendereco(rs.getString("endereco"));
                if (colunas.contains("cidade")) cliente.setCidade(rs.getString("cidade"));
                if (colunas.contains("estado")) cliente.setEstado(rs.getString("estado"));
                if (colunas.contains("nt")) cliente.setNumero_tel(rs.getString("nt"));
                if (colunas.contains("nc")) cliente.setNumero_casa(rs.getInt("nc"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na busca ");

            return null;
        }
        return cliente;
    }


    @Override
    public List<Cliente> listarTodos() {
        List<Cliente> clientes = new ArrayList<>();

        String sql = "SELECT cpf,nome,endereco,cidade,estado,nt,nc From Cliente";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getString("cpf"),
                        rs.getString("nome"),
                        rs.getString("endereco"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("nt"),
                        rs.getInt("nc")
                );
                clientes.add(cliente);


            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar " );

        }
        return clientes;
    }

    @Override
    public void deletar(String cpf) {
        String sql = "DELETE FROM cliente WHERE cpf = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);

            int linhasAlteradas = stmt.executeUpdate();
            System.out.println("Cliente deletado dados do cliente:" + linhasAlteradas);
        } catch (SQLException e) {
            System.out.println("Erro ao Deletar " );

        }


    }

    @Override
    public void adicionar(Cliente cliente) {
        String sql = "INSERT INTO cliente (cpf,nome,cidade,endereco,estado,nt,nc) VALUES(?,?,?,?,?,?,?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getCpf());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getCidade());
            stmt.setString(4, cliente.getendereco());
            stmt.setString(5, cliente.getEstado());
            stmt.setString(6, cliente.getNumero_tel());
            stmt.setInt(7, cliente.getNumero_casa());

            int Linhasalteradass = stmt.executeUpdate();

            if (Linhasalteradass > 0) {
                System.out.println("Cliente inserido com sucesso ao banco de dados");

            } else
                System.out.println("Falha ao inserir o cliente");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir Cliente " );

        }
    }
}
