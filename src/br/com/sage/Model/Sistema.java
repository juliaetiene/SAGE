package br.com.sage.Model;
import br.com.sage.Enum.Cargos;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import br.com.sage.Enum.Produtos;
import br.com.sage.Enum.Generos;
import br.com.sage.Contract.*;

public class Sistema extends Supervisor implements ISistema, ICargoSupervisor, ICargoComercial, ICargoServiceDesk, ICliente {
    private String nomeEmpresa;
    private String cnpj;
    private String endereco;
    private List<String> usuariosGerais;
    private List<Cliente> clientes;
    private List<String> reclamacoes;
    private boolean autenticando;
    private List<Comercial> setorComercial;
    private List<ServiceDesk> setorServiceDesk;
    private String cargoAtual = "";
    private int contagemNãoresolvidos;
    private int contagemResolvidos;
    private String senhaAcessoServiceDeskSistema = "8081";
    private String senhaAcessoComercialSistema = "8080";
    private String senhaAcessoAdministradorSistema = "8082";
    private boolean autenticandoLogin = false;
    private boolean autenticandoAcessoComercial;
    private boolean autenticandoAcessoServiceDesk;


    public Sistema(String nomeEmpresa, String cnpj, String endereco) {
        this.nomeEmpresa = nomeEmpresa;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.usuariosGerais = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.reclamacoes = new ArrayList<>();
        this.autenticando = false;
        this.setorComercial = new ArrayList<>();
        this.setorServiceDesk = new ArrayList<>();
    }


    @Override
    public void cadastrarUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("USUARIO:");
        String usuario = scanner.nextLine().toLowerCase();
        System.out.print("SENHA:");
        String senha = scanner.nextLine();
        System.out.print("CARGO:");
        String cargoStr = scanner.nextLine().toUpperCase();

