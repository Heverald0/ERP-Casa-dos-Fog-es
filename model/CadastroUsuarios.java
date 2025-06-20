package model;

public class CadastroUsuarios{
    private String NomeCompleto;
    private String EmailCompleto;
    private String SenhaAcesso;
    private String DataNascimento;
    private String CPFCompleto;
    private boolean isAdmin;

    public CadastroUsuarios(String NomeCompleto, String EmailCompleto, String SenhaAcesso, String DataNascimento, String CPFCompleto, boolean isAdmin){
        this.NomeCompleto = NomeCompleto;
        this.EmailCompleto = EmailCompleto;
        this.SenhaAcesso = SenhaAcesso;
        this.DataNascimento = DataNascimento;
        this.CPFCompleto = CPFCompleto;
        this.isAdmin = isAdmin;
    }

    public String getNomeCompleto(){
        return NomeCompleto;
    }

    public String getEmailCompleto(){
        return EmailCompleto;
    }

    public String getSenhaAcesso(){
        return SenhaAcesso;
    }

    public String getDataNascimento(){
        return DataNascimento;
    }

    public String getCPFCompleto(){
        return CPFCompleto;
    }

    public void setAdmin(boolean isAdmin){
        this.isAdmin = isAdmin;
    }

    public void ExibirInfos(){
        System.out.println("Nome de usuário: " + getNomeCompleto() + 
        "\nEmail: " + getEmailCompleto() + "CPF: " + getCPFCompleto());
    }
}