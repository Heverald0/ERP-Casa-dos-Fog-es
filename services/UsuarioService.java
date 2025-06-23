package services;
import java.util.ArrayList;
import java.util.List;

import model.CadastroUsuarios;

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

    public CadastroUsuarios buscarPorCpf(String cpf) {
    for (CadastroUsuarios u : usuarios) {
        if (u.getCPFCompleto().equals(cpf)) {
            return u;
        }
    }
    return null;
}

public boolean excluirUsuario(String email) {
    CadastroUsuarios usuario = buscarPorEmail(email);
    if (usuario != null) {
        usuarios.remove(usuario); // Certifique-se que 'usuarios' é uma lista mutável
        return true;
    }
    return false;
}

public List<CadastroUsuarios> getUsuarios() {
    return usuarios;
}

}
