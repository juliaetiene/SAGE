package br.com.sage.Model;

import javax.swing.*;
import java.util.ArrayList;

public class Cliente {
    private String nomeCompleto;
    private Data nascidoEm;
    private String cpf;
    private String endereco;
    private String numeroContato;
    private ArrayList<String> os;
    private Data dataOs;

    public Cliente(String nomeCompletoObj, Data nascidoEmObj, String cpfObj, String enderecoObj, String numeroContatoObj){
        this.nomeCompleto = nomeCompletoObj;
        this.nascidoEm = nascidoEmObj;
        this.cpf = cpfObj;
        this.endereco = enderecoObj;
        this.numeroContato = numeroContatoObj;
    }


}
