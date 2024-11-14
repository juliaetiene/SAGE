package br.com.sage.Model;

public abstract class Funcionario {
    private String nome;
    private Enum cargo;
    private double  salario;
    private Enum genero;

    public Funcionario(String nomeObj, Enum cargoObj, double salario, Enum genero ){
        this.nome = nomeObj;
        this.cargo = cargoObj;
        this.salario = salario;
        this.genero = genero;
    }
}
