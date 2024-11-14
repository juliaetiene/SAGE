package br.com.sage.Enum;

public enum Generos {
    FEMININO,
    MASCULINO;


    public static boolean validaGenero(String genero) {
        for (Generos g : Generos.values()) {
            if (g.name().equals(genero)) {
                return true;
            }
        }
        return false;
    }
}
