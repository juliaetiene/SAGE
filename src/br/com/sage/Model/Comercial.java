package br.com.sage.Model;

import java.util.Scanner;

public class Comercial extends Usuario{

    public Comercial(String nome, Data nascidoEm, Enum cargo, double salario){
        super(nome, nascidoEm, cargo,salario);
    }

    public static Cliente cadastrarCliente(Scanner scanner){
        //
        System.out.println("DIGITE O NOME DO CLIENTE:");
        String nomeObj = scanner.nextLine().toUpperCase();
        //

        System.out.println("INFORME A DATA DE NASCIMENTO DO CLIENTE (ex: dd/MM/yyyy):");
        String dataNascimentoObj = scanner.nextLine();
        Data nascidoEmObj = Data.parse(dataNascimentoObj);
        //

        System.out.println("DIGITE O CPF/CNPJ DO CLIENTE:");
        String cpfObj = scanner.nextLine().toUpperCase();
        if(cpfObj.length() == 11){
            cpfObj = cpfObj.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
            System.out.println(cpfObj);}

        else if (cpfObj.length() == 14) {
            cpfObj = cpfObj.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
            System.out.println(cpfObj);
            } else{
            System.out.println("CPF INVALIDO! TENTE NOVAMENTE.");
        }

        System.out.println("DIGITE O ENDEREÇO DO CLIENTE:");
        String enderecoObj = scanner.nextLine().toUpperCase();

        System.out.println("DIGITE O NÚMERO DO CLIENTE:");
        String numeroContatoObj = scanner.nextLine();
        if(numeroContatoObj.length() == 10){
            numeroContatoObj = numeroContatoObj.replaceAll("(\\d{2})(\\d{4})(\\d{4})", "($1)$2-$3");
            System.out.println(numeroContatoObj);
        }

        return new Cliente(nomeObj,nascidoEmObj,cpfObj,enderecoObj,numeroContatoObj);

    }
}
