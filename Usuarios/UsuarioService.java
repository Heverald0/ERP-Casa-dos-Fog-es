package Usuarios;
import java.util.ArrayList;

public class UsuarioService {
    private ArrayList<CadastroUsuarios> usuarios = new ArrayList<>();

    public void cadastrarUsuario(CadastroUsuarios usuario) {
        usuarios.add(usuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    public void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        for (CadastroUsuarios u : usuarios) {
            System.out.println("---------------------");
            u.ExibirInfos();
        }
    }

    public CadastroUsuarios buscarPorEmail(String email) {
        for (CadastroUsuarios u : usuarios) {
            if (u.getEmailCompleto().equalsIgnoreCase(email)) {
                return u;
            }
        }
        return null;
    }
}
