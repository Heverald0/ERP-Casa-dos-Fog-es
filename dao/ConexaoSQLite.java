package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoSQLite {
    private static final String URL = "jdbc:sqlite:libs/banco.db";

    public static Connection conectar() {
        try {
            Connection conn = DriverManager.getConnection(URL);
            criarTabelaUsuarios(conn); 
            System.out.println(" Conectado ao banco SQLite com sucesso.");
            return conn;
        } catch (SQLException e) {
            System.out.println(" Erro ao conectar com o banco de dados.");
            e.printStackTrace();
            return null;
        }
    }

    private static void criarTabelaUsuarios(Connection conn) {
        String sql = """
            CREATE TABLE IF NOT EXISTS usuarios (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                email TEXT NOT NULL UNIQUE,
                senha TEXT NOT NULL,
                cpf TEXT NOT NULL UNIQUE,
                dataNascimento TEXT,
                isAdmin BOOLEAN,
                dataCriacao TEXT DEFAULT CURRENT_TIMESTAMP
            );
        """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println(" Tabela 'usuarios' pronta.");
        } catch (SQLException e) {
            System.out.println(" Erro ao criar tabela de usuários.");
            e.printStackTrace();
        }
    }
}
