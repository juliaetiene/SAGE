package br.com.sage.Model;

import br.com.sage.Contract.ISistema;

public abstract class Supervisor implements ISistema {
    protected String usuario = "ADMIN";
    protected String senha = "@123";
    protected boolean acessoAdmin;

}
