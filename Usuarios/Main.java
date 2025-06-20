import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioService usuarioService = new UsuarioService();

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n==== MENU ====");
            System.out.println("1 - Cadastrar Usuário");
            System.out.println("2 - Listar Usuários");
            System.out.println("3 - Buscar por E-mail");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Data de Nascimento (dd/mm/aaaa): ");
                    String dataNasc = scanner.nextLine();

                    System.out.print("Senha: ");
                    String senha = scanner.nextLine();

                    CadastroUsuarios novoUsuario = new CadastroUsuarios(nome, email, dataNasc, senha);
                    usuarioService.cadastrarUsuario(novoUsuario);
                    break;

                case 2:
                    usuarioService.listarUsuarios();
                    break;

                case 3:
                    System.out.print("Digite o e-mail para busca: ");
                    String buscaEmail = scanner.nextLine();
                    CadastroUsuarios encontrado = usuarioService.buscarPorEmail(buscaEmail);
                    if (encontrado != null) {
                        System.out.println("---- Usuário encontrado ----");
                        encontrado.ExibirInfos();
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;

                case 0:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }
}
