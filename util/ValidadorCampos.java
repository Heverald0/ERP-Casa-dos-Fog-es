package util;

public class ValidadorCampos {

    public static boolean validarEmail(String email) {
        return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    public static boolean validarNome(String nome) {
        return nome != null && nome.matches("^[A-Za-zÀ-ú ]{2,}$");
    }

    public static boolean validarSenhaForte(String senha) {
        return senha != null && senha.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%!]).{8,}$");
    }

    public static boolean validarSenhaSimples(String senha) {
        return senha != null && senha.length() >= 6;
    }

    public static boolean senhasConferem(String senha, String confirmar) {
        return senha != null && senha.equals(confirmar);
    }
}
