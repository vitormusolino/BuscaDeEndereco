public class Endereco {
    private String cep;
    private String logradouro;

    // Construtor padrão
    public Endereco() {
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return logradouro;
    }

    public void setEndereco(String endereco) {
        this.logradouro = endereco;
    }

    @Override
    public String toString() {
        return "(CEP: " + cep + "," + "Endereço: " + logradouro + ")";
    }
}
