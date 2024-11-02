package br.com.sage.Model;

public abstract class  Usuario {
    private String nome;
    private Data nascidoEm;
    private Enum cargo;
    private double  salario;


    public Usuario(String nomeObj, Data nascidoEmObj, Enum cargoObj, double salario ){
        this.nome = nomeObj;
        this.nascidoEm = nascidoEmObj;
        this.cargo = cargoObj;
        this.salario = salario;
    }
}
