
public class CadastroUsuarios{
    private String NomeCompleto;
    private String EmailCompleto;
    private String SenhaAcesso;
    private String DataNascimento;

    public CadastroUsuarios(String NomeCompleto, String EmailCompleto, String SenhaAcesso, String DataNascimento){
        this.NomeCompleto = NomeCompleto;
        this.EmailCompleto = EmailCompleto;
        this.SenhaAcesso = SenhaAcesso;
        this.DataNascimento = DataNascimento;
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

    public void ExibirInfos(){
        System.out.println("Nome de usuário: " + getNomeCompleto() + 
        "\nEmail: " + getEmailCompleto());
    }
}