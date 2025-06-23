package services;
<<<<<<< HEAD

import dao.UsuarioDAO;
import java.util.List;
import model.CadastroUsuarios;

public class UsuarioService {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void cadastrarUsuario(CadastroUsuarios usuario) {
        usuarioDAO.salvarUsuario(usuario);
=======
import java.util.ArrayList;
import java.util.List;

import model.CadastroUsuarios;

public class UsuarioService {
    private ArrayList<CadastroUsuarios> usuarios = new ArrayList<>();

    public void cadastrarUsuario(CadastroUsuarios usuario) {
        usuarios.add(usuario);
>>>>>>> 4c2b3edd48a5dec54551b5acd96b5c813370f52e
        System.out.println("Usuário cadastrado com sucesso!");
    }

    public void listarUsuarios() {
<<<<<<< HEAD
        List<CadastroUsuarios> lista = usuarioDAO.listarTodos();

        if (lista.isEmpty()) {
=======
        if (usuarios.isEmpty()) {
>>>>>>> 4c2b3edd48a5dec54551b5acd96b5c813370f52e
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

<<<<<<< HEAD
        for (CadastroUsuarios u : lista) {
=======
        for (CadastroUsuarios u : usuarios) {
            System.out.println("---------------------");
>>>>>>> 4c2b3edd48a5dec54551b5acd96b5c813370f52e
            u.ExibirInfos();
        }
    }

    public CadastroUsuarios buscarPorEmail(String email) {
<<<<<<< HEAD
        return usuarioDAO.buscarPorEmail(email);
    }

    public CadastroUsuarios buscarPorCpf(String cpf) {
        return usuarioDAO.buscarPorCpf(cpf);
    }

    public boolean excluirUsuario(String email) {
        return usuarioDAO.excluirPorEmail(email);
    }

    public List<CadastroUsuarios> getUsuarios() {
        return usuarioDAO.listarTodos();
    }
=======
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

>>>>>>> 4c2b3edd48a5dec54551b5acd96b5c813370f52e
}
