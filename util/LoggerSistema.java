package util;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerSistema {

    private static final String ARQUIVO_LOG = "logs.txt";

    public static void registrarLog(String usuario, String acao) {
        try (FileWriter writer = new FileWriter(ARQUIVO_LOG, true)) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.write("[" + timestamp + "] " + usuario + " " + acao + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo de log: " + e.getMessage());
        }
    }
}
