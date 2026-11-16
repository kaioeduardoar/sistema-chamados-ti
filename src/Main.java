import model.Chamado;
import model.Prioridade;
import model.StatusChamado;
import model.Usuario;
import service.ChamadoService;
import service.UsuarioService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    static UsuarioService usuarioService = new UsuarioService();
    static ChamadoService chamadoService = new ChamadoService();

    static Usuario usuarioLogado = null;

    static String opcao;
    static String email;

    static String mostrarMenuUsuario = "=== SISTEMA DE CHAMADOS ===\n" +
            "1 - Abrir chamado\n" +
            "2 - Listar chamados\n" +
            "3 - Buscar chamado\n" +
            "4 - Alterar status\n" +
            "5 - Sair\n" +
            "Escolha: ";

    static String mostrarMenuTecnico = "=== SISTEMA DE CHAMADOS ===\n" +
            "1 - Abrir chamado\n" +
            "2 - Listar chamados\n" +
            "3 - Buscar chamado\n" +
            "4 - Alterar status\n" +
            "5 - Sair\n" +
            "Escolha: ";


    public static void main(String[] args) {

        boolean acesso = loginUsuario(false);

        // CORREÇÃO DO BUG: Se o usuário escolheu "0" no login, 'acesso' será false e o sistema encerra aqui
        if (!acesso) {
            System.out.println("Encerrando...");
            return;
        }

        opcao = "";

        // Trocado para do-while mantendo a mesma lógica
        do {
            System.out.print(mostrarMenuUsuario);

            opcao = scanner.nextLine();
            if (opcao.equals("5")){
                System.out.println("Saindo...");
                break;
            }

            switch (opcao) {
                case "1": {
                    System.out.print("Titulo do chamado: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Descrição do chamado: ");
                    String descricao = scanner.nextLine();
                    Prioridade prioridade = definirPrioridade();

                    Chamado chamado = chamadoService.abrirChamado(titulo, descricao, prioridade, usuarioLogado);
                    System.out.println("Seu chamado foi aberto. ID: " + chamado.getId());

                    pausarParaProsseguir();
                    break;
                }
                case "2": {
                    listarChamados(chamadoService.getChamados());
                    pausarParaProsseguir();
                    break;
                }
                case "3": {
                    opcao = "";
                    // Trocado para do-while mantendo a mesma lógica interna
                    do {
                        System.out.print("1 - Buscar chamado por ID.\n" +
                                "2 - Buscar chamado por Nome do usuário.\n" +
                                "3 - Sair.\n"+
                                "Escolha: ");
                        opcao = scanner.nextLine();
                        if(opcao.equals("3")){
                            System.out.println("Saindo...");
                            break;
                        }
                        switch (opcao) {
                            case "1": {
                                int id = lerInteiro("ID do Chamado: ");

                                Chamado encontrado = chamadoService.buscarPorId(id);
                                System.out.println(encontrado != null ? encontrado : "Chamado não encontrado.");

                                pausarParaProsseguir();
                                break;
                            }
                            case "2": {
                                System.out.print("Nome do solicitante do Chamado: ");
                                String nome = scanner.nextLine();
                                listarChamados(chamadoService.buscarPorUsuario(nome));

                                pausarParaProsseguir();
                                break;
                            }
                            default: {
                                System.out.println("Opção inválida.");

                                pausarParaProsseguir();
                                break;
                            }
                        }
                    } while (!opcao.equals("1") && !opcao.equals("2") && !opcao.equals("3"));
                    break;
                }
                case "4": {
                    int id = lerInteiro("ID do chamado: ");
                    Chamado chamado = chamadoService.buscarPorId(id);
                    if (chamado == null) {
                        System.out.println("Chamado não encontrado.");
                    } else {
                        System.out.println("Status atual: " + chamado.getStatus());
                        StatusChamado novoStatus = definirStatus();
                        chamadoService.alterarStatus(id, novoStatus);
                        System.out.println("Status do chamado #" + id + " alterado para " + novoStatus + ".");
                    }
                    pausarParaProsseguir();
                    break;
                }
                default: {
                    System.out.println("Opção inválida!");
                }
            }
            System.out.println();
        } while (!opcao.equals("5"));
    }

    static void navegacaoDeUsuario(){

    }

    static String usuarioOuTecnico(boolean tecnico){

        do {
            System.out.print("0 - Encerrar\n" +
                    "1 -  Sou USUÁRIO.\n" +
                    "2 - Sou TÉCNICO.\n" +
                    "Escolha: ");
            opcao = scanner.nextLine();
            if (!(opcao.equals("0") || opcao.equals("1") || opcao.equals("2"))){
                System.out.println("Opção inválida.");
                pausarParaProsseguir();
            }
            switch (opcao){
                case "1": {
                    break;
                }
                case "2": {
                    String tentativa = "";
                    while(!usuarioService.validarSenhaAdmin(tentativa)){
                        System.out.println("0 - voltar");
                        System.out.print("Senha de administrador: ");
                        tentativa = scanner.nextLine();
                        if (tentativa.equals("0")){
                            break;
                        }
                        if(usuarioService.validarSenhaAdmin(tentativa)){
                            return "tecnico";
                        }
                    }
                    break; // evita cair no default quando o usuário escolhe "0 - voltar"
                }
                default: {
                    if (opcao.equals("0")) {
                        return "usuario";
                    }
                    System.out.println("Opção inválida.");

                    pausarParaProsseguir();
                    break;
                }
            }

        }while(!(opcao.equals("0") || opcao.equals("1") || opcao.equals("2")));

        return "usuario";
    }

    static boolean loginUsuario(boolean acessoUsuario){
        while (!acessoUsuario) {
            System.out.print(
                    "0 - Encerrar\n" +
                            "1 - Login\n" +
                            "2 - Signup\n" +
                            "Escolha: ");
            opcao = scanner.nextLine();
            if (opcao.equals("0")) {
                return false;
            }

            switch (opcao) {
                case "1": {
                    System.out.print("Insira seu e-mail: ");
                    email = scanner.nextLine();
                    if (usuarioService.validarUsuarioPorEmail(email)) {
                        usuarioLogado = usuarioService.buscarPorEmail(email);
                        System.out.println("Sucesso.");
                        acessoUsuario = true;
                    } else {
                        System.out.println("Usuário não encontrado.\n");
                    }
                    pausarParaProsseguir();
                    break;
                }
                case "2":
                    System.out.print("Insira seu nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Insira o seu e-mail: ");
                    email = scanner.nextLine();
                    if (usuarioService.buscarPorEmail(email) == null) {
                        usuarioLogado = usuarioService.cadastrarUsuario(nome, email);
                        acessoUsuario = true;
                        break;
                    } else {
                        System.out.println("O e-mail já está vinculado a um usuário");

                        pausarParaProsseguir();
                    }
                    break;

                default:
                    System.out.println("Opção inválida.");
                    pausarParaProsseguir();
                    break;
            }
        }

        return true;
    }

    static Prioridade definirPrioridade(){
        Prioridade prioridade = null;
        do {
            System.out.print("Prioridade do chamado:\n" +
                    "1 - BAIXA\n" +
                    "2 - MEDIA\n" +
                    "3 - ALTA\n" +
                    "4 - URGENTE\n" +
                    "Escolha: ");
            opcao = scanner.nextLine();

            prioridade = switch (opcao) {
                case "1" -> Prioridade.BAIXA;
                case "2" -> Prioridade.MEDIA;
                case "3" -> Prioridade.ALTA;
                case "4" -> Prioridade.URGENTE;
                default -> null;
            };
            if (prioridade == null) {
                System.out.println("Opção inválida.\n");

                pausarParaProsseguir();
            }
        } while (prioridade == null);

        return prioridade;
    }

    static void listarChamados(List<Chamado> listaDeChamados){
        if (!listaDeChamados.isEmpty()){
            for (Chamado c : listaDeChamados){
                System.out.println(c);
            }
        }else {
            System.out.println("Nenhum chamado encontrado.");
        }
    }

    static StatusChamado definirStatus(){
        StatusChamado status = null;
        do {
            System.out.print("Novo status:\n" +
                    "1 - ABERTO\n" +
                    "2 - EM_ATENDIMENTO\n" +
                    "3 - RESOLVIDO\n" +
                    "4 - FECHADO\n" +
                    "Escolha: ");
            String escolha = scanner.nextLine();

            status = switch (escolha) {
                case "1" -> StatusChamado.ABERTO;
                case "2" -> StatusChamado.EM_ATENDIMENTO;
                case "3" -> StatusChamado.RESOLVIDO;
                case "4" -> StatusChamado.FECHADO;
                default -> null;
            };
            if (status == null) {
                System.out.println("Opção inválida.\n");
            }
        } while (status == null);

        return status;
    }

    // Lê um número inteiro sem quebrar o programa se o usuário digitar texto
    static int lerInteiro(String mensagem){
        while (true) {
            System.out.print(mensagem);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite apenas números.");
            }
        }
    }

    public static void pausarParaProsseguir(){
        System.out.println("Pressione ENTER para prosseguir.");
        scanner.nextLine();
    }
}