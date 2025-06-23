package dao;

import model.CadastroUsuarios;
import java.sql.*;
import java.util.ArrayList;

public class UsuarioDAO {

    public void salvarUsuario(CadastroUsuarios usuario) {
        String sql = "INSERT INTO usuarios (nome, email, senha, cpf, dataNascimento, isAdmin) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoSQLite.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNomeCompleto());
            stmt.setString(2, usuario.getEmailCompleto());
            stmt.setString(3, usuario.getSenhaHash());
            stmt.setString(4, usuario.getCPFCompleto());
            stmt.setString(5, usuario.getDataNascimento());
            stmt.setBoolean(6, usuario.isAdmin());

            stmt.executeUpdate();
            System.out.println("Usuário salvo com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao salvar usuário no banco.");
            e.printStackTrace();
        }
    }

    public ArrayList<CadastroUsuarios> listarTodos() {
        ArrayList<CadastroUsuarios> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection conn = ConexaoSQLite.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                CadastroUsuarios user = new CadastroUsuarios(
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("dataNascimento"),
                    rs.getString("senha"),
                    rs.getString("cpf"),
                    rs.getBoolean("isAdmin")
                );
                lista.add(user);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar usuários.");
            e.printStackTrace();
        }

        return lista;
    }

    public CadastroUsuarios buscarPorEmail(String email) {
        String sql = "SELECT * FROM usuarios WHERE email = ?";

        try (Connection conn = ConexaoSQLite.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new CadastroUsuarios(
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("dataNascimento"),
                    rs.getString("senha"),
                    rs.getString("cpf"),
                    rs.getBoolean("isAdmin")
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuário por e-mail.");
            e.printStackTrace();
        }

        return null;
    }

    public CadastroUsuarios buscarPorCpf(String cpf) {
    String sql = "SELECT * FROM usuarios WHERE cpf = ?";
    try (Connection conn = ConexaoSQLite.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, cpf);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new CadastroUsuarios(
                rs.getString("nome"),
                rs.getString("email"),
                rs.getString("dataNascimento"),
                rs.getString("senha"),
                rs.getString("cpf"),
                rs.getBoolean("isAdmin")
            );
        }

    } catch (SQLException e) {
        System.out.println("Erro ao buscar usuário por CPF.");
        e.printStackTrace();
    }
    return null;
}

    public boolean excluirPorEmail(String email) {
    String sql = "DELETE FROM usuarios WHERE email = ?";
    try (Connection conn = ConexaoSQLite.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, email);
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;

    } catch (SQLException e) {
        System.out.println("Erro ao excluir usuário.");
        e.printStackTrace();
        return false;
    }
}

}
