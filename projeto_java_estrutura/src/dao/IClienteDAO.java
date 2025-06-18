import java.util.List;

public interface IClienteDAO {
    void salvar(Cliente cliente);
    void atualizar(Cliente cliente);
    Cliente buscarPorCpf(String colunas, String cpf);
    List<Cliente> listarTodos();
    void deletar(String cpf);
    void adicionar(Cliente cliente);
}
