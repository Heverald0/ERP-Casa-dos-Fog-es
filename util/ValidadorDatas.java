package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidadorDatas {

    public static String formatarData(String input) {
        try {
            SimpleDateFormat entrada;

            if (input.matches("\\d{8}")) {
                entrada = new SimpleDateFormat("ddMMyyyy");
            } else if (input.matches("\\d{2}/\\d{2}/\\d{4}")) {
                entrada = new SimpleDateFormat("dd/MM/yyyy");
            } else {
                return null;
            }

            entrada.setLenient(false);
            Date data = entrada.parse(input);

            int ano = Integer.parseInt(new SimpleDateFormat("yyyy").format(data));
            if (ano > 2025) {
                return null;
            }

            SimpleDateFormat saida = new SimpleDateFormat("dd/MM/yyyy");
            return saida.format(data);

        } catch (ParseException e) {
            return null;
        }
    }
}

