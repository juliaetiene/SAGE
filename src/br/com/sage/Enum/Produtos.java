package br.com.sage.Enum;

public enum Produtos {
    PLANO_WIRELESS_4MB,
    PLANO_FIBRA_100MB,
    PLANO_FIBRA_200MB,
    PLANO_FIBRA_300MB,
    PLANO_FIBRA_500MB,
    PLANO_FIBRA_550MB;



    public static boolean validaPlano(String plano) {
        for (Produtos p : Produtos.values()) {
            if (p.name().equals(plano)) {
                return true;
            }
        }
        return false;
    }
}
