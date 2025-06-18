import java.util.List;
import java.util.Scanner;

public class dadosDAO {
    Scanner scan = new Scanner(System.in);
    ClienteDAOimpl clienteDao = new ClienteDAOimpl();

    public void Buscar() {

        System.out.println("Digite qual será a sua busca:");
        System.out.println("1 - nome");
        System.out.println("2 - cidade");
        System.out.println("3 - cpf");
        System.out.println("4 - endereço");
        System.out.println("5 - estado");
        System.out.println("6 - número de telefone");
        System.out.println("7 - número da casa");

        String escolha = scan.nextLine();
        String coluna = traduzirEscolhas(escolha);

        if (coluna.isEmpty()) {
            System.out.println("Opção inválida!");
            return;
        }

        System.out.println("Digite o valor para busca na coluna " + coluna + ":");
        String valorBusca = scan.nextLine();

        Cliente cliente = clienteDao.buscarPorCpf(coluna, valorBusca);

        if (cliente != null) {
            System.out.println("Cliente encontrado:");
            System.out.println(cliente);
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private String traduzirEscolhas(String escolha) {
        switch (escolha.trim()) {
            case "1":
                return "nome";
            case "2":
                return "cidade";
            case "3":
                return "cpf";
            case "4":
                return "endereco";
            case "5":
                return "estado";
            case "6":
                return "nt";
            case "7":
                return "nc";
            default:
                return "";
        }
    }


    public void Adicionar(){
        System.out.println("Digite o CPF (Deve conter 14 caracteres):");
        String cpf = scan.nextLine();

        System.out.println("Digite o Nome (texto):");
        String nome = scan.nextLine();

        System.out.println("Digite o Endereço (texto):");
        String endereco = scan.nextLine();

        System.out.println("Digite a Cidade (texto):");
        String cidade = scan.nextLine();

        System.out.println("Digite o Estado (texto):");
        String estado = scan.nextLine();
        while (true) {
            System.out.println("Digite o Estado (2 siglas ex PE, ES, RJ):");
            estado = scan.nextLine().trim().toUpperCase();
            if (estado.length() == 2) {
                break;
            } else {
                System.out.println("Estado deve conter exatamente 2 caracteres. Tente novamente.");
            }
        }
        System.out.println("Digite o Número de telefone (texto):");
        String numero_tel = scan.nextLine();

        System.out.println("Digite o Número da casa (número inteiro):");
        int numero_casa = 0;

        while(true){
            try{
                numero_casa = Integer.parseInt(scan.nextLine());
                break;
            }catch (NumberFormatException e ){
                System.out.println("Entrada invalida Digite um numero valido");
            }
        }
        Cliente cliente = new Cliente(cpf, nome, endereco, cidade, estado, numero_tel, numero_casa);
        clienteDao.adicionar(cliente);
        System.out.println("Cliente inserido da maneira correta");
    }
    public void ListarTodos() {
        List<Cliente> clientes = clienteDao.listarTodos();

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            System.out.println("--- Lista de Clientes ---");
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }

    public void Atualizar() {
        System.out.print("Digite o CPF do cliente que deseja atualizar: ");
        String cpf = scan.nextLine();

        Cliente clienteExistente = clienteDao.buscarPorCpf("cpf,nome,endereco,cidade,estado,nt,nc", cpf);
        if (clienteExistente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.println("Digite os novos dados (pressione Enter para manter o valor atual):");

        System.out.print("Nome (" + clienteExistente.getNome() + "): ");
        String nome = scan.nextLine();
        if (!nome.isEmpty()) clienteExistente.setNome(nome);

        System.out.print("Endereço (" + clienteExistente.getendereco() + "): ");
        String end = scan.nextLine();
        if (!end.isEmpty()) clienteExistente.setendereco(end);

        System.out.print("Cidade (" + clienteExistente.getCidade() + "): ");
        String cidade = scan.nextLine();
        if (!cidade.isEmpty()) clienteExistente.setCidade(cidade);

        System.out.print("Estado (" + clienteExistente.getEstado() + "): ");
        String estadoAtual = clienteExistente.getEstado();
        String estado = scan.nextLine();
        while (true) {
            System.out.print("Estado (" + estadoAtual + ") [2 letras, sem números]: ");

            estado = scan.nextLine().trim();

            if (estado.length() != 2) {
                System.out.println("Estado inválido! Digite exatamente 2 letras.");
                continue;
            }

            if (!estado.matches("[a-zA-Z]{2}")) {
                System.out.println("Estado inválido! Use apenas letras (A-Z).");
                continue;
            }

            break;
        }

        clienteExistente.setEstado(estado.toUpperCase());

        System.out.print("Número de Telefone (" + clienteExistente.getNumero_tel() + "): ");
        String nt = scan.nextLine();
        if (!nt.isEmpty()) clienteExistente.setNumero_tel(nt);

        System.out.print("Número da Casa (" + clienteExistente.getNumero_casa() + "): ");
        String nc = scan.nextLine();
        if (!nc.isEmpty()) {
            try {
                clienteExistente.setNumero_casa(Integer.parseInt(nc));
            } catch (NumberFormatException e) {
                System.out.println("Número da casa inválido. Mantendo o valor anterior.");
            }
        }

        clienteDao.atualizar(clienteExistente);
        System.out.println("Cliente atualizado com sucesso.");
    }

    public void Deletar() {
        System.out.print("Digite o CPF do cliente a ser deletado: ");
        String cpf = scan.nextLine();

        Cliente cliente = clienteDao.buscarPorCpf("cpf", cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        clienteDao.deletar(cpf);
        System.out.println("Cliente deletado com sucesso.");
    }
}
