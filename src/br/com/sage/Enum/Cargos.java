package br.com.sage.Enum;

public enum Cargos {
    SERVICEDESK,
    COMERCIAL;


    public static boolean validaCargo(String cargo) {
        for (Cargos c : Cargos.values()) {
            if (c.name().equals(cargo)) {
                return true;
            }
        }
        return false;
    }
}
