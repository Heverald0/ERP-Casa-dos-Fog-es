package services;

import dao.UsuarioDAO;
import java.util.List;
import model.CadastroUsuarios;

public class UsuarioService {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void cadastrarUsuario(CadastroUsuarios usuario) {
        usuarioDAO.salvarUsuario(usuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    public void listarUsuarios() {
        List<CadastroUsuarios> lista = usuarioDAO.listarTodos();

        if (lista.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        for (CadastroUsuarios u : lista) {
            u.ExibirInfos();
        }
    }

    public CadastroUsuarios buscarPorEmail(String email) {
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
}
