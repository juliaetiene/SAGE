package br.com.sage.Model;

import java.awt.*;

public class Sistema {
    private String nomeEmpresa;
    private String cnpj;
    private String endereco;
    private Data dataAbertura;

    public Sistema(String nomeEmpresaObj, String cnpjObj, String enderecoObj, Data sistemaDataObj, Data dataAbertura){
        this.nomeEmpresa = nomeEmpresaObj;
        this.cnpj = cnpjObj;
        this.endereco = enderecoObj;
        this.dataAbertura = dataAbertura;
    }


}
