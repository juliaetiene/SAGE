package br.com.sage.Main;
import br.com.sage.Model.Sistema;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Sistema sistema = new Sistema("Netline Telecom",
                "000.000.0000/00",
                "Praça João da Mata, 80");




                Scanner entrada = new Scanner(System.in);
                boolean controle = true;

                while (controle) {
                    System.out.println("--------- MENU ----------");
                    System.out.println("OLÁ, SEJA BEM-VINDO(A) NETLINE TELECOM");
                    System.out.println("O QUÊ DESEJA REALIZAR HOJE? ;)");
                    System.out.println("[1.] SUPORTE TÉCNICO");
                    System.out.println("[2.] VER PLANOS DISPONÍVEIS");
                    System.out.println("[3.] SAIR DO SISTEMA");
                    System.out.println("--------------------------");
                    System.out.print("DIGITE A OPÇÃO DESEJADA: ");
                    String op = entrada.nextLine();

                    if (op.equals(sistema.getSenhaAcessoServiceDeskSistema())) {
                        sistema.fazerLogin(op);
                        if (sistema.getAutenticandoAcessoServiceDesk()) {
                            boolean controleServiceDesk = true;
                            while (controleServiceDesk) {
                                System.out.println("--------- MENU SERVICE DESK ----------");
                                System.out.println("[1.] VER CARGO");
                                System.out.println("[2.] PROCURAR CLIENTE");
                                System.out.println("[3.] SAIR");
                                System.out.println("--------------------------------------");
                                System.out.print("DIGITE A OPÇÃO DESEJADA: ");
                                String opcao = entrada.nextLine();

                                switch (opcao) {
                                    case "1":
                                        sistema.verCargo();
                                        break;
                                    case "2":
                                        sistema.procurarCliente();
                                        break;
                                    case "3":
                                        System.out.println("Deslogando...");
                                        sistema.setAutenticandoLogin(false);
                                        controleServiceDesk = false;
                                        break;
                                    default:
                                        System.out.println("OPÇÃO INVÁLIDA, TENTE NOVAMENTE!");
                                        break;
                                }
                            }
                        }
                    } else if (op.equals(sistema.getSenhaAcessoComercialSistema())) {
                        sistema.fazerLogin(op);
                        if (sistema.getAutenticandoAcessoComercial()) {
                            boolean controleComercial = true;
                            while (controleComercial) {
                                System.out.println("--------- MENU COMERCIAL ----------");
                                System.out.println("[1.] CADASTRAR CLIENTE");
                                System.out.println("[2.] PROCURAR CLIENTE");
                                System.out.println("[3.] SAIR");
                                System.out.println("------------------------------------");
                                System.out.print("DIGITE A OPÇÃO DESEJADA: ");
                                String opcao = entrada.nextLine();

                                switch (opcao) {
                                    case "1":
                                        sistema.cadastrarCliente(sistema);
                                        break;
                                    case "2":
                                        sistema.procurarCliente();
                                        break;
                                    case "3":
                                        System.out.println("Deslogando...");
                                        sistema.setAutenticandoLogin(false);
                                        controleComercial = false;
                                        break;
                                    default:
                                        System.out.println("OPÇÃO INVÁLIDA, TENTE NOVAMENTE!");
                                        break;
                                }
                            }
                        }
                    } else if (op.equals(sistema.getSenhaAcessoAdministradorSistema())) {
                        sistema.entrar();
                        if (sistema.getAcessoAdmin()) {
                            boolean controleAdmin = true;
                            while (controleAdmin) {
                                System.out.println("--------- MENU ADMINISTRADOR ----------");
                                System.out.println("[1.] CADASTRAR USUÁRIO");
                                System.out.println("[2.] ESTATÍSTICAS ATENDIMENTOS ABERTOS");
                                System.out.println("[3.] ESTATÍSTICAS ATENDIMENTOS ENCERRADOS");
                                System.out.println("----------------------------------------");
                                System.out.println("[4.] SAIR");
                                System.out.print("DIGITE A OPÇÃO DESEJADA: ");
                                String opcao = entrada.nextLine();

                                switch (opcao) {
                                    case "1":
                                        sistema.cadastrarUsuario();
                                        break;
                                    case "2":
                                        sistema.controleAtendimentosAberto();
                                        break;
                                    case "3":
                                        sistema.controleAtendimentosEncerrados();
                                        break;
                                    case "4":
                                        System.out.println("Deslogando...");
                                        sistema.setAcessoAdmin(false);
                                        controleAdmin = false;
                                        break;
                                    default:
                                        System.out.println("OPÇÃO INVÁLIDA, TENTE NOVAMENTE!");
                                        break;
                                }
                            }
                        }
                    } else {

                        switch (op) {
                            case "1":
                                sistema.clienteEntrarEmContato();
                                break;
                            case "2":
                                sistema.mostrarPlanos();
                                break;
                            case "3":
                                System.out.println("Até mais....");
                                controle = false;
                                break;
                            default:
                                System.out.println("OPÇÃO INVÁLIDA, TENTE NOVAMENTE!");
                                break;
                        }
                    }
                }

                entrada.close();
            }
        }