        boolean usuarioExiste = false;
        for (String u : usuariosGerais) {
            if (u.equals(usuario + ":" + senha + ":" + cargoStr)) {
                usuarioExiste = true;
                break;
            }
        }
        if (acessoAdmin) {
            if (!usuarioExiste) {
                if (Cargos.validaCargo(cargoStr)) {
                    usuariosGerais.add(usuario + ":" + senha + ":" + cargoStr);
                    System.out.println("USUÁRIOS CADASTRADO COM SUCESSO!");
                    Cargos cargo = Cargos.valueOf(cargoStr);
                    switch (cargo) {
                        case COMERCIAL:
                            setorComercial.add(new Comercial(usuario, cargo, 0.0, null));
                            break;
                        case SERVICEDESK:
                            setorServiceDesk.add(new ServiceDesk(usuario, cargo, 0.0, null));
                            break;
                        default:
                            System.out.println("CARGO DESCONHECIDO");
                            break;
                    }
                } else {
                    System.out.println("CARGO INVÁLIDO.");
                }
            } else {
                System.out.println("USUÁRIO JÁ CADASTRADO!");
            }
        } else {
            System.out.println("ACESSO NEGADO");
        }
    }


    @Override
    public void clienteEntrarEmContato() {
        Scanner scanner = new Scanner(System.in);
        String contato = "";
        contagemNãoresolvidos = 0;
        System.out.print("CONFIRME O CPF/CNPJ DO ASSINANTE:");
        String cpfCnpj = scanner.nextLine();
        if (cpfCnpj.length() == 11) {
            cpfCnpj = cpfCnpj.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
            System.out.println("DIGITE UM NÚMERO PARA CONTATO:");
            String numeroTel = scanner.nextLine();
            if (numeroTel.length() == 10) {
                numeroTel = numeroTel.replaceAll("(\\d{2})(\\d{4})(\\d{4})", "($1) $2-$3");
                contato = numeroTel;
            } else {
                System.out.println("NÚMERO DE TELEFONE INVALIDO");
                return;
            }

        } else if (cpfCnpj.length() == 14) {
            cpfCnpj = cpfCnpj.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
            System.out.println("DIGITE UM NÚMERO PARA CONTATO:");
            String numeroTel1 = scanner.nextLine();
            if (numeroTel1.length() == 10) {
                numeroTel1 = numeroTel1.replaceAll("(\\d{2})(\\d{4})(\\d{4})", "($1) $2-$3");
                contato = numeroTel1;
            } else {
                System.out.println("NÚMERO DE TELEFONE INVALIDO.");
                return;
            }
        } else {
            System.out.println("A PESQUISA FALHOU, TENTE NOVAMENTE!");
            return;
        }

        boolean clienteEncontrado = false;
        for (Cliente cliente : clientes) {
            if (cliente.getCpfCnpj().equals(cpfCnpj)) {
                clienteEncontrado = true;
                System.out.println("CLIENTE ENCONTRADO:  " + cliente);
                System.out.println("DIGITE SUA RECLAMAÇÃO: ");
                String reclamacao = scanner.nextLine().toUpperCase();
                String enviar = reclamacao + " CLIENTE:" + cpfCnpj + " CONTATO:" + contato;
                reclamacoes.add(enviar);
                contagemNãoresolvidos += 1;
                System.out.println("SUA RECLAMAÇÃO FOI ENCIADO PARA O SETOR DE SUPORTE!");
                break;
            }
        }

        if (!clienteEncontrado) {
            System.out.println("CLIENTE NÃO ENCONTRADO.");
        }
    }


    @Override
    public void fazerLogin(String porta) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("DIGITE SEU USUARIO:");
        String usuarioDigitado = scanner.nextLine().toLowerCase();
        System.out.println("DIGITE SUA SENHA:");
        String senhaDigitada = scanner.nextLine();

        for (String usuario : usuariosGerais) {
            String[] partes = usuario.split(":");
            String usuarioCadastrado = partes[0];
            String senhaCadastrada = partes[1];
            String cargoCadastrado = partes[2];

            if (usuarioCadastrado.equals(usuarioDigitado) && senhaCadastrada.equals(senhaDigitada)) {
                autenticando = true;
                this.cargoAtual = cargoCadastrado;
                break;
            }
        }

        if (autenticando) {
            if (cargoAtual.equals("COMERCIAL")) {
                if (porta.equals("8080")) {
                    this.autenticandoAcessoComercial = true;
                    System.out.println("LOGIN BEM-SUCEDIDO!");
                    System.out.printf("\nBEM-VINDO(A), %S.\n", usuarioDigitado);
                } else {
                    System.out.println("PORTA INCORRETA PARA O COMERCIAL, TENTE NOVAMENTE");
                }
            } else if (cargoAtual.equals("SERVICEDESK")) {
                if (porta.equals("8081")) {
                    this.autenticandoAcessoServiceDesk = true;
                    System.out.println("LOGIN BEM-SUCEDIDO!");
                    System.out.printf("\nBEM-VINDO(A), %S.\n", usuarioDigitado);
                } else {
                    System.out.println("PORTA INCORRETA PARA O SERVIDE DESK, TENTE NOVAMENTE");
                }
            } else {
                System.out.println("VOCÊ NÃO TEM PERMISSÃO");
            }
        } else {
            System.out.println("USUÁRIO OU SENHA INCORRETOS, TENTE NOVAMENTE");
        }
    }



    public void setAutenticandoLogin(boolean autenticandoLogin) {
        this.autenticandoLogin = autenticandoLogin;
    }

    public boolean getAutenticarUsuario() {
        return autenticando;
    }

    public void adicionarClientes(Cliente cliente) {
        clientes.add(cliente);
    }


    @Override
    public void procurarCliente() {
        if (!autenticando) {
            System.out.println("VOCÊ PRECISA ESTÁ AUTENTICADO PARA ACESSAR O SISTEMA");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("DIGITE O CPF/CNPJ DO CLIENTE PARA BUSCAR: ");
        String cpfCnpj = scanner.nextLine();
        if (cpfCnpj.length() == 11) {
            cpfCnpj = cpfCnpj.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
        } else if (cpfCnpj.length() == 14) {
            cpfCnpj = cpfCnpj.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
        } else {
            System.out.println("CPF/CNPJ INVÁLIDO! TENTE NOVAMENTE.");
        }
        boolean encontrado = false;
        for (Cliente cliente : clientes) {
            if (cliente.getCpfCnpj().equals(cpfCnpj)) {
                System.out.println("CLIENTE ENCONTRADO!");
                System.out.println("NOME: " + cliente.getNome());
                System.out.println("CPF/CNPJ: " + cliente.getCpfCnpj());
                System.out.println("ENDEREÇO: " + cliente.getEndereco());
                System.out.println("CONTATO: " + cliente.getNumeroContato());
                System.out.println("PLANO CONTRATADO: " + cliente.getPlanoContratadoStr());
                System.out.println("GÊNERO: " + cliente.getGenero());
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("CLIENTE NÃO ENCONTRADO");
        }
    }

    @Override
    public void cadastrarCliente(Sistema sistema) {
        if (!sistema.getAutenticarUsuario()) {
            System.out.println("ACESSO NEGADO! VOCÊ PRECISA ESTÁ AUTENTICADO PARA CADASTRAR UM CLIENTE");
            return;
        }
        if (!cargoAtual.equals("COMERCIAL")) {
            System.out.println("VOCÊ NÃO TEM PERMISSÃO PARA ACESSAR ESSA FUNCIONALIDADE");
            return;
        }
        Scanner scanner = new Scanner(System.in);

        System.out.println("DIGITE O NOME DO CLIENTE:");
        String nome = scanner.nextLine().toUpperCase();

        System.out.println("DIGITE O CPF/CNPJ DO CLIENTE:");
        String cpf = scanner.nextLine();

        if (cpf.length() == 11) {
            cpf = cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
            System.out.println("CPF FORMATADO: " + cpf);
        } else if (cpf.length() == 14) {
            cpf = cpf.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
            System.out.println("CNPJ FORMATADO: " + cpf);
        } else {
            System.out.println("CPF/CNPJ INVÁLIDO! TENTE NOVAMENTE.");
            scanner.close();
            return;
        }

        System.out.println("DIGITE O ENDEREÇO DO CLIENTE:");
        String endereco = scanner.nextLine().toUpperCase();

        System.out.println("DIGITE O NÚMERO DO CLIENTE:");
        String numeroContato = scanner.nextLine();
        if (numeroContato.length() == 10) {
            numeroContato = numeroContato.replaceAll("(\\d{2})(\\d{4})(\\d{4})", "($1) $2-$3");
        } else if (numeroContato.length() == 11) {
            numeroContato = numeroContato.replaceAll("(\\d{2})(\\d{5})(\\d{4})", "($1) $2-$3");
        } else {
            System.out.println("NUMERO DE TELEFONE INVALIDO");
            scanner.close();
            return;
        }
        System.out.println("-------- SELECIONE O PLANO CONTRATADO ----------");
        System.out.println("[1.] PLANO WIRELESS 4MB");
        System.out.println("[2.] PLANO FIBRA 100MB");
        System.out.println("[3.] PLANO FIBRA 200MB");
        System.out.println("[4.] PLANO FIBRA 300MB");
        System.out.println("[5.] PLANO_FIBRA_500MB");
        System.out.println("[6.] PLANO_FIBRA_550MB");
        System.out.println("--------------------------");
        String planoContratadoStr = scanner.nextLine().toUpperCase();
        switch (planoContratadoStr){
            case "1":
                planoContratadoStr = "PLANO_WIRELESS_4MB";
                break;

            case "2":
                planoContratadoStr = "PLANO_FIBRA_100MB";
                break;

            case "3":
                planoContratadoStr = "PLANO_FIBRA_200MB";
                break;

            case "4":
                planoContratadoStr = "PLANO_FIBRA_300MB";
                break;

            case "5":
                planoContratadoStr = "PLANO_FIBRA_500MB";
                break;

            case "6":
                planoContratadoStr = "PLANO_FIBRA_550MB";
                break;
        }


        if (!Produtos.validaPlano(planoContratadoStr)) {
            System.out.println("PLANO INVALIDO. TENTE NOVAMENTE");
            scanner.close();
            return;
        }
        System.out.println("PLANO VÁLIDO: " + planoContratadoStr);

        System.out.println("DIGITE O GENERO DO CLIENTE (MASCULINO/FEMININO)");
        String genero = scanner.nextLine().toUpperCase();
        if (!Generos.validaGenero(genero)) {
            System.out.println("GENERO INVALIDO, TENTE NOVAMENTE");
            scanner.close();
            return;
        }

        Cliente novoCliente = new Cliente(nome, cpf, endereco, numeroContato, planoContratadoStr, genero);
        sistema.adicionarClientes(novoCliente);

        System.out.println("\nCLIENTE CADASTRADO COM SUCESSO!\n" + novoCliente);
    }

    @Override
    public void verCargo() {
        Scanner scanner = new Scanner(System.in);

        if (cargoAtual.equals("SERVICEDESK")) {
            if (reclamacoes.size() > 0) {
                System.out.println("RECLAMAÇÕES DOS CLIENTES: ");
                for (int i = 0; i < reclamacoes.size(); i++) {
                    String reclamacao = reclamacoes.get(i);
                    System.out.printf("ID: %d - %s\n", i + 1, reclamacao);
                }
                System.out.println("DESEJA ENVIAR ALGUMA ORDEM DE SERVIÇO PARA A VISITA?");
                System.out.println("-------- MENU ----------");
                System.out.println("[1.] SIM");
                System.out.println("[2.] NÃO");
                System.out.println("--------------------------");
                int opção = scanner.nextInt();
                contagemResolvidos = 0;

                if (opção == 1) {
                    System.out.println("DIGITE O ID DO ATENDIMENTO:");
                    int idSelecionado = scanner.nextInt();

                    if (idSelecionado > 0 && idSelecionado <= reclamacoes.size()) {
                        System.out.println("RECLAMAÇÃO ENVIADA PARA A VISITA: " + reclamacoes.get(idSelecionado - 1));
                        reclamacoes.remove(idSelecionado - 1);
                        contagemResolvidos += 1;

                    } else {
                        System.out.println("ID INVALIDO, TENTE NOVAMENTE.");
                    }
                } else if (opção == 2) {
                    System.out.println("NENHUMA RECLAMAÇÃO FOI ENVIADA PARA A VISITA");
                } else {
                    System.out.println("OPÇÃO INVALIDA, TENTE NOVAMENTE");
                }
            } else {
                System.out.println("NÃO EXISTE RECLAMAÇÕES PARA EXIBIR");
            }
        } else {
            System.out.println("VOCÊ NÃO TEM PERMISSÃO PARA ACESSAR ESTÁ FUNCIONALIDADE");
        }
    }

    @Override
    public void mostrarPlanos() {
        for (Produtos p : Produtos.values()) {
            System.out.printf("PLANOS: %s\n", p);
        }
    }


    @Override
    public void controleAtendimentosAberto() {
        System.out.printf("NÚMERO DE ATENIDMENTOS ABERTOS: %d\n", this.contagemNãoresolvidos);

    }

    @Override
    public void controleAtendimentosEncerrados() {
        System.out.printf("NÚMERO DE ATENDIMENTOS ENCERRADOS:  %d\n", this.contagemResolvidos);
    }

    @Override
    public void entrar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("DIGITE SEU USUARIO ADMINISTRADOR:");
        String usuario = scanner.nextLine().toUpperCase();
        System.out.println("DIGITE SUA SENHA ADMINISTRADOR:");
        String senha = scanner.nextLine().toUpperCase();

        if (usuario.equals(this.usuario) && senha.equals(this.senha)) {
            System.out.println("ADMINISTRADOR ENCONTRADO");
            this.acessoAdmin = true;
        } else {
            System.out.println("ADMINISTRADOR NÃO ENCONTRADO");
        }
    }

    public String getSenhaAcessoServiceDeskSistema() {
        return senhaAcessoServiceDeskSistema;
    }

    public void setSenhaAcessoServiceDeskSistema(String senhaAcessoServiceDeskSistema) {
        this.senhaAcessoServiceDeskSistema = senhaAcessoServiceDeskSistema;
    }

    public String getSenhaAcessoComercialSistema() {
        return senhaAcessoComercialSistema;
    }

    public void setSenhaAcessoComercialSistema(String senhaAcessoComercialSistema) {
        this.senhaAcessoComercialSistema = senhaAcessoComercialSistema;
    }

    public String getSenhaAcessoAdministradorSistema() {
        return senhaAcessoAdministradorSistema;
    }

    public void setSenhaAcessoAdministradorSistema(String senhaAcessoAdministradorSistema) {
        this.senhaAcessoAdministradorSistema = senhaAcessoAdministradorSistema;
    }

    public boolean getAcessoAdmin() {
        return acessoAdmin;
    }

    public void setAcessoAdmin(boolean entrada) {
        this.acessoAdmin = entrada;
    }


    public boolean getAutenticandoAcessoComercial() {
        return autenticandoAcessoComercial;
    }

    public void setAutenticandoAcessoComercial(boolean autenticandoAcessoComercial) {
        this.autenticandoAcessoComercial = autenticandoAcessoComercial;
    }

    public boolean getAutenticandoAcessoServiceDesk() {
        return autenticandoAcessoServiceDesk;
    }

    public void setAutenticandoAcessoServiceDesk(boolean autenticandoAcessoServiceDesk) {
        this.autenticandoAcessoServiceDesk = autenticandoAcessoServiceDesk;
    }
}
