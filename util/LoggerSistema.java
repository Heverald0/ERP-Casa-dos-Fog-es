package util;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class LoggerSistema {

    public static void registrarAcao(String usuario, String acao) {
        try (FileWriter writer = new FileWriter("log.txt", true)) {
            String linha = LocalDateTime.now() + " | " + usuario + " | " + acao + "\n";
            writer.write(linha);
        } catch (IOException e) {
            System.out.println("Erro ao registrar log: " + e.getMessage());
        }
    }
}
