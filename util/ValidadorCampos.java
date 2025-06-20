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

    public static boolean validarCPF(String cpf) {
    if (cpf == null) return false;

    cpf = cpf.replaceAll("[^\\d]", "");

    if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) return false;

    try {
        int soma1 = 0, soma2 = 0;
        for (int i = 0; i < 9; i++) {
            int num = Character.getNumericValue(cpf.charAt(i));
            soma1 += num * (10 - i);
            soma2 += num * (11 - i);
        }

        int digito1 = 11 - (soma1 % 11);
        digito1 = (digito1 >= 10) ? 0 : digito1;

        soma2 += digito1 * 2;
        int digito2 = 11 - (soma2 % 11);
        digito2 = (digito2 >= 10) ? 0 : digito2;

        return digito1 == Character.getNumericValue(cpf.charAt(9)) &&
               digito2 == Character.getNumericValue(cpf.charAt(10));
    } catch (Exception e) {
        return false;
    }
}

}
