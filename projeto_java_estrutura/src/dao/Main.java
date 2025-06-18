import java.util.Scanner;
public class Main {
    public static void main (String args[]){

        Scanner scan = new Scanner(System.in);
        dadosDAO dados = new dadosDAO();

        while (true) {
            System.out.println("\n--- MENU CLIENTE ---");
            System.out.println("1 - Adicionar Cliente");
            System.out.println("2 - Buscar Cliente por Coluna");
            System.out.println("3 - Listar Todos os Clientes");
            System.out.println("4 - Atualizar Cliente");
            System.out.println("5 - Deletar Cliente");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            String opcao = scan.nextLine();

            switch (opcao) {
                case "1":
                    dados.Adicionar();
                    break;
                case "2":
                    dados.Buscar();
                    break;
                case "3":
                    dados.ListarTodos();
                    break;
                case "4":
                    dados.Atualizar();
                    break;
                case "5":
                    dados.Deletar();
                    break;
                case "0":
                    System.out.println("Encerrando o sistema...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void extracted(dadosDAO dados) {
        dados.Adicionar();
    }
}


