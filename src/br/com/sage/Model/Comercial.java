package br.com.sage.Model;


import br.com.sage.Contract.ICargoComercial;

public class Comercial extends Funcionario implements ICargoComercial {


    public Comercial(String nome, Enum cargo, double salario, Enum genero){
        super(nome, cargo, salario,genero);
    }


    @Override
    public void cadastrarCliente(Sistema sistema) {

    }
}

