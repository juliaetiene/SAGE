package br.com.sage.Model;


public class Cliente {
    private String nome;
    private String cpf;
    private String endereco;
    private String numeroContato;
    private String planoContratadoStr;
    private String genero;

    public Cliente(String nomeCompleto, String cpf, String endereco, String numeroContato, String planoContratadoStr, String genero){
        this.nome = nomeCompleto;
        this.cpf = cpf;
        this.endereco = endereco;
        this.numeroContato = numeroContato;
        this.planoContratadoStr = planoContratadoStr;
        this.genero = genero;
    }


    public String getCpfCnpj() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumeroContato() {
        return numeroContato;
    }

    public void setNumeroContato(String numeroContato) {
        this.numeroContato = numeroContato;
    }


    public String getPlanoContratadoStr() {
        return planoContratadoStr;
    }

    public void setPlanoContratadoStr(String planoContratadoStr) {
        this.planoContratadoStr = planoContratadoStr;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }


}
