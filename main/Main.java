package main;
import java.util.Scanner;
import util.HashUtils;
import util.LoggerSistema;
import util.ValidadorCampos;
import util.ValidadorDatas;
import model.CadastroUsuarios;
import services.UsuarioService;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioService usuarioService = new UsuarioService();

        String adminEmail = "admin@erp.com";
        if (usuarioService.buscarPorEmail(adminEmail) == null) {
            String adminNome = "Admin";
            String adminData = "01/01/2000";
            String adminCpf = "078.536.645-83";
            String adminSenha = "Wizard3.0";
            String senhaHash = HashUtils.gerarHash(adminSenha);

            CadastroUsuarios admin = new CadastroUsuarios(adminNome, adminEmail, adminData, senhaHash, adminCpf, true);
            usuarioService.cadastrarUsuario(admin);
            System.out.println("Administrador padrão criado.");
            System.out.println("E-mail: " + adminEmail + " | Senha: " + adminSenha);
        }

        CadastroUsuarios usuarioLogado = null;


        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n==== MENU ====");
            System.out.println("1 - Cadastrar Usuário");
            System.out.println("2 - Listar Usuários");
            System.out.println("3 - Buscar por E-mail");
            System.out.println("4 - Fazer Login");
            System.out.println("5 - Fazer Logout");
            System.out.println("6 - Editar Usuário (Admin)");
            System.out.println("7 - Excluir Usuário (Admin)");
            System.out.println("8 - Listar apenas administradores");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    if (usuarioLogado == null || !usuarioLogado.isAdmin()) {
                        LoggerSistema.registrarLog("ACESSO NEGADO", "Usuário tentou acessar cadastro sem permissão");
                        System.out.println("Apenas administradores logados podem cadastrar novos usuários.");
                        break;
                    }
                    System.out.println("=== Cadastro de Usuário ===");

                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    if (!ValidadorCampos.validarNome(nome)) {
                        System.out.println("Nome inválido! Use apenas letras e no mínimo 2 caracteres.");

                    LoggerSistema.registrarLog("ADMIN " + usuarioLogado.getNomeCompleto(), "cadastrou o usuário " + nome);

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

            System.out.print("CPF (somente números): ");
            String cpf = scanner.nextLine();

            if (!ValidadorCampos.validarCPF(cpf)) {
            System.out.println("CPF inválido!");
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

            System.out.print("Este usuário será ADMINISTRADOR? (s/n): ");
            String adminInput = scanner.nextLine().trim().toLowerCase();
            boolean isAdmin = adminInput.equals("s");

            String senhaHash = HashUtils.gerarHash(senha);
            CadastroUsuarios novoUsuario = new CadastroUsuarios(nome, email, dataFormatada, senhaHash, cpf, isAdmin);
            usuarioService.cadastrarUsuario(novoUsuario);
                break;

                case 2:
                    if (usuarioLogado == null) {
                    System.out.println("Você precisa estar logado para realizar essa ação.");
                    break;
}
                    usuarioService.listarUsuarios();
                    LoggerSistema.registrarLog(usuarioLogado.isAdmin() ? "ADMIN " + usuarioLogado.getNomeCompleto() : "USUÁRIO " + usuarioLogado.getNomeCompleto(), "listou todos os usuários");
                    break;

                case 3:
                    if (usuarioLogado == null) {
                    System.out.println("Você precisa estar logado para realizar essa ação.");
                    break;
                }

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

                case 4:
                    System.out.println("=== LOGIN ===");
                    System.out.print("E-mail: ");
                    String emailLogin = scanner.nextLine();

                    System.out.print("Senha: ");
                    String senhaLogin = scanner.nextLine();

                    CadastroUsuarios usuario = usuarioService.buscarPorEmail(emailLogin);
                    if (usuario != null && HashUtils.verificarSenha(senhaLogin, usuario.getSenhaHash())) {
                        usuarioLogado = usuario;
                        System.out.println("Login realizado com sucesso! Bem-vindo(a), " + usuarioLogado.getNomeCompleto());

                        String perfil = usuarioLogado.isAdmin() ? "ADMIN" : "USUÁRIO";
                        LoggerSistema.registrarLog(perfil + " " + usuarioLogado.getNomeCompleto(), "realizou login");

                    } else {
                        System.out.println("E-mail ou senha incorretos.");
                    }
                    break;
                case 5:
                    if (usuarioLogado != null) {
                        LoggerSistema.registrarLog(
                            (usuarioLogado.isAdmin() ? "ADMIN " : "USUÁRIO ") + usuarioLogado.getNomeCompleto(),
                            "realizou logout"
                        );
                        System.out.println("Logout realizado com sucesso. Até logo, " + usuarioLogado.getNomeCompleto() + "!");
                        usuarioLogado = null;
                    } else {
                        System.out.println("Nenhum usuário está logado no momento.");
                    }
                    break;
                case 6:
                    if (usuarioLogado == null || !usuarioLogado.isAdmin()) {
                        System.out.println("Apenas administradores podem editar usuários.");
                        LoggerSistema.registrarLog("ACESSO NEGADO", "Tentativa de edição sem permissão");
                        break;
                    }

                    System.out.print("E-mail do usuário a editar: ");
                    String emailEdit = scanner.nextLine();
                    CadastroUsuarios usuarioEditar = usuarioService.buscarPorEmail(emailEdit);

                    if (usuarioEditar == null) {
                        System.out.println("Usuário não encontrado.");
                        break;
                    }

                    System.out.print("Novo nome: ");
                    String novoNome = scanner.nextLine();
                    usuarioEditar.setNomeCompleto(novoNome);

                    System.out.print("Nova data de nascimento (ddMMyyyy ou dd/MM/yyyy): ");
                    String novaData = scanner.nextLine();
                    String dataFormatadaEdit = ValidadorDatas.formatarData(novaData);
                    if (dataFormatadaEdit != null) {
                        usuarioEditar.setDataNascimento(dataFormatadaEdit);
                    } else {
                        System.out.println("Data inválida, não foi alterada.");
                    }

                    System.out.println("Usuário atualizado.");
                    LoggerSistema.registrarLog("ADMIN " + usuarioLogado.getNomeCompleto(), "editou o usuário " + usuarioEditar.getEmailCompleto());
                    break;

                    case 7:
                    if (usuarioLogado == null || !usuarioLogado.isAdmin()) {
                        System.out.println("Apenas administradores podem excluir usuários.");
                        LoggerSistema.registrarLog("ACESSO NEGADO", "Tentativa de exclusão sem permissão");
                        break;
                    }

                    System.out.print("E-mail do usuário a excluir: ");
                    String emailDel = scanner.nextLine();
                    boolean removido = usuarioService.excluirUsuario(emailDel);

                    if (removido) {
                        System.out.println("Usuário excluído com sucesso.");
                        LoggerSistema.registrarLog("ADMIN " + usuarioLogado.getNomeCompleto(), "excluiu o usuário " + emailDel);
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;

                    case 8:
                        if (usuarioLogado == null || !usuarioLogado.isAdmin()) {
                            System.out.println("Apenas administradores podem acessar esse relatório.");
                            LoggerSistema.registrarLog("ACESSO NEGADO", "Tentativa de acessar relatório de admins");
                            break;
                        }

                        System.out.println("=== Lista de Administradores ===");
                        for (CadastroUsuarios u : usuarioService.getUsuarios()) {
                            if (u.isAdmin()) {
                                System.out.println("- " + u.getNomeCompleto() + " (" + u.getEmailCompleto() + ")");
                            }
                        }

                        LoggerSistema.registrarLog("ADMIN " + usuarioLogado.getNomeCompleto(), "acessou o relatório de administradores");
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
