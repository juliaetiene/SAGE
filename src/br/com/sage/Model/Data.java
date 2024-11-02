package br.com.sage.Model;

public class Data {

    //CLASSE COM TRATAMENTO PARA SER USADA DE UMA FORMA
    // GERAL EM TODO O PROJETO QUANDO SE TRATA DE DATAS.



    private int dia;
    private int mes;
    private int ano;


    public Data(int diaObj, int mesObj, int anoObj){
        this.dia = diaObj;
        this.mes = mesObj;
        this.ano = anoObj;
    }

    public static Data parse(String dataStr) {
        String[] partes = dataStr.split("/");
        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int ano = Integer.parseInt(partes[2]);
        return new Data(dia, mes, ano);
    }


    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }

    public void setDia(int dia) {
        if(dia >= 1 && dia <= 31) {
            this.dia = dia;
            System.out.println("Alteração realizada com sucesso!");
        }else{
            System.out.println("Alteração invalida!");
        }
    }

    public void setMes(int mes){
        if(mes >= 1 && mes <= 12){
            System.out.println("Alteração realizada com sucesso!");
            this.mes = mes;

        }else{
            System.out.println("Alteração invalida!");
        }
    }
    public void setAno(int ano){
        this.ano = ano;
    }

    @Override
    public String toString() {
        return String.format("%d/%d/%d", this.dia, this.mes, this.ano);
    }
}
