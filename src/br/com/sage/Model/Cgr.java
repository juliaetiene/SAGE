package br.com.sage.Model;

import java.util.ArrayList;

public class Cgr extends Usuario{
    private String usuario;
    private String senha;
    protected Data sistemaData;
    private ArrayList<String> listaFuncionariosAtivos;
    private ArrayList<String> listaFuncionariosDemitidos;
    protected boolean empresaAtiva;


    public Cgr(String nome, Data nascidoEm, Enum cargo, double salario){
        super(nome, nascidoEm, cargo,salario);
    }


}
