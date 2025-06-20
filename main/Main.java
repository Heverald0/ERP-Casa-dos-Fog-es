package main;
import java.util.Scanner;
import util.HashUtils;
import util.ValidadorCampos;
import util.ValidadorDatas;
import model.CadastroUsuarios;
import services.UsuarioService;

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
    System.out.println("=== Cadastro de Usuário ===");

    System.out.print("Nome: ");
    String nome = scanner.nextLine();
    if (!ValidadorCampos.validarNome(nome)) {
        System.out.println("Nome inválido! Use apenas letras e no mínimo 2 caracteres.");
        break;
    }

    System.out.print("E-mail: ");
    String email = scanner.nextLine();
    if (!ValidadorCampos.validarEmail(email)) {
        System.out.println("E-mail inválido!");
        break;
    }

    if (usuarioService.buscarPorEmail(email) != null) {
        System.out.println("Já existe um usuário com esse e-mail.");
        break;
    }

    System.out.print("Data de Nascimento (ddMMyyyy ou dd/MM/yyyy): ");
    String dataDigitada = scanner.nextLine();
    String dataFormatada = ValidadorDatas.formatarData(dataDigitada);
    if (dataFormatada == null) {
        System.out.println("Data inválida ou ano superior a 2025.");
        break;
    }

    System.out.print("Senha (mínimo 6 caracteres): ");
    String senha = scanner.nextLine();
    if (!ValidadorCampos.validarSenhaSimples(senha)) {
        System.out.println("Senha fraca! Use pelo menos 6 caracteres.");
        break;
    }

    System.out.print("Confirme a senha: ");
    String confirmarSenha = scanner.nextLine();
    if (!ValidadorCampos.senhasConferem(senha, confirmarSenha)) {
        System.out.println("As senhas não coincidem.");
        break;
    }

    String senhaHash = HashUtils.gerarHash(senha);
    CadastroUsuarios novoUsuario = new CadastroUsuarios(nome, email, dataFormatada, senhaHash);
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
